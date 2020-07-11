package br.com.camMercados.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproduto; 
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name= "supermercado_id")
	private Supermercado supermercado;

	
	public Supermercado getSupermercado() {
		return supermercado;
	}
	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idproduto == null) ? 0 : idproduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idproduto == null) {
			if (other.idproduto != null)
				return false;
		} else if (!idproduto.equals(other.idproduto))
			return false;
		return true;
	}
	public Integer getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
