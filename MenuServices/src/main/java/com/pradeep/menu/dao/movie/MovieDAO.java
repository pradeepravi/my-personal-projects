package com.pradeep.menu.dao.movie;

import java.util.List;
import java.util.Map;

import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.bean.to.movie.MoviesDetailTO;

public interface MovieDAO {

	List<MovieTO> getMovie(Map<String, Object> criteriasMap);

	List<MovieTO> getMovies(List<String> titles);

	List<String> getAllGenreNames();

	List<String> getAllDirectors();

	List<String> getAllActors();

	List<MoviesDetailTO> getMoviesForActors(List<String> actors);

	List<MoviesDetailTO> getMoviesForDirectors(List<String> actors);

	List<MoviesDetailTO> getMoviesForGenres(List<String> actors);

}
