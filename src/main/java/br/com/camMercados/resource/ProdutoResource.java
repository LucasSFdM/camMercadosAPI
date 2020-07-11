package br.com.camMercados.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.camMercados.model.Produto;
import br.com.camMercados.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	
	private ProdutoService produtoService;
	
	@GetMapping("/todas")
	
	public List<Produto> listasTodas(){
		return produtoService.ListarTodasProdutos();
	}
	
	@GetMapping("/{idproduto}")
	
	public ResponseEntity<Produto> find(@PathVariable Integer idproduto){
		Produto produto = produtoService.findProduto(idproduto);
		return ResponseEntity.ok().body(produto);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody Produto produto){
		produto = produtoService.insert(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idproduto}").buildAndExpand(produto.getIdproduto()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{idproduto}")
	public ResponseEntity<Void> delete(@PathVariable Integer idproduto){
		produtoService.delete(idproduto);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable Integer idproduto,
			    @RequestBody Produto produto) {
		try {
			Produto produtoSalva = produtoService.update(idproduto, produto);
			return ResponseEntity.ok(produtoSalva);
		}
		catch (IllegalArgumentException e) {
		return ResponseEntity.notFound().build();
		}
	}	

	@GetMapping()
	public Page<Produto> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable){
		return produtoService.pesquisar(nome, pageable);
	}
}
