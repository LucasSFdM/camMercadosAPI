package br.com.camMercados.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="supermercado")
public class Supermercado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idsupermercado;
	
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy="supermercado")
	private List<Produto> produtos =  new ArrayList<>(); 

	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idsupermercado == null) ? 0 : idsupermercado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supermercado other = (Supermercado) obj;
		if (idsupermercado == null) {
			if (other.idsupermercado != null)
				return false;
		} else if (!idsupermercado.equals(other.idsupermercado))
			return false;
		return true;
	}

	public Integer getIdsupermercado() {
		return idsupermercado;
	}

	public void setIdsupermercado(Integer idsupermercado) {
		this.idsupermercado = idsupermercado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
	
