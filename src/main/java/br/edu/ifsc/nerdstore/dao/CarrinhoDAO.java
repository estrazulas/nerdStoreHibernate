package br.edu.ifsc.nerdstore.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifsc.nerdstore.modelo.CarrinhoCompras;
import br.edu.ifsc.nerdstore.modelo.Usuario;

/**
 * Utilizado para guardar o histórico dos carrinhos dos usuários, para que no
 * login não se perca
 * 
 * @author estrazulas
 *
 */
public class CarrinhoDAO extends DAO<CarrinhoCompras> {

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

	private CarrinhoCompras buscaPorIdDoUsuario(Long userId) {
		try {
			TypedQuery<CarrinhoCompras> query = em
					.createQuery("select c from CarrinhoCompras c where c.usuario.id = :idUser", CarrinhoCompras.class);
			query.setParameter("idUser", userId);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			// ex.printStackTrace();
		}
		return null;
	}


	public CarrinhoCompras adicionaCarrinhoSeNaoTem(Usuario userBanco) {
		CarrinhoCompras carrinho = this.buscaPorIdDoUsuario(userBanco.getId());
		if (carrinho == null) {
			carrinho = new CarrinhoCompras();
			carrinho.setUsuario(userBanco);
			carrinho = (CarrinhoCompras) atualiza(carrinho);
		}
		return carrinho;
	}

}
