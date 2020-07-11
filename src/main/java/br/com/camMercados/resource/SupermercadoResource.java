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

import br.com.camMercados.model.Supermercado;
import br.com.camMercados.service.SupermercadoService;

@RestController
@RequestMapping("/supermercados")
public class SupermercadoResource {

	@Autowired
	
	private SupermercadoService supermercadoService;
	
	@GetMapping("/todas")
	
	public List<Supermercado> listasTodas(){
		return supermercadoService.ListarTodasSupermercados();
	}
	
	@GetMapping("/{idsupermercado}")
	
	public ResponseEntity<Supermercado> find(@PathVariable Integer idsupermercado){
		Supermercado supermercado = supermercadoService.findSupermercado(idsupermercado);
		return ResponseEntity.ok().body(supermercado);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody Supermercado supermercado){
		supermercado = supermercadoService.insert(supermercado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idsupermercado}").buildAndExpand(supermercado.getIdsupermercado()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{idsupermercado}")
	public ResponseEntity<Void> delete(@PathVariable Integer idsupermercado){
		supermercadoService.delete(idsupermercado);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Supermercado> update(@PathVariable Integer idsupermercado,
			    @RequestBody Supermercado supermercado) {
		try {
			Supermercado supermercadoSalva = supermercadoService.update(idsupermercado, supermercado);
			return ResponseEntity.ok(supermercadoSalva);
		}
		catch (IllegalArgumentException e) {
		return ResponseEntity.notFound().build();
		}
	}	

	@GetMapping()
	public Page<Supermercado> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable){
		return supermercadoService.pesquisar(nome, pageable);
	}
}
