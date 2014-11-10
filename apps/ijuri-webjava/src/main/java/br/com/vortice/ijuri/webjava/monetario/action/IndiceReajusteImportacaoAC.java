package br.com.vortice.ijuri.webjava.monetario.action;

import java.io.BufferedReader;
import java.io.StringReader;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;
import br.com.vortice.ijuri.webjava.monetario.form.IndiceReajusteImportacaoForm;

public class IndiceReajusteImportacaoAC extends BaseAction
{
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	public ActionForward executar(ActionMapping mapping,
						 ActionForm form,
						 HttpServletRequest request,
						 HttpServletResponse response) throws Exception{

        IndiceReajusteImportacaoForm irForm = (IndiceReajusteImportacaoForm)form;
		
		//obt�m o �ndice
		IndiceReajusteVO indiceReajuste = new IndiceReajusteVO();
		indiceReajuste.setCodigo(irForm.getIndiceReajusteCodigo());

		//obt�m o arquivo
		FormFile formFile = irForm.getArquivo();
		byte[] bytes = formFile.getFileData();
				
		//Gera a cole��o de periodo de �ndices a partir do txt importado.
        StringReader stringReader = new StringReader(new String(bytes, 0,bytes.length,"ISO-8859-1")); 
        BufferedReader br = new BufferedReader(stringReader);
        String linha = null; 
		ArrayList variacoesIndices = new ArrayList();
		PeriodoIndiceVO variacaoIndiceVO = null;
		int mes = 1,ano = 0;
        NumberFormat fmt = NumberFormat.getNumberInstance(getLocale());
		while ((linha = br.readLine()) != null) { 
				if (linha.indexOf("#")>-1){
				    ano = Integer.parseInt(linha.substring(1,5));
					mes = 1;
                }else{
				    variacaoIndiceVO = new PeriodoIndiceVO();
					variacaoIndiceVO.setIndiceReajuste(indiceReajuste);
					variacaoIndiceVO.setMes(new Integer(mes++));
					variacaoIndiceVO.setAno(new Integer(ano));
					variacaoIndiceVO.setValor(new Float(fmt.parse(linha).floatValue()));
					
					variacoesIndices.add(variacaoIndiceVO);
				}
		} 
        
		correcaoMonetariaDelegate.insertVaricoesIndice(variacoesIndices);
        
        registrarMensagemSucesso(request, MensagemSucessoIf.IMPORT);
        
        irForm.carregarComponentes(mapping, request, correcaoMonetariaDelegate);
		
        return mapping.getInputForward();
    }

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}	
}