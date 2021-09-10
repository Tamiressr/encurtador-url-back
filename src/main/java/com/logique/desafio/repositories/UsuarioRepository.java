package com.logique.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logique.desafio.domain.Url;
import com.logique.desafio.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("FROM Url u  WHERE u.usuario.id= :id")
	public List<Url> returnUrls(@Param("id") int id);
}
