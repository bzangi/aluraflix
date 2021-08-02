package br.com.alura.aluraflix.controller.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.aluraflix.modelo.Categoria;
import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.CategoriaRepo;

public class VideoForm {

	@NotNull @NotEmpty @Size(min = 4, max = 120)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 2, max = 500)
	private String description;
	@NotNull @NotEmpty 
	private String url;
	private long categoriaId;
	
//	public VideoForm() {}

//	public VideoForm(@NotNull @NotEmpty @Size(min = 4, max = 120) String titulo,
//			@NotNull @NotEmpty @Size(min = 2, max = 500) String description, @NotNull @NotEmpty String url) {
//		this.titulo = titulo;
//		this.description = description;
//		this.url = url;
//	}
	
	public VideoForm(@NotNull @NotEmpty @Size(min = 4, max = 120) String titulo,
			@NotNull @NotEmpty @Size(min = 2, max = 500) String description, @NotNull @NotEmpty String url, long categoriaId) {
		this.titulo = titulo;
		this.description = description;
		this.url = url;
		this.categoriaId = categoriaId;
	}
	
	public Video converter(CategoriaRepo categoriaRepo) {
		if(this.url.contains("youtube.") && this.url.contains("/watch") || this.url.contains("youtu.be/")) {
			if (this.getCategoriaId() == 0) {
				this.setCategoriaId(1);
			}
			Optional<Categoria> categoria = categoriaRepo.findById(categoriaId);
			return new Video(titulo, description, url, categoria.get());
		} else {
			throw new IllegalArgumentException("Url inválida");
		}
		
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
	
	public long getCategoriaId() {
		return categoriaId;
	}

	public long setCategoriaId(long i) {
		return categoriaId = i;
	}

	
	
	
}
