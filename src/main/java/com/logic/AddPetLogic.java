package com.logic;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.dom.Element;

import group.entities.Pet;
import group.entities.User;
import group.exception.ErrorInProcessPetData;
import group.exception.ErrorInProcessPetOwner;
import group.exception.ErrorInProcessUser;
import group.models.PetModel;
import group.models.UserModel;
import group.utilities.AdoptionStatus;
import group.utilities.Category;
import group.utilities.Gender;
import group.utilities.PetSize;

public class AddPetLogic {

	
	private UserModel userModel;
	private PetModel pet;
	
	public AddPetLogic() {
		userModel = new UserModel();
		pet = new PetModel();
	}
	
	public void AddPet(VerticalLayout vl, String petCategory, String petName, Double petAge, String petSize, String gender,
			 String shortDescription, String detailDescription,	byte[] petPhoto) {
		Category petC = findPetCategory(petCategory) ;
		PetSize petS = findPetSize(petSize);
		Gender gen = findGender(gender);
		int age = petAge.intValue();
		
		
		
		try {
			if(!MainView.isUserRegistered()) {
				HorizontalLayout data=new HorizontalLayout();
	            Span details=new Span("User field is null");
	            data.add(details);
	            vl.add(data);
	            return;
			}
			
			pet.createNewPet(MainView.getUser().getUserId(), petC, petName, age, petS, gen, AdoptionStatus.ADOPTABLE,
					shortDescription, detailDescription, null, MainView.getUser().getFirstName(), 
					MainView.getUser().getLastName(), MainView.getUser().getPhoneNumber(), MainView.getUser().getCity(), MainView.getUser().getStreet(),
					MainView.getUser().getHouseNumber());
			
		} catch (ErrorInProcessPetOwner | ErrorInProcessUser | ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private Gender findGender(String gender) {
		if(gender.equals("Male")) {
			return Gender.MALE;
		}
		return Gender.FEMALE;
	}
	private PetSize findPetSize(String petSize) {
		if(petSize.equals("Small")) {
			return PetSize.SMALL;
		}
		if(petSize.equals("Medium")) {
			return PetSize.MEDIUM;
		}
		if(petSize.equals("Large")) {
			return PetSize.LARGE;
		}
		if(petSize.equals("XLarge")) {
			return PetSize.XLARGE;
		}
		return null;
	}
	
	private Category findPetCategory(String petCategory) {
		if(petCategory.equals("Bird")) {
			return Category.BIRD;
		}
		if(petCategory.equals("Cat")) {
			return Category.CAT;
		}
		if(petCategory.equals("Dog")) {
			return Category.DOG;
		}
		if(petCategory.equals("Fish")) {
			return Category.FISH;
		}
		if(petCategory.equals("Other")) {
			return Category.OTHER;
		}
		if(petCategory.equals("Rodent")) {
			return Category.RODENT;
		}
		return null;
	}

	
}
