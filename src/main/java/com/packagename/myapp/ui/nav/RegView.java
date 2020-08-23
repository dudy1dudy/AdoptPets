package com.packagename.myapp.ui.nav;

import com.logic.RegisterLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="reg",layout= MainView.class)
@PageTitle("Registration")

public class RegView extends VerticalLayout{
		RegisterLogic regLogic = new RegisterLogic();
        VerticalLayout vl=new VerticalLayout();
        public RegView(){
            H3 title=new H3("Owner Details");

            HorizontalLayout titlelayout=new HorizontalLayout();
            title.setClassName("titletext");
            titlelayout.add(title);
            title.addClassName("titletext");

            FormLayout registerLayout = new FormLayout();


            TextField firstName = new TextField(); 
            //petName.setLabel("Pet name");
            firstName.setPlaceholder("First Name");

            H4 firstNameLabel=new H4("First name");
            firstNameLabel.addClassName("titletext");

            TextField lastName = new TextField(); 
           
            lastName.setPlaceholder("Last Name");

            H4 lastNameLabel=new H4("Last name");
            lastNameLabel.addClassName("titletext");

            NumberField phone = new NumberField();
            phone.setPlaceholder("Phone Number");

            H4 phoneLabel=new H4("Phone number");
            phoneLabel.addClassName("titletext");

            TextField UserName = new TextField();
            UserName.setPlaceholder("User Name");    
            
            H4 UserNameLabel=new H4("User Name");
            UserNameLabel.addClassName("titletext");

            PasswordField password = new PasswordField();
            password.setPlaceholder("Password");
            
            H4 passwordLabel = new H4("Password");
            passwordLabel.addClassName("titletext");
            
            PasswordField repeatePassword = new PasswordField();
            repeatePassword.setPlaceholder("Repeate Password");
            
            H4 repeatePasswordLabel = new H4("Repeate Password");
            repeatePasswordLabel.addClassName("titletext");
      
           
            EmailField email = new EmailField();
            email.setClearButtonVisible(true);
            email.setErrorMessage("Please enter a valid email address");

            H4 emailLabel=new H4("Email address");
            emailLabel.addClassName("titletext");
            
            TextField city = new TextField(); //state
            //petName.setLabel("Pet name");
            city.setPlaceholder("city");

            H4 citiLabel=new H4("CIty");
            citiLabel.addClassName("titletext");

            TextField street = new TextField(); //state
            //petName.setLabel("Pet name");
            street.setPlaceholder("Street name");

            H4 streetLabel=new H4("Street");
            streetLabel.addClassName("titletext");

            NumberField house = new NumberField();
            house.setPlaceholder("sNo");

            H4 houseLabel=new H4("House Number");
            houseLabel.addClassName("titletext");

            registerLayout.addFormItem(firstName,firstNameLabel);
            registerLayout.addFormItem(lastName,lastNameLabel);
            registerLayout.addFormItem(phone,phoneLabel);
            registerLayout.addFormItem(UserName,UserNameLabel);
            registerLayout.addFormItem(password,passwordLabel);  
            registerLayout.addFormItem(repeatePassword,repeatePasswordLabel); 
            registerLayout.addFormItem(email,emailLabel);
            registerLayout.addFormItem(city,citiLabel);
            registerLayout.addFormItem(street,streetLabel);
            registerLayout.addFormItem(house,houseLabel);
            
            VerticalLayout form=new VerticalLayout();
            form.setWidth("500px");
         
            Button register;
            
            
			register=new Button("Register", click-> regLogic.parametersCheck(vl ,UserName, password, repeatePassword
					, firstName, lastName, email, phone, city, street, house));
			registerLayout.add(register);


			form.add(registerLayout);

            vl.add(titlelayout,form);

            vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

            add(vl);
        }
        
        
        
        
        

}
