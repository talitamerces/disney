package br.uesb.dovic.conversores;

//import java.io.Serializable;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//import br.uesb.dovic.beans.Cidade;
//import br.uesb.dovic.jpa.EntityManagerUtil;
//
//@FacesConverter(value="converterTipoMacro", forClass=TipoDocumentoMacro.class)
//public class ConverterTipoMacro implements Converter, Serializable {
//
//	// converte da tela para o objeto 
//	@Override
//	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
//		if (string == null || string.equals("Selecione um tipo de documento")){
//			return null;
//		}
//		return EntityManagerUtil.getEntityManager().find(TipoDocumentoMacro.class, 
//				Integer.parseInt(string));
//		
//	}
//
//	// converte do objeto para a tela
//	@Override
//	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
//		if (o == null){
//			return null;
//		}
//		TipoDocumentoMacro obj = (TipoDocumentoMacro) o;
//		return obj.getId().toString();
//	}
//
//}

import br.uesb.dovic.entidades.TipoDocumentoMacro;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = TipoDocumentoMacro.class)
public class ConverterTipoMacro implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (TipoDocumentoMacro) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof TipoDocumentoMacro) {
        	TipoDocumentoMacro entity= (TipoDocumentoMacro) value;
            if (entity != null && entity instanceof TipoDocumentoMacro && entity.getId() != null) {
                uiComponent.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }
}
