package br.edu.ifsc.nerdstore.web.filtros;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.edu.ifsc.nerdstore.modelo.Usuario;
import br.edu.ifsc.nerdstore.util.RedirectView;


public class Autorizador implements PhaseListener{

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
    	FacesContext fc = event.getFacesContext();
    	
    	String pagina = fc.getViewRoot().getViewId();
    	
    	if("/login.xhtml".equals(pagina) || 
    		"/usuario.xhtml".equals(pagina) || 
    		"/usuarios.xhtml".equals(pagina)){
    		return;
    	}
    	
    	Usuario usuarioLogado = (Usuario) fc.getExternalContext().getSessionMap().get("usuarioLogado");
    	if(usuarioLogado!=null){
    		return;
    	}
    	
    	//redirecionamento para login.xhtml
    	NavigationHandler nh = fc.getApplication().getNavigationHandler();
    	nh.handleNavigation(fc,null, new RedirectView("login").toString());
    	fc.renderResponse();
    	System.out.println("FASE: " + event.getPhaseId() + " Página: "+pagina);
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}