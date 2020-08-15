package com.packagename.myapp.ui.nav;

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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="reg",layout= MainView.class)
@PageTitle("Registration")

public class RegView extends VerticalLayout{

        VerticalLayout vl=new VerticalLayout();
        public RegView(){
            H3 title=new H3("Register");

            HorizontalLayout titlelayout=new HorizontalLayout();
            title.setClassName("titletext");
            titlelayout.add(title);
            title.addClassName("titletext");

            FormLayout registerLayout = new FormLayout();


            TextField firstName = new TextField(); //state
            //petName.setLabel("Pet name");
            firstName.setPlaceholder("mitton");

            H4 firstNameLabel=new H4("First name");
            firstNameLabel.addClassName("titletext");

            TextField lastName = new TextField(); //state
            //petName.setLabel("Pet name");
            lastName.setPlaceholder("mitton");

            H4 lastNameLabel=new H4("Last name");
            lastNameLabel.addClassName("titletext");

            NumberField phone = new NumberField();
            phone.setPlaceholder("0741236547");

            H4 phoneLabel=new H4("Phone number");
            phoneLabel.addClassName("titletext");

            TextField city = new TextField(); //state
            //petName.setLabel("Pet name");
            city.setPlaceholder("city");

            H4 citiLabel=new H4("CIty");
            citiLabel.addClassName("titletext");

            TextField street = new TextField(); //state
            //petName.setLabel("Pet name");
            street.setPlaceholder("sea road");

            H4 streetLabel=new H4("Street");
            streetLabel.addClassName("titletext");

            NumberField house = new NumberField();
            house.setPlaceholder("sNo");

            H4 houseLabel=new H4("House Number");
            houseLabel.addClassName("titletext");

            EmailField email = new EmailField();
            email.setClearButtonVisible(true);
            email.setErrorMessage("Please enter a valid email address");

            H4 emailLabel=new H4("Email address");
            emailLabel.addClassName("titletext");

            registerLayout.addFormItem(firstName,firstNameLabel);
            registerLayout.addFormItem(lastName,lastNameLabel);
            registerLayout.addFormItem(phone,phoneLabel);
            registerLayout.addFormItem(city,citiLabel);
            registerLayout.addFormItem(street,streetLabel);
            registerLayout.addFormItem(house,houseLabel);
            registerLayout.addFormItem(email,emailLabel);;

            VerticalLayout form=new VerticalLayout();
            form.setWidth("500px");

            Button register=new Button("Register", click->
            {
                HorizontalLayout data=new HorizontalLayout();
                Span details=new Span(email.getValue());
                data.add(details);
                vl.add(data);
            });

            registerLayout.add(register);

            form.add(registerLayout);

            vl.add(titlelayout,form);

            vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

            add(vl);
        }

}
