package com.packagename.myapp.ui.nav;

import static com.vaadin.flow.server.VaadinSession.getCurrent;
import com.logic.AddPetLogic;
import com.logic.RegisterLogic;
import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.internal.CurrentInstance;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import group.entities.Pet;
import group.entities.User;
import group.exception.ErrorInProcessPetData;
import group.exception.ErrorInProcessUser;
import group.models.UserModel;
import group.utilities.Category;
import group.utilities.ConvertPhoto;
import group.utilities.PetSize;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.apache.commons.compress.utils.IOUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Route(value = "EditPet", layout = MainView.class)

@PageTitle("Edit Pet")

public class PetEditView extends VerticalLayout {

	File fi;
	byte[] fileContent;
	byte[] currPhoto;
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

	public PetEditView() {

		isLogin();
		H3 title = new H3("Edit your pet");

		HorizontalLayout titlelayout = new HorizontalLayout();
		titlelayout.add(title);
		title.addClassName("titletext");

		FormLayout addLayout = new FormLayout();

		TextField petName = new TextField(); // state
		petName.setPlaceholder("Set Pet Name");

		H4 petNameLabel = new H4("Pet name");
		petNameLabel.addClassName("titletext");

		Select<String> category = new Select<>();
		category.setItems("Dog", "Cat", "Fish", "Bird", "Rodent", "Other");
		category.setPlaceholder("Choose Category");

		H4 categoryLabel = new H4("Category");
		categoryLabel.addClassName("titletext");

		Select<String> size = new Select<>();
		size.setItems("Small", "Medium", "Large", "XLarge");
		size.setPlaceholder("Choose Size");

		H4 sizeLabel = new H4("Size");
		sizeLabel.addClassName("titletext");

		NumberField age = new NumberField(); // miles
		age.setHasControls(true);
		age.setMin(0);
		H4 ageLabel = new H4("Age");
		ageLabel.addClassName("titletext");

		Select<String> breed = new Select<>();
		breed.setItems("Male", "Famale");
		breed.setPlaceholder("Choose Gender");

		H4 breedLabel = new H4("Genger");
		breedLabel.addClassName("titletext");

		TextField description = new TextField();
		description.setMaxLength(50);
		description.setPlaceholder("Description");

		H4 descriptionLabel = new H4("Description");
		
		descriptionLabel.addClassName("titletext");

		TextArea descriptionL = new TextArea();
		descriptionL.setMaxLength(255);
		descriptionL.setPlaceholder("Description more ...");

		H4 descriptionLLabel = new H4("Detailed description");
		descriptionLLabel.addClassName("titletext");

		MemoryBuffer buffer = new MemoryBuffer(); // temporary memory
		Upload upload = new Upload(buffer);

		H4 uploadLabel = new H4("Upload Photo");
		uploadLabel.addClassName("titletext");

		upload.addSucceededListener(event -> {
			try {
				BufferedImage inputImage = ImageIO.read(buffer.getInputStream());
				ByteArrayOutputStream pngContent = new ByteArrayOutputStream();
				ImageIO.write(inputImage, "jpg", pngContent);
				fileContent = pngContent.toByteArray();
			} catch (Exception e) {
				new Span("Error in process photo");
			}
		});

		Select<String> aduptStatus = new Select<>();
		aduptStatus.setItems("ADOPTABLE", "PENDING", "ADOPTED");
		aduptStatus.setPlaceholder("Choose Status");

		H4 aduptStatusLabel = new H4("Genger");
		aduptStatusLabel.addClassName("titletext");

		VerticalLayout form = new VerticalLayout();
		form.setWidth("500px");

		// Data for Owner
		FormLayout ownerLayout = new FormLayout();

		TextField firstName = new TextField();
		firstName.setPlaceholder("First Name");
		H4 firstNameLabel = new H4("First name");
		firstNameLabel.addClassName("titletext");

		TextField lastName = new TextField();
		lastName.setPlaceholder("Last Name");
		H4 lastNameLabel = new H4("Last name");
		lastNameLabel.addClassName("titletext");

		NumberField phone = new NumberField();
		phone.setPlaceholder("Phone Number");
		H4 phoneLabel = new H4("Phone number");
		phoneLabel.addClassName("titletext");

		TextField city = new TextField();
		city.setPlaceholder("city");
		H4 citiLabel = new H4("CIty");
		citiLabel.addClassName("titletext");

		TextField street = new TextField();
		street.setPlaceholder("Street name");
		H4 streetLabel = new H4("Street");
		streetLabel.addClassName("titletext");

		NumberField house = new NumberField();
		house.setPlaceholder("sNo");
		H4 houseLabel = new H4("House Number");
		houseLabel.addClassName("titletext");

		// Get login user
		User loginUser = MainView.getUser();

		if (loginUser != null) {
			firstName.setValue(loginUser.getFirstName());
			lastName.setValue(loginUser.getLastName());
			phone.setValue(Double.valueOf(loginUser.getPhoneNumber()));
			city.setValue(loginUser.getCity());
			street.setValue(loginUser.getStreet());
			house.setValue(Double.valueOf(loginUser.getHouseNumber()));
		}

		ownerLayout.addFormItem(firstName, firstNameLabel);
		ownerLayout.addFormItem(lastName, lastNameLabel);
		ownerLayout.addFormItem(phone, phoneLabel);
		ownerLayout.addFormItem(city, citiLabel);
		ownerLayout.addFormItem(street, streetLabel);
		ownerLayout.addFormItem(house, houseLabel);

		// Get pet
		Pet pet = MainView.getCurrentEditPet();

		if (pet != null) {
			petName.setValue(pet.getPetName());
			category.setLabel(pet.getCategory().name());
			size.setLabel(pet.getPetSize().name());
			age.setValue(pet.getPetAge());
			breed.setLabel(pet.getGender().name());
			description.setValue(pet.getShortDescription());
			descriptionL.setValue(pet.getDetailDescription());
			aduptStatus.setLabel(pet.getAdoptionStatus().name());
			currPhoto = pet.getPetPhoto();
			Image image = new Image();

			image = ConvertPhoto.dbPhotoToImage(pet);

			image.addClassName("image");
			image.setWidth("200px");
			image.setHeight("200px");
			titlelayout.add(image);
			
		}

		addLayout.addFormItem(petName, petNameLabel);
		addLayout.addFormItem(category, categoryLabel);
		addLayout.addFormItem(size, sizeLabel);
		addLayout.addFormItem(age, ageLabel);
		addLayout.addFormItem(breed, breedLabel);
		addLayout.addFormItem(description, descriptionLabel);
		addLayout.addFormItem(descriptionL, descriptionLLabel);
		addLayout.addFormItem(aduptStatus, aduptStatusLabel);
		addLayout.addFormItem(upload, uploadLabel);

		VerticalLayout form2 = new VerticalLayout();
		form2.setWidth("500px");

		Button add = new Button("Edit", click -> edit(pet.getPetId(), petName, category, size, age, breed, description,
				descriptionL, upload, firstName, lastName, phone, city, street, house, aduptStatus));

		Button delete = new Button("Delete pet", click -> delete(pet.getPetId(), petName, category, size, age, breed, description,
				descriptionL, upload, firstName, lastName, phone, city, street, house, aduptStatus));
		
		form.add(addLayout);
		form2.add(ownerLayout);

		HorizontalLayout horizontal = new HorizontalLayout();
		horizontal.add(form, form2);

		vl.add(titlelayout, horizontal, add, delete);

		vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

		add(vl);
	}

