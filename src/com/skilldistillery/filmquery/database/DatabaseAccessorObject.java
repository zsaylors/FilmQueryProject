package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;
import com.skilldistillery.filmquery.entities.Store;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//STANDARD SQL STRING FOR ALL FILMS
	//Note: Specific where statements are concatenated in their respective methods.
	//added to not repeat and ease to edit in future.
	private String setSql() {
		return "select * FROM film \n" + 
				"JOIN language ON film.language_id = language.id\n" +  // adds language category
				"JOIN film_category ON film.id = film_category.film_id\n" +  //adds film category
				"JOIN category ON film_category.category_id = category.id\n";  //adds film category		
	}
	
	//CREATES FILM OBJECT
	//added to not repeat and ease to edit in future.
	private Film createFilm(ResultSet rs, Film film, int filmId) throws SQLException {
		film = new Film();
		film.setId(rs.getInt("id"));
		film.setTitle(rs.getString("title"));
		film.setDescription(rs.getString("description"));
		film.setReleaseYear(rs.getInt("release_year"));
		film.setLanguageId(rs.getInt("language_id"));
		film.setDescription(rs.getString("description"));
		film.setRentalDuration(rs.getInt("rental_duration"));
		film.setRentalRate(rs.getDouble("rental_rate"));
		film.setLength(rs.getInt("length"));
		film.setReplacementCost(rs.getDouble("replacement_cost"));
		film.setRating(rs.getString("rating"));
		film.setSpecialFeatures(rs.getString("special_features"));
		film.setActorList(findActorsByFilmId(filmId));
		film.setLanguage(rs.getString("language.name"));
		film.setCategory(rs.getString("category.name"));
		return film;
	}
	
	//FINDS FILM BY ID
	//first menu item in FilmQueryApp.
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = setSql() + "WHERE film.id = ?;";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,filmId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {	
				film = createFilm(rs, film, filmId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return film;
	}
	
	//FINDS FILM BY KEYWORD
	//second menu item in FilmQueryApp.
	public List<Film> findFilmByKeyword(String keyword) {
		Film film = null;
		String sql = setSql() + "WHERE film.title like ? OR film.description like ?;";
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				film = createFilm(rs, film, rs.getInt("id"));
				films.add(film);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return films;
	}
	
	//FINDS ACTOR
	//currently unused.
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?;";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,actorId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirst_name(rs.getString("first_name"));
				actor.setLast_name(rs.getString("last_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actor;
	}

	//CREATES AN ACTOR LIST SPECIFIC TO A FILM.
	//Added in the film object methods above.
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT * FROM film_actor "
	    		+ "JOIN film ON film.id = film_actor.film_id "
	    		+ "JOIN actor on actor.id = film_actor.actor_id"
	    		+ " WHERE film.id = ?";
		  try {
		    Connection conn = DriverManager.getConnection(URL, user, pass);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, filmId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		    	Actor actor = new Actor();
		    	actor.setId(rs.getInt("id"));
		    	actor.setFirst_name(rs.getString("first_name"));
		    	actor.setLast_name(rs.getString("last_name"));
		    	actors.add(actor);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return actors;
	}
	
	//CREATES AN INVENTORY LIST SPECIFIC TO A FILM.
	//This is used in the sub menu (option 2) in FilmQueryApp.  Prints all film locations and conditions.
	public List<Inventory> findInventory(int filmId) {
		List<Inventory> inventory = new ArrayList<>();
	    Inventory inv = null;
	    String sql = "SELECT * FROM inventory_item "
	    		+ "JOIN film ON film.id = inventory_item.film_id \n" //adds inventory.  May need it's own method.
	    		+ "JOIN store_list ON store_list.store_id = inventory_item.store_id\n"
	    		+ "WHERE film.id = ?";
	    try {
		    Connection conn = DriverManager.getConnection(URL, user, pass);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, filmId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		    	inv = new Inventory(rs.getInt("id"), rs.getInt("film_id"), rs.getInt("store_id"),
		    			rs.getString("media_condition"), rs.getString("last_update"), findStores(rs.getInt("store_id")));
		    	inventory.add(inv);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		return inventory;
	}
	
	//CREATES A STORE OBJECT TO BE USED WITH INVENTORY TO FIND LOCATION.
	//Added in findInventory method above.
	public Store findStores(int storeId) {
	    String sql = "SELECT * FROM store_list WHERE store_list.store_id = ?";
	    Store store = null;
	    try {
		    Connection conn = DriverManager.getConnection(URL, user, pass);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, storeId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		    	store = new Store(rs.getInt("store_id"), rs.getInt("manager_id"), rs.getString("address"), 
		    			rs.getString("city"), rs.getString("state"), rs.getString("postal_code"));
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return store;
	}
}
