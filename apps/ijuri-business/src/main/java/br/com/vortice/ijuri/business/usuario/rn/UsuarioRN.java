package br.com.vortice.ijuri.business.usuario.rn;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.usuario.dao.UsuarioDAOIf;
import br.com.vortice.ijuri.core.seguranca.UsuarioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class UsuarioRN extends RegraNegocio {
	
    private UsuarioDAOIf usuarioDAO;
    
    public UsuarioRN(){
    }
    
    public UsuarioVO autenticar(UsuarioVO usuario) throws AmbienteException,AplicacaoException{
	    UsuarioVO lUsuario = usuarioDAO.findByUsuario(usuario);
	    if (lUsuario == null){
	        throw new AplicacaoException("O usuário com este login e senha não foi encontrado.");
	    }
		return lUsuario;
    }

	public void setUsuarioDAO(UsuarioDAOIf usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}   
}