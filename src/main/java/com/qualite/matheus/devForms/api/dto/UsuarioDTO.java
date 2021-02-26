package com.qualite.matheus.devForms.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private Integer telefone;
	private Integer idade;
	private String tipoConsulta;
}
