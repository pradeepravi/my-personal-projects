package com.pradeep.menu.bo.recommendation;

public class Recommendation {
	RecommendationType recommendationType;
	Object recommendation;
	
	public RecommendationType getRecommendationType() {
		return recommendationType;
	}
	public void setRecommendationType(RecommendationType recommendationType) {
		this.recommendationType = recommendationType;
	}
	public Object getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(Object recommendation) {
		this.recommendation = recommendation;
	}	
}
