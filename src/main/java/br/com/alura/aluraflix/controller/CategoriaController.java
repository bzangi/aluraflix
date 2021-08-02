package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import br.com.alura.aluraflix.controller.dto.CategoriaDto;
import br.com.alura.aluraflix.controller.dto.CategoriaForm;
import br.com.alura.aluraflix.controller.dto.SimpleVideoDto;
import br.com.alura.aluraflix.modelo.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepo;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepo categoriaRepo;

	@PostMapping
	@Transactional
	public ResponseEntity<?> addCategories(@RequestBody @Valid CategoriaForm catForm, UriComponentsBuilder uriBuilder) {
		Categoria categoria = catForm.converter();
		categoriaRepo.save(categoria);

		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria).toUri();
		return ResponseEntity.created(uri).body(new CategoriaForm(categoria));
	}

	@GetMapping
	public ResponseEntity<?> listCategories() {
		List<Categoria> categorias = categoriaRepo.findAll();
		return ResponseEntity.ok().body(CategoriaDto.toCategoriaDtoList(categorias));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listSingleCategorie(@PathVariable long id) {
		try {
			return ResponseEntity.ok().body(CategoriaDto.toCategoriaDto(categoriaRepo.findById(id).get()));
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok().body("ID de categoria inexistente");
		}

	}

	@GetMapping("/{id}/videos")
	public ResponseEntity<?> listCategorieAndVideos(@PathVariable long id) {
		try {
			List<SimpleVideoDto> categoriaVideosDto = SimpleVideoDto
					.toVideoDtoList(categoriaRepo.findById(id).get().getVideos());
			return ResponseEntity.ok().body(categoriaVideosDto);
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok().body("ID de categoria inexistente");
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategorieColor(@PathVariable long id,
			@RequestBody @Valid CategoriaForm updateCategorie) {
		try {
			categoriaRepo.findById(id);
			Categoria categoria = updateCategorie.update(id, categoriaRepo);
			return ResponseEntity.ok(new CategoriaForm(categoria));
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok().body("Categoria não encontrada");
		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeCategorie(@PathVariable long id) {
		Optional<Categoria> categorieOpt = categoriaRepo.findById(id);
		if (categorieOpt.isPresent()) {
			categoriaRepo.deleteById(id);
			return ResponseEntity.ok().body("Categoria removida com sucesso");
		}
		return ResponseEntity.ok().body("Categoria não encontrada");
	}
}
