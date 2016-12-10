package br.edu.ifsc.nerdstore.beans;

import java.io.Serializable;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.nerdstore.dao.CarrinhoDAO;
import br.edu.ifsc.nerdstore.dao.UsuarioDAO;
import br.edu.ifsc.nerdstore.modelo.CarrinhoCompras;
import br.edu.ifsc.nerdstore.modelo.Usuario;
import br.edu.ifsc.nerdstore.util.JsfUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	private UsuarioDAO dao;

	
	public LoginBean(){
		this.dao = UsuarioDAO.getInstance();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail());
		Usuario usuarioBanco = dao.getUsuario(this.usuario.getEmail());

		FacesContext fc = JsfUtil.getFacesContext();
		if (usuarioBanco!=null) {
			fc.getExternalContext().getSessionMap().put("usuarioLogado", usuarioBanco);
			fc.getExternalContext().getSessionMap().put("carrinho", CarrinhoDAO.getInstance().adicionaCarrinhoSeNaoTem(usuarioBanco.getId()) );
			return "loja?faces-redirect=true";
		}
		fc.getExternalContext().getFlash().setKeepMessages(true);
		fc.addMessage(null, new FacesMessage("Usuário não encontrado!"));

		return "login?faces-redirect=true";
	}

	public String deslogar() {
		FacesContext fc = JsfUtil.getFacesContext();
		CarrinhoCompras carrinho= (CarrinhoCompras) fc.getExternalContext().getSessionMap().get("carrinho");
		CarrinhoDAO.getInstance().atualiza(carrinho);
		fc.getExternalContext().getSessionMap().remove("usuarioLogado");
		fc.getExternalContext().getSessionMap().remove("carrinho");
		return "login?faces-redirect=true";
	}
	
}