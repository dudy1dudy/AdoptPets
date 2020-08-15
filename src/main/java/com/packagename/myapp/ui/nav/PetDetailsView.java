package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="detail",layout= MainView.class)
@PageTitle("Pet details")
public class PetDetailsView extends VerticalLayout {

    String PET_NAME="Hula";
    String GENDER="male";
    String AGE="6y";
    String DESCRIPTION= PET_NAME + "is a super smart amazing dog with so much love to give. Hula is well trained knows all commands sit, heal, go to your bed, stay on your bed, go to your crate, paw she knows it all. Hula is very protective and so loving g she needs a experienced owner who keeps her in check and loves her. She loves to play with toys she is a great fetcher and will be the most loyal dog you will ever know. We require that you meet with our trainer and that you take 15 training sessions with our trainer. You will love our trainer he has been training for 30 years. Give Hula a chance to be your forever best friend. She was abandoned and given up and hit in the face and she has come through it all.";

    public PetDetailsView(){

        VerticalLayout vl=new VerticalLayout();
        vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        HorizontalLayout pet = new HorizontalLayout();

        HorizontalLayout title=new HorizontalLayout();
        title.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        H2 h2=new H2(PET_NAME);
        title.add(h2);
        h2.setClassName("titletext");

        TextField titleField = new TextField();

        Div div=new Div();
        div.setWidth("400px");
        //div.setHeight("20rem");

        Image image = new Image("icons/img.jpg", "DummyImage");
        image.addClassName("image");
        image.setWidth("200px");
        image.setHeight("200px");

        VerticalLayout divLayout=new VerticalLayout();

        H3 name=new H3("My name is "+PET_NAME);
        Span detail=new Span(DESCRIPTION);

        HorizontalLayout ageLine=new HorizontalLayout();
        VerticalLayout age=new VerticalLayout();
        VerticalLayout gender=new VerticalLayout();

        H4 ageLabel=new H4("Age");
        H5 ageNumb=new H5(AGE);
        H4 sexLabel=new H4("Sex");
        H5 sexType=new H5(GENDER);

        age.add(ageLabel,ageNumb);
        gender.add(sexLabel,sexType);

        ageLine.add(age,gender);

        divLayout.add(name,detail,ageLine);

        div.add(divLayout);

        pet.add(image,div);

        vl.add(title,pet);

        add(vl);
    }
}
