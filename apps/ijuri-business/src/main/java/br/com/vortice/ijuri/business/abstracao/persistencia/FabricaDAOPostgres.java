/*
 * Created on 13/03/2005
 */
package br.com.vortice.ijuri.business.abstracao.persistencia;

import br.com.vortice.ijuri.documento.dao.DiretorioDAOIf;
import br.com.vortice.ijuri.documento.dao.DocumentoDAOIf;
import br.com.vortice.ijuri.documento.dao.postgresql.DiretorioDAOPostgreSql;
import br.com.vortice.ijuri.documento.dao.postgresql.DocumentoDAOPostgreSql;
import br.com.vortice.ijuri.localidade.dao.EstadoDAOIf;
import br.com.vortice.ijuri.localidade.dao.MunicipioDAOIf;
import br.com.vortice.ijuri.localidade.dao.postgresql.EstadoDAOPostgreSql;
import br.com.vortice.ijuri.localidade.dao.postgresql.MunicipioDAOPostgreSql;
import br.com.vortice.ijuri.monetario.dao.IndiceReajusteDAOIf;
import br.com.vortice.ijuri.monetario.dao.PeriodoIndiceDAOIf;
import br.com.vortice.ijuri.monetario.dao.postgresql.IndiceReajusteDAOPostgreSql;
import br.com.vortice.ijuri.monetario.dao.postgresql.PeriodoIndiceDAOPostgre;
import br.com.vortice.ijuri.pagamento.dao.BancoDAOIf;
import br.com.vortice.ijuri.pagamento.dao.ChequeDAOIf;
import br.com.vortice.ijuri.pagamento.dao.postgresql.BancoDAOPostgreSql;
import br.com.vortice.ijuri.pagamento.dao.postgresql.ChequeDAOPostgreSql;
import br.com.vortice.ijuri.pessoa.dao.PessoaDAOIf;
import br.com.vortice.ijuri.pessoa.dao.postgresql.PessoaDAOPostgreSql;
import br.com.vortice.ijuri.processo.acordo.dao.AcordoDAOIf;
import br.com.vortice.ijuri.processo.acordo.dao.ParcelaAcordoDAOIf;
import br.com.vortice.ijuri.processo.acordo.dao.postgresql.AcordoDAOPostgreSql;
import br.com.vortice.ijuri.processo.acordo.dao.postgresql.ParcelaAcordoDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.AndamentoDAOIf;
import br.com.vortice.ijuri.processo.dao.ClasseProcessoDAOIf;
import br.com.vortice.ijuri.processo.dao.ComarcaDAOIf;
import br.com.vortice.ijuri.processo.dao.JuizoDAOIf;
import br.com.vortice.ijuri.processo.dao.OrgaoJudiciarioDAOIf;
import br.com.vortice.ijuri.processo.dao.ProcessoDAOIf;
import br.com.vortice.ijuri.processo.dao.TaxaDAOIf;
import br.com.vortice.ijuri.processo.dao.TipoAndamentoDAOIf;
import br.com.vortice.ijuri.processo.dao.TipoOrgaoDAOIf;
import br.com.vortice.ijuri.processo.dao.postgresql.AndamentoDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.ClasseProcessoDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.ComarcaDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.JuizoDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.OrgaoJudiciarioDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.ProcessoDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.TaxaDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.TipoAndamentoDAOPostgreSql;
import br.com.vortice.ijuri.processo.dao.postgresql.TipoOrgaoDAOPostgreSql;
import br.com.vortice.ijuri.site.dao.LinkDAOIf;
import br.com.vortice.ijuri.site.dao.ParceiroDAOIf;
import br.com.vortice.ijuri.site.dao.postgresql.LinkDAOPostgreSql;
import br.com.vortice.ijuri.site.dao.postgresql.ParceiroDAOPostgreSql;
import br.com.vortice.ijuri.site.dao.JurisprudenciaDAOIf;
import br.com.vortice.ijuri.site.dao.postgresql.JurisprudenciaDAOPostgreSql;
import br.com.vortice.ijuri.usuario.dao.UsuarioDAOIf;
import br.com.vortice.ijuri.usuario.dao.postgresql.UsuarioDAOPostgreSql;

/**
 * @author Desenvolvimento
 */
public class FabricaDAOPostgres implements FabricaDAOIf {
	
	public LinkDAOIf getLinkDAO(){
		return new LinkDAOPostgreSql();
	}
	
	public EstadoDAOIf getEstadoDAO(){
		return new EstadoDAOPostgreSql();
	}
	
	public MunicipioDAOIf getMunicipioDAO(){
		return new MunicipioDAOPostgreSql();
	}
	
	public AndamentoDAOIf getAndamentoDAO(){
		return new AndamentoDAOPostgreSql();
	}
	
	public ComarcaDAOIf getComarcaDAO(){
		return new ComarcaDAOPostgreSql();
	}
	
	public JuizoDAOIf getJuizoDAO(){
		return new JuizoDAOPostgreSql(); 
	}
	
	public OrgaoJudiciarioDAOIf getOrgaoJudiciarioDAO(){
		return new OrgaoJudiciarioDAOPostgreSql();
	}
	
	public ProcessoDAOIf getProcessoDAO(){
		return new ProcessoDAOPostgreSql();
	}
	
	public ClasseProcessoDAOIf getClasseProcessoDAO(){
		return new ClasseProcessoDAOPostgreSql();
	}
	
	public TipoAndamentoDAOIf getTipoAndamentoDAO(){
		return new TipoAndamentoDAOPostgreSql();
	}
	
	public TipoOrgaoDAOIf getTipoOrgaoDAO(){
		return new TipoOrgaoDAOPostgreSql();
	}
    
    public PessoaDAOIf getPessoaDAO(){
        return new PessoaDAOPostgreSql();
    }
    
    public UsuarioDAOIf getUsuarioDAO(){
        return new UsuarioDAOPostgreSql();
    }
    
    public AcordoDAOIf getAcordoDAO(){
        return new AcordoDAOPostgreSql();
    }
    
    public TaxaDAOIf getTaxaDAO(){
        return new TaxaDAOPostgreSql();
    }
    
    public ParcelaAcordoDAOIf getParcelaAcordoDAO(){
        return new ParcelaAcordoDAOPostgreSql();
    }
    
    public BancoDAOIf getBancoDAO(){
        return new BancoDAOPostgreSql();
    }
    
    public ChequeDAOIf getChequeDAO(){
        return new ChequeDAOPostgreSql();    
    }
    
    public IndiceReajusteDAOIf getIndiceReajusteDAO(){
    	return new IndiceReajusteDAOPostgreSql();
    }
    
    public PeriodoIndiceDAOIf getPeriodoIndiceDAO(){
    	return new PeriodoIndiceDAOPostgre();
    }

    public DiretorioDAOIf getDiretorioDAO() {
        return null;//new DiretorioDAOPostgreSql();
    }

    public DocumentoDAOIf getDocumentoDAO() {
        return new DocumentoDAOPostgreSql();
    }
    
    public JurisprudenciaDAOIf getJurisprudenciaDAO(){
        return new JurisprudenciaDAOPostgreSql(); 
    }
    
    public ParceiroDAOIf getParceiroDAO(){
        return new ParceiroDAOPostgreSql();
    }
}
