package br.edu.ifsc.nerdstore.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsc.nerdstore.modelo.CarrinhoCompras;
import br.edu.ifsc.nerdstore.util.JsfUtil;
import br.edu.ifsc.nerdstore.util.RedirectView;

@ManagedBean
@ViewScoped
public class CarrinhoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public RedirectView listar(){
		return new RedirectView("carrinho");
	}
	
	public String removerItem() {
		FacesContext fc = JsfUtil.getFacesContext();
		CarrinhoCompras carrinho = (CarrinhoCompras) fc.getExternalContext().getSessionMap().get("carrinho");
		String idItemComercializadoAtual= (String)fc.getExternalContext().getRequestParameterMap().get("idItem");
		carrinho.removeProduto(idItemComercializadoAtual);
		return "carrinho?faces-redirect=true";
	}
}
