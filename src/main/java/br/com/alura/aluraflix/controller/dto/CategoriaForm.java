package br.com.alura.aluraflix.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.aluraflix.modelo.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepo;

public class CategoriaForm {
	
	@NotNull @NotEmpty @Size(max = 30)
	private String titulo;
	@NotNull @NotEmpty @Size(max = 7)
	private String cor;
	
	public CategoriaForm(Categoria categoria) {
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();
		
	}
	
	public CategoriaForm(@NotNull @NotEmpty @Size(max = 30) String titulo,
			@NotNull @NotEmpty @Size(max = 7) String cor) {
		this.titulo = titulo;
		this.cor = cor;
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

	public Categoria converter(){
		return new Categoria(titulo, cor);
	}

	public Categoria update(long id, CategoriaRepo categoriaRepo) {
		Categoria categoria = categoriaRepo.findById(id).get();
		categoria.setTitulo(this.titulo);
		categoria.setCor(this.cor);
		return categoriaRepo.save(categoria);
	}
}
