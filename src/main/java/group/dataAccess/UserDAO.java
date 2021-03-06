package group.dataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import group.entities.PetOwner;
import group.entities.User;
import group.exception.ErrorInProcessPetOwner;
import group.exception.ErrorInProcessUser;
import group.utilities.AccessToDb;
import group.utilities.UserType;

public class UserDAO {
	public UserDAO() {
	}

	// Create new user
	public User create(User newUser) throws ErrorInProcessUser {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.persist(newUser);

			// Save and close
			AccessToDb.commitFactory();
			return newUser;
		} catch (Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Update user
	public User update(User updateUser) throws ErrorInProcessUser {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.merge(updateUser);

			// Save and close
			AccessToDb.commitFactory();
			return updateUser;
		} catch (Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Delete user
	public void remove(int idUser) throws ErrorInProcessUser {
		try {

			User user = getUser(idUser);

			// access to DB
			EntityManager em = AccessToDb.createFactory();

			if (!em.contains(user)) {
				user = em.merge(user);
			}
			em.remove(user);

			// Save and close
			AccessToDb.commitFactory();
		} catch (Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get user
	public User getUser(int idUser) throws ErrorInProcessUser {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			User user = em.find(User.class, idUser);

			// Close
			return user;
		} catch (Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get User by username and password
	public User findUserByData(String username, String password) throws ErrorInProcessUser {

		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			Query q = em.createNamedQuery("FindUser", User.class).setParameter("username", username)
					.setParameter("password", password);

			// Select User by username and password
			if (q.getResultList().isEmpty() != true) {
				User selectedUser = (User) q.getSingleResult();
				return selectedUser;
			} else
				return null;
		} catch (Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get User by username - for checking if username already exists
	public User checkUserName(String username) throws ErrorInProcessUser {

		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			// Select User by username
			Query q = em.createNamedQuery("CheckUsername", User.class).setParameter("username", username);

			if (q.getResultList().isEmpty() != true) {
				User selectedUser = (User) q.getSingleResult();
				return selectedUser;
			} else
				return null;
		} catch (Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get all pet owners of user
	@SuppressWarnings("unchecked")
	public List<PetOwner> getAllPetOwners(int idUser) throws ErrorInProcessUser {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			// Select Pet owners of user
			Query q = em.createNamedQuery("OwnersByUser", PetOwner.class).setParameter("userId", idUser);

			if (q.getResultList().isEmpty() != true) {
				List<PetOwner> results = (List<PetOwner>) q.getResultList();
				return results;
			} else
				return null;

		} catch (Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get User by username - for checking if user is admin
	public User checkUserAdmin(String username) throws ErrorInProcessUser {

		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			// Select User by username
			Query q = em.createNamedQuery("CheckAdmin", User.class).setParameter("username", username)
					.setParameter("type", UserType.ADMIN);

			if (q.getResultList().isEmpty() != true) {
				User selectedUser = (User) q.getSingleResult();
				return selectedUser;
			} else
				return null;
		} catch (

		Exception e) {
			throw new ErrorInProcessUser("Error in process user data");
		} finally {
			AccessToDb.closeFactory();
		}
	}
}
