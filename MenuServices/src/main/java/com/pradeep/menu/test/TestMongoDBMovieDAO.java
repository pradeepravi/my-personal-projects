package com.pradeep.menu.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.dao.movie.impl.MovieDAOImpl;

public class TestMovieDAO {

	static MovieDAO movieDao = null;
	@BeforeClass
	public static void init (){
		movieDao = new MovieDAOImpl();
	}
	
	@Test
	public void testGetGenres(){
		
		System.out.println( movieDao.getAllGenreNames());
		Assert.assertTrue(true); 
	}
	

	@Test
	public void testGetMovie(){
		System.out.println("testGetMovie***");
		Map<String , Object > mapOfCriteria = new HashMap<String,Object>();
		mapOfCriteria.put("title", "Star Trek"); 
		System.out.println( movieDao.getMovie(mapOfCriteria).size());
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetMovies(){
		System.out.println( movieDao.getMovies(Arrays.asList("Star Trek","The Martian"))); 
		Assert.assertTrue(true); 
	}
	
	@AfterClass
	public static void close(){
		movieDao = null;
	}
}
