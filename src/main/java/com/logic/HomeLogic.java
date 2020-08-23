package com.logic;

import java.util.ArrayList;

import com.packagename.myapp.ui.MainView;
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
import group.entities.Pet;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.Category;
import group.utilities.Gender;
import group.utilities.PetSize;

public class HomeLogic implements RouterLayout{
	
	private PetModel petM = new PetModel();
	 
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private Gender genderF;
	private ArrayList<Integer> ageF = new ArrayList<Integer>();
	private PetSize sizeF;
	private static List<Pet> pets = new ArrayList<Pet>();
	
	public static List<Pet> getPetsList(){
		return pets;
	}
	
	public void parametersCheck(VerticalLayout vl, Button search, Checkbox dogs, Checkbox cats, Checkbox rodent, 
			Checkbox birds, Checkbox fish, Checkbox other, Checkbox all, 
			Select<String> gender, Select<String> age, Select<String> size) {
		
		
		checkCategory(dogs, cats, rodent, birds, fish, other, all);
		checkGender(gender);
		checkAge(age);
		checkSize(size);
		try {
			pets.addAll(petM.getAllPets());
		} catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pets == null || pets.isEmpty()) {
			search.getUI().get().navigate("about");
			HorizontalLayout data=new HorizontalLayout();
            Span details=new Span("No pets available");
            data.add(details);
            vl.add(data);
		}
		
		createPetsList();
		if(pets != null && !pets.isEmpty()) {
			search.getUI().get().navigate("about");
		}
	}

	private void createPetsList() {
		Boolean categoryB = false;
		Boolean ageB = false;
		ArrayList<Pet> tempPets = new ArrayList<Pet>();
		tempPets.addAll(pets);
		for(int i = 0 ; i < tempPets.size() ; i++) {
			
			if(tempPets.get(i).getCategory() != null) {
				if(!categoryList.isEmpty()) {
					for(int j = 0; j < categoryList.size() ; j++) {
						
						if(tempPets.get(i).getCategory().equals(categoryList.get(j))) {
							categoryB = true;
							break;
						}
					}
					if(!categoryB) {
						pets.remove(tempPets.get(i));
						continue;
					}
				}
			}
			
			if(tempPets.get(i).getGender() != null) {
				if(genderF != null) {
					
					if(!genderF.equals(tempPets.get(i).getGender())) {
							pets.remove(tempPets.get(i));
							continue;
						}
					
				}
			}
			
			if(!ageF.isEmpty()) {
				
				for(int j = 0; j < ageF.size() ; j++) {
					if(ageF.get(j) == 4 && tempPets.get(i).getPetAge() >= 4) {
						ageB = true;
						break;
					}
					if(tempPets.get(i).getPetAge() == ageF.get(j)) {
						ageB = true;
						break;
					}
				}
				if(!ageB) {
					pets.remove(tempPets.get(i));
					continue;
				}
			}
			
			
			if(tempPets.get(i).getPetSize() != null) {
				if(sizeF != null) {
					if(!sizeF.equals(tempPets.get(i).getPetSize())) {
							pets.remove(tempPets.get(i));
							continue;
					}
				}
			}
			
		}
		return;
	}

	private void checkSize(Select<String> size) {
		if (size == null || size.isEmpty() || size.getValue().contentEquals("All")) {
			return;
		}
		if(size.getValue().equals("Small"))
			sizeF = PetSize.SMALL;
		if(size.getValue().equals("Medium"))
			sizeF = PetSize.MEDIUM;
		if(size.getValue().equals("Large"))
			sizeF = PetSize.LARGE;
		if(size.getValue().equals("XLarge"))
			sizeF = PetSize.XLARGE;
		return;
	}
	private void checkAge(Select<String> age) {
		if(age == null || age.isEmpty() || age.getValue().equals("All")) {
			return;
		}
		if(age.getValue().equals("0 - 1")) {
			ageF.add(0);
			ageF.add(1);
		}if(age.getValue().equals("2 - 3")) {
			ageF.add(2);
			ageF.add(3);
		}
		if(age.getValue().equals("4+")) {
			ageF.add(4);
		}
		return;
	}
	private void checkGender(Select<String> gender) {
		if(gender == null || gender.isEmpty() || gender.getValue().equals("All")) {
			return;
		}
		
		if(gender.getValue().equals("Female"))
			genderF = Gender.FEMALE;
		
		if(gender.getValue().equals("Male"))
			genderF = Gender.MALE;
		return;
	}
	
	private void checkCategory(Checkbox dogs, Checkbox cats, Checkbox rodent, 
			Checkbox birds, Checkbox fish, Checkbox other, Checkbox all) {
		if(all.getValue()) {
			return;
		}
		if(dogs.getValue())
			categoryList.add(Category.DOG);
		if(cats.getValue())
			categoryList.add(Category.CAT);
		if(rodent.getValue())
			categoryList.add(Category.RODENT);
		if(birds.getValue())
			categoryList.add(Category.BIRD);
		if(fish.getValue())
			categoryList.add(Category.FISH);
		if(other.getValue())
			categoryList.add(Category.OTHER);
	}

	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
