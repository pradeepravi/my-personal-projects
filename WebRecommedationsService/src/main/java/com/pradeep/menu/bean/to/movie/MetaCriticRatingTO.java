package com.pradeep.menu.bean.to.movie;

import com.pradeep.menu.bean.to.movie.MovieRating;

public class MetaCriticRatingTO implements MovieRating {

	private float rating;
	
	@Override
	public String getRatingsBody() {
		return "metacritic";
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
