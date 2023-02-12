package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Cars;

/**
 * Jonathan Argueta-Herrera - jiarguetaherrera CIS175 Spring 2023 Feb 6, 2023
 */

public class CarsHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Cars");

	public void insertCar(Cars li) {
		EntityManager em = emfactory.createEntityManager();

		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteCar(Cars toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Cars> typedQuery = em.createQuery(
				"select li from Cars li where li.make = :selectedMake and li.model = :selectedModel and li.transmission = :selectedTransmission",
				Cars.class);

		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setParameter("selectedTransmission", toDelete.getTransmission());
		// we only want one result
		typedQuery.setMaxResults(1);
		// get the result and save it into a new list item
		Cars result = typedQuery.getSingleResult();
		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public List<Cars> searchForCarByMake(String make) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Cars> typedQuery = em.createQuery("select li from Cars li where li.make = :selectedMake",
				Cars.class);
		typedQuery.setParameter("selectedMake", make);
		List<Cars> foundCar = typedQuery.getResultList();
		em.close();

		return foundCar;
	}

	public Cars searchForCarByModel(String modelToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Cars> typedQuery = em.createQuery("select li from Cars li where li.model = :selectedModelToEdit",
				Cars.class);
		typedQuery.setParameter("selectedModelToEdit", modelToEdit);

		Cars found = em.find(Cars.class, modelToEdit);
		em.close();
		return found;
	}

	public void updateCar(Cars toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Cars> showAllCars() {
		EntityManager em = emfactory.createEntityManager();
		List<Cars> allCars = em.createQuery("SELECT i FROM Cars i").getResultList();
		return allCars;
	}

	public void cleanUp() {
		emfactory.close();
	}
}