	private void delete(int petId, TextField petName, Select<String> category, Select<String> size, NumberField age,
			Select<String> breed, TextField description, TextArea descriptionL, Upload upload, TextField firstName,
			TextField lastName, NumberField phone, TextField city, TextField street, NumberField house,
			Select<String> aduptStatus) {
		isLogin();
		addPetLogic.deletePet(petId);
		
		// Notification
		Notification.show("Pet was deleted")  
			.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
		UI.getCurrent().navigate("");
	}
	
	private void edit(int petId, TextField petName, Select<String> category, Select<String> size, NumberField age,
			Select<String> breed, TextField description, TextArea descriptionL, Upload upload, TextField firstName,
			TextField lastName, NumberField phone, TextField city, TextField street, NumberField house,
			Select<String> aduptStatus) {

		isLogin();

		if (petName.isEmpty() || category.isEmpty() || size.isEmpty() || age.isEmpty() || breed.isEmpty()
				|| description.isEmpty() || descriptionL.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
				|| phone.isEmpty() || city.isEmpty() || street.isEmpty() || house.isEmpty() || aduptStatus.isEmpty()) {

			// Notification
			Notification.show("Details are missing, please fill all of the fields")
					.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
			return;
		} else {

			if (fileContent == null)
				fileContent = currPhoto;

			addPetLogic.editPet(petId, category.getValue(), petName.getValue(), age.getValue(), size.getValue(),
					breed.getValue(), description.getValue(), descriptionL.getValue(), fileContent,
					firstName.getValue(), lastName.getValue(), phone.getValue().intValue(), city.getValue(),
					street.getValue(), house.getValue().intValue(), aduptStatus.getValue());
		}

		// Notification
		Notification.show("Pet was updated")  
				.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER);
		UI.getCurrent().navigate("");
	}

}
