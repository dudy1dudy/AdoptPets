package com.logic;

import static com.vaadin.flow.server.VaadinSession.getCurrent;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
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

	private UserModel logic = new UserModel();

	public void parametersCheck(VerticalLayout vl, TextField userName, PasswordField password,
			PasswordField repeatePassword, TextField firstName, TextField lastName, EmailField email, NumberField phone,
			TextField city, TextField street, NumberField house) {

		if (userName.isEmpty() || password.isEmpty() || repeatePassword.isEmpty() || firstName.isEmpty()
				|| lastName.isEmpty() || email.isEmpty() || city.isEmpty() || street.isEmpty()) {

			// Notification
			Notification.show("details are missing, please fill all of the fields")
					.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
			return;

		} else {
			createUser(vl, userName.getValue(), password.getValue(), repeatePassword.getValue(), firstName.getValue(),
					lastName.getValue(), email.getValue(), phone.getValue(), city.getValue(), street.getValue(),
					house.getValue());

		}
	}

	public void createUser(VerticalLayout vl, String username, String password, String repeatePassword,
			String firstName, String lastName, String email, double phone, String userCity, String userStreet,
			double house) {
		try {

			boolean a = logic.checkUsernameExists(username);
			if (a) {

				// Notification
				Notification.show("The User name already exists, please try again")
						.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
				return;
			}

			if (!password.equals(repeatePassword)) {

				// Notification
				Notification.show("The passwords don't match, Please try again")
						.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
				return;

			}
			int phoneInt = (int) phone;
			int userHouseNumber = (int) house;

			logic.createNewUser(username, password, firstName, lastName, UserType.REGULAR, email, phoneInt, userCity,
					userStreet, userHouseNumber);
			
			// Go back to home
			UI.getCurrent().navigate("");

			// Notification
			Notification.show("User created succsessfully")
					.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
			return;

		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}
