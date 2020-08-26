package com.logic;

import java.util.ArrayList;
import java.util.Collection;

import com.packagename.myapp.ui.MainView;
import com.packagename.myapp.ui.nav.HomeView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.RouterLayout;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import group.entities.Pet;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.Category;
import group.utilities.Gender;
import group.utilities.PetSize;

public class PetSearchLogic implements RouterLayout {
	
	private PetModel petM = new PetModel();
	private ArrayList<Pet> tempPets = new ArrayList<Pet>();
		
	private static List<Pet> petsSearch = new ArrayList<Pet>();

	public static List<Pet> getPetsSearchList(){
		return PetSearchLogic.petsSearch;
	}

	public static void setPetsList(List<Pet> allPets) {
		PetSearchLogic.petsSearch.clear();
		PetSearchLogic.petsSearch.addAll(allPets);
	}
	
	
	public void parametersCheck(VerticalLayout vl, Button search, Checkbox dogs, Checkbox cats, Checkbox rodent, 
			Checkbox birds, Checkbox fish, Checkbox other, Checkbox all, 
			Select<String> gender, Select<String> age, Select<String> size) {
		
		PetSearchLogic.petsSearch.clear();
		tempPets.clear();
		checkCategory(dogs, cats, rodent, birds, fish, other, all);
		checkGender(gender);
		checkAge(age);
		checkSize(size);
		
		
////		if(PetSearchLogic.petsSearch == null || PetSearchLogic.petsSearch.isEmpty()) {
////			search.getUI().get().navigate("petSearch");
////			
////            Span details=new Span("No pets available");
////            vl.add(details);
////            return;
////		}
//		
//		
//		if(PetSearchLogic.petsSearch != null && !PetSearchLogic.petsSearch.isEmpty()) {
//			search.getUI().get().navigate("petSearch");
//			return;
//		}
		return;
	}
	
private void checkSize(Select<String> size) {
		
		if(size.getValue() == null || size.getValue().equals("All")) {
			return;
		}
		for(int i = 0 ; i < tempPets.size() ; i ++) {
			if(size.getValue().equals("Small") && !tempPets.get(i).getGender().equals(PetSize.SMALL)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
			if(size.getValue().equals("Medium") && !tempPets.get(i).getGender().equals(PetSize.MEDIUM)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
			if(size.getValue().equals("Large") && !tempPets.get(i).getGender().equals(PetSize.LARGE)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
			if(size.getValue().equals("XLarge") && !tempPets.get(i).getGender().equals(PetSize.XLARGE)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
		}
		tempPets.clear();
		tempPets.addAll(PetSearchLogic.petsSearch);
		return;
	}

	private void checkAge(Select<String> age) {
		
		if(age.getValue() == null || age.getValue().equals("All")) {
			return;
		}
		for(int i = 0; i < tempPets.size() ; i++) {
			if(age.getValue().equals("0 - 1") && 
					!(tempPets.get(i).getPetAge() == 0.0 || tempPets.get(i).getPetAge() == 1.0)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
			if(age.getValue().equals("2 - 3") && 
					!(tempPets.get(i).getPetAge() == 2.0 || tempPets.get(i).getPetAge() == 3.0)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
			if(age.getValue().equals("4 +") && 
					(tempPets.get(i).getPetAge() < 4.0)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
			
		}
		tempPets.clear();
		tempPets.addAll(PetSearchLogic.petsSearch);
		return;
		
		
		
	}



	private void checkGender(Select<String> gender) {
		
		if(gender.getValue() == null || gender.getValue().equals("All")) {
			return;
		}
		for(int i = 0 ; i < tempPets.size() ; i++) {
			if(gender.getValue().equals("Male") && !tempPets.get(i).getGender().equals(Gender.MALE)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
			if(gender.getValue().equals("Female") && !tempPets.get(i).getGender().equals(Gender.FEMALE)) {
				PetSearchLogic.petsSearch.remove(tempPets.get(i));
				
			}
		}
		tempPets.clear();
		tempPets.addAll(PetSearchLogic.petsSearch);
		return;
	}

	private void checkCategory(Checkbox dogs, Checkbox cats, Checkbox rodent, Checkbox birds, 
			Checkbox fish, Checkbox other, Checkbox all) {
		try {
			tempPets.addAll(petM.getAllPets());
		} catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(all.getValue() || (!dogs.getValue() && !cats.getValue() && !fish.getValue() &&
				!birds.getValue() && !rodent.getValue() && !other.getValue())) {
			PetSearchLogic.petsSearch.addAll(tempPets);
			return;
		}
	
		for(int i=0; i<tempPets.size() ; i++) {
			if(dogs.getValue() && tempPets.get(i).getCategory().equals(Category.DOG)) {
				PetSearchLogic.petsSearch.add(tempPets.get(i));
				
			}
			if(cats.getValue() && tempPets.get(i).getCategory().equals(Category.CAT)) {
				PetSearchLogic.petsSearch.add(tempPets.get(i));
				
			}
			if(fish.getValue() && tempPets.get(i).getCategory().equals(Category.FISH)) {
				PetSearchLogic.petsSearch.add(tempPets.get(i));
				
			}
			if(birds.getValue() && tempPets.get(i).getCategory().equals(Category.BIRD)) {
				PetSearchLogic.petsSearch.add(tempPets.get(i));
				
			}
			if(rodent.getValue() && tempPets.get(i).getCategory().equals(Category.RODENT)) {
				PetSearchLogic.petsSearch.add(tempPets.get(i));
				
			}
			if(other.getValue() && tempPets.get(i).getCategory().equals(Category.OTHER)) {
				PetSearchLogic.petsSearch.add(tempPets.get(i));
				
			}
		}
		
		tempPets.clear();
		tempPets.addAll(PetSearchLogic.petsSearch);
		return;
		
	}
	
	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}