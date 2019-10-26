package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Actor;

public class FilmQueryApp {
  private Scanner kb = new Scanner(System.in);
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }
  
  private int userInput() {
	  System.out.println("Enter a movie id: ");
	  int a = kb.nextInt();
	  return a;
  }
  

  private void test() {
    Film film = db.findFilmById(userInput());
    System.out.println(film);
    
//	List<Actor> actors = db.findActorsByFilmId(userInput());
//	for (Actor actor : actors) {
//		System.out.println(actor);
//	}
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
    
  }

}
