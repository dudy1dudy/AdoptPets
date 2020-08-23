package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="about",layout= MainView.class)
@PageTitle("About")
public class AboutView extends VerticalLayout {
    public AboutView(){
        H1 h1=new H1("Aboutt");
        add(h1);
    }
}
