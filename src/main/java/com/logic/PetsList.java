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


    private Category categoryC;
    private Gender genderC;
    private int ageC;
    private PetSize sizeC;
	private String petNameC;
	private String shortDescriptionC;
	private String cityC;
	private Collection<PetsList> petsList= new ArrayList<PetsList>();
	
	
	public PetsList() {
		petsList.clear();
		
		for(int i = 0 ; i < HomeLogic.getPetsList().size() ; i++) {
			
			this.ageC = (int) HomeLogic.getPetsList().get(i).getPetAge();
			this.categoryC = HomeLogic.getPetsList().get(i).getCategory();
			this.genderC = HomeLogic.getPetsList().get(i).getGender();
			this.petNameC = HomeLogic.getPetsList().get(i).getPetName();
			this.shortDescriptionC = HomeLogic.getPetsList().get(i).getShortDescription();
			this.sizeC = HomeLogic.getPetsList().get(i).getPetSize();
			this.cityC = HomeLogic.getPetsList().get(i).getPetOwner().getCity();
			petsList.add(this);
		}
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

	public Collection<PetsList> getPetsList() {
		return petsList;
	}

	public void setPetsList(Collection<PetsList> petsList) {
		this.petsList = petsList;
	}


	public String getCityC() {
		return cityC;
	}


	public void setCityC(String cityC) {
		this.cityC = cityC;
	}

	
    

}
   