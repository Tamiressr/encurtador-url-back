package com.logique.desafio.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logique.desafio.domain.Url;
import com.logique.desafio.domain.Usuario;
import com.logique.desafio.services.UsuarioService;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {

		Usuario obj = usuarioService.find(id);
		return ResponseEntity.ok().body(obj);

	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> find() {

		List<Usuario> listURL = usuarioService.findAll().stream().map(obj -> new Usuario(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listURL);

	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> insert(@Valid @RequestBody Usuario user) {

	
		Usuario u=usuarioService.insert(user);
		return ResponseEntity.ok().body(u);

	}

//	@CrossOrigin("*")
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
//
//		Usuario obj = usuarioService.insert(usuario);
//		obj.setId(id);
//		obj = usuarioService.update(obj);
//		return ResponseEntity.noContent().build();
//	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}


	@RequestMapping(value = "/{id}/urls", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Url>> findUrls(@PathVariable("id") int id) {

		List<Url> list = usuarioService.findUrls(id);

		ArrayList<Url> listURL = (ArrayList<Url>) list.stream().map(obj -> new Url(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listURL);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Url> insertUrl(@Valid @RequestBody Usuario usuario) throws Exception {
String urlOriginal=usuario.getUrls().get( usuario.getUrls().size()-1).getUrl();
		Url obj = usuarioService.encurtarUrl(usuario,  urlOriginal);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);

	}
//	@CrossOrigin("*")
//	@RequestMapping(value = "/urls/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Void> AdicionaUrl( @Valid @RequestBody Usuario usuario, Url u) {
// 
//		Usuario objNovo = usuarioService.find(usuario.getId());
//		
//		objNovo.setId(usuario.getId());
//		objNovo.setLogin(usuario.getLogin());
//		ArrayList <Url>urls=(ArrayList<Url>) usuario.getUrls();
//		urls.add(u);
//		objNovo.setUrls(urls);
//		objNovo.adicionaUrl(u);
//
//		try {
//			usuarioService.encurtarUrl(objNovo, u.getUrl());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ResponseEntity.noContent().build();
//	}
//	
}
