package com.skilldistillery.filmquery.database;

import java.util.List;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	@Override
	public Film findFilmById(int filmId) {
		return null;
	}

	public Actor findActorById(int actorId) {
		return null;
	}

	public List<Actor> findActorsByFilmId(int filmId) {
		return null;
	}

}
