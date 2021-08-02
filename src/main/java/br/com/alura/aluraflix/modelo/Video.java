package br.com.alura.aluraflix.modelo;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Video {

	@Id
	private UUID id = UUID.randomUUID();
	private String titulo;
	private String description;
	private String url;
	@ManyToOne
	private Categoria categoria;

	public Video() {
	}

	public Video(String titulo, String description, String url, Categoria categoria) {
		this.titulo = titulo;
		this.description = description;
		this.url = url;
		this.categoria = categoria;
	}

	public UUID getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


}
