package com.qualite.matheus.devForms.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qualite.matheus.devForms.exception.RegraNegocioException;
import com.qualite.matheus.devForms.model.entity.Usuario;
import com.qualite.matheus.devForms.model.repository.UsuarioRepository;
import com.qualite.matheus.devForms.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioServiceImpl service;
	
	@MockBean
	UsuarioRepository repository;
	
	@Test
	public void deveLancarErroAoSalvarUsuarioComMesmoEmail() {
		String email = "email@email";
		Usuario usuario = Usuario.builder().email(email).build();
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);
		
		org.junit.jupiter.api.Assertions
			.assertThrows(RegraNegocioException.class, () -> {
				service.salvarUsuario(usuario);
			});
		
		Mockito.verify( repository, Mockito.never() ).save(usuario);
		
	}
	
	@Test
	public void salvarUsuario() {
		Mockito.doNothing().when(service).validarEmail("sousam150@gmail.com");
		Usuario usuario = Usuario.builder()
									.id(1l)
									.nome("nome")
									.email("sousam150@gmail.com")
									.idade(19)
									.telefone(33754133)
									.tipoConsulta("Clinico geral").build();
		
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("sousam150@gmail.com");
		Assertions.assertThat(usuarioSalvo.getIdade()).isEqualTo(19);
		Assertions.assertThat(usuarioSalvo.getTelefone()).isEqualTo(33754133);
		Assertions.assertThat(usuarioSalvo.getTipoConsulta()).isEqualTo("Clinico geral");
		
	}
	
	@Test
	public void deveValidarEmail() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		service.validarEmail("sousam150@gmail.com");
	}
	
	@Test
	public void deveLancarErroAoValidarEmailJaCadastrado() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		org.junit.jupiter.api.Assertions.assertThrows(RegraNegocioException.class, () -> {
			service.validarEmail("sousam150@gmail.com");
		});
	}
	
}
