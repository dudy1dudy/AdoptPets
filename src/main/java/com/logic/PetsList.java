package com.logic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.packagename.myapp.ui.MainView;

import group.entities.Pet;
import group.entities.PetOwner;
import group.utilities.AdoptionStatus;
import group.utilities.Category;
import group.utilities.Gender;
import group.utilities.PetSize;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
public class PetsList  {

	//
    private Category categoryC;
    private Gender genderC;
    private int ageC;
    private PetSize sizeC;
	private String petNameC;
	private String shortDescriptionC;
	private String cityC;
	
	
	
	public PetsList(Pet pet) {
			
		this.ageC = (int) pet.getPetAge();
		this.categoryC = pet.getCategory();
		this.genderC = pet.getGender();
		this.petNameC = pet.getPetName();
		this.shortDescriptionC = pet.getShortDescription();
		this.sizeC = pet.getPetSize();
		this.cityC = pet.getPetOwner().getCity();
	
	}
	
	public PetsList() {
		// TODO Auto-generated constructor stub
	}

	public PetsList addPet(Pet pet) {
		this.ageC = (int) pet.getPetAge();
		this.categoryC = pet.getCategory();
		this.genderC = pet.getGender();
		this.petNameC = pet.getPetName();
		this.shortDescriptionC = pet.getShortDescription();
		this.sizeC = pet.getPetSize();
		this.cityC = pet.getPetOwner().getCity();
		
		
		return this;
		
	}
	
	
	public Category getCategoryC() {
		return categoryC;
	}

	public void setCategoryC(Category categoryC) {
		this.categoryC = categoryC;
	}

	public Gender getGenderC() {
		return genderC;
	}

	public void setGenderC(Gender genderC) {
		this.genderC = genderC;
	}

	public int getAgeC() {
		return ageC;
	}

	public void setAgeC(int ageC) {
		this.ageC = ageC;
	}

	public PetSize getSizeC() {
		return sizeC;
	}

	public void setSizeC(PetSize sizeC) {
		this.sizeC = sizeC;
	}

	
	public String getPetNameC() {
		return petNameC;
	}

	public void setPetNameC(String petNameC) {
		this.petNameC = petNameC;
	}
	public String getShortDescriptionC() {
		return shortDescriptionC;
	}

	public void setShortDescriptionC(String shortDescriptionC) {
		this.shortDescriptionC = shortDescriptionC;
	}

	public String getCityC() {
		return cityC;
	}


	public void setCityC(String cityC) {
		this.cityC = cityC;
	}

	

}
   