package com.pradeep.menu.dao.movie;

import com.pradeep.menu.dao.movie.impl.MovieDAOImpl;

public class ContentsDAOFactory {

	
	private ContentsDAOFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static MovieDAO getMoviesDAOInstance (String contentType){
		return new MovieDAOImpl();
		
	}


}
