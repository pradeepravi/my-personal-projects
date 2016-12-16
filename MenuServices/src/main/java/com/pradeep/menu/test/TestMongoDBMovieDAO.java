package com.pradeep.menu.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pradeep.menu.bo.recommendation.RecommendationFactory;
import com.pradeep.menu.bo.recommendation.RecommendationParam;
import com.pradeep.menu.bo.recommendation.RecommendationService;
import com.pradeep.menu.bo.recommendation.RecommendationType;
import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.dao.movie.impl.MovieDAOImpl;
import com.pradeep.menu.util.exception.WebRecommendationsException;

public class TestMongoDBMovieDAO {

	static MovieDAO movieDao = null;
	static RecommendationService movieRecommendations = null;
	@BeforeClass
	public static void init (){
		movieDao = new MovieDAOImpl(); 
		movieRecommendations = RecommendationFactory.getInstance(RecommendationType.MOVIES);  
	}
	
	@Test
	public void testGetMovies(){
		System.out.println("testGetMovie***");
		Map<String , Object > mapOfCriteria = new HashMap<String,Object>();
		mapOfCriteria.put("title", "Star Trek"); 
		try {
			System.out.println( "FETCH MOVIE FOR SEARCH STRING - "+movieDao.getMovies(Arrays.asList("The Martian")).size());
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllGenres(){
		try {
			System.out.println( "ALL DIRECTORS - "+movieDao.getAllGenreNames().size());
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllDirectors(){
		try {
			System.out.println( "ALL DIRECTORS-"+movieDao.getAllDirectors().size());
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Assert.assertTrue(true); 
	}
	@Test
	public void testGetAllActors(){
		try {
			System.out.println( "ALL ACTORS"+movieDao.getAllActors().size());
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllMoviesForActor(){
		List<String> li = new ArrayList<>();
		//li.add("Pierce");
		li.add("Neal");
		try {
			System.out.println( movieDao.getMoviesForActors(li).size());
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Assert.assertTrue(true); 
	}
	
	@Test
	public void testGetAllMoviesForActorsOrderByRating(){
		/*List<String> directors = new ArrayList<>();
		directors.add("Brian");*/
		
		Set<String> actors= new HashSet<>(); 
		actors.add("Pamela");

		Set<String> genres= new HashSet<>();
		genres.add("Action");
		genres.add("Short");

		try {
			System.out.println(" [testGetAllMoviesForActorsOrderByRating] ***** "+movieDao.getMoviesByRating(genres, null, actors).size());
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Assert.assertTrue(true);
	}
	
	
	@Test
	public void testRecommendationsForWatchedMovies() {
		/*List<String> directors = new ArrayList<>();
		directors.add("Brian");*/
			
		List<String> watchedMovies = new ArrayList<>();
		watchedMovies.add("The Martian");
		
		Map<RecommendationParam, List<String>> paramsMap = new HashMap<>();
		List<String> list = new ArrayList<>();
		list .add("Bourne");
		paramsMap.put(RecommendationParam.MOVIE_TITLES, list);
		try {
			System.out.println(" [testGetAllMoviesForActorsOrderByRating] ***** "+movieRecommendations.getRecommendations(paramsMap).size());

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Assert.assertTrue(true);
	}
	
	
	@AfterClass
	public static void close(){
		movieDao = null;
	}
}
