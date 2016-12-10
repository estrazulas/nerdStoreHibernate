package br.edu.ifsc.nerdstore.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.TypedQuery;

import br.edu.ifsc.nerdstore.modelo.CarrinhoCompras;
import br.edu.ifsc.nerdstore.modelo.Usuario;

/**
 * Utilizado para guardar o histórico dos carrinhos dos usuários, para que no login não se perca
 * @author estrazulas
 *
 */
public class CarrinhoDAO extends DAO<CarrinhoCompras>{
	
	public CarrinhoDAO() {
		super(CarrinhoCompras.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static synchronized CarrinhoDAO getInstance() {
		if (instancia == null) {
			instancia = new CarrinhoDAO();
		}
		return instancia;
	}
	
	
	private static CarrinhoDAO instancia;
	
	
	private CarrinhoCompras buscaPorIdDoUsuario(Long userId){
		TypedQuery<CarrinhoCompras> query = em.createQuery("select c from CarrinhoCompras c where c.usuario.id = :idUser", CarrinhoCompras.class);
		query.setParameter("idUser", userId);
		return query.getSingleResult();
	}
	
	private void adicionaCarrinho(CarrinhoCompras carrinho , Long userId){
		CarrinhoCompras carrinhoBD = buscaPorIdDoUsuario(userId);
		if(carrinhoBD==null){
			atualiza(carrinhoBD);
		}
	}
	
	public CarrinhoCompras adicionaCarrinhoSeNaoTem(Long userId){
		CarrinhoCompras carrinho = this.buscaPorIdDoUsuario(userId);
		if(carrinho==null)
		{
			carrinho = new CarrinhoCompras();
			adicionaCarrinho(carrinho,userId);
		}
		return carrinho;
	}

}
