package br.uesb.dovic.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("senhaValidator")
public class ValidacaoSenha implements Validator{
	@Override 

    public void validate(FacesContext context, UIComponent component,Object value)

    throws ValidatorException {

   
		 UIInput passwordField = (UIInput) context.getViewRoot().findComponent("mainForm:senhaUsuario");
		    if (passwordField == null)
		        throw new IllegalArgumentException(String.format("Componente senhaUsuario não encontrado"));
		    String password = (String) passwordField.getValue();
		    
		    
		    String confirmPassword = (String) value;
		    if (!confirmPassword.equals(password)) {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não são iguais!", "As senha e a confirmação devem ser iguais!");
		        throw new ValidatorException(message);
		    
		}


	}
}
