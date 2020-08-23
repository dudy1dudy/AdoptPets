package com.packagename.myapp.ui.nav;

import com.logic.AddPetLogic;
import com.logic.RegisterLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.User;
import group.exception.ErrorInProcessUser;
import group.models.UserModel;
import group.utilities.Category;
import group.utilities.ConvertPhoto;
import group.utilities.PetSize;
import java.nio.file.Files;

import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Route(value = "petadding", layout = MainView.class)

@PageTitle("Add Pet")

public class PetAdding extends VerticalLayout {

	File fi;
	byte[] fileContent;
	AddPetLogic addPetLogic;
	VerticalLayout vl = new VerticalLayout();

	private void isLogin() {
		if (MainView.isUserRegistered()) {
			addPetLogic = new AddPetLogic();
		} else {
			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("Please register to the site, before adding pet for addoption");
			data.add(details);
			vl.add(data);
			return;
		}
	}

	public PetAdding() {

		isLogin();
		H3 title = new H3("Add a new pet");

		HorizontalLayout titlelayout = new HorizontalLayout();
		titlelayout.add(title);
		title.addClassName("titletext");

		FormLayout addLayout = new FormLayout();

		TextField petName = new TextField(); // state
		petName.setPlaceholder("");

		H4 petNameLabel = new H4("Pet name");
		petNameLabel.addClassName("titletext");

		Select<String> category = new Select<>();
		category.setItems("Dog", "Cat", "Fish", "Bird", "Rodent", "Other");
		category.setPlaceholder("Cats");

		H4 categoryLabel = new H4("Category");
		categoryLabel.addClassName("titletext");

		Select<String> size = new Select<>();
		size.setItems("Small", "Medium", "Large", "XLarge");
		size.setPlaceholder("Medium");

		H4 sizeLabel = new H4("Size");
		sizeLabel.addClassName("titletext");

		NumberField age = new NumberField(); // miles
		age.setHasControls(true);

		H4 ageLabel = new H4("Age");
		ageLabel.addClassName("titletext");

		Select<String> color = new Select<>();
		color.setItems("Black", "White");
		color.setPlaceholder("Black");

		H4 colorLabel = new H4("Color");
		colorLabel.addClassName("titletext");

		Select<String> breed = new Select<>();
		breed.setItems("Male", "Famale");
		breed.setPlaceholder("Genger");

		H4 breedLabel = new H4("Genger");
		breedLabel.addClassName("titletext");

		TextField description = new TextField();
		description.setPlaceholder("Description");

		H4 descriptionLabel = new H4("Description");
		descriptionLabel.addClassName("titletext");

		TextArea descriptionL = new TextArea();
		descriptionL.setPlaceholder("Description more ...");

		H4 descriptionLLabel = new H4("Detailed description");
		descriptionLLabel.addClassName("titletext");

		MemoryBuffer buffer = new MemoryBuffer(); // temporary memory
		Upload upload = new Upload(buffer);
		Div output = new Div();

		H4 uploadLabel = new H4("Upload");
		uploadLabel.addClassName("titletext");

		upload.addSucceededListener(event -> {
			try {
				fileContent = IOUtils.toByteArray(buffer.getInputStream());
			} catch (Exception e) {
				new Span("Error in process photo");
			}
		});

		addLayout.addFormItem(petName, petNameLabel);
		addLayout.addFormItem(category, categoryLabel);
		addLayout.addFormItem(size, sizeLabel);
		addLayout.addFormItem(age, ageLabel);
		addLayout.addFormItem(color, colorLabel);
		addLayout.addFormItem(breed, breedLabel);
		addLayout.addFormItem(description, descriptionLabel);
		addLayout.addFormItem(descriptionL, descriptionLLabel);
		addLayout.addFormItem(upload, uploadLabel);

		VerticalLayout form = new VerticalLayout();
		form.setWidth("500px");

		Button add = new Button("Add", click -> parametersCheck(petName, category, size, age, color, breed, description,
				descriptionL, upload));
		// Button add=new Button("Add", click-> addPetLogic.AddPet(
		// user.getCurrentUser().getUserId() ,
		// null , petName.getValue(), age.getValue(), size.getValue(),
		// description.getValue(), descriptionL.getValue(), upload,
		// user.getCurrentUser().getFirstName(), user.getCurrentUser().getLastName()));

		addLayout.add(add);

		form.add(addLayout);

		vl.add(titlelayout, form);

		vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

		add(vl);
	}

	private void parametersCheck(TextField petName, Select<String> category, Select<String> size, NumberField age,
			Select<String> color, Select<String> breed, TextField description, TextArea descriptionL, Upload upload) {

		isLogin();
			
		
		if (petName.isEmpty() || category.isEmpty() || size.isEmpty() || age.isEmpty() || color.isEmpty()
				|| breed.isEmpty() || description.isEmpty() || descriptionL.isEmpty()) {

			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("details are missing, please fill all of the fields");
			data.add(details);
			vl.add(data);
			return;
		} else {
			addPetLogic.AddPet(vl, category.getValue(), petName.getValue(), age.getValue(), size.getValue(),
					breed.getValue(), description.getValue(), descriptionL.getValue(), fileContent);
		}
	}

}
