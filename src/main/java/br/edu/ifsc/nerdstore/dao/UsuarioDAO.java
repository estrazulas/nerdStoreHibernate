package br.edu.ifsc.nerdstore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifsc.nerdstore.modelo.Usuario;


public class UsuarioDAO extends DAO<Usuario> {
	
	private UsuarioDAO() {
		super(Usuario.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static synchronized UsuarioDAO getInstance() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
			instancia.verificaBancoDemoExiste();
		}
		
		return instancia;
	}
	
	
	private void verificaBancoDemoExiste() {
		List<Usuario> usuarioBanco = listaTodos();
		if(usuarioBanco.isEmpty()){
			for (Usuario usuario : USUARIOSDEMO) {
				adiciona(usuario);
			}
		}
	}

	private static UsuarioDAO instancia;

	private final static List<Usuario> USUARIOSDEMO = new ArrayList();
	static {
		USUARIOSDEMO.add(new Usuario("daniel.estrazulas@gmail.com","teste"));
		USUARIOSDEMO.add(new Usuario("diretor@sematecsolucoes.com.br","diretor"));
	}
	
	public Usuario buscaPorEmailESenha(String email, String senha) {
		Usuario usuarioBanco =null;
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", email);
		query.setParameter("pSenha", senha);
		try {
			usuarioBanco= query.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return usuarioBanco;
	}
	

	public boolean usuarioExiste(String email) {
		Usuario usuarioBanco = getUsuario(email);
		return usuarioBanco!=null;
		
	}


	public Usuario getUsuario(String email) {
		Usuario usuarioBanco =null;
		TypedQuery<Usuario> query = em.createQuery(" select u from Usuario u where u.email = :pEmail", Usuario.class);
		query.setParameter("pEmail", email);
		try {
			usuarioBanco= query.getSingleResult();
		} catch (NoResultException ex) {
			//ex.printStackTrace();
		}
		return usuarioBanco;
	}
	
}
