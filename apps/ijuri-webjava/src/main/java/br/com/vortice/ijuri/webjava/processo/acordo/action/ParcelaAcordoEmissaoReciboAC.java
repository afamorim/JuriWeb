/**
 * 
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.abstracao.util.NumeroExtensoConverter;
import br.com.vortice.ijuri.core.pagamento.FormaPagamentoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.ParteVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TipoParteVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * Action respons�vel em emitir o Recibo de uma ou mais parcelas agrupadas. 
 * @author Amadeu
 *
 */
public class ParcelaAcordoEmissaoReciboAC extends BaseReportAction {
	
	private AcordoDelegate acordoDelegate;
	private ProcessoDelegate processoDelegate;
	
    protected String getReport(HttpServletRequest pRequest) {
        String formaPagamentoCodigo = pRequest.getParameter("formaPagamentoCodigo");
        
        if (formaPagamentoCodigo!=null && formaPagamentoCodigo.equals(FormaPagamentoVO.CHEQUE.toString()))
            return "/admin/processo/acordo/relatorio/ReciboCheque.jasper";
        
        return "/admin/processo/acordo/relatorio/ReciboDinheiro.jasper";
    }

    protected Object configurationReport(HttpServletRequest pRequest,
            HttpServletResponse pResponse, Map parametros) throws AmbienteException,
            AplicacaoException {
        
         super.setParameterImagePath(parametros, "P_LOGO", "/img/logo.gif");
         
         String[] selectedRows = pRequest.getParameterValues("chkParcela");
         int length = selectedRows.length; 
        
         //ArrayList parcelas = (ArrayList) Base64Decoder.decodeToObject(pRequest.getParameter("parcelasTexto"));
         
         //Salva a primeira parcela para valida��o
         ParcelaAcordoVO primParcela = new ParcelaAcordoVO();
         primParcela.setCodigo(Long.valueOf(selectedRows[0]));
         primParcela = acordoDelegate.findParcelaByPrimaryKey(primParcela); 
         
         float vlTotalPago=0;
         StringBuffer numParcelasPagas = new StringBuffer();
         int qtdParcelas = Integer.parseInt(pRequest.getParameter("quantidadeParcelas")); 
         float acrescimo = 0;
         ParcelaAcordoVO parcela = null;
         String msgErro;
         int indiceParcela; 
         for (int i=0;i<length;i++) 
         {
             //se n�o for a primeira parcela
             if (i>0){
                 parcela = new ParcelaAcordoVO();
                 parcela.setCodigo(Long.valueOf(selectedRows[i]));
                 //Obt�m detalhes da parcela 
                 parcela = acordoDelegate.findParcelaByPrimaryKey(parcela);
             }else{
                 parcela = primParcela;
             }
             
             if (!parcela.isPago()){
                 throw new AplicacaoException("� permitido emiss�o de recibo somente para parcelas que estejam pagas.");
             } 
             
             //se n�o for a primeira parcela, compara a parcela corrente com a primeira
             if (i>0){
                 validarEmissaoParcelaEmConjunto(primParcela,parcela);
             }
             
             acrescimo = parcela.getValorAcrescimos().floatValue();
             
             vlTotalPago = vlTotalPago+parcela.getValorPago().floatValue()+acrescimo;
             if (i==0)
                 numParcelasPagas.append(parcela.getNumero()+"/"+qtdParcelas);           
             else if (i+1==length)
                 numParcelasPagas.append(" e "+ parcela.getNumero()+"/"+qtdParcelas);           
             else
                 numParcelasPagas.append(", "+ parcela.getNumero()+"/"+qtdParcelas);           
         } 
         
         //Obt�m o processo
         ProcessoVO processo = new ProcessoVO(Long.valueOf(pRequest.getParameter("processoCodigo"))); 
         processo = processoDelegate.findByPrimaryKey(processo);
         if (processo.getOrgaoJudiciario().getJuizo().getCodigo().equals(JuizoVO.EXTRA_JUDICIAL))
             parametros.put("processo","do acordo EXTRA-JUDICIAL do processo de n� "+processo.getNumero());
         else
             parametros.put("processo","do acordo realizado nos autos do processo de n� "+processo.getNumero());
         
         //Obt�m as partes
         ArrayList partes = (ArrayList) processoDelegate.findPartesByProcesso(processo);
         String nomeCliente = null;
         String nomeDevedor = null;
         for (Iterator iter = partes.iterator(); iter.hasNext();) {
             ParteVO parte = (ParteVO) iter.next();
             if (parte.getTipoParte().getCodigo().intValue() == TipoParteVO.AUTOR){
                 parametros.put("nomeCliente",parte.getNome().toUpperCase());
             }else if (parte.getTipoParte().getCodigo().intValue() == TipoParteVO.REU){
                 parametros.put("nomeDevedor",parte.getNome().toUpperCase());
                 
                 if (parte.getApto() != null && parte.getApto().intValue() > 0){
                     parametros.put("apto",", apt. "+parte.getApto().toString());
                 }else{
                     parametros.put("apto","");
                 }
                 
                 if (parte.getBloco() != null && parte.getBloco().length() > 0){
                     parametros.put("bloco"," bloco "+parte.getBloco());
                 }
             }
         }
         
         //Obt�m as taxas
         Collection taxasComuns = 
             processoDelegate.findTaxasByProcesso(processo, TipoTaxaVO.findByCodigo(TipoTaxaVO.COMUN));
         processo.setTaxasComuns(taxasComuns);
         parametros.put("txComum",processo.getTaxasComunsDescricao());
         
         Collection taxasExtras = 
             processoDelegate.findTaxasByProcesso(processo, TipoTaxaVO.findByCodigo(TipoTaxaVO.EXTRA));
         if (taxasExtras.size() > 0){
             processo.setTaxasExtras(taxasExtras);
             parametros.put("txExtra",", inclusive taxa(s) extra(s) de "+processo.getTaxasExtrasDescricao());
         }else  
             parametros.put("txExtra","");
        
         
         parametros.put("parcelas",numParcelasPagas.toString());
         
         parametros.put("valor",DecimalFormat.getCurrencyInstance(getLocale()).format((double)vlTotalPago));
         parametros.put("valorExtenso", new NumeroExtensoConverter(vlTotalPago).toString());
         
         if (primParcela.getFormaPagamento()!=null && 
                 FormaPagamentoVO.CHEQUE.equals(primParcela.getFormaPagamento().getCodigo())){
             parametros.put("numCheque",primParcela.getCheque().getNumero().trim());
             parametros.put("banco",primParcela.getCheque().getBanco().getNome().trim());
             parametros.put("numAgencia",primParcela.getCheque().getNumeroAgencia().trim());
         }
             
         return null;
   }
    
