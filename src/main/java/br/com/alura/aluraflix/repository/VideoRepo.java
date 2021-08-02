package br.com.alura.aluraflix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.aluraflix.modelo.Video;

public interface VideoRepo extends JpaRepository<Video, UUID> {

	public List<Video> findByTituloContainingIgnoreCase(String titulo);

}
