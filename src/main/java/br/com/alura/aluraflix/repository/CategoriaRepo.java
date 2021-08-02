package br.com.alura.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.aluraflix.modelo.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Long>{
	
}
