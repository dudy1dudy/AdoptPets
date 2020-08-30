package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;


import com.logic.PetsList;
import com.logic.UserPetsLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
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

@Route(value="userPetsView",layout= MainView.class)
@PageTitle("UserPetsView")

public class UserPetsView extends VerticalLayout {

    H1 h1=new H1("Your Pets, \nPleas double click a pet to edit it");

    VerticalLayout mainVerticalLayout=new VerticalLayout();

	public void returnLogin(){
		UI.getCurrent().navigate("login");
	}
    
    public UserPetsView(){

		mainVerticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		h1.setClassName("userPetsViewHeader");
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    	UserPetsLogic.setUserPetsList();
    	if (MainView.getUser() == null) {
    		UI.getCurrent().navigate("login");
    		UI.getCurrent().getPage().reload();
		}	
    	   
	    if(UserPetsLogic.getUserPetsList() == null) {
	    	Span details = new Span("You don't have pets");
			
			add(details);
			return;
	    }
	    
	    add(h1);
	    
	    PetsList petL;
        ArrayList<PetsList> userPets = new ArrayList<PetsList>();
				
        for(int i =0 ; i < UserPetsLogic.getUserPetsList().size() ; i++) {
        	petL = new PetsList(UserPetsLogic.getUserPetsList().get(i));
        	userPets.add(petL);
        }

        VerticalLayout gridDiv=new VerticalLayout();
        gridDiv.addClassName("grid-div");
       
		Grid<PetsList> grid = new Grid<PetsList>(PetsList.class);
		grid.setItems(userPets);
		grid.removeAllColumns();
		
		//grid.setColumns("categoryC", "genderC", "ageC", "sizeC" ,"petNameC", "shortDescriptionC");
		grid.addColumn(PetsList::getPetId).setHeader("ID").setWidth("20px");
		grid.addColumn(PetsList::getPetNameC).setHeader("Name").setAutoWidth(true);
		grid.addColumn(PetsList::getCategoryC).setHeader("Category").setAutoWidth(true);
		grid.addColumn(PetsList::getGenderC).setHeader("Gender").setWidth("20px");
		grid.addColumn(PetsList::getCityC).setHeader("City").setAutoWidth(true);
		grid.addColumn(PetsList::getShortDescriptionC).setHeader("Short Description").setAutoWidth(true);
		grid.addItemDoubleClickListener(e -> {
			MainView.setPet(e.getItem().getPet());			
			UI.getCurrent().navigate("EditPet");
		});
		
		grid.setThemeName("column-borders");
		grid.setMaxWidth("1600px");

		gridDiv.add(grid);
		gridDiv.setWidth("1600px");
		gridDiv.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

		grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT,GridVariant.LUMO_ROW_STRIPES,GridVariant.MATERIAL_COLUMN_DIVIDERS);
		mainVerticalLayout.add(gridDiv);
		add(mainVerticalLayout);
	   
		
    }
}

