package br.com.alura.aluraflix.controller.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Id;

import br.com.alura.aluraflix.modelo.Video;

public class SimpleVideoDto {

	@Id
	private UUID id;
	private String titulo;
	private String description;
	private String url;

	public SimpleVideoDto() {
	}

	public SimpleVideoDto(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.description = video.getDescription();
		this.url = video.getUrl();
	}

	public UUID getId() {
		return id;
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

	public static List<SimpleVideoDto> toVideoDtoList(List<Video> videoList) {
		return videoList.stream().map(SimpleVideoDto::new).collect(Collectors.toList());
	}

}
