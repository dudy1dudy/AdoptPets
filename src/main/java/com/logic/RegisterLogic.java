package com.logic;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import group.entities.User;
import group.exception.ErrorInProcessUser;
import group.models.UserModel;
import group.utilities.UserType;

public class RegisterLogic {

	
	private UserModel logic;
	
	public RegisterLogic() {
		logic = new UserModel();
	}
	
	public void createUser(VerticalLayout vl, String username, String password, String repeatePassword, String firstName, String lastName, String email, double phone
			,String userCity,String userStreet,double house) {
		try {
			
			boolean a = logic.checkUsernameExists(username);
			if(a) {
				HorizontalLayout data=new HorizontalLayout();
				vl.removeAll();
                Span details=new Span("The User name already exists, please try again");
                data.add(details);
                vl.add(data);
				return;
			}

			if (!password.equals(repeatePassword)) {
				
				HorizontalLayout data=new HorizontalLayout();
                Span details=new Span("The passwords don't match, Please try again");
                data.add(details);
                vl.add(data);
				return;
				
			}
			int phoneInt = (int)phone;
			int userHouseNumber =(int)house;
			
			logic.createNewUser(username, password, firstName, lastName, UserType.REGULAR , email, phoneInt, 
					userCity, userStreet, userHouseNumber);
			return;
		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

