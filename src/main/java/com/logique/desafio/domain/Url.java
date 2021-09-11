package com.logique.desafio.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "url")
public class Url implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;
	private String url;
	private String urlEncurtada;

	public Url() {

	}

	public Url(Integer id, Date data, Usuario user, String url, String urlEncurtada) {
		super();
		this.id = id;
		this.data = data;
		this.url = url;
		this.urlEncurtada = urlEncurtada;
	}

	public Url(Url url) {
		this.id = url.getId();
		this.data = url.getData();
		this.url = url.getUrl();
		this.urlEncurtada = url.getUrlEncurtada();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlEncurtada() {
		return urlEncurtada;
	}

	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((urlEncurtada == null) ? 0 : urlEncurtada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Url other = (Url) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (urlEncurtada == null) {
			if (other.urlEncurtada != null)
				return false;
		} else if (!urlEncurtada.equals(other.urlEncurtada))
			return false;
		
		return true;
	}

}
