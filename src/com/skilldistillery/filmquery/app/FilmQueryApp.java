package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;
import com.skilldistillery.filmquery.entities.Actor;

public class FilmQueryApp {
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean run = true;
		do {
			System.out.println("🍿 | Film Lists | 🎞\n" + "1.  Look up a film by its id.\n"
					+ "2.  Look up a film by a search keyword.\n" + "3.  Exit.");
			String userSelection = input.next();
			switch (userSelection) {
			case "1":
				printFilmById(input);
				break;
			case "2":
				printFilmByKeyword(input);
				break;
			case "3":
				System.out.println("Goodbye!");
				run = false;
				break;
			default:
				System.out.println("\nInvalid selection.  Please enter \"1\" or \"2\".");
				break;
			}
		} while (run);
	}

	// METHODS BELOW WILL PRINT PRINT FILM BY ID OR SEARCH QUERY.
	private void printFilmById(Scanner input) {
		System.out.print("Please enter film Id: ");
		Film film = db.findFilmById(input.nextInt());
		if (film != null) {
			printFilm(film);
			printSubMenu(input, film);
		} else {
			System.out.println("\nThat movie id does not exist.  Try agian.");
		}
	}

	//Following method is a sub menu of printFilmById() allowing the user to pick all information using the toString, or seeing inv data.
	private void printSubMenu(Scanner input, Film film) {
		boolean run = true;	
		input.nextLine();
		do {
			System.out.println("Would you like to:\n" + "1. View all film details?\n" + "2. See available copies?\n"
					+ "3. Return to main menu?\n");
			String userSelection = input.nextLine();
			System.out.println(userSelection);
			switch (userSelection) {
			case "1":
				System.out.println(film + "\n");
				break;
			case "2":
				List<Inventory> inv = db.findInventory(film.getId());
				printInventory(inv);
				break;
			case "3":
				run = false;
				break;
			default:
				System.out.println("\nPlease enter \"1\", \"2\", or \"3\"");
				break;
			}
		} while (run);
	}

	private void printFilmByKeyword(Scanner input) {
		System.out.print("Please enter keyword: ");
		input.nextLine();
		String userInput = input.nextLine(); // changed to search direct
		List<Film> films = db.findFilmByKeyword(userInput);
		if (films.size() != 0) {
			for (Film film : films) {
				printFilm(film);
			}
		} else {
			System.out.println("\nNo movies with that keyword exist.  Try agian.\n");
		}
	}

	// METHODS BELOW WILL PRINT FILM DATA TO ABOVE METHODS, "OVERWRITING" THE TOSTRING.  
	// NOTE: toString's could have been used, but these were created to provide a more user friendly console output.
	private void printFilm(Film film) {
		System.out.println("\nTitle: " + film.getTitle() + "\nYear: " + film.getReleaseYear() + "\nRating: "
				+ film.getRating() + "\nDecription: " + film.getDescription() + "\nLanguage: " + film.getLanguage()
				+ "\nCategory: " + film.getCategory());
		printActors(film.getActorList());
	}

	private void printActors(List<Actor> actorList) {
		System.out.print("Actors: ");
		for (int i = 0; i < actorList.size(); i++) {
			if (i < actorList.size() - 1) {
				System.out.print(actorList.get(i).getFirst_name() + " " + actorList.get(i).getLast_name() + ", ");
			} else {
				System.out.print(actorList.get(i).getFirst_name() + " " + actorList.get(i).getLast_name() + ".\n\n");
			}
		}
	}

	private void printInventory(List<Inventory> inventory) {
		System.out.println("Inventory: ");
		for (Inventory inv : inventory) {
			if (inv.getMediaCondition() != null)
				System.out.println(inv.getMediaCondition() + "\t" + inv.getStoreLocation().getAddress() + ", "
						+ inv.getStoreLocation().getCity() + ", " + inv.getStoreLocation().getState() + " "
						+ inv.getStoreLocation().getPostalCode());
			else {
				System.out.println("Unknown" + "\t" + inv.getStoreLocation().getAddress() + ", "
						+ inv.getStoreLocation().getCity() + ", " + inv.getStoreLocation().getState() + " "
						+ inv.getStoreLocation().getPostalCode());
			}
		}
		System.out.println("\n");
	}
}
