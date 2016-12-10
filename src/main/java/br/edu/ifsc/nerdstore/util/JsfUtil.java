package br.edu.ifsc.nerdstore.util;

import javax.faces.context.FacesContext;

public class JsfUtil {

	public static FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
			
}