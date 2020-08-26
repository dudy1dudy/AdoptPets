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
		
		//grid.addColumn("shortDescriptionC").setId("columnId");

		//Button button = new Button("click me");
		//button.addClickListener(event -> { });
		
		//grid.getHeaderRow(0).getCell("columnId").setComponent(button);
		grid.addSelectionListener(event -> {
		     Button b = new Button("Edit");
		    add(b);
		});
		
		
		add(grid);
	   
		
    }
}


/*
 * grid.addColumn(..).setId("columnId")

Button button = new Button("click me");
button.addClickListener(event -> { 
   ...
});
...
grid.getHeaderRow(0).getCell("columnId").setComponent(button)
 * 
 */












/*
package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;

import com.logic.LikeLogic;
import com.logic.PetsList;
import com.logic.UserPetsLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.Pet;
import group.entities.PetOwner;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.AdoptionStatus;
import group.utilities.ConvertPhoto;
import group.utilities.Gender;
import group.utilities.PetSize;

@Route(value="userPets",layout= MainView.class)
@PageTitle("UserPets")
public class UserPetsView extends VerticalLayout {
	
	    
	private List<Pet> userPetsList = new ArrayList<Pet>();
	private UserPetsLogic userPetsL = new UserPetsLogic();
	
	public UserPetsView(){
		H1 h1=new H1("Your Pets");
        add(h1);
        if (MainView.getUser() == null) {
			
			Span details = new Span("Please register to view your pets");
			
			add(details);
			return;
		}	
        userPetsList.clear();
        
        userPetsList.addAll(userPetsL.getUserPetsList());
        
        setUserPetsCards();
	}
	
	private void setUserPetsCards() {

		FlexLayout workspace = new FlexLayout();
		workspace.setAlignItems(Alignment.CENTER);
		workspace.setFlexWrap(FlexWrap.WRAP);
		workspace.setMaxWidth("72%");
		workspace.setAlignContent(FlexLayout.ContentAlignment.SPACE_AROUND);

		for (int i = 0; i < userPetsList.size(); i++) {
			Pet pet = userPetsList.get(i);
			workspace.add(createCard(pet));
		}
		workspace.addClassName("workspace");
		add(workspace);
		return;
	}
	
	
	private Component createCard(Pet pet) {

//		Pet pet = new Pet();
//		pet = (Pet) HomeLogic.getPetsList().get(index);

//		 HomeLogic.getPetsList().

		// card title
		if (pet != null) {
			H4 category = new H4(pet.getCategory().toString());
			H4 name = new H4(pet.getPetName());

			// card details

			Span details = new Span("Age: " + pet.getPetAge() + " Size: " + pet.getPetSize() + "\nCity: "
					+ pet.getPetOwner().getCity());
			details.setWidth("210px");
			details.getStyle().set("cursor", "pointer");
			details.addClickListener(e -> details.getUI().ifPresent(ui -> ui.navigate("detail")));

			Image image = ConvertPhoto.dbPhotoToImage(pet);

			image.addClassName("image");
			image.setWidth("160px");
			image.setHeight("140px");
			image.getStyle().set("cursor", "pointer");
			image.addClickListener(e -> image.getUI().ifPresent(ui -> // Vaadin way to navigate between UIs
			ui.navigate("detail")));

			
			HorizontalLayout title = new HorizontalLayout(category, name);
			title.setDefaultVerticalComponentAlignment(Alignment.END);
			title.setWidth("210px");

			// card layout
			VerticalLayout verrticalcard = new VerticalLayout();
			verrticalcard.setAlignItems(Alignment.CENTER);
			verrticalcard.setJustifyContentMode(JustifyContentMode.CENTER);
			verrticalcard.add(image, title, details);

			FlexLayout card = new FlexLayout(verrticalcard);
			card.addClassName("card");
			card.setAlignItems(Alignment.CENTER);
			card.setJustifyContentMode(JustifyContentMode.CENTER);
			card.setWidth("300px");
			card.setHeight("300px");

			return card;
		}
		return null;
	}
	
	
	
}
	

/*
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
*/