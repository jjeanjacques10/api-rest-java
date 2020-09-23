package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import br.com.fiap.model.MarcaModel;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.MarcaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/marca")
public class MarcaController {
	@Autowired
	public MarcaRepository repository;

	@GetMapping()
	@ApiOperation("Retorna uma lista de marcas")
	public ResponseEntity<List<MarcaModel>> findAll() {

		List<MarcaModel> marcas = repository.findAll();
		return ResponseEntity.ok(marcas);
	}

	@GetMapping("/{id}")
	@ApiOperation("Retorna uma marca apartir do identificador")
	public ResponseEntity<MarcaModel> findById(@PathVariable("id") long id) {

		MarcaModel marca = repository.findById(id).get();
		return ResponseEntity.ok(marca);
	}

	@PostMapping()
	@ApiOperation("Salva uma nova marca")
	public ResponseEntity save(@RequestBody @Valid MarcaModel marcaModel) {

		repository.save(marcaModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(marcaModel.getIdMarca()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ApiOperation("Atualiza uma marca apartir do identificador")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody @Valid MarcaModel marcaModel) {

		marcaModel.setIdMarca(id);
		repository.save(marcaModel);

		return ResponseEntity.ok().build();

	}

	@DeleteMapping("/{id}")
	@ApiOperation("Exclui uma marca apartir do identificador")
	public ResponseEntity deleteById(@PathVariable("id") long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
