package group.utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.component.html.Image;

import group.entities.Pet;

public class ConvertPhoto {

	public static byte[] convertPhotoToDB(String urlPhoto) throws IOException {

		FileInputStream fis = null;

		try {
			// Create File from URL
			File file = new File(urlPhoto);

			// Get file data
			fis = new FileInputStream(file);
			int fileLength = (int) file.length();

			// Allocate byte array of right size
			byte[] fileData = new byte[fileLength];

			// read into byte array
			fis.read(fileData, 0, fileLength);

			return fileData;
		} catch (IOException e) {
			throw new IOException("Unable to convert photo to byte array");
		} finally {
			if (fis != null)
				fis.close();
		}
	}

	// Convert bytes to photo for screen
	public static Image dbPhotoToImage(Pet pet) {

		Image image = new Image("icons/no-image-available.jpg", "DummyImage");

		if (pet != null) {
			if (pet.getPetPhoto() != null) {

				// Get stream data of photo
				StreamResource sr = new StreamResource("pet", () -> {
					return new ByteArrayInputStream(pet.getPetPhoto());
				});
				
				sr.setContentType("image/jpg");
				image = new Image(sr, "Pet-image");
			}
		}
		return image;
	}

}
