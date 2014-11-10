/*
 * Created on 13/01/2005

 */
package br.com.vortice.ijuri.business.abstracao.persistencia;

import br.com.vortice.ijuri.business.documento.rn.dao.DiretorioDAOIf;
import br.com.vortice.ijuri.business.documento.rn.dao.DocumentoDAOIf;
import br.com.vortice.ijuri.business.localidade.dao.EstadoDAOIf;
import br.com.vortice.ijuri.business.localidade.dao.MunicipioDAOIf;
import br.com.vortice.ijuri.business.monetario.dao.IndiceReajusteDAOIf;
import br.com.vortice.ijuri.business.monetario.dao.PeriodoIndiceDAOIf;
import br.com.vortice.ijuri.business.pagamento.dao.BancoDAOIf;
import br.com.vortice.ijuri.business.pagamento.dao.ChequeDAOIf;
import br.com.vortice.ijuri.business.pessoa.dao.PessoaDAOIf;
import br.com.vortice.ijuri.business.processo.acordo.dao.AcordoDAOIf;
import br.com.vortice.ijuri.business.processo.acordo.dao.ParcelaAcordoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.AndamentoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.ClasseProcessoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.ComarcaDAOIf;
import br.com.vortice.ijuri.business.processo.dao.JuizoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.OrgaoJudiciarioDAOIf;
import br.com.vortice.ijuri.business.processo.dao.ProcessoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.TaxaDAOIf;
import br.com.vortice.ijuri.business.processo.dao.TipoAndamentoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.TipoOrgaoDAOIf;
import br.com.vortice.ijuri.business.site.dao.JurisprudenciaDAOIf;
import br.com.vortice.ijuri.business.site.dao.LinkDAOIf;
import br.com.vortice.ijuri.business.site.dao.ParceiroDAOIf;
import br.com.vortice.ijuri.business.usuario.dao.UsuarioDAOIf;

/**
 * @author Antonio Fernando
 */
public interface FabricaDAOIf {
	
	public LinkDAOIf getLinkDAO();
	
	public MunicipioDAOIf getMunicipioDAO();
	
	public EstadoDAOIf getEstadoDAO();
	
	public AndamentoDAOIf getAndamentoDAO();
	
	public ComarcaDAOIf getComarcaDAO();
	
	public JuizoDAOIf getJuizoDAO();
	
	public OrgaoJudiciarioDAOIf getOrgaoJudiciarioDAO();
	
	public ProcessoDAOIf getProcessoDAO();
	
	public ClasseProcessoDAOIf getClasseProcessoDAO();
	
	public TipoAndamentoDAOIf getTipoAndamentoDAO();
	
	public TipoOrgaoDAOIf getTipoOrgaoDAO();
    
    public PessoaDAOIf getPessoaDAO();
    
    public UsuarioDAOIf getUsuarioDAO();
    
    public AcordoDAOIf getAcordoDAO();
    
    public TaxaDAOIf getTaxaDAO(); 
    
    public ParcelaAcordoDAOIf getParcelaAcordoDAO();
    
    public BancoDAOIf getBancoDAO();
    
    public ChequeDAOIf getChequeDAO();
    
    public IndiceReajusteDAOIf getIndiceReajusteDAO();
    
    public PeriodoIndiceDAOIf getPeriodoIndiceDAO();
    
    public DiretorioDAOIf getDiretorioDAO();
    
    public DocumentoDAOIf getDocumentoDAO();
    
    public JurisprudenciaDAOIf getJurisprudenciaDAO();
    
    public ParceiroDAOIf getParceiroDAO();
}
