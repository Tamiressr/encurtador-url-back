package com.logique.desafio.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logique.desafio.domain.Url;
import com.logique.desafio.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value="SELECT u.* FROM  url u, usuario_urls uu  where uu.urls_id=u.id  and uu.usuario_id=:id",nativeQuery=true )
	public ArrayList<Url> returnUrls(@Param("id") int id);
}
