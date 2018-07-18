package com.pradeep.menu.bo.recommendation;

import com.pradeep.menu.bo.recommendation.movie.MovieRecommendationsServiceImpl;

public class RecommendationFactory {
	
	private RecommendationFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static RecommendationService getInstance (RecommendationType recommendationType){
		RecommendationService recomm = null; 
		if(recommendationType == RecommendationType.MOVIES ){
			recomm = new MovieRecommendationsServiceImpl();
		}
		
		return recomm;
		
	}

}
