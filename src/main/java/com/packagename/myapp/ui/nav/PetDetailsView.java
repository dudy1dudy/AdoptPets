package com.packagename.myapp.ui.nav;

import java.util.Optional;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.exception.ErrorInProcessPetData;
import group.models.PetModel;

@Route(value="detail",layout= MainView.class)
@PageTitle("Pet details")
public class PetDetailsView extends VerticalLayout {

	private PetModel petM = new PetModel();
	private HomeView homeV = new HomeView();    
		
    public PetDetailsView(){
    	
        VerticalLayout vl=new VerticalLayout();
        vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        HorizontalLayout pet = new HorizontalLayout();

        HorizontalLayout title=new HorizontalLayout();
        title.setDefaultVerticalComponentAlignment(Alignment.CENTER);
       
        Div div=new Div();
        div.setWidth("400px");
        //div.setHeight("20rem");

        Image image = new Image("icons/img.jpg", "DummyImage");
        image.addClassName("image");
        image.setWidth("200px");
        image.setHeight("200px");

        VerticalLayout divLayout=new VerticalLayout();
        H3 name = null;
        Span detail = null;
        int id = homeV.getPetOwnerCardId();
        try {
        name=new H3("My name is "+petM.getPetById(id).getPetName());
        detail=new Span("DESCRIPTION: /n" + petM.getPetById(id).getShortDescription()
        		+ "/n /n" + petM.getPetById(id).getDetailDescription());

        } catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        HorizontalLayout ageLine=new HorizontalLayout();
        VerticalLayout age=new VerticalLayout();
        VerticalLayout gender=new VerticalLayout();

       

        try {
			age.add(petM.getPetById(id).toString());
		    gender.add(petM.getPetById(id).getPetOwner().toString());
        } catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ageLine.add(age,gender);

        divLayout.add(name,detail,ageLine);

        div.add(divLayout);

        pet.add(image,div);

        vl.add(title,pet);

        add(vl);
    }
}
