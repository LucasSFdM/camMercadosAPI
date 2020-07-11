package br.com.camMercados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.camMercados.model.Produto;
import br.com.camMercados.repository.ProdutoRepository;
import br.com.camMercados.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> ListarTodasProdutos(){
		return produtoRepository.findAll();
	}
	
	public Produto findProduto(Integer idproduto) {
		Optional <Produto> produto = produtoRepository.findById(idproduto);
		return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!! Id: "+ idproduto + "Tipo " + Produto.class.getName()));
	}
	
	public Produto insert(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void delete(Integer idproduto) {
		findProduto(idproduto);
		produtoRepository.deleteById(idproduto);
	}
	
	public Produto update(int idproduto, Produto produto) {
		Produto produtoSalva = findProduto(idproduto);
		BeanUtils.copyProperties(produto, produtoSalva, "idproduto");
		return produtoRepository.save(produtoSalva);
	}	

	public Page<Produto> pesquisar(String nome, Pageable pageable){
		return produtoRepository.findByNomeContaining(nome, pageable);
	}
}
