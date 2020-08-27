package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.AbstractLogin.ForgotPasswordEvent;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;

import group.entities.User;
import group.exception.ErrorInProcessUser;
import group.models.UserModel;
import group.utilities.UserType;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Route(value = "login", layout = MainView.class)
//
@PageTitle("Login")

public class LoginView extends VerticalLayout {

	UserModel user = new UserModel();
//	H1 h1 = new H1("Login");
	H1 h1 = new H1();

	public LoginView() {

		LoginForm component = new LoginForm();
		setAlignItems(Alignment.CENTER);
		setSizeFull();

		component.addLoginListener(e -> {
			boolean isAuthenticated = authenticate(e);
			if (isAuthenticated) {
				HorizontalLayout data = new HorizontalLayout();
				h1.add(data);

				// Go back to home
				UI.getCurrent().navigate("");

				// Notification
				Notification.show("Log in succsessful")
						.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
				return;
			} else {

				component.setError(true);
				return;
			}
		});

		component.addForgotPasswordListener(e -> {
			try {
				sendMail();
			} catch (Exception exp) {
				// TODO: handle exception
				exp.printStackTrace();
			}
		});

		add(component);

		add(h1);
	}

	private boolean authenticate(LoginEvent e) {
		user = new UserModel();
		try {
			User userID = user.findUser(e.getUsername(), e.getPassword());
			if (userID != null) {
				MainView.setUser(userID);
				return true;
			} else {
				return false;
			}

		} catch (ErrorInProcessUser e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	private void sendMail() {

		VerticalLayout vl = new VerticalLayout();

		// Get user name
		HorizontalLayout titlelayout = new HorizontalLayout();
		H3 title = new H3("Set Your User");
		titlelayout.add(title);
		title.addClassName("titletext");
		
		FormLayout fLayout = new FormLayout();

		TextField usernane = new TextField();
		usernane.setPlaceholder("User Name");
		H4 descriptionLabel = new H4("User Name");
		descriptionLabel.addClassName("titletext");
		fLayout.addFormItem(usernane, descriptionLabel);
		fLayout.setHeightFull();

		Button send = new Button("Send", click -> sendMailToUser(usernane.getValue()));
		
		VerticalLayout form = new VerticalLayout();
		form.setWidth("500px");
		form.add(fLayout);
		
		vl.add(titlelayout, form, send);
		
		vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		
		h1.add(vl);

	}

	private void sendMailToUser(String userName) {

		User userID = new User();

		// Get User name
		try {
			userID = user.getUserByUsername(userName);
		} catch (Exception e) {
			// Notification
			Notification.show("Email can't be send")
					.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
		}

		// load email configuration from properties file
		Properties properties = new Properties();

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.username", "adoptpets082020@gmail.com");
		properties.put("mail.smtp.password", "Aa123456Aa123456");
		String host = properties.getProperty("mail.smtp.host");
		String port = properties.getProperty("mail.smtp.port");
		String ssl = properties.getProperty("mail.smtp.ssl.enable");
		String username = properties.getProperty("mail.smtp.username");
		String password = properties.getProperty("mail.smtp.password");

		String subject = "Forgot password?";
		String text = "You forgot your password. It is: " + userID.getPassword();

		// create an email message with html support
		HtmlEmail email = new HtmlEmail();

		// configure SMTP connection
		email.setHostName(host);
		email.setSmtpPort(Integer.parseInt(port));
		email.setAuthentication(username, password);
		email.setSSLOnConnect(Boolean.parseBoolean(ssl));

		// set its properties accordingly
		try {
			email.setFrom("adoptpets082020@gmail.com");
			email.addTo(userID.getEmail());
			email.setSubject(subject);
			email.setHtmlMsg(text);

			// send it!
			email.send();

			// Notification
			Notification.show("Email sended")
					.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
		} catch (Exception e) {
			// Notification
			Notification.show("Email can't be send")
					.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);

		}
	}
}
