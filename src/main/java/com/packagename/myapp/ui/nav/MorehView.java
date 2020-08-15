package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="moreh", layout = MainView.class)
//
@PageTitle("More")

public class MorehView extends VerticalLayout {
    H1 h1=new H1("More");
    VerticalLayout vl=new VerticalLayout();

    public MorehView(){
        add(h1);
    }
}
