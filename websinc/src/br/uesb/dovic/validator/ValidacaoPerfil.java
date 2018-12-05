package br.uesb.dovic.validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("perfilValidator")
public class ValidacaoPerfil implements Validator {
	public void validate(FacesContext context, UIComponent component,
			Object value)

	throws ValidatorException {

		if (value==null) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					"Um perfil deve ser selecionado!");
			throw new ValidatorException(message);

		} 

	}
}

