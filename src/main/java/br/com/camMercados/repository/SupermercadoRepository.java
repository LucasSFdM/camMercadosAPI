package br.com.camMercados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.camMercados.model.Supermercado;

@Repository
public interface SupermercadoRepository extends JpaRepository <Supermercado, Integer>{
	
	public Page<Supermercado> findByNomeContaining(String nome, Pageable pageable);
	
}
