package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.naming.directory.NoSuchAttributeException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.aluraflix.controller.dto.UpdateVideoDto;
import br.com.alura.aluraflix.controller.dto.VideoDto;
import br.com.alura.aluraflix.controller.dto.VideoForm;
import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.CategoriaRepo;
import br.com.alura.aluraflix.repository.VideoRepo;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoRepo videoRepo;
	@Autowired
	private CategoriaRepo categoriaRepo;

	@GetMapping
	public ResponseEntity<?> listaVideos() {
		List<Video> videoList = videoRepo.findAll();
		return ResponseEntity.ok().body(VideoDto.toVideoDtoList(videoList));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getSingleVideo(@PathVariable UUID id) {
		Optional<Video> singleVideo = videoRepo.findById(id);
		try {
			return ResponseEntity.ok().body(new VideoDto(singleVideo.get()));
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok().body("ID não existe");
		}

	}
	
	@GetMapping("search")
	public ResponseEntity<?> searchVideoByTitle(@RequestParam String titulo){
		List<Video> videosFiltrados = videoRepo.findByTituloContainingIgnoreCase(titulo);
		return ResponseEntity.ok().body(VideoDto.toVideoDtoList(videosFiltrados));
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> addVideos(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
		try {
			
			Video video = videoForm.converter(categoriaRepo);
			videoRepo.save(video);

			URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
			return ResponseEntity.created(uri).body(new VideoDto(video));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("URL informada é inválida, insira um vídeo do youtube");
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body("Categoria não existe");
		}

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> updateVideo(@PathVariable UUID id, @RequestBody @Valid UpdateVideoDto updateDto) {
		System.out.println(updateDto.getCategoriaId());
		try {
			return ResponseEntity.ok().body(new VideoDto(updateDto.update(id, videoRepo, categoriaRepo)));
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok().body("Id do vídeo não encontrado");
		} catch (NoSuchAttributeException e) {
			return ResponseEntity.ok().body("Id da categoria não encontrado");
		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeVideo(@PathVariable UUID id) {
		Optional<Video> videoOpt = videoRepo.findById(id);
		if (videoOpt.isPresent()) {
			videoRepo.deleteById(id);
			return ResponseEntity.ok().body("Vídeo removido com sucesso");
		}
		return ResponseEntity.ok().body("Vídeo não encontrado");
	}

}
