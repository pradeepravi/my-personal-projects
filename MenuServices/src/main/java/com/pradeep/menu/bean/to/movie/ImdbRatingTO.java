package com.pradeep.menu.bean.to.movie;

public class ImdbRatingTO implements MovieRating {
	private String id;
	private float rating;
	private int votes;

	@Override
	public String getRatingsBody() {
		return "imdb";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
