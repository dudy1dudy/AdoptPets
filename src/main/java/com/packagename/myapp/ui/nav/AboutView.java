package com.packagename.myapp.ui.nav;

import com.logic.HomeLogic;
import com.logic.PetsList;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.PetOwner;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.AdoptionStatus;
import group.utilities.Gender;
import group.utilities.PetSize;

@Route(value="about",layout= MainView.class)
@PageTitle("About")
public class AboutView extends VerticalLayout {
	
	 H1 h1=new H1("Like");
    
	private PetsList home;
	private Grid<PetsList> grid = new Grid<>(PetsList.class); 
    public AboutView(){
    	
     addClassName("list_view"); 
     setSizeFull(); 
     configureGrid(); 

     add(grid); 
    
    	 
    	 grid.setItems(home.getPetsList());
    	
     
     add(h1);
    
 }

 private void configureGrid() {
	 grid.addClassName("pet-grid");
	 
     grid.setColumns("categoryC", "genderC", "ageC", "sizeC", "petNameC", "shortDescriptionC", "cityC");
     
 }

}
