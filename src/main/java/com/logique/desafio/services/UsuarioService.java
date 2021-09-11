package com.logique.desafio.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.logique.desafio.domain.EncurtadorURL;
import com.logique.desafio.domain.Url;
import com.logique.desafio.domain.Usuario;
import com.logique.desafio.exceptions.ObjectNotFoundException;
import com.logique.desafio.repositories.URLRepository;
import com.logique.desafio.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private URLRepository urlRepository;

	public Usuario find(Integer id) {
		Optional<Usuario> user = usuarioRepository.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ",Tipo: " + Usuario.class.getName()));
	}

	public List<Usuario> findAll() {
		List<Usuario> users = usuarioRepository.findAll();

		return users;
	}

	public List<Url> findUrls(@Param("id") int usuario) {
		Usuario u= find(usuario);
		//ArrayList<Url>
		return u.getUrls();
	}
	public Usuario insert(Usuario user) {
		
	List<Url>urls=new ArrayList<Url>();
		for(Url u:user.getUrls()) {
			try {
				urls.add(this.shortUrl( u.getUrl()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		user.setUrls((ArrayList<Url>) urls);
		usuarioRepository.save(user);
		return user;
	}

	// método de update e delete
	public void delete(Integer id) {
		try {
			find(id);
			usuarioRepository.deleteById(id);
			
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	public Usuario update(Usuario obj) {
		// busca o obj, caso não exista lança excessão
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return usuarioRepository.save(newObj);
	}

	

	public Url encurtarUrl(Usuario usuario, String url) throws Exception {
		
		Usuario user = usuarioRepository.getById(usuario.getId());
		Url u = new Url();
		if (EncurtadorURL.urlValida(url)) {
			try {
				u.setUrlEncurtada(EncurtadorURL.convertToShortUrl(url));
				u.setUrl(url);
			//	u.setUser(user);
				u.setData(new Date());
				user.adicionaUrl(u);
				usuarioRepository.save(user);
				//urlRepository.save(u);
			} catch (Exception e) {
				throw new Exception("Erro ao tentar salvar url em usuário", e);
			}

		}
		return u;

	}
	
public Url shortUrl(String url) throws Exception {
		
		//Usuario user = usuarioRepository.getById(usuario.getId());
		Url u = new Url();
		if (EncurtadorURL.urlValida(url)) {
			try {
				u.setUrlEncurtada(EncurtadorURL.convertToShortUrl(url));
				u.setUrl(url);
			//	u.setUser(user);
				u.setData(new Date());
				//user.adicionaUrl(u);
				//usuarioRepository.save(user);
				//urlRepository.save(u);
			} catch (Exception e) {
				throw new Exception("Erro ao tentar salvar url em usuário", e);
			}

		}
		return u;

	}
	
	
	
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
		for(Url u:obj.getUrls()) {
			newObj.adicionaUrl(u);
		}
	}
}
