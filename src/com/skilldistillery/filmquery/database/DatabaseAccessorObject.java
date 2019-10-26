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
import com.skilldistillery.filmquery.entities.Language;

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
		String sql = "select * from film where id = ?;";
		
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
				film.setLanguage(findFilmLanguage(filmId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return film;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;
		
		String user = "student";
		String pass = "student";
		String sql = "select * from actor where id = ?;";
		
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
//
	public List<Actor> findActorsByFilmId(int filmId) {
		String user = "student";
		String pass = "student";
	    String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM film_actor JOIN film ON film.id = film_actor.film_id "
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

	public Language findFilmLanguage(int filmId) {
		String user = "student";
		String pass = "student";
	    String sql = "SELECT language.name FROM language JOIN film ON film.language_id  = language.id"
       + " WHERE film.id = ?";
	    
    	Language language = new Language();
	    try {
		    Connection conn = DriverManager.getConnection(URL, user, pass);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, filmId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		    	language.setLanguage(rs.getString("name"));
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		return language;
	}
}
