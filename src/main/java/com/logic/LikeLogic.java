package com.logic;

import java.util.ArrayList;
import java.util.List;

import com.packagename.myapp.ui.MainView;
import com.packagename.myapp.ui.nav.HomeView;

import group.entities.Pet;
import group.exception.ErrorInProcessPetData;
import group.exception.ErrorInProcessPetLove;
import group.exception.ErrorInProcessUser;
import group.models.LikeModel;
import group.models.PetModel;

public class LikeLogic {

	private LikeModel likeM = new LikeModel();
	private PetModel petM = new PetModel();
	private List<Pet> petsL = new ArrayList<Pet>();

	public  List<Pet> getLikePetsList() {
		likePetList();
		return petsL;
	}
	
	public boolean isRegUesrLikePet(Pet pet) {
		int petId = pet.getPetId();
		List<Pet> userPetsL = new ArrayList<Pet>();
		try {
			
			userPetsL.addAll(likeM.getAllLikes(MainView.getUser().getUserId()));
		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0 ; i < userPetsL.size() ; i++) {
			if(userPetsL.get(i).getPetId() == pet.getPetId()) {
				return true;
			}
		}
		
		return false;
		
	}

	private void likePetList() {
		petsL.clear();
		int userId = MainView.getUser().getUserId();
		try {
			List<Pet> pets = likeM.getAllLikes(userId);
			if (pets != null)
				petsL.addAll(pets);
			return;
		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	public void like(int petId) {
			
			try {
				likeM.createNewLike(MainView.getUser().getUserId() , petId);
				return;
			} catch (ErrorInProcessUser e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ErrorInProcessPetData e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ErrorInProcessPetLove e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
	}

	public void unLike(int petId) {
		
			try {
				likeM.deleteLike(MainView.getUser().getUserId(), petId);
				return;
			} catch (ErrorInProcessPetData e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ErrorInProcessPetLove e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ErrorInProcessUser e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return;
	}

}
