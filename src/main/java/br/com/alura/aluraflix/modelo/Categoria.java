package br.com.alura.aluraflix.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.alura.aluraflix.controller.dto.CategoriaDto;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	private String cor;
	@OneToMany(mappedBy = "categoria")
	private List<Video> videos = new ArrayList<>();

	public Categoria() {
	}

	public Categoria(String titulo, String cor) {
		this.titulo = titulo;
		this.cor = cor;
	}

	public static Categoria toCategoria(CategoriaDto categoriaDto) {
		return new Categoria(categoriaDto.getTitulo(), categoriaDto.getCor());
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

	public void setId(long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public List<Video> getVideos() {
		return videos;
	}

}
