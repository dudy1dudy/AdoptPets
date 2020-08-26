package com.logic;

import static com.vaadin.flow.server.VaadinSession.getCurrent;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;

import group.entities.User;
import group.exception.ErrorInProcessUser;
import group.models.UserModel;
import group.utilities.UserType;

public class RegisterLogic {

	private UserModel logic;

	public RegisterLogic() {
		logic = new UserModel();
	}

	public void parametersCheck(VerticalLayout vl, TextField userName, PasswordField password,
			PasswordField repeatePassword, TextField firstName, TextField lastName, EmailField email, NumberField phone,
			TextField city, TextField street, NumberField house) {

		if (userName.isEmpty() || password.isEmpty() || repeatePassword.isEmpty() || firstName.isEmpty()
				|| lastName.isEmpty() || email.isEmpty() || city.isEmpty() || street.isEmpty()) {

			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("details are missing, please fill all of the fields");
			data.add(details);
			vl.add(data);
			return;

		} else {
			createUser(vl, userName.getValue(), password.getValue(), repeatePassword.getValue(), firstName.getValue(),
					lastName.getValue(), email.getValue(), phone.getValue(), city.getValue(), street.getValue(),
					house.getValue());
			return;

		}
	}

	public void createUser(VerticalLayout vl, String username, String password, String repeatePassword,
			String firstName, String lastName, String email, double phone, String userCity, String userStreet,
			double house) {
		try {

			boolean a = logic.checkUsernameExists(username);
			if (a) {
				HorizontalLayout data = new HorizontalLayout();
				vl.removeAll();
				Span details = new Span("The User name already exists, please try again");
				data.add(details);
				vl.add(data);
				return;
			}

			if (!password.equals(repeatePassword)) {

				HorizontalLayout data = new HorizontalLayout();
				Span details = new Span("The passwords don't match, Please try again");
				data.add(details);
				vl.add(data);
				return;

			}
			int phoneInt = (int) phone;
			int userHouseNumber = (int) house;

			logic.createNewUser(username, password, firstName, lastName, UserType.REGULAR, email, phoneInt, userCity,
					userStreet, userHouseNumber);
			return;

		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
