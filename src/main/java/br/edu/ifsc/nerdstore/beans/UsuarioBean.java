package br.edu.ifsc.nerdstore.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.nerdstore.dao.UsuarioDAO;
import br.edu.ifsc.nerdstore.modelo.Usuario;
import br.edu.ifsc.nerdstore.util.JsfUtil;
import br.edu.ifsc.nerdstore.util.RedirectView;

@ManagedBean
@ViewScoped
public class UsuarioBean  implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios = UsuarioDAO.getInstance().listaTodos();
	
	private Usuario novo = new Usuario();
	
	private UsuarioDAO dao = UsuarioDAO.getInstance();
	

	public List<Usuario> getUsuarios() 
	{
		return usuarios;
	}
	
	public Usuario getNovo() 
	{
		return novo;
	}
	
	public RedirectView  gravar() 
	{
		if(!dao.usuarioExiste(novo.getEmail())){
			dao.adiciona(this.novo);
			System.out.println("Gravando usuário " + novo.getEmail());
		}else{
			FacesContext fc = JsfUtil.getFacesContext();
			fc.addMessage(null, new FacesMessage("Usuário já existe!"));
			fc.getExternalContext().getFlash().setKeepMessages(true);
			return new RedirectView("usuario");
		}
		
		return new RedirectView("usuarios");
	}
	
}
