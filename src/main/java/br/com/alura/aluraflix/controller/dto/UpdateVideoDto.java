package br.com.alura.aluraflix.controller.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.VideoRepo;

public class UpdateVideoDto {

	@NotNull @NotEmpty @Size(min = 4, max = 120)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 2, max = 500)
	private String description;
	
public UpdateVideoDto() {}
	
	public UpdateVideoDto(Video video) {
		this.titulo = video.getTitulo();
		this.description = video.getDescription();
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
	
	public Video update(UUID id, VideoRepo videoRepo) {
		Video video = videoRepo.findById(id).get();
		video.setTitulo(this.titulo);
		video.setDescription(this.description);
		
		return video;
	}
}
