package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.aluraflix.controller.dto.UpdateVideoDto;
import br.com.alura.aluraflix.controller.dto.VideoDto;
import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.VideoRepo;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoRepo videoRepo;

	@GetMapping
	public List<VideoDto> listaVideos() {
		List<Video> videoList = videoRepo.findAll();
		return VideoDto.converter(videoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> getSingleVideo(@PathVariable UUID id) {
		Optional<Video> singleVideo = videoRepo.findById(id);
		if (singleVideo.isPresent()) {
			return ResponseEntity.ok(new VideoDto(singleVideo.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> addVideos(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
		try {
			Video video = videoForm.converter();
			videoRepo.save(video);

			URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
			return ResponseEntity.created(uri).body(new VideoDto(video));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("URL informada é inválida, insira um vídeo do youtube");
		}

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDto> updateVideo(@PathVariable UUID id, @RequestBody @Valid UpdateVideoDto updateDto) {
		Optional<Video> videoOpt = videoRepo.findById(id);
		if (videoOpt.isPresent()) {
			Video video = updateDto.update(id, videoRepo);
			return ResponseEntity.ok(new VideoDto(video));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeVideo(@PathVariable UUID id) {
		Optional<Video> videoOpt = videoRepo.findById(id);
		if (videoOpt.isPresent()) {
			videoRepo.deleteById(id);
			return ResponseEntity.ok().body("Vídeo removido com sucesso");
		}
		return ResponseEntity.notFound().build();
	}

}
