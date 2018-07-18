package com.pradeep.menu.bo.recommendation;

import java.util.List;

import com.pradeep.menu.bean.to.movie.MoviesDetailTO;

public class Recommendation {
	RecommendationType recommendationType;
	List<MoviesDetailTO> recommendationsList;
	
	public RecommendationType getRecommendationType() {
		return recommendationType;
	}
	public void setRecommendationType(RecommendationType recommendationType) {
		this.recommendationType = recommendationType;
	}
	public List<MoviesDetailTO> getRecommendationsList() {
		return recommendationsList;
	}
	public void setRecommendationList(List<MoviesDetailTO> recommendation) {
		this.recommendationsList = recommendation;
	}	
}
