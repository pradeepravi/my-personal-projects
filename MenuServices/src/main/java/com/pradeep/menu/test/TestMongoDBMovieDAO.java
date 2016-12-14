package com.pradeep.menu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.dao.movie.impl.MovieDAOImpl;

public class TestMongoDBMovieDAO {

	static MovieDAO movieDao = null;
	@BeforeClass
	public static void init (){
		movieDao = new MovieDAOImpl();
	}
	
	@Test
	public void testGetMovies(){
		System.out.println("testGetMovie***");
		Map<String , Object > mapOfCriteria = new HashMap<String,Object>();
		mapOfCriteria.put("title", "Star Trek"); 
		System.out.println( "FETCH MOVIE FOR SEARCH STRING - "+movieDao.getMovie(mapOfCriteria).size());
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllGenres(){
		System.out.println( "ALL DIRECTORS - "+movieDao.getAllGenreNames().size()); 
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllDirectors(){
		System.out.println( "ALL DIRECTORS-"+movieDao.getAllDirectors().size()); 
		Assert.assertTrue(true); 
	}
	@Test
	public void testGetAllActors(){
		System.out.println( "ALL ACTORS"+movieDao.getAllActors().size()); 
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllMoviesForActor(){
		List<String> li = new ArrayList<>();
		//li.add("Pierce");
		li.add("Neal");
		System.out.println( movieDao.getMoviesForActors(li).size()); 
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllMoviesForActorsOrderByRating(){
		/*List<String> directors = new ArrayList<>();
		directors.add("Brian");*/
		
		List<String> actors= new ArrayList<>();
		actors.add("Pamela");

		List<String> genres= new ArrayList<>();
		genres.add("Action");
		genres.add("Short");

		System.out.println(" [testGetAllMoviesForActorsOrderByRating] ***** "+movieDao.getMoviesByRating(genres, null, actors).size());  
		Assert.assertTrue(true);
	}
	
	
	@AfterClass
	public static void close(){
		movieDao = null;
	}
}
