package com.packagename.myapp.ui;

import com.logic.UserPetsLogic;
import com.packagename.myapp.ui.nav.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;

import group.entities.Pet;
import group.entities.User;

import org.junit.rules.ExternalResource;

@CssImport("./styles/shared-styles.css")
public class MainView extends AppLayout {

	// make link for route
	RouterLink addNewPet = new RouterLink("Add Pet", PetAdding.class);
	RouterLink home = new RouterLink("Home", HomeView.class);
	RouterLink yourPets = new RouterLink("Your Pets", AboutView.class);
	RouterLink editPet = new RouterLink("EditPet", PetSearchView.class);
//	RouterLink editPet = new RouterLink("EditPet", PetChange.class);
	RouterLink login = new RouterLink("Login", LoginView.class);

	/**
	 * Static Login parameter
	 */
	private static User user;
	/**
	 * Static Current edit pet parameter
	 */
	private static Pet CurrEditPet;
	/**
	 * Static Current detailed pet parameter
	 */
	private static Pet currDeatailPet;

	public static Pet getCurrDeatailPet() {
		return MainView.currDeatailPet;
	}

	public static void setCurrDeatailPet(Pet pet) {
		MainView.currDeatailPet = new Pet();
		MainView.currDeatailPet = pet;
	}

	public static Pet getCurrentEditPet() {
		return MainView.CurrEditPet;
	}

	public static void setPet(Pet pet) {
		MainView.CurrEditPet = new Pet();
		MainView.CurrEditPet = pet;
	}

	public static User getUser() {
		return MainView.user;
	}

	public static void setUser(User user) {
		MainView.user = new User();
		MainView.user = user;
	}

	public static boolean isUserRegistered() {
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public MainView() {

		// nav bar side icon
		// H1 logo = new H1("Pet");
		// logo.addClassName("logo");

		Icon ico = new Icon(VaadinIcon.CLIPBOARD_USER);

		// nav bar item layout
		HorizontalLayout header = new HorizontalLayout(ico);
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.setWidth("100%");
		header.setHeight("40px");
		header.addClassName("header");

		// icon add
		Icon logoV = new Icon(VaadinIcon.HEART_O);
		logoV.getStyle().set("cursor", "pointer");
		logoV.addClickListener(e -> logoV.getUI().ifPresent(ui -> ui.navigate("likeview")));
		logoV.addClassName("heart");

		Icon reg = new Icon(VaadinIcon.USER_STAR);
		reg.getStyle().set("cursor", "pointer");
		reg.addClickListener(e -> reg.getUI().ifPresent(ui -> ui.navigate("reg")));
		reg.addClassName("reg");

		// icon add to header layout horizontaly
		HorizontalLayout likelayout = new HorizontalLayout(reg, logoV);
		likelayout.setSizeFull();
		likelayout.setHeight("40px");
		likelayout.setWidth("60px");

		addNewPet.setText("Add Pet");

		// add links to headertab
		header.add(home, addNewPet, login, yourPets, likelayout);

		header.setHeight("80px");
		addToNavbar(true, header);

	}

}