package com.qualite.matheus.devForms.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qualite.matheus.devForms.api.dto.UsuarioDTO;
import com.qualite.matheus.devForms.exception.RegraNegocioException;
import com.qualite.matheus.devForms.model.entity.Usuario;
import com.qualite.matheus.devForms.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {
	
	private final UsuarioService service;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder()
									.nome(dto.getNome())
									.email(dto.getEmail())
									.telefone(dto.getTelefone())
									.idade(dto.getIdade())
									.tipoConsulta(dto.getTipoConsulta()).build();
		
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
}
