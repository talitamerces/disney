package br.uesb.dovic.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.uesb.dovic.entidades.TipoDocumentoMicro;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = TipoDocumentoMicro.class)
public class ConverterTipoMicro implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (TipoDocumentoMicro) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof TipoDocumentoMicro) {
        	TipoDocumentoMicro entity= (TipoDocumentoMicro) value;
            if (entity != null && entity instanceof TipoDocumentoMicro && entity.getId() != null) {
                uiComponent.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }
}
