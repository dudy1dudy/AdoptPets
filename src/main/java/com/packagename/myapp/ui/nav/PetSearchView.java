package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;

import com.logic.HomeLogic;
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
 



List<PetsList> petList = new ArrayList<>();
for(int i=0; i<HomeLogic.getPetsList().size(); i++) {
	petList.add(new PetsList(HomeLogic.getPetsList().get(i)));
}


Grid<PetsList> grid = new Grid<>(PetsList.class);
grid.setItems(petList);

//grid.removeColumnByKey("");

// The Grid<>(Person.class) sorts the properties and in order to
// reorder the properties we use the 'setColumns' method.
grid.setColumns("categoryC", "genderC", "ageC", "sizeC" ,"petNameC", "shortDescriptionC", "cityC");
add(grid);
}
}