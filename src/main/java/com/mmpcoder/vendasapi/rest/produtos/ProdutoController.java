package com.mmpcoder.vendasapi.rest.produtos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmpcoder.vendasapi.model.Produto;
import com.mmpcoder.vendasapi.model.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public List<ProdutosFormRequest> getLista(){
		return repository.findAll().stream()
				.map(ProdutosFormRequest::fromModel)
				.collect(Collectors.toList()) ;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutosFormRequest> getById(@PathVariable Long id) {
		Optional<Produto> produtoExistente = repository.findById(id);
		if(produtoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		var produto = produtoExistente.map(ProdutosFormRequest::fromModel).get();
		return ResponseEntity.ok(produto);
	}

	@PostMapping
	public ProdutosFormRequest salvar(@RequestBody ProdutosFormRequest produto) {
		Produto entidadeProduto = produto.toModel();
		repository.save(entidadeProduto);
		return ProdutosFormRequest.fromModel(entidadeProduto);
	}
	
	// API/PRODUTO/1
	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutosFormRequest produto) {
		Optional<Produto> produtoExistente = repository.findById(id);
		
		if(produtoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Produto entidade = produto.toModel();
		entidade.setId(id);
		repository.save(entidade);
		
		return ResponseEntity.ok().build();
	}
}
