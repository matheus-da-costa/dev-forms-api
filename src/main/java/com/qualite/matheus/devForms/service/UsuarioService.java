package com.qualite.matheus.devForms.service;

import com.qualite.matheus.devForms.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);

}
