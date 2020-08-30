package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.spring.annotation.UIScope;

import group.entities.Pet;
import group.exception.ErrorInProcessPetData;
import group.exception.ErrorInProcessUser;
import group.models.LikeModel;
import group.models.PetModel;
import group.utilities.ConvertPhoto;

@UIScope
@Route(value = "", layout = MainView.class)
//
@PageTitle("Home")

@CssImport("./styles/shared-styles.css")

public class HomeView extends VerticalLayout {

	private LikeLogic likeL = new LikeLogic();
	private PetModel petM = new PetModel();
	private PetSearchLogic searchL = new PetSearchLogic();
	private VerticalLayout vl = new VerticalLayout();
	private H2 title;
	private VerticalLayout formvert;

	public HomeView() {
		setClassName("homeView");
		setAlignItems(Alignment.CENTER);
		// layout for title "find adoptable pets near"
		HorizontalLayout findadopt = new HorizontalLayout();
		this.title = new H2("Find the pet for you");
		findadopt.add(title);

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

		}

		FlexLayout workspace = new FlexLayout();
		workspace.setAlignItems(Alignment.CENTER);
		workspace.setFlexWrap(FlexWrap.WRAP);
		// workspace.setMaxWidth("72%");
		workspace.setAlignContent(FlexLayout.ContentAlignment.SPACE_AROUND);

		for (int i = 0; i < allPets.size(); i++) {
			Pet pet = (Pet) allPets.get(i);
			workspace.add(createCard(pet));
		}
		workspace.addClassName("workspace");

		removeAll();
		VerticalLayout upperLayout = new VerticalLayout();
		VerticalLayout lowerLayout = new VerticalLayout();

		upperLayout.addClassName("petAddingUpperLayout");
		lowerLayout.addClassName("petAddingLowerLayout");
		lowerLayout.setAlignItems(Alignment.CENTER);
		// add to main layout
		upperLayout.add(title, formvert);
		lowerLayout.add(workspace);

		upperLayout.setWidth("60%");
		lowerLayout.setWidth("60%");
		//// vl.add(title, formvert, workspace);

		// layout add to page - main vertical unit layout
		/// add(vl);
		add(upperLayout, lowerLayout);
	}

	// create cards
	private Component createCard(Pet pet) {

		// card title
		if (pet != null) {
			H4 category = new H4(pet.getCategory().toString());
			H4 name = new H4(pet.getPetName());

			// card details
			Span age = new Span("Age: " + pet.getPetAge());
			age.setClassName("cardText");
			Span size = new Span("Size: " + pet.getPetSize());
			size.setClassName("cardText");
			Span city = new Span("City: " + pet.getPetOwner().getCity());
			city.setClassName("cardText");
			Span desc = new Span("Description: " + pet.getShortDescription());
			desc.setClassName("cardText");
//			Span details = new Span("Age: " + pet.getPetAge() + " Size: " + pet.getPetSize() + "\nCity: "
//					+ pet.getPetOwner().getCity());

			VerticalLayout details = new VerticalLayout();
			details.setWidth("100%");
			details.getStyle().set("cursor", "pointer");
			details.setId(String.valueOf(pet.getPetId()));
			details.add(age, size, city, desc);
			details.setClassName("cardText");

			details.addClickListener(e -> {
				if (MainView.isUserRegistered()) {
					MainView.setCurrDeatailPet(pet);
					details.getUI().ifPresent(ui -> {
						ui.navigate("detail");
					});
				} else {
					Notification.show("Please register to view details").setPosition(Position.TOP_CENTER);
				}

			});

			Image image = ConvertPhoto.dbPhotoToImage(pet);

			image.addClassName("image");
			image.setWidth("200px");
			image.setHeight("180px");
			image.getStyle().set("cursor", "pointer");
			image.setId(String.valueOf(pet.getPetId()));

			image.addClickListener(e -> {
				if (MainView.isUserRegistered()) {
					MainView.setCurrDeatailPet(pet);
					image.getUI().ifPresent(ui -> {
						ui.navigate("detail");
					});
				} else {
					Notification.show("Please register to view details").setPosition(Position.TOP_CENTER);
				}

			});

			// like button heart add to custom card
			Icon logoV = new Icon(VaadinIcon.HEART_O);
			logoV.getStyle().set("cursor", "pointer");
			logoV.setColor("Gray");

			// Check if user like pet, then like == red
			if (MainView.isUserRegistered()) {

				if (likeL.isRegUesrLikePet(pet)) {
					logoV.setColor("Red");
				}
			} else {
				logoV.setColor("Gray");
			}
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
			card.setHeight("500px");

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

		if (logoV.getColor().equals("Gray")) {
			likeL.like(petId);
			logoV.setColor("Red");
			like("Thank you for like me!");
		} else {
			if (logoV.getColor().equals("Red")) {
				likeL.unLike(petId);
				logoV.setColor("Gray");
				like("OK, Bye Bye");
			}
		}
		return;
	}

	public void like(String text) {
		Notification.show(text).setPosition(Position.TOP_CENTER);

	}
}