    private boolean validarEmissaoParcelaEmConjunto(ParcelaAcordoVO primParcela,
                                                    ParcelaAcordoVO parcela) throws AplicacaoException{
            
        StringBuffer msgErro = new StringBuffer()
                                        .append("Para emitir um recibo para ")
                                        .append("mais de uma parcela simultaneamente ")
                                        .append("� necess�rio que a forma de pagamento ")
                                        .append("seja a mesma. ");
            
            if (!parcela.getFormaPagamento().getCodigo().equals(primParcela.getFormaPagamento().getCodigo())){
                 throw new AplicacaoException(msgErro.toString());
            }
            
            if (primParcela.getFormaPagamento()!=null &&
                    FormaPagamentoVO.CHEQUE.equals(primParcela.getFormaPagamento().getCodigo())){
                
                if (!parcela.getCheque().getBanco().getNumero().equals(primParcela.getCheque().getBanco().getNumero())){
                   throw new AplicacaoException(msgErro.append("O banco est� incoerente.").toString());         
                }  
                if (!parcela.getCheque().getNumeroAgencia().equals(primParcela.getCheque().getNumeroAgencia())){
                    throw new AplicacaoException(msgErro.append("O n�mero da ag�ncia est� incoerente.").toString());         
                }
                if (!parcela.getCheque().getNumero().equals(primParcela.getCheque().getNumero())){
                    throw new AplicacaoException(msgErro.append("O n�mero do cheque est� incoerente.").toString());         
                }
                
            }  
        
        return true;
    }
    
    @Override
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		acordoDelegate = (AcordoDelegate)context.getBean("acordoDelegate");
		processoDelegate = (ProcessoDelegate)context.getBean("processoDelegate");
	}

    protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
   
}
