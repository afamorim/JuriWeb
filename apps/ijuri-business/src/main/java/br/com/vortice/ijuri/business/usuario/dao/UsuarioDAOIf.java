/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.usuario.dao;

import br.com.vortice.ijuri.core.seguranca.UsuarioVO;

import com.vortice.core.exception.AmbienteException;

/**
 * @author Antonio Amadeu
 */
public interface UsuarioDAOIf {
	
	public UsuarioVO findByUsuario(UsuarioVO parteVO) throws AmbienteException;
}
