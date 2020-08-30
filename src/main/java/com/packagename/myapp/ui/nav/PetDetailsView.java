package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import group.entities.User;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.ConvertPhoto;

@Route(value = "detail", layout = MainView.class)
@PageTitle("Pet details")
public class PetDetailsView extends VerticalLayout {

	private PetModel petM = new PetModel();
	private HomeView homeV = new HomeView();
	private VerticalLayout petDetails = new VerticalLayout();
	FormLayout petDetailsForm = new FormLayout();

	public PetDetailsView() {

		if (MainView.getUser() == null) {
			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("Please register to view details");
			data.add(details);
			add(data);
			return;
		}
		setClassName("petAdding");
		H3 title = new H3("Pet Details");

		VerticalLayout vl = new VerticalLayout();
		vl.setHorizontalComponentAlignment(Alignment.CENTER);
		vl.setClassName("petAddingForm");
		vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		HorizontalLayout pet = new HorizontalLayout();

//		HorizontalLayout title = new HorizontalLayout();
//		title.setDefaultVerticalComponentAlignment(Alignment.CENTER);

		Div div = new Div();
		div.setWidth("400px");
		// div.setHeight("20rem");

		VerticalLayout divLayout = new VerticalLayout();
		H3 name = null;
		Span detail = null;
		int id = MainView.getCurrDeatailPet().getPetId();

		Image image = new Image();

		try {
			image = ConvertPhoto.dbPhotoToImage(petM.getPetById(id));
		} catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image.addClassName("image");
		image.setWidth("200px");
		image.setHeight("200px");

		try {
			name = new H3("My name is " + petM.getPetById(id).getPetName());
//			detail = new Span("DESCRIPTION: /n" + petM.getPetById(id).getShortDescription() + "/n /n"
//					+ petM.getPetById(id).getDetailDescription());

		} catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HorizontalLayout ageLine = new HorizontalLayout();
		VerticalLayout age = new VerticalLayout();
		VerticalLayout gender = new VerticalLayout();

		try {
			age.add(petM.getPetById(id).toString());
			gender.add(petM.getPetById(id).getPetOwner().toString());
		} catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		H4 petAgeLabel = new H4("Pet Age");
		petAgeLabel.addClassName("titletext");

		ageLine.add(age, gender);

		divLayout.add(name);

		div.add(divLayout);
		pet.setAlignItems(Alignment.CENTER);
		pet.add(image, div);

		HorizontalLayout petDetailsLayoutR1 = new HorizontalLayout();
		HorizontalLayout petDetailsLayoutR2 = new HorizontalLayout();

		petDetailsLayoutR1.setWidth("350px");
		petDetailsLayoutR2.setWidth("350px");

		// Row 1
		VerticalLayout ageLayout = new VerticalLayout();
		VerticalLayout categoryLayout = new VerticalLayout();

		ageLayout.setHeight("60px");
		categoryLayout.setHeight("60px");

		// Row 2
		VerticalLayout genderLayout = new VerticalLayout();
		VerticalLayout cityLayout = new VerticalLayout();

		genderLayout.setHeight("60px");
		cityLayout.setHeight("60px");

		petDetailsLayoutR1.add(ageLayout, categoryLayout);
		petDetailsLayoutR2.add(genderLayout, cityLayout);

		// Row 1 data
		NumberField ageF = new NumberField("Age");
		ageF.setClassName("detailsData");
		TextField sizeF = new TextField("Size");
		sizeF.setClassName("detailsData");

		// Row 2 data
		TextField genderF = new TextField("Gender");
		genderF.setClassName("detailsData");
		TextField categoryF = new TextField("Category");
		categoryF.setClassName("detailsData");

		ageF.setReadOnly(true);
		ageF.setWidth("150px");
		sizeF.setReadOnly(true);
		sizeF.setWidth("150px");
		genderF.setReadOnly(true);
		genderF.setWidth("150px");
		categoryF.setReadOnly(true);
		categoryF.setWidth("150px");

		ageLayout.add(ageF);
		categoryLayout.add(sizeF);
		genderLayout.add(genderF);
		cityLayout.add(categoryF);

		VerticalLayout petDetailsLayoutR3 = new VerticalLayout();
		petDetailsLayoutR3.setAlignItems(Alignment.CENTER);
		TextArea description = new TextArea("Description");
		description.setClassName("detailsData");
		description.setReadOnly(true);
		description.setHeight("200px");
		description.setWidth("600px");
		petDetailsLayoutR3.add(description);

		HorizontalLayout details = new HorizontalLayout();
		details.add(petDetailsLayoutR1, petDetailsLayoutR2);

		vl.add(title, pet, details, petDetailsLayoutR3);

		// Data for Owner
		VerticalLayout owner = new VerticalLayout();
		owner.setHorizontalComponentAlignment(Alignment.STRETCH);
		owner.setClassName("petAddingForm");
//		owner.setDefaultHorizontalComponentAlignment(alignment);
		H3 titleOwner = new H3("Owner Details");
		owner.setWidth("750px");
		
		TextField firstName = new TextField("First name");
		firstName.setReadOnly(true);
		firstName.setClassName("detailsData");
		TextField lastName = new TextField("Last Name");
		lastName.setReadOnly(true);
		lastName.setClassName("detailsData");
		NumberField phone = new NumberField("Phone Number");
		phone.setReadOnly(true);
		phone.setClassName("detailsData");
		TextField city = new TextField("City");
		city.setReadOnly(true);
		city.setClassName("detailsData");
		TextField street = new TextField("Street name");
		street.setReadOnly(true);
		street.setClassName("detailsData");
		NumberField house = new NumberField("House Number");
		house.setReadOnly(true);
		house.setClassName("detailsData");
		owner.add(titleOwner, firstName, lastName, phone, city, street, house);

		try {
			firstName.setValue(petM.getPetById(id).getPetOwner().getFirstName());
			lastName.setValue(petM.getPetById(id).getPetOwner().getLastName());
			phone.setValue(Double.valueOf(petM.getPetById(id).getPetOwner().getPhoneNumber()));
			city.setValue(petM.getPetById(id).getPetOwner().getCity());
			street.setValue(petM.getPetById(id).getPetOwner().getStreet());
			house.setValue(Double.valueOf(petM.getPetById(id).getPetOwner().getHouseNumber()));
		} catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ageF.setValue(petM.getPetById(id).getPetAge());
			sizeF.setValue(petM.getPetById(id).getPetSize().toString());
			genderF.setValue(petM.getPetById(id).getGender().toString());
			categoryF.setValue(petM.getPetById(id).getCategory().toString());
			description.setValue(petM.getPetById(id).getDetailDescription());
		} catch (ErrorInProcessPetData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HorizontalLayout vAll = new HorizontalLayout();
		vAll.add(vl, owner);
		add(vAll);

	}
}
