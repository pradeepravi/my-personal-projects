package com.pradeep.menu.bean.to.movie;

public class MoviesDetailTO {
	private String id;
	private String title;
	private String year;
	private String rated;
	private String released;
	private String runtime;
	private String[] countries;
	private String[] genres;
	private String[] directors;
	private String[] writers;
	private String[] actors;
	private String plot;
	private String[] posters;
	private MovieRating[] ratings;
	private AwardTO award;
	
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
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String[] getCountries() {
		return countries;
	}
	public void setCountries(String[] countries) {
		this.countries = countries;
	}
	public String[] getGenres() {
		return genres;
	}
	public void setGenres(String[] genres) {
		this.genres = genres;
	}
	public String[] getDirectors() {
		return directors;
	}
	public void setDirectors(String[] directors) {
		this.directors = directors;
	}
	public String[] getWriters() {
		return writers;
	}
	public void setWriters(String[] writers) {
		this.writers = writers;
	}
	public String[] getActors() {
		return actors;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String[] getPosters() {
		return posters;
	}
	public void setPosters(String[] posters) {
		this.posters = posters;
	}
	public MovieRating[] getRatings() {
		return ratings;
	}
	public void setRatings(MovieRating[] ratings) {
		this.ratings = ratings;
	}
	public AwardTO getAward() {
		return award;
	}
	public void setAward(AwardTO award) {
		this.award = award;
	}
	
	

}
