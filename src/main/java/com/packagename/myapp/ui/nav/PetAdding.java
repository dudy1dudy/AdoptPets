package com.packagename.myapp.ui.nav;

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


@Route(value="petadding",layout= MainView.class)

@PageTitle("Add Pet")

public class PetAdding extends VerticalLayout{

    VerticalLayout vl=new VerticalLayout();
    public PetAdding(){
        H3 title=new H3("Add a new pet");

        HorizontalLayout titlelayout=new HorizontalLayout();
        titlelayout.add(title);
        title.addClassName("titletext");

        FormLayout addLayout = new FormLayout();


        TextField petName = new TextField(); //state
        petName.setPlaceholder("inoue");

        H4 petNameLabel=new H4("Pet name");
        petNameLabel.addClassName("titletext");

        Select<String> category = new Select<>();
        category.setItems("Dogs","Cats", "Reptiles","Birds","Rabbit");
        category.setPlaceholder("Cats");

        H4 categoryLabel=new H4("Category");
        categoryLabel.addClassName("titletext");

        Select<String> size = new Select<>();
        size.setItems("Small","Medium");
        size.setPlaceholder("Medium");

        H4 sizeLabel=new H4("Size");
        sizeLabel.addClassName("titletext");

        NumberField age = new NumberField(); //miles
        age.setHasControls(true);

        H4 ageLabel=new H4("Age");
        ageLabel.addClassName("titletext");

        Select<String> color = new Select<>();
        color.setItems("Black","White");
        color.setPlaceholder("Black");

        H4 colorLabel=new H4("Color");
        colorLabel.addClassName("titletext");

        Select<String> breed = new Select<>();
        breed.setItems("breed1","breed2");
        breed.setPlaceholder("breed1");

        H4 breedLabel=new H4("Breed");
        breedLabel.addClassName("titletext");

        TextField description = new TextField();
        description.setPlaceholder("Description");

        H4 descriptionLabel=new H4("Description");
        descriptionLabel.addClassName("titletext");

        TextArea descriptionL = new TextArea();
        descriptionL.setPlaceholder("Description more ...");

        H4 descriptionLLabel=new H4("Detailed description");
        descriptionLLabel.addClassName("titletext");

        MemoryBuffer buffer = new MemoryBuffer(); //temporary memory
        Upload upload = new Upload(buffer);
        Div output = new Div();

        H4 uploadLabel=new H4("Upload");
        uploadLabel.addClassName("titletext");

        upload.addSucceededListener(event -> {

        });

        addLayout.addFormItem(petName,petNameLabel);
        addLayout.addFormItem(category,categoryLabel);
        addLayout.addFormItem(size,sizeLabel);
        addLayout.addFormItem(age,ageLabel);
        addLayout.addFormItem(color,colorLabel);
        addLayout.addFormItem(breed,breedLabel);
        addLayout.addFormItem(description,descriptionLabel);
        addLayout.addFormItem(descriptionL,descriptionLLabel);
        addLayout.addFormItem(upload,uploadLabel);

        VerticalLayout form=new VerticalLayout();
        form.setWidth("500px");

        Button add=new Button("Add", click->
        {
            HorizontalLayout data=new HorizontalLayout();
            Span details=new Span(petName.getValue());
            data.add(details);
            vl.add(data);
        });

        addLayout.add(add);

        form.add(addLayout);

        vl.add(titlelayout,form);

        vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(vl);
    }
}

