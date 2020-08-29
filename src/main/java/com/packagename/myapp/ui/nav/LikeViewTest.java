package com.packagename.myapp.ui.nav;

import java.util.ArrayList;
import java.util.List;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.html.Image;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import group.entities.Pet;
import group.utilities.AdoptionStatus;
import group.utilities.Gender;

@Route(value="likeviewtest",layout= MainView.class)
@PageTitle("About")

public class LikeViewTest extends VerticalLayout {


    public LikeViewTest() {

        Grid<Pet> grid = new Grid<>(Pet.class);

        List<Pet> petList = new ArrayList<>();

        grid.removeAllColumns();
        grid.addColumn(Pet::getPetId).setHeader("PetId");

        Image pet = new Image("icons/img.jpg", "DummyImage"); //use image from internal server
        pet.setWidth("160px");
        pet.setHeight("140px");
        grid.addComponentColumn(e -> pet).setHeader("Photo");//use image from internal server
        grid.addColumn(Pet::getPetName).setHeader("PetName");
        grid.addColumn(Pet::getPetPhoto).setHeader("Image"); //image from the database
        grid.addComponentColumn(e -> new Button("Delete")).setHeader("Delete"); //add button to row

        petList.add(0, new Pet("name", 1, 2, Gender.MALE, AdoptionStatus.PENDING, "short desc", "long desc", "dsad", "icons/img.jpg"));//dummy data

        grid.setItems(petList);

        add(grid);
    }
}