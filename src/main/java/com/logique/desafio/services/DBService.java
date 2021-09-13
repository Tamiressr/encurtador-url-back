package com.logique.desafio.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logique.desafio.domain.EncurtadorURL;
import com.logique.desafio.domain.Url;
import com.logique.desafio.domain.Usuario;

/*
 * responsável pela instanciação do banco de dados de test
 */
@Service
public class DBService {

	@Autowired
	private UsuarioService usuarioService;

	public void instantiateTestDatabase() throws ParseException {

		Usuario usuario1 = new Usuario();
		usuario1.setLogin("tamiressr");
		usuario1.setSenha("123");
		Usuario usuario2 = new Usuario();
		usuario2.setLogin("João");
		usuario2.setSenha("1234");
		
		Url url1 = new Url(null, null,"https://rockcontent.com/br/blog/url/","");
		Url url4 = new Url(null,null, "https://rockcontent.com/br/blog/url/","");
		Url url2 = new Url(null, null, "https://rockcontent.com/br/blog/url/","");
		Url url3 = new Url(null,null, "https://rockcontent.com/br/blog/url/","");

		usuario1.getUrls().addAll(Arrays.asList(url1, url3));
		usuario2.getUrls().addAll(Arrays.asList(url2, url4));

		usuarioService.insert(usuario1);
		usuarioService.insert(usuario2);
		
	}

}
