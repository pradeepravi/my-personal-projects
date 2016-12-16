package com.pradeep.menu.bo.recommendation;

import java.util.List;
import java.util.Map;

public interface RecommendationService {
	public List<Recommendation> getRecommendations(Map<RecommendationParam, List<String> > parameters) throws NoSuchMethodException; 
	public List<Recommendation> getRecommendations(Map<RecommendationParam, List<String> > parameters, List<String> exceptions)  throws NoSuchMethodException; 
}
