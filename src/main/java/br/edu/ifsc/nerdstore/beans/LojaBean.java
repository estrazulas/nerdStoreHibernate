package br.edu.ifsc.nerdstore.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.nerdstore.dao.ProdutoDAO;
import br.edu.ifsc.nerdstore.modelo.CarrinhoCompras;
import br.edu.ifsc.nerdstore.modelo.Produto;
import br.edu.ifsc.nerdstore.util.JsfUtil;

@ManagedBean
@ViewScoped
public class LojaBean implements Serializable {
	
	private List<Produto> produtos;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer quantidadeAtual;
	
	private List<String> images;
	
	public Integer getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(Integer quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Integer> getQuantidades(){
		return LojaBean.quantidadesOps();
	}

	public static List<Integer> quantidadesOps() {
		List<Integer> quantidades = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			quantidades.add(i);			
		}
		return quantidades;
	}
	
	public List<Produto> getProdutos() {
		if(this.produtos == null) {
	        this.produtos = ProdutoDAO.getInstance().buscaPorSimilaridade(null);           
	    }
		return produtos;
	}

	
	public String comprar(){
		FacesContext fc = JsfUtil.getFacesContext();
		String idProduto = (String)fc.getExternalContext().getRequestParameterMap().get("idProduto");
		CarrinhoCompras carrinho = (CarrinhoCompras) fc.getExternalContext().getSessionMap().get("carrinho");
		carrinho.adicionaProduto(ProdutoDAO.getInstance().buscaPorId(Long.valueOf(idProduto)), this.getQuantidadeAtual());
		return "carrinho?faces-redirect=true";
	}

	public List<String> getImages() {
		return ProdutoDAO.getInstance().getImagens();
	}
	

	public Integer getRandom(){
		Random ran = new Random();
		int x = ran.nextInt(5)+1;
		return x;
	}

}
