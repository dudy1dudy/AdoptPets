package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;

import com.logic.HomeLogic;
import com.logic.LikeLogic;
import com.logic.PetSearchLogic;
import com.logic.PetsList;
import com.logic.UserPetsLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.Pet;
import group.exception.ErrorInProcessPetData;
import group.utilities.ConvertPhoto;

@Route(value="likeview",layout= MainView.class)
@PageTitle("About")

public class LikeView extends VerticalLayout{

	private List<Pet> likePetsList = new ArrayList<Pet>();
	private LikeLogic likeL = new LikeLogic();
	
	public LikeView(){
		 
        H1 h1=new H1("Like");
        add(h1);
        if (MainView.getUser() == null) {
			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("Please register to view your likes");
			data.add(details);
			add(data);
			return;
		}	
       
        likePetsList.addAll(likeL.getLikePetsList());
        
        setLikePetsCards();
	}
	
	private void setLikePetsCards() {

		FlexLayout workspace = new FlexLayout();
		workspace.setAlignItems(Alignment.CENTER);
		workspace.setFlexWrap(FlexWrap.WRAP);
		workspace.setMaxWidth("72%");
		workspace.setAlignContent(FlexLayout.ContentAlignment.SPACE_AROUND);

		for (int i = 0; i < likePetsList.size(); i++) {
			Pet pet = (Pet) likePetsList.get(i);
			workspace.add(createCard(pet));
		}
		workspace.addClassName("workspace");
		add(workspace);
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
	 * public LikeView(){
	 
        H1 h1=new H1("Like");
        add(h1);
        if (MainView.getUser() == null) {
			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("Please register to view your likes");
			data.add(details);
			add(data);
			return;
		}	
        
        likeL.likePetList();
        if (likeL == null) {
			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("You have no likes");
			data.add(details);
			add(data);
			return;
		}	
        
        PetsList petL;
        ArrayList<PetsList> likePets = new ArrayList<PetsList>();
        
        for(int i = 0 ; i < LikeLogic.getLikePetsList().size() ; i++) {
        	petL = new PetsList(LikeLogic.getLikePetsList().get(i));
        	likePets.add(petL);
        }
        
		Grid<PetsList> grid = new Grid<PetsList>(PetsList.class);
		grid.setItems(likePets);
		
	
		grid.setColumns("categoryC", "genderC", "ageC", "sizeC" ,"petNameC", "shortDescriptionC");
		add(grid);
        
        
        
        
    }


}
*/
