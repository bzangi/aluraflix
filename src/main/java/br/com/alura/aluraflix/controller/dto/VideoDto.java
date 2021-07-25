package br.com.alura.aluraflix.controller.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.aluraflix.modelo.Video;

public class VideoDto {

	@Id
	private UUID id; //NECESSÁRIO CRIAR DTO PARA METODO POST SEM O ID
	@NotNull @NotEmpty @Size(min = 4, max = 120)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 2, max = 500)
	private String description;
	@NotNull @NotEmpty
	private String url;
	
	public VideoDto() {}
	
	public VideoDto(Video video) {
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


	public static List<VideoDto> converter(List<Video> videoList) {
		return videoList.stream().map(VideoDto::new).collect(Collectors.toList());
	}


	public Video converter() {
		return new Video(titulo, description, url);
	}
	

}
