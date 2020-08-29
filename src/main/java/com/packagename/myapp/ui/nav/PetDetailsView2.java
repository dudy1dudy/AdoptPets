package com.packagename.myapp.ui.nav;

import com.packagename.myapp.ui.MainView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import group.exception.ErrorInProcessPetData;
import group.models.PetModel;
import group.utilities.ConvertPhoto;

@Route(value = "detail", layout = MainView.class)
@PageTitle("Pet details")
public class PetDetailsView2 extends VerticalLayout {

	private PetModel petM = new PetModel();
	private HomeView homeV = new HomeView();
	private VerticalLayout petDetails = new VerticalLayout();
	FormLayout petDetailsForm = new FormLayout();

	public PetDetailsView2() {

		if (MainView.getUser() == null) {
			HorizontalLayout data = new HorizontalLayout();
			Span details = new Span("Please register to view details");
			data.add(details);
			add(data);
			return;
		}

		VerticalLayout vl = new VerticalLayout();
		vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		HorizontalLayout pet = new HorizontalLayout();

		HorizontalLayout title = new HorizontalLayout();
		title.setDefaultVerticalComponentAlignment(Alignment.CENTER);

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
			detail = new Span("DESCRIPTION: /n" + petM.getPetById(id).getShortDescription() + "/n /n"
					+ petM.getPetById(id).getDetailDescription());

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

		divLayout.add(name, detail, ageLine);

		div.add(divLayout);

		pet.add(image, div);

		HorizontalLayout ageLayout=new HorizontalLayout();
		VerticalLayout ageV=new VerticalLayout();
		VerticalLayout catagoryV=new VerticalLayout();
		ageLayout.add(ageV,catagoryV);

		NumberField ageNymb =new NumberField("Age");
		TextField catagoryText=new TextField("Category");
		ageV.add(ageNymb);
		catagoryV.add(catagoryText);
		vl.add(title, pet, ageLayout);
		add(vl);

	}
}
