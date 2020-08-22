package com.packagename.myapp.ui;

import com.packagename.myapp.ui.nav.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;

import group.entities.User;

@CssImport("./styles/shared-styles.css")
public class MainView extends AppLayout {

    /**
     */
	private static User user;
	
	public static User getUser() {
		return user;
	}
	
	public static void setUser(User user) {
		MainView.user = new User();
		MainView.user = user;
	}
	
	public static boolean isUserRegistered() {
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}

    public MainView() {

        //nav bar side icon
        //H1 logo = new H1("Pet");
        //logo.addClassName("logo");
    	
        Icon ico=new Icon(VaadinIcon.CLIPBOARD_USER);

        
       
        
        
        //nav bar item layout
        HorizontalLayout header = new HorizontalLayout( ico);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.setHeight("40px");
        header.addClassName("header");

        //make link for route
        //RouterLink add = new RouterLink("Add", PetAdding.class);
        RouterLink add = new RouterLink("Add", AddPetView.class);
        RouterLink home = new RouterLink("Home", HomeView.class);
        RouterLink about = new RouterLink("About", AboutView.class);
        RouterLink contact = new RouterLink("Contact", ContactView.class);
        RouterLink more = new RouterLink("More", MorehView.class);


        //icon add
        Icon logoV = new Icon(VaadinIcon.HEART_O);
        logoV.getStyle().set("cursor", "pointer");
        logoV.addClickListener(e ->
                logoV.getUI().ifPresent(ui ->
                        ui.navigate("likeview"))
        );
        logoV.addClassName("heart");

        Icon reg = new Icon(VaadinIcon.USER_STAR);
        reg.getStyle().set("cursor", "pointer");
        reg.addClickListener(e ->
                reg.getUI().ifPresent(ui ->
                        ui.navigate("reg"))
        );
        reg.addClassName("reg");

        //icon add to header layout horizontaly
        HorizontalLayout likelayout=new HorizontalLayout(reg,logoV);
        likelayout.setSizeFull();
        likelayout.setHeight("40px");

        //add links to headertab
        header.add(home,add,about,contact,more,likelayout);

        addToNavbar(header);

    }




}