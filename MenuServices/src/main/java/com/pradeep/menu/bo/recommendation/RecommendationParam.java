package com.pradeep.menu.bo.recommendation;

public enum RecommendationParam{ 
	MOVIE_GENRES("genres"),
	MOVIE_ACTORS("actors"),
	MOVIE_TITLES("movieTitles"),
	MOVIE_DIRECTORS("directors");
	
	String type;
	RecommendationParam(String param){
		this.type=param;
	}	
	
}
