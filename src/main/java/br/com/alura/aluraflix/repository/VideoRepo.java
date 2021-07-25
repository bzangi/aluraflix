package br.com.alura.aluraflix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.aluraflix.modelo.Video;

public interface VideoRepo extends JpaRepository<Video, UUID>{

}
