package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="contact",layout= MainView.class)
@PageTitle("Contact")

public class ContactView extends VerticalLayout {

    H1 h1=new H1("Contact");
    VerticalLayout vl=new VerticalLayout();

    public ContactView(){
        add(h1);
    }
}