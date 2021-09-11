package com.logique.desafio.resources;

import java.util.List;

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
import com.logique.desafio.services.URLService;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/urls")
public class URLResource {
	@Autowired
	private URLService urlService;

	// busca a url pelo id e retorna seus dados

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Url> find(@PathVariable Integer id) {
		Url obj = urlService.find(id);
		return ResponseEntity.ok().body(obj);

	}
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<Url>> findAll() {
		List <Url> urls= urlService.findAll();
		return ResponseEntity.ok().body(urls);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Url> insert(@Valid @RequestBody Url url) throws Exception {

		urlService.insert(url);
		return ResponseEntity.status(HttpStatus.CREATED).body(url);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Url> delete(@PathVariable Integer id) {
		urlService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
