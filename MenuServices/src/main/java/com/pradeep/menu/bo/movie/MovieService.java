package com.pradeep.menu.bo.movie;

import java.util.List;

import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.util.exception.WebRecommendationsException;

public interface MovieService {
	List<String> getAllGenres() throws WebRecommendationsException;
	List<String> getAllDirectors() throws WebRecommendationsException;
	List<String> getAllActors() throws WebRecommendationsException;
	List<MoviesDetailTO> getMovies(List<String> movies) throws WebRecommendationsException;
	List<MoviesDetailTO> getMovieForGenre(List<String> genres) throws WebRecommendationsException;
	 
}
