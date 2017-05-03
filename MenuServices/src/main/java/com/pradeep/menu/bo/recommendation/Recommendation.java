package com.pradeep.menu.bo.recommendation;

import java.util.List;

import com.pradeep.menu.bean.to.movie.MoviesDetailTO;

public class Recommendation {
	RecommendationType recommendationType;
	List<MoviesDetailTO> recommendation;
	
	public RecommendationType getRecommendationType() {
		return recommendationType;
	}
	public void setRecommendationType(RecommendationType recommendationType) {
		this.recommendationType = recommendationType;
	}
	public List<MoviesDetailTO> getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(List<MoviesDetailTO> recommendation) {
		this.recommendation = recommendation;
	}	
}
