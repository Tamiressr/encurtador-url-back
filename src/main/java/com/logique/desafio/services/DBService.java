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
		usuario2.setLogin("Elaine");

		Usuario usuario3 = new Usuario();
		usuario3.setLogin("joao");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Url url1 = new Url(null, sdf.parse("03/09/2021 01:32"), usuario1, "https://rockcontent.com/br/blog/url/",
				"https://rockcontent.com/br/blog/url/");
		Url url2 = new Url(null, sdf.parse("03/09/2021 01:50"), usuario1, "https://rockcontent.com/br/blog/url/",
				"https://rockcontent.com/br/blog/url/");
		Url url3 = new Url(null, sdf.parse("03/09/2021 01:55"), usuario1, "https://rockcontent.com/br/blog/url/",
				"https://rockcontent.com/br/blog/url/");
		Url url4 = new Url(null, sdf.parse("03/09/2021 01:55"), usuario1, "https:/rockcontent.com/br/blog/url/",
				"https://rockcontent.com/br/blog/url/");
		Url url5 = new Url(null, sdf.parse("03/09/2021 01:55"), usuario2, "https://rockcontent.com/br/blog/url/",
				"https://rockcontent.com/br/blog/url/");
		Url url6 = new Url(null, sdf.parse("03/09/2021 01:55"), null, "https://rockcontent.com/br/blog/url/",
				"https://rockcontent.com/br/blog/url/");

		usuario1.getUrls().addAll(Arrays.asList(url1, url2, url3, url4));

		usuario2.getUrls().addAll(Arrays.asList(url5));

		// usuarioService.insert(usuario1);
		// usuarioService.insert(usuario2);
		EncurtadorURL en = new EncurtadorURL();
		String urlGrande = "https://web.digitalinnovation.one/course/introducao-ao-angular-8/learning/560cfdec-19cc-43ce-b2d1-b53cdd4d9b1a?back=/track/capgemini-fullstack-java-and-angular";

	}

}
