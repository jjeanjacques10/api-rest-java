package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.repository.CategoriaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	public CategoriaRepository repository;

	@GetMapping()
	@ApiOperation("Retorna uma lista de categorias")
	public ResponseEntity<List<CategoriaModel>> findAll() {

		List<CategoriaModel> categorias = repository.findAll();
		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	@ApiOperation("Retorna uma categoria apartir do identificador")
	public ResponseEntity<CategoriaModel> findById(@PathVariable("id") long id) {

		CategoriaModel categoria = repository.findById(id).get();
		return ResponseEntity.ok(categoria);
	}

	@PostMapping()
	@ApiOperation("Salva uma nova categoria")
	public ResponseEntity save(@RequestBody @Valid CategoriaModel categoriaModel) {

		repository.save(categoriaModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaModel.getIdCategoria()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ApiOperation("Atualiza uma categoria apartir do identificador")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody @Valid CategoriaModel categoriaModel) {

		categoriaModel.setIdCategoria(id);
		repository.save(categoriaModel);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Exclui uma categoria apartir do identificador")
	public ResponseEntity deleteById(@PathVariable("id") long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
