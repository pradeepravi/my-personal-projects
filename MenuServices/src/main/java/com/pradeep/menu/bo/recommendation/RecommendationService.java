package com.pradeep.menu.bo.recommendation;

import java.util.List;
import java.util.Map;

public interface RecommendationService {
	public List<Recommendation> getRecommendations(Map<String, String > parameters);
	public List<Recommendation> getRecommendations(Map<RecommendationParam, String > parameters); 
}
