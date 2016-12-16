package com.pradeep.menu.bean.to.movie;

import java.io.Serializable;

public class MovieTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7372085060347207568L;
	String id;
	String title;
	String year;
	String imdbRating;
	String type;
	MoviesDetailTO movieDetails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MoviesDetailTO getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(MoviesDetailTO movieDetails) {
		this.movieDetails = movieDetails;
	}

	@Override
	public String toString() {
		return "MovieTO [id=" + id + ", title=" + title + ", year=" + year + ", imdbRating=" + imdbRating + ", type="
				+ type + "]";
	}

}
