package com.qualite.matheus.devForms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qualite.matheus.devForms.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email);
}
