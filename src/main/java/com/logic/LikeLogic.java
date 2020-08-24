package com.logic;

import java.util.ArrayList;
import java.util.List;

import com.packagename.myapp.ui.MainView;
import com.packagename.myapp.ui.nav.HomeView;

import group.entities.Pet;
import group.exception.ErrorInProcessUser;
import group.models.LikeModel;
import group.models.PetModel;

public class LikeLogic {

	private LikeModel likeM = new LikeModel();
	private PetModel petM = new PetModel();
	private static List<Pet> petsL = new ArrayList<Pet>(); 
	
	public static List<Pet> getLikePetsList(){
		return petsL;
	}
	
	public void likePetList() {
		petsL.clear();
		int userId = MainView.getUser().getUserId();
		try {
			petsL.addAll(likeM.getAllLikes(userId));
		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;		
	}
	
	public void like() {
		
	}
	
	
	
}
