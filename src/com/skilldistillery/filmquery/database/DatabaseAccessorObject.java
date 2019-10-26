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

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		
		String user = "student";
		String pass = "student";
		String sql = "select * FROM film \n" + 
				"JOIN language ON film.language_id = language.id\n" + 
				"JOIN film_category ON film.id = film_category.film_id\n" + 
				"JOIN category ON film_category.category_id = category.id\n" + 
				"WHERE film.id = ?;";
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,filmId);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
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
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return film;
	}
	
	public List<Film> findFilmByKeyword(String keyword) {
		Film film = null;
		String user = "student";
		String pass = "student";
		String sql = "select * from film JOIN language ON film.language_id = language.id WHERE title like ? OR description like ?;";
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
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
				film.setActorList(findActorsByFilmId(rs.getInt("id")));
				film.setLanguage(rs.getString("language.name"));
				films.add(film);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return films;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;
		String user = "student";
		String pass = "student";
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

	public List<Actor> findActorsByFilmId(int filmId) {
		String user = "student";
		String pass = "student";
	    String sql = "SELECT * FROM film_actor "
	    		+ "JOIN film ON film.id = film_actor.film_id "
	    		+ "JOIN actor on actor.id = film_actor.actor_id"
	    		+ " WHERE film.id = ?";
		List<Actor> actors = new ArrayList<>();
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
}
