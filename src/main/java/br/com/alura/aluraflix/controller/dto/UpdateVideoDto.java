package br.com.alura.aluraflix.controller.dto;

import java.util.Optional;
import java.util.UUID;

import javax.naming.directory.NoSuchAttributeException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.aluraflix.modelo.Categoria;
import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.CategoriaRepo;
import br.com.alura.aluraflix.repository.VideoRepo;

public class UpdateVideoDto {

	@NotNull @NotEmpty @Size(min = 4, max = 120)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 2, max = 500)
	private String description;
	private long categoriaId;
	
public UpdateVideoDto() {}
	
	public UpdateVideoDto(Video video) {
		this.titulo = video.getTitulo();
		this.description = video.getDescription();
		this.categoriaId = video.getCategoria().getId();
	}

	public Video update(UUID uuid, VideoRepo videoRepo, CategoriaRepo categoriaRepo) throws NoSuchAttributeException {
		Video video = videoRepo.findById(uuid).get();
		video.setTitulo(this.titulo);
		video.setDescription(this.description);
		Optional<Categoria> categoria = categoriaRepo.findById(this.categoriaId);
		if (categoria.isPresent()) {
			video.setCategoria(categoria.get());
		} else {
			throw new NoSuchAttributeException();
		}
		return videoRepo.save(video);
	}

	public long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(long categoriaId) {
		this.categoriaId = categoriaId;
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
	
	
}
