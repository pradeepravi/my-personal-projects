package com.pradeep.menu.bo.recommendation;

public enum RecommendationType{ 
	MOVIES("movies"),
	MUSIC(""), //TODO
	BOOKS("") //TODO	
	;
	String type;
	RecommendationType(String type){
		this.type=type;
	}	
	
}
