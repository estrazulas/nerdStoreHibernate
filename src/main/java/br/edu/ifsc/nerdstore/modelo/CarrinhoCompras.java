package br.edu.ifsc.nerdstore.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CarrinhoCompras {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<ItemComercializado> itemsCarrinho;
	
	private BigDecimal precoTotal;
	

	public CarrinhoCompras(){
		this(new ArrayList<ItemComercializado>(),new BigDecimal(0));
	}
	public CarrinhoCompras(List<ItemComercializado> itemsCarrinho, BigDecimal precoTotal) {
		this.itemsCarrinho = itemsCarrinho;
		this.precoTotal = precoTotal;
	}
	
	public List<ItemComercializado> getItemsCarrinho() {
		return itemsCarrinho;
	}
	
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}
	
	public void adicionaProduto(Produto produto, Integer quantidade){
		ItemComercializado novoItem = new ItemComercializado(produto,quantidade);
		itemsCarrinho.add(novoItem);
		precoTotal = precoTotal.add(novoItem.getTotalDoItem());
	}
	
	
	public void removeProduto(String idItem){
		ItemComercializado itemDoCarrinho = buscaItemPeloId(idItem);
		if(itemDoCarrinho!=null){
			this.itemsCarrinho.remove(itemDoCarrinho);
			precoTotal = precoTotal.subtract(itemDoCarrinho.getTotalDoItem());
			if(precoTotal.toString().equals("0E-46")){
				precoTotal = BigDecimal.ZERO;
			}
		}
	}
	
	private ItemComercializado buscaItemPeloId(String idItem) {
		for (Iterator<ItemComercializado> iterator = itemsCarrinho.iterator(); iterator.hasNext();) {
			ItemComercializado itemComercializado = (ItemComercializado) iterator.next();
			if(itemComercializado.getIdMemoria().equals(idItem)){
				return itemComercializado;
			}
		}
		return null;
	}
	
	public void setItemsCarrinho(ArrayList<ItemComercializado> itemsCarrinho) {
		this.itemsCarrinho = itemsCarrinho;
	}
	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
