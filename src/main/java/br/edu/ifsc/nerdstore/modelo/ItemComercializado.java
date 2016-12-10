package br.edu.ifsc.nerdstore.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemComercializado {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Produto  produto;
	private Integer quantidade;
	private BigDecimal totalDoItem;
	
	public ItemComercializado(){
		this(new Produto(), 0);
	}
	
	public ItemComercializado(Produto produto, Integer quantidade){
		this.produto = produto;
		this.quantidade = quantidade;
		this.totalDoItem = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public Produto getProduto(){
		return this.produto;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getTotalDoItem() {
		return totalDoItem;
	}
	public void setTotalDoItem(BigDecimal totalDoItem) {
		this.totalDoItem = totalDoItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
