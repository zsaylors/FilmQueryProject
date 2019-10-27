package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
  private DatabaseAccessor db;

  @BeforeEach
  void setUp() throws Exception {
    db = new DatabaseAccessorObject();
  }

  @AfterEach
  void tearDown() throws Exception {
    db = null;
  }

  @Test
  void test_getFilmById_with_invalid_id_returns_null() {
    Film f = db.findFilmById(-42);
    assertNull(f);
  }
  
  @Test
  void test_getFilmById_with_no_id_returns_null() {
    Film f = db.findFilmById(0);
    assertNull(f);
  }
  
  @Test
  void test_getFilmById_with_1() {
    Film f = db.findFilmById(1);
    assertEquals("PG", f.getRating());
  }
  
  @Test
  void test_getFilmById_with_releaseYear() {
	    Film f = db.findFilmById(1);
	    assertEquals(1993, f.getReleaseYear());
	  }
  
  @Test
  void test_getFilmById_with_non_existant_Id() {
	    Film f = db.findFilmById(1001);
	    assertNull(f);
	  }
  
  @Test
  void test_findFilmBySearch_with_non_existant_Id() {
	    List<Film> lf = db.findFilmByKeyword("mad cow");
	    assertEquals(74, lf.size());
	  }
  
  @Test
  void test_findFilmBySearch_with_non_existant_Id_test2() {
	  List<Film> lf = db.findFilmByKeyword("Monkey who must Defeat");
	  assertEquals(1, lf.size());
  }
  
  @Test
  void test_findFilmBySearch_with_non_existant_with_no_input() {
	  List<Film> lf = db.findFilmByKeyword("");
	  assertEquals(1000, lf.size());
  }
}
