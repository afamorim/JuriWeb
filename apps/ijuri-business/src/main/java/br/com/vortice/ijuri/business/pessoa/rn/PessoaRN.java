/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.pessoa.rn;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.pessoa.dao.PessoaDAOIf;
import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.core.seguranca.UsuarioVO;
import br.com.vortice.jvseguranca.business.UsuarioRN;
import br.com.vortice.jvseguranca.core.Perfil;
import br.com.vortice.jvseguranca.core.Usuario;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class PessoaRN extends RegraNegocio {
	
    private PessoaDAOIf 		dao;
    private UsuarioRN			usuarioRN;
    private static final Logger LOG = Logger.getLogger(PessoaRN.class);
    
    public PessoaRN(){
    }
    
    public PessoaVO insert(PessoaVO pessoa) throws AmbienteException,AplicacaoException{
    	return dao.insert(pessoa);
    }
    
    public void update(PessoaVO pessoa)  throws AmbienteException,AplicacaoException{
    	dao.update(pessoa);
    	if (pessoa.getUsuario() != null){
    		Usuario usuario = pessoa.getUsuario();
    		usuario.setNome(pessoa.getNome());
    		usuario.setLogin(pessoa.getEmail());
    		usuario.setAtivo(usuarioRN.findByPrimaryKey(usuario).getAtivo());
    		LOG.debug("usuario.getPerfil().getCodigo() " + usuario.getPerfil().getCodigo());
    		usuarioRN.update(usuario);
    	}
    }
    
    public void delete(PessoaVO pessoa) throws AmbienteException,AplicacaoException{
    	dao.remove(pessoa);
    }
    
    public PessoaVO findByPrimaryKey(PessoaVO pessoaVO)throws AmbienteException, AplicacaoException {
    	return dao.findByPrimaryKey(pessoaVO);
    }
    
    public PessoaVO findByUsuario(Usuario usuario) throws AmbienteException, AplicacaoException{
    	return dao.findByUsuario(usuario);
    }
    
    public Collection findAll() throws AmbienteException,AplicacaoException{
            return null;
    }
    
    public Collection findByFilter(PessoaVO pessoaVO) throws AmbienteException, AplicacaoException{
    	return dao.findByFilter(pessoaVO);
    }
    
    public void criarUsuario(PessoaVO pessoa, Perfil perfil) throws AmbienteException, AplicacaoException{
    	pessoa = findByPrimaryKey(pessoa);
    	if (pessoa != null){
    		if (pessoa.getEmail() != null){
	    		Usuario usuario = new Usuario();
	    		usuario.setPerfil(perfil);
	    		usuario.setLogin(pessoa.getEmail());
	    		usuario.setSenha("inicio");
	    		usuario.setNome(pessoa.getNome());
	    		usuario.setAtivo(false);
	    		LOG.debug("USUARIO_RN " + usuarioRN);
	    		usuario = usuarioRN.insert(usuario);
	    		pessoa.setUsuario(usuario);
	    		dao.criarUsuario(pessoa);
    		} else throw new AplicacaoException("� necess�rio que a pessoa selecionada tenha um e-mail valido para essa opera�o.");
    	}else throw new AplicacaoException("Selecione uma pessoa v�lida para criar um usu�rio.");
    }
    
    public void ativarUsuario(PessoaVO pessoa) throws AmbienteException, AplicacaoException{
    	pessoa = findByPrimaryKey(pessoa);
    	Usuario usuario = usuarioRN.findByPrimaryKey(pessoa.getUsuario());
    	if (usuario != null){
    		if (!usuario.isAtivo()){
    			usuario.setAtivo(true);
    			usuarioRN.update(usuario);
    		}else throw new AplicacaoException("O usu�rio j� esta ativo.");
    	}else throw new AplicacaoException("Selecione um usu�rio v�lido");
    }
    
    public void desativarUsuario(PessoaVO pessoa) throws AmbienteException, AplicacaoException{
    	pessoa = findByPrimaryKey(pessoa);
    	Usuario usuario = usuarioRN.findByPrimaryKey(pessoa.getUsuario());
    	if (usuario != null){
    		if (usuario.isAtivo()){
    			usuario.setAtivo(false);
    			usuarioRN.update(usuario);
    		}else throw new AplicacaoException("O usu�rio j� esta desativado.");
    	}else throw new AplicacaoException("Selecione um usu�rio v�lido");
    }
    
	public void setDao(PessoaDAOIf dao) {
		this.dao = dao;
	}

	public void setUsuarioRN(UsuarioRN usuarioRN) {
		this.usuarioRN = usuarioRN;
	}
	
}