package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import group.entities.User;
import group.exception.ErrorInProcessUser;
import group.models.UserModel;
import group.utilities.UserType;


@Route(value="login", layout = MainView.class)
//
@PageTitle("Login")

public class LoginView extends VerticalLayout {
	
	
	UserModel user;
    H1 h1=new H1("Login");
   

    public LoginView(){
    	
    	LoginForm component = new LoginForm();
    	
    	component.addLoginListener(e -> {
    	    boolean isAuthenticated = authenticate(e);
    	    if (isAuthenticated) {
    	    	HorizontalLayout data=new HorizontalLayout();
	            Span details=new Span("Log in succsessful");
	            data.add(details);
	            h1.add(data);
    	        //navigateToMainPage();
    	    } else {
    	    	
    	        component.setError(true);
    	    }
    	});

    	add(component);
    	
        add(h1);
    }

	private boolean authenticate(LoginEvent e) {
		user = new UserModel();
		try {
			User userID = user.findUser(e.getUsername(), e.getPassword());
			if(userID != null) {
				MainView.setUser(userID);
				return true;
			}else {
				return false;
			}
			
		} catch (ErrorInProcessUser e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
}
