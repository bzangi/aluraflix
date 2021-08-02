package br.com.alura.aluraflix.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.aluraflix.modelo.Categoria;

public class CategoriaDto {

	private long id;
	private String titulo;
	private String cor;

	public CategoriaDto(long id, String titulo, String cor) {
		this.id = id;
		this.titulo = titulo;
		this.cor = cor;
	}

	public static CategoriaDto toCategoriaDto(Categoria categoria) {
		return new CategoriaDto(categoria.getId(), categoria.getTitulo(), categoria.getCor());
	}

	public static List<CategoriaDto> toCategoriaDtoList(List<Categoria> categorias) {
		List<CategoriaDto> listDto = new ArrayList<>();
		for (Categoria categoria : categorias) {
			listDto.add(CategoriaDto.toCategoriaDto(categoria));
		}
		return listDto;
	}

	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCor() {
		return cor;
	}

}
