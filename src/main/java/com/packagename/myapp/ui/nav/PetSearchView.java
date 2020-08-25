package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;

import com.logic.HomeLogic;
import com.logic.PetSearchLogic;
import com.logic.PetsList;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.Pet;
import group.utilities.Gender;
import group.utilities.PetSize;


@Route(value="petSearch",layout= MainView.class)
@PageTitle("PetSearch")

public class PetSearchView extends VerticalLayout {

    H1 h1=new H1("Pets found for you");
    VerticalLayout vl=new VerticalLayout();

    public PetSearchView(){
        add(h1);
        PetsList petL;
        ArrayList<PetsList> pets = new ArrayList<PetsList>();
				
        for(int i =0 ; i < PetSearchLogic.getPetsSearchList().size() ; i++) {
        	petL = new PetsList(PetSearchLogic.getPetsSearchList().get(i));
        	pets.add(petL);
        }
        
		Grid<PetsList> grid = new Grid<>(PetsList.class);
		grid.setItems(pets);
		
	
		grid.setColumns("categoryC", "genderC", "ageC", "sizeC" ,"petNameC", "shortDescriptionC", "cityC");
		add(grid);
		
	}
}
