package br.com.alura.aluraflix.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.alura.aluraflix.modelo.Categoria;

public class CategoriasComVideoDto {
	private long id;
	private String titulo;
	private String cor;
	private List<VideoDto> videos;

	public CategoriasComVideoDto(long id, String titulo, String cor, List<VideoDto> videos) {
		this.id = id;
		this.titulo = titulo;
		this.cor = cor;
		this.videos = videos;
	}

	private static CategoriasComVideoDto toCategoriaComVideoDto(Categoria categoria) {
		 List<VideoDto> videoDtoList = VideoDto.toVideoDtoList((categoria.getVideos()));
		return new CategoriasComVideoDto(categoria.getId(), categoria.getTitulo(), categoria.getCor(), videoDtoList);
	}

	public static List<CategoriasComVideoDto> toCategoriaComVideoDtoList(List<Categoria> categorias) {
		List<CategoriasComVideoDto> listDto = new ArrayList<>();
		for (Categoria categoria : categorias) {
			listDto.add(CategoriasComVideoDto.toCategoriaComVideoDto(categoria));
		}
		return listDto;
	}
	
//	public static List<CategoriasComVideoDto> toCategoriaComVideoDtoList(Page<Categoria> categorias) {
//		List<CategoriasComVideoDto> listDto = new ArrayList<>();
//		for (Categoria categoria : categorias) {
//			listDto.add(CategoriasComVideoDto.toCategoriaComVideoDto(categoria));
//		}
//		return listDto;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public List<VideoDto> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoDto> videos) {
		this.videos = videos;
	}

	


}
