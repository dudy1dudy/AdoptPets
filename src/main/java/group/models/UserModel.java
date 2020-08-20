package group.models;

import group.dataAccess.UserDAO;
import group.entities.User;
import group.exception.ErrorInProcessUser;
import group.utilities.UserType;

// Class that process logic for create/update/delete user data

public class UserModel {

	// Access to User table in DB
	private UserDAO userAccess = new UserDAO();

	public User createNewUser(String username, String password, String firstName, String lastName, UserType userType,
			String email, int userPhoneNumber, String userCity, String userStreet, int userHouseNumber)
			throws ErrorInProcessUser {

		// Create new user
		User newUser = new User();

		// Set data of this user
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setType(userType);
		newUser.setEmail(email);
		newUser.setPhoneNumber(userPhoneNumber);
		newUser.setCity(userCity);
		newUser.setStreet(userStreet);
		newUser.setHouseNumber(userHouseNumber);

		// Save new User to DB
		try {
			this.userAccess.create(newUser);

			return newUser;
		} catch (ErrorInProcessUser e) {
			throw e;
		}
	}

	public User updateUser(String username, String password, String firstName, String lastName, String email,
			int userPhoneNumber, String userCity, String userStreet, int userHouseNumber) throws ErrorInProcessUser {

		// Get needed user
		User currentUser = this.userAccess.findUserByData(username, password);

		// Check current user is set - otherwise can't be update
		if (currentUser != null) {

			// Set updated data to current user
			currentUser.setUsername(username);
			currentUser.setPassword(password);
			currentUser.setFirstName(firstName);
			currentUser.setLastName(lastName);
			currentUser.setEmail(email);
			currentUser.setPhoneNumber(userPhoneNumber);
			currentUser.setCity(userCity);
			currentUser.setStreet(userStreet);
			currentUser.setHouseNumber(userHouseNumber);

			// Save updated User to DB
			try {
				this.userAccess.update(currentUser);
				return currentUser;
			} catch (ErrorInProcessUser e) {
				throw e;
			}
		}
		return null;
	}

	public void deleteUser(int idUser) throws ErrorInProcessUser {

		// Get needed user
		User currentUser = this.userAccess.getUser(idUser);

		// Check current user is set - otherwise can't be delete
		if (currentUser != null) {

			// Save updated User to DB
			try {
				this.userAccess.remove(currentUser.getUserId());
			} catch (ErrorInProcessUser e) {
				throw e;
			}
		}
	}

	// Find user for access application
	public User findUser(String username, String password) throws ErrorInProcessUser {

		// Find user in DB
		try {
			User currentUser = this.userAccess.findUserByData(username, password);

			// Set found user
			return currentUser;
		} catch (ErrorInProcessUser e) {
			throw e;
		}
	}

	// Check if username already exist
	public boolean checkUsernameExists(String username) throws ErrorInProcessUser {

		// Find user in DB
		try {
			User foundUser = this.userAccess.checkUserName(username);

			if (foundUser != null)
				return true;
			else
				return false;
		} catch (ErrorInProcessUser e) {
			throw e;
		}
	}

	// Check if username is admin
	public boolean checkUserAdmin(String username) throws ErrorInProcessUser {

		// Find user in DB
		try {
			User foundUser = this.userAccess.checkUserAdmin(username);

			if (foundUser != null)
				return true;
			else
				return false;
		} catch (ErrorInProcessUser e) {
			throw e;
		}
	}
}
