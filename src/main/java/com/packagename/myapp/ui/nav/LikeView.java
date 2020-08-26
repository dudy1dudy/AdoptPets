package com.packagename.myapp.ui.nav;

import java.util.ArrayList;

import com.logic.LikeLogic;
import com.logic.PetsList;
import com.logic.UserPetsLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="likeview",layout= MainView.class)
@PageTitle("About")

public class LikeView extends VerticalLayout{

	
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
