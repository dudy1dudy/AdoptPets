package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;


import com.logic.PetsList;
import com.logic.UserPetsLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.Pet;
import group.entities.PetOwner;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.AdoptionStatus;
import group.utilities.Gender;
import group.utilities.PetSize;

@Route(value="about",layout= MainView.class)
@PageTitle("About")
public class AboutView extends VerticalLayout {
	

    H1 h1=new H1("Your Pets");
    
    UserPetsLogic userPetsLogic = new UserPetsLogic();
    
    public AboutView(){
    	
    	if (MainView.getUser() == null) {
			
			Span details = new Span("Please register to view your pets");
			
			add(details);
			return;
		}	
    	    	
	    add(h1);
	    userPetsLogic.findUserPets();
		
	    PetsList petL;
        ArrayList<PetsList> userPets = new ArrayList<PetsList>();
				
        for(int i =0 ; i < UserPetsLogic.getUserPetsList().size() ; i++) {
        	petL = new PetsList(UserPetsLogic.getUserPetsList().get(i));
        	userPets.add(petL);
        }
        
		Grid<PetsList> grid = new Grid<PetsList>(PetsList.class);
		grid.setItems(userPets);
		
	
		grid.setColumns("categoryC", "genderC", "ageC", "sizeC" ,"petNameC", "shortDescriptionC");
		add(grid);
	   
		
    }
}
