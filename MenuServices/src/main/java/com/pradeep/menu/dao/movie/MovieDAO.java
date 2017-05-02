package com.pradeep.menu.dao.movie;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.util.exception.WebRecommendationsException;

@Component("movieDAO")
public interface MovieDAO {

	List<MovieTO> getMovie(MoviesDetailTO searchParameter) throws WebRecommendationsException ;

	List<MovieTO> getMovies(List<String> titles) throws WebRecommendationsException ;

	Set<String> getAllGenreNames() throws WebRecommendationsException ;

	Set<String> getAllDirectors() throws WebRecommendationsException ;

	Set<String> getAllActors() throws WebRecommendationsException ;

	List<MoviesDetailTO> getMoviesForActors(List<String> actors) throws WebRecommendationsException ;

	List<MoviesDetailTO> getMoviesForDirectors(List<String> actors) throws WebRecommendationsException ;

	List<MoviesDetailTO> getMoviesForGenres(List<String> genres) throws WebRecommendationsException ;
	
	List<MoviesDetailTO> getMoviesByRating(Set<String> genres, Set<String> directors, Set<String> actors) throws WebRecommendationsException ;
	
	List<MoviesDetailTO> getMoviesByRating(Set<String> genres, Set<String> directors, Set<String> actors, Set <String> excludeTitles) throws WebRecommendationsException ;

}
