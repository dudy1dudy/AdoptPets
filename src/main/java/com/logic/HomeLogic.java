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

public class HomeLogic implements RouterLayout{
	
	private PetModel petM = new PetModel();
	private ArrayList<Pet> tempPets = new ArrayList<Pet>();
	
	
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private Gender genderF;
	private ArrayList<Integer> ageF = new ArrayList<Integer>();
	private PetSize sizeF;
	
	
	private static List<Pet> pets = new ArrayList<Pet>();
	
	
	
	public static List<Pet> getPetsList(){
		return HomeLogic.pets;
	}
	
	public static void setPetsList(List<Pet> allPets) {
		HomeLogic.pets.clear();
		HomeLogic.pets.addAll(allPets);
	}
	
	
	

	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
