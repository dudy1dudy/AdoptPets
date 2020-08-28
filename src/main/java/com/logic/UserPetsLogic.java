package com.logic;

import java.util.ArrayList;
import java.util.List;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;

import group.entities.Pet;
import group.exception.ErrorInProcessPetOwner;
import group.exception.ErrorInProcessUser;
import group.models.PetModel;

public class UserPetsLogic {

	private static PetModel petM = new PetModel();
	
	private static List<Pet> petsL; 
	
	public static List<Pet> getUserPetsList(){
		return petsL;
	}
	
	public static void setUserPetsList() {
		UserPetsLogic.petsL = new ArrayList<Pet>();
		if( MainView.getUser() == null) {
			return;
		}
		int ownerID = MainView.getUser().getUserId();
		try {
			UserPetsLogic.petsL = UserPetsLogic.petM.getAllPetsByUser(ownerID);
						
		} catch (ErrorInProcessPetOwner e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
		
	}
	
	
	
	
}
