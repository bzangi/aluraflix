package br.com.alura.aluraflix.controller.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Id;

import br.com.alura.aluraflix.modelo.Video;

public class VideoDto {

	@Id
	private UUID id;
	private long categoriaId;
	private String titulo;
	private String description;
	private String url;

	public VideoDto() {
	}

	public VideoDto(Video video) {
		this.id = video.getId();
		this.categoriaId = video.getCategoria().getId();
		this.titulo = video.getTitulo();
		this.description = video.getDescription();
		this.url = video.getUrl();
	}

	public static List<VideoDto> toVideoDtoList(List<Video> videoList) {
		return videoList.stream().map(VideoDto::new).collect(Collectors.toList());
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

	public void setCategoriaId(long l) {
		this.categoriaId = l;
	}

	public long getCategoriaId() {
		return categoriaId;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
