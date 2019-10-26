package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
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
  	  System.out.println("🍿 | Film Lists | 🎞\n"
      		+"1.  Look up a film by its id.\n"
      		+"2.  Look up a film by a search keyword.\n"
      		+"3.  Exit.");
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
	  } else {
		  System.out.println("\nThat movie id does not exist.  Try agian.");
	  }
  }
  
  private void printFilmByKeyword(Scanner input) {
	  System.out.print("Please enter keyword: ");
	  String userInput = input.next();
	  input.nextLine();
	  List<Film> films = db.findFilmByKeyword(userInput);
	  if (films.size() != 0) {
		  for (Film film : films) {
			  printFilm(film);
		}
	  } else {
		  System.out.println("\nNo movies with that keyword exist.  Try agian.\n");
	  }
  }
  
  // METHODS BELOW WILL PRINT FILM DATA
  private void printFilm(Film film) {
	  System.out.println("\nTitle: " + film.getTitle()
	  + "\nYear: " + film.getReleaseYear()
	  + "\nRating: " + film.getRating()
	  + "\nDecription: " + film.getDescription()
	  + "\nLanguage: " + film.getLanguage());
	  printActors(film.getActorList());
  }
  
  private void printActors(List<Actor> actorList) {
	  System.out.print("Actors: ");
	  for (int i = 0; i < actorList.size(); i++) {
		  if (i < actorList.size() - 1) {
			  System.out.print(actorList.get(i).getFirst_name() + " " + actorList.get(i).getLast_name() + ", ");
		  }
		  else {
			  System.out.print(actorList.get(i).getFirst_name() + " " + actorList.get(i).getLast_name() + ".\n\n");
		  }
	  }
  }
}
