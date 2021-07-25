package br.com.alura.aluraflix.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.aluraflix.modelo.Video;

public class VideoForm {

	@NotNull @NotEmpty @Size(min = 4, max = 120)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 2, max = 500)
	private String description;
	@NotNull @NotEmpty 
	private String url;
	
	public VideoForm(@NotNull @NotEmpty @Size(min = 4, max = 120) String titulo,
			@NotNull @NotEmpty @Size(min = 2, max = 500) String description, @NotNull @NotEmpty String url) {
		super();
		this.titulo = titulo;
		this.description = description;
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public Video converter() {
		if(this.url.contains("youtube.") || this.url.contains("youtu.be/")) {
			return new Video(titulo, description, url);
		} else {
			throw new IllegalArgumentException("Url inválida");
		}
		
	}
	
	
}
