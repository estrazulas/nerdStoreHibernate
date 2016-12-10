package br.edu.ifsc.nerdstore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import br.edu.ifsc.nerdstore.modelo.Produto;
import br.edu.ifsc.nerdstore.modelo.Usuario;

public class ProdutoDAO extends DAO<Produto>{
	
	private ProdutoDAO() {
		super(Produto.class);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static synchronized ProdutoDAO getInstance() {
		if (instancia == null) {
			instancia = new ProdutoDAO();
			instancia.verificaBaseDemoExiste();
		}
		return instancia;
	}
	
	
	private void verificaBaseDemoExiste() {
		List<Produto> produtoBanco = listaTodos();
		if(produtoBanco.isEmpty()){
			for (Produto produto : PRODUTOSDEMO) {
				adiciona(produto);
			}
		}
		
	}


	private static ProdutoDAO instancia;

	private final static List<Produto> PRODUTOSDEMO = new ArrayList<>();
	static {
		PRODUTOSDEMO.add(new Produto("Porta café R2-D2","https://www.thinkgeek.com/images/products/frontsquare/itns_r2-d2_coffee_press.jpg",new BigDecimal(99.99)));
		PRODUTOSDEMO.add(new Produto("Chaveiro HULK","https://www.thinkgeek.com/images/products/frontsquare/jgvo_marvel_hulk_ornament.jpg",new BigDecimal(55.99)));
		PRODUTOSDEMO.add(new Produto("Chaveiro DALEK","https://www.thinkgeek.com/images/products/frontsquare/jhkg_doctor_who_dalek_ornament.jpg",new BigDecimal(7.88)));
		PRODUTOSDEMO.add(new Produto("Caneca Yoda", "http://www.thinkgeek.com/images/products/frontsquare/ilgs_sw_yoda_heat_change_mug.gif",new BigDecimal(80)));
		PRODUTOSDEMO.add(new Produto("Caderno Dragon Ball", "http://www.thinkgeek.com/images/products/frontsquare/jltp_dragon_ball_z_journal.jpg",new BigDecimal(30.50)));
		PRODUTOSDEMO.add(new Produto("Fallout BoobleHead", "http://www.thinkgeek.com/images/products/frontsquare/1af2_fallout_bobbleheads.jpg",new BigDecimal(9.50)));
	}
	
	public List<Produto> buscaPorSimilaridade(String nome) {
		List<Produto> lista = null;
		TypedQuery<Produto> query= null;
		if(nome==null || nome.isEmpty()){
			query = em.createQuery("select p from Produto p", Produto.class);
			lista= query.getResultList();
		}else{
			query = em.createQuery("select p from Produto p where p.nome like :pNome", Produto.class);
			lista= query.getResultList();
		}
		return lista;
	}

	public int quantidadeDeElementos() {
		return listaTodos().size();
	}

	public List<String> getImagens() {
		List<String> linksImages = new ArrayList<>();
		List<Produto> produtos = this.buscaPorSimilaridade(null);
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext();) {
			Produto produto = (Produto) iterator.next();
			linksImages.add(produto.getUrlImagem());
		}
				
		return linksImages;
	}


}
