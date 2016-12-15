package com.pradeep.menu.bo.recommendation;

public enum RecommendationParam{ 
	MOVIES_GENRES("genres"),
	MOVIES_ACTORS("actors"),
	MOVIES_DIRECTORS("directors");
	
	String type;
	RecommendationParam(String param){
		this.type=param;
	}	
	
}
