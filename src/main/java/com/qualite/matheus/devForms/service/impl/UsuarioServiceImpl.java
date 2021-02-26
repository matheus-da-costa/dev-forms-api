package com.qualite.matheus.devForms.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qualite.matheus.devForms.exception.RegraNegocioException;
import com.qualite.matheus.devForms.model.entity.Usuario;
import com.qualite.matheus.devForms.model.repository.UsuarioRepository;
import com.qualite.matheus.devForms.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com esse email!");
		}
		
	}

}
