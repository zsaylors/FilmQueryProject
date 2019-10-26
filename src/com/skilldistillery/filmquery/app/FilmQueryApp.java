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
//    app.test();
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
  	  System.out.println("🍿 | Movie Lists | 🎞\n"
      		+"1.  Look up a film by its id.\n"
      		+"2.  Look up a film by a search keyword.\n"
      		+"3.  Exit.");
  	  String userSelection = input.next();
  	  switch (userSelection) {
  	  case "1":
  		  System.out.println("you got it");
  		  break;
  	  case "2":
  		  System.out.println("ok");
  		  break;
  	  case "3":
  		  System.out.println("Goodbye!");
  		  run = false;
  		  break;
  	  default:  
  		  System.out.println("Invalid selection.  Please enter \"1\" or \"2\".");
  		  break;
  	  }
    } while (run == true);
  }
  
  //TEST METHODS
  
  //private Scanner kb = new Scanner(System.in);
//private int userInput() {
//  System.out.println("Enter a movie id: ");
//  int a = kb.nextInt();
//  return a;
//}
//

//private void test() {
//Film film = db.findFilmById(userInput());
//System.out.println(film);
//}

}
