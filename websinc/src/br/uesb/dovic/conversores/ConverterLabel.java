package br.uesb.dovic.conversores;
	
	import javax.faces.component.UIComponent;
	import javax.faces.context.FacesContext;
	import javax.faces.convert.Converter;
	import javax.faces.convert.FacesConverter;
	 
  import br.uesb.dovic.beans.*;
  import br.uesb.dovic.servicos.EtiquetaService;
	 
	@FacesConverter("labelConverter")            
	public class ConverterLabel implements Converter {
	 
	    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	        if(value != null && value.trim().length() > 0) {
	            EtiquetaService service = (EtiquetaService) fc.getExternalContext().getApplicationMap().get("themeService");
	            return service.getEtiquetas().get(Integer.parseInt(value));
	           // return service.getThemes().get(Integer.parseInt(value));
	        }
	        else {
	            return null;
	        }
	    }
	 
	    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
	        if(object != null) {
	            return String.valueOf(((Etiqueta) object).getId());
	        }
	        else {
	            return null;
	        }
	    }  
	} 


