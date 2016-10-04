package com.pradeep.menu.bean.to.movie;

import com.pradeep.menu.bean.to.movie.MovieRating;

public class TomatoRatingTO implements MovieRating {

	private String meter;
	private String image;
	private String rating;
	private int reviews;
	private float fresh;
	private String consensus;
	private float userMeter;
	private float userRating;
	private int userReviews;

	@Override
	public String getRatingsBody() {
		return "tomato";
	}

	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getReviews() {
		return reviews;
	}

	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public float getFresh() {
		return fresh;
	}

	public void setFresh(float fresh) {
		this.fresh = fresh;
	}

	public String getConsensus() {
		return consensus;
	}

	public void setConsensus(String consensus) {
		this.consensus = consensus;
	}

	public float getUserMeter() {
		return userMeter;
	}

	public void setUserMeter(float userMeter) {
		this.userMeter = userMeter;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

	public int getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(int userReviews) {
		this.userReviews = userReviews;
	}

}
