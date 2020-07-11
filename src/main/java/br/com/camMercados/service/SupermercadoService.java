package br.com.camMercados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.camMercados.model.Supermercado;
import br.com.camMercados.repository.SupermercadoRepository;
import br.com.camMercados.service.exceptions.ObjectNotFoundException;

@Service
public class SupermercadoService {

	@Autowired
	private SupermercadoRepository supermercadoRepository;
	
	public List<Supermercado> ListarTodasSupermercados(){
		return supermercadoRepository.findAll();
	}
	
	public Supermercado findSupermercado(Integer idsupermercado) {
		Optional <Supermercado> supermercado = supermercadoRepository.findById(idsupermercado);
		return supermercado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!! Id: "+ idsupermercado + "Tipo " + Supermercado.class.getName()));
	}
	
	public Supermercado insert(Supermercado supermercado) {
		return supermercadoRepository.save(supermercado);
	}
	
	public void delete(Integer idsupermercado) {
		findSupermercado(idsupermercado);
		supermercadoRepository.deleteById(idsupermercado);
	}
	
	public Supermercado update(int idsupermercado, Supermercado supermercado) {
		Supermercado supermercadoSalva = findSupermercado(idsupermercado);
		BeanUtils.copyProperties(supermercado, supermercadoSalva, "idsupermercado");
		return supermercadoRepository.save(supermercadoSalva);
	}	

	public Page<Supermercado> pesquisar(String nome, Pageable pageable){
		return supermercadoRepository.findByNomeContaining(nome, pageable);
	}
}
