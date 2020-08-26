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
	private static List<Pet> petsL = new ArrayList<Pet>();

	public static List<Pet> getLikePetsList() {
		return petsL;
	}

	public void likePetList() {
		petsL.clear();
		int userId = MainView.getUser().getUserId();
		try {
			List<Pet> pets = likeM.getAllLikes(userId);
			if (pets != null)
				petsL.addAll(pets);
		} catch (ErrorInProcessUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	public void like(int index) {
			
			try {
				likeM.createNewLike(MainView.getUser().getUserId()
							, HomeLogic.getPetsList().get(index).getPetId());
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
	}

	public void unLike(int index) {
		
			try {
				likeM.deleteLike(MainView.getUser().getUserId(), HomeLogic.getPetsList().get(index).getPetId());
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
		
		
	}

}
