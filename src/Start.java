import java.util.List;
import java.util.Scanner;

import controller.CarsHelper;
import entities.Cars;

/**
 * Jonathan Argueta-Herrera - jiarguetaherrera CIS175 Spring 2023 Feb 6, 2023
 */

public class Start {

	static Scanner in = new Scanner(System.in);
	static CarsHelper carHelp = new CarsHelper();

	private static void addACar() {
		// TODO Auto-generated method stub
		System.out.print("Enter a make: ");
		String make = in.nextLine();
		System.out.print("Enter an model: ");
		String model = in.nextLine();
		System.out.print("Enter an transmission type: ");
		String transmission = in.nextLine();

		Cars toAdd = new Cars(make, model, transmission);
		carHelp.insertCar(toAdd);
	}

	private static void deleteCar() {
		// TODO Auto-generated method stub
		System.out.print("Enter the make of car to delete: ");
		String make = in.nextLine();
		System.out.print("Enter the model to delete: ");
		String model = in.nextLine();
		System.out.print("Enter the transmission type of car to delete: ");
		String transmission = in.nextLine();

		Cars toDelete = new Cars(make, model, transmission);
		carHelp.deleteCar(toDelete);

	}

	private static void editACar() {
		// TODO Auto-generated method stub

		List<Cars> foundCar;

		System.out.print("Enter the make of car: ");
		String make = in.nextLine();
		foundCar = carHelp.searchForCarByMake(make);

		if (!foundCar.isEmpty()) {
			System.out.println("\nFound Results.");
			for (Cars l : foundCar) {
				System.out.println(l.getMake() + " : " + l.toString());
			}
			System.out.print("Which model to edit: ");
			String modelToEdit = in.nextLine();

			List<Cars> foundModel;
			String newMake = "";
			String newModel = "";
			String newTransmission = "";
			Cars toEdit = new Cars(make, modelToEdit, newTransmission);
			foundModel = (List<Cars>) carHelp.searchForCarByModel(modelToEdit);

			System.out.println("1 : Update Make");
			System.out.println("2 : Update Model");
			System.out.println("3 : Update Transmission");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Make: ");
				newMake = in.nextLine();
				toEdit.setMake(newMake);
			} else if (update == 2) {
				System.out.print("New Model: ");
				newModel = in.nextLine();
				toEdit.setModel(newModel);
			} else if (update == 3) {
				System.out.print("New Transmission: ");
				newTransmission = in.nextLine();
				toEdit.setTransmission(newTransmission);
			}

			carHelp.updateCar(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome shopping list! ---");
		while (goAgain) {
			System.out.println("*  Select an Car:");
			System.out.println("*  1 -- Add an Car");
			System.out.println("*  2 -- Edit an Car");
			System.out.println("*  3 -- Delete an Car");
			System.out.println("*  4 -- View the list of Cars");
			System.out.println("*  5 -- Exit");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addACar();
			} else if (selection == 2) {
				editACar();
			} else if (selection == 3) {
				deleteCar();
			} else if (selection == 4) {
				viewTheList();
			} else {
				carHelp.cleanUp();
				System.out.println("\nPeace out succa");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<Cars> allCars = carHelp.showAllCars();

		for (Cars singleItem : allCars) {
			System.out.println("\n" + singleItem.returnCarDetails() + "\n");
		}
	}

}