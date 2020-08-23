package com.packagename.myapp.ui.nav;

import com.logic.HomeLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="", layout = MainView.class)
//
@PageTitle("Home")

@CssImport("./styles/shared-styles.css")

public class HomeView extends VerticalLayout {

	HomeLogic logic = new HomeLogic();
    VerticalLayout vl=new VerticalLayout();

    public HomeView(){

        //layout for title "find adoptable pets near"
        HorizontalLayout findadopt=new HorizontalLayout();
        H2 title=new H2("Find the pet for you");
        findadopt.add(title);

        //css class for title "find adoptable pets near"
        title.addClassName("titletext");

        //form layout as formvert
        VerticalLayout formvert=new VerticalLayout();

        //layouts for text fields
        HorizontalLayout formlayoutr1=new HorizontalLayout();
        HorizontalLayout formlayoutr2=new HorizontalLayout();
        HorizontalLayout formlayoutr3=new HorizontalLayout();
        HorizontalLayout formlayoutr4=new HorizontalLayout();

        

        Checkbox dogs = new Checkbox("Dogs");
        Checkbox cats = new Checkbox("Cats");
        Checkbox rodent = new Checkbox("Rodent");
        Checkbox birds = new Checkbox("Birds");
        Checkbox fish = new Checkbox("Fish");
        Checkbox other = new Checkbox("Other");
        Checkbox all = new Checkbox("All");

        formlayoutr2.add(dogs,cats,rodent,birds,fish,other);

        Select<String> gender = new Select<>();//combobox
        gender.setItems("Female", "Male", "All");
        gender.setLabel("Gender");
        gender.setPlaceholder("Gender");

        Select<String> age = new Select<>();
        age.setItems("0 - 1","2 - 3", "4 +", "All");
        age.setLabel("Age");
        age.setPlaceholder("any");

        Select<String> size = new Select<>();
        size.setItems("Medium", "Small", "Large", "XLarge", "All");
        size.setLabel("Size");
        size.setPlaceholder("any");

        formlayoutr3.add(gender,age,size);

       

        formvert.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        Button search=new Button("Search"); 
        
        search.addClickListener( new ComponentEventListener( ) {
            
        	
			@Override
			public void onComponentEvent(ComponentEvent arg0) {
				logic.parametersCheck(vl, search, dogs, cats, rodent, birds, fish,
						other, all, gender, age, size);
				
			}
        } );
        
        search.setWidth("160px");

        formvert.add(formlayoutr1,formlayoutr2,formlayoutr3,formlayoutr4, search);

        vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        //add defined card layout
        HorizontalLayout workspace = new HorizontalLayout(createCard(), createCard(), createCard(), createCard(),createCard(), createCard());
        workspace.addClassName("workspace");

        //add to main layout
        vl.add(title,formvert,workspace);

        //layout add to page - main vertical unit layout
        add(vl);
    }

    //create cards
    private Component createCard() {

        //card title
        H4 card1Label = new H4("Title");
        //card details
        Span details = new Span("dogs are near to you selected area");
        details.setWidth("180px");
        details.getStyle().set("cursor", "pointer");
        details.addClickListener(e->
                details.getUI().ifPresent(ui ->
                        ui.navigate("detail")));

        Image image = new Image("icons/img.jpg", "DummyImage");
        image.addClassName("image");
        image.setWidth("160px");
        image.setHeight("140px");
        image.getStyle().set("cursor", "pointer");
        image.addClickListener(e ->
                image.getUI().ifPresent(ui ->//Vaadin way to navigate between UIs
                        ui.navigate("detail"))
        );

        //like button heart add to custom card
        Icon logoV = new Icon(VaadinIcon.HEART_O);
        logoV.getStyle().set("cursor", "pointer");
        logoV.addClickListener(
                event -> like("You Like"));
        logoV.addClassName("heartlike");

        HorizontalLayout title=new HorizontalLayout(card1Label,logoV);
        title.setDefaultVerticalComponentAlignment(Alignment.END);
        title.setWidth("180px");

        //card layout
        VerticalLayout verrticalcard=new VerticalLayout();
        verrticalcard.setAlignItems(Alignment.CENTER);
        verrticalcard.setJustifyContentMode(JustifyContentMode.CENTER);
        verrticalcard.add(image,title,details);

        FlexLayout card = new FlexLayout(verrticalcard);
        card.addClassName("card");
        card.setAlignItems(Alignment.CENTER);
        card.setJustifyContentMode(JustifyContentMode.CENTER);
        card.setWidth("200px");
        card.setHeight("280px");

        return card;
    }

    public void like(String text){

        Notification.show(text);
    }
}
