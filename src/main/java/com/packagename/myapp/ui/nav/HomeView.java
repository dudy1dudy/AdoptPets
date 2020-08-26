package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;

import com.logic.HomeLogic;
import com.logic.LikeLogic;
import com.logic.PetSearchLogic;
import com.logic.PetsList;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.component.orderedlayout.FlexLayout.WrapMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.Pet;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.ConvertPhoto;

@Route(value = "", layout = MainView.class)
//
@PageTitle("Home")

@CssImport("./styles/shared-styles.css")

public class HomeView extends VerticalLayout {

	private LikeLogic likeL = new LikeLogic();
	private PetModel petM = new PetModel();
	private HomeLogic logic = new HomeLogic();
	private PetSearchLogic searchL = new PetSearchLogic();
	private VerticalLayout vl = new VerticalLayout();
	private int petOwnerCardId;
	private H2 title;
	private VerticalLayout formvert;

	public HomeView() {

		// layout for title "find adoptable pets near"
		HorizontalLayout findadopt = new HorizontalLayout();
		this.title = new H2("Find the pet for you");
		findadopt.add(title);

		// css class for title "find adoptable pets near"
		title.addClassName("titletext");

		// form layout as formvert
		this.formvert = new VerticalLayout();

		// layouts for text fields
		HorizontalLayout formlayoutr1 = new HorizontalLayout();
		HorizontalLayout formlayoutr2 = new HorizontalLayout();
		HorizontalLayout formlayoutr3 = new HorizontalLayout();
		HorizontalLayout formlayoutr4 = new HorizontalLayout();

		Checkbox dogs = new Checkbox("Dogs");
		Checkbox cats = new Checkbox("Cats");
		Checkbox rodent = new Checkbox("Rodent");
		Checkbox birds = new Checkbox("Birds");
		Checkbox fish = new Checkbox("Fish");
		Checkbox other = new Checkbox("Other");
		Checkbox all = new Checkbox("All");

		formlayoutr2.add(dogs, cats, rodent, birds, fish, other, all);

		Select<String> gender = new Select<>();// combobox
		gender.setItems("Female", "Male", "All");
		gender.setLabel("Gender");
		gender.setPlaceholder("Gender");

		Select<String> age = new Select<>();
		age.setItems("0 - 1", "2 - 3", "4 +", "All");
		age.setLabel("Age");
		age.setPlaceholder("any");

		Select<String> size = new Select<>();
		size.setItems("Small", "Medium", "Large", "XLarge", "All");
		size.setLabel("Size");
		size.setPlaceholder("any");

		formlayoutr3.add(gender, age, size);

		formvert.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

		Button search = new Button("Search");

		search.addClickListener(new ComponentEventListener() {

			@Override
			public void onComponentEvent(ComponentEvent arg0) {

				searchL.parametersCheck(vl, search, dogs, cats, rodent, birds, fish, other, all, gender, age, size);
				setPetsCards(false);
				return;
			}
		});

		search.setWidth("160px");

		formvert.add(formlayoutr1, formlayoutr2, formlayoutr3, formlayoutr4, search);

		vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

		// add defined card layout
		setPetsCards(true);
	}

	private void setPetsCards(boolean first) {

		List<Pet> allPets = new ArrayList<Pet>();

		// Check if pets already selected
		if (first == false) {
			for (int i = 0; i < PetSearchLogic.getPetsSearchList().size(); i++) {
				allPets.add(PetSearchLogic.getPetsSearchList().get(i));
			}
		} else {
			try {
				allPets.addAll(petM.getAllPets());
			} catch (ErrorInProcessPetData e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HomeLogic.setPetsList(allPets);
		}

		FlexLayout workspace = new FlexLayout();
		workspace.setAlignItems(Alignment.CENTER);
		workspace.setFlexWrap(FlexWrap.WRAP);
		workspace.setMaxWidth("72%");
		workspace.setAlignContent(FlexLayout.ContentAlignment.SPACE_AROUND);

		for (int i = 0; i < allPets.size(); i++) {
			Pet pet = (Pet) allPets.get(i);
			petOwnerCardId = pet.getPetId();
			workspace.add(createCard(pet));
		}
		workspace.addClassName("workspace");

		vl.removeAll();

		// add to main layout
		vl.add(title, formvert, workspace);

		// layout add to page - main vertical unit layout
		add(vl);
	}

	// create cards
	private Component createCard() {

		// card title
		H4 card1Label = new H4("Title");
		// card details
		Span details = new Span("dogs are near to you selected area");
		details.setWidth("180px");
		details.getStyle().set("cursor", "pointer");
		details.addClickListener(e -> details.getUI().ifPresent(ui -> ui.navigate("detail")));

		Image image = new Image("icons/img.jpg", "DummyImage");
		image.addClassName("image");
		image.setWidth("160px");
		image.setHeight("140px");
		image.getStyle().set("cursor", "pointer");
		image.addClickListener(e -> image.getUI().ifPresent(ui -> // Vaadin way to navigate between UIs
		ui.navigate("detail")));

		// like button heart add to custom card
		Icon logoV = new Icon(VaadinIcon.HEART_O);
		logoV.getStyle().set("cursor", "pointer");
		logoV.addClickListener(event -> like("You Like"));
		logoV.addClassName("heartlike");

		HorizontalLayout title = new HorizontalLayout(card1Label, logoV);
		title.setDefaultVerticalComponentAlignment(Alignment.END);
		title.setWidth("180px");

		// card layout
		VerticalLayout verrticalcard = new VerticalLayout();
		verrticalcard.setAlignItems(Alignment.CENTER);
		verrticalcard.setJustifyContentMode(JustifyContentMode.CENTER);
		verrticalcard.add(image, title, details);

		FlexLayout card = new FlexLayout(verrticalcard);
		card.addClassName("card");
		card.setAlignItems(Alignment.CENTER);
		card.setJustifyContentMode(JustifyContentMode.CENTER);
		card.setWidth("200px");
		card.setHeight("280px");

		return card;
	}

	// create cards
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

			// like button heart add to custom card
			Icon logoV = new Icon(VaadinIcon.HEART_O);
			logoV.getStyle().set("cursor", "pointer");
			logoV.setColor("White");
			logoV.addClickListener(event -> like(pet.getPetId(), logoV));
			logoV.addClassName("heartlike");

			HorizontalLayout title = new HorizontalLayout(category, name, logoV);
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

	public void like(int petId, Icon logoV) {
		if (!MainView.isUserRegistered()) {
			HorizontalLayout data = new HorizontalLayout();
			like("Please login Before pressing like");
			vl.add(data);
			return;
		}

		if (logoV.getColor().equals("White")) {
			likeL.like(petId);
			logoV.setColor("Blue");
			like("Thank You for like me");
		}
		if (logoV.getColor().equals("Blue")) {
			likeL.unLike(petId);
			logoV.setColor("White");
		}
	}

	public int getPetOwnerCardId() {
		return petOwnerCardId;

	}

	public void like(String text) {
		Notification.show(text).setPosition(Position.TOP_CENTER);

	}
}
