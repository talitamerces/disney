package br.uesb.dovic.validator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.uesb.dovic.entidades.Usuario;
import br.uesb.dovic.modelo.DAOUsuario;

@FacesValidator("loginValidator")
public class ValidacaoLogin implements Validator {
	public void validate(FacesContext context, UIComponent component,
			Object value)

	throws ValidatorException {

		String login = (String) value;
		int tamanho = login.length();

		if (tamanho < 3 || tamanho > 15) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					"O login deve ter de 3 a 15 caracteres!");
			throw new ValidatorException(message);

		} else {

			DAOUsuario<Usuario> dao = new DAOUsuario<Usuario>();
			List<Usuario> usuario = dao.listarPorLogin(login);

			if (!usuario.isEmpty()) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Login existente!",
						"Este login já existe!");
				throw new ValidatorException(message);

			}
		}

	}
}
