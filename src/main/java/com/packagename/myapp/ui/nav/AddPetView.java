package com.packagename.myapp.ui.nav;

import com.logic.AddPetLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="AddNewPet",layout= MainView.class)

@PageTitle("Add New Pet")


public class AddPetView extends VerticalLayout {

    AddPetLogic addPetLogic;

    public AddPetView(){
        HorizontalLayout mergedLayout = new HorizontalLayout();
        mergedLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        PetAdding v1 = new PetAdding();
        RegView v2 = new RegView();
        mergedLayout.add(v1,v2);
        H2 title = new H2("Add New Pet");
        title.setClassName("addNewPetTitle");
        this.add(title, mergedLayout);
        isLogin();
        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }

    private void isLogin() {
        if (MainView.isUserRegistered()) {
            addPetLogic = new AddPetLogic();
        }else {
            HorizontalLayout data=new HorizontalLayout();
            Span details=new Span("Please register to the site, before adding pet for addoption");
            data.add(details);
            this.add(data);
        }
    }



}
