package com.logique.desafio.services;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logique.desafio.domain.EncurtadorURL;
import com.logique.desafio.domain.Url;
import com.logique.desafio.exceptions.ObjectNotFoundException;
import com.logique.desafio.repositories.URLRepository;

@Service
public class URLService {

	@Autowired
	private URLRepository urlRepository;

	public Url find(Integer id) {
		Optional<Url> url = urlRepository.findById(id);
		return url.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ",Tipo: " + URL.class.getName()));
	}
	public List<Url> findAll() {
		List<Url> urls = urlRepository.findAll();
		return urls;
	}

	public Url insert(Url url) throws Exception {
		if (EncurtadorURL.urlValida(url.getUrl())) {
			try {
				url.setUrlEncurtada(EncurtadorURL.convertToShortUrl((url.getUrl())));
				url.setUser(url.getUser());
				url.setData(new Date());
				url = urlRepository.save(url);
				
			} catch (Exception e) {
				throw new Exception("URL inválida");
			}
		}
		return url;
	}
	// método update e delete

	public void delete(Integer id) {
		try {
			find(id);
			urlRepository.deleteById(id);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
}
