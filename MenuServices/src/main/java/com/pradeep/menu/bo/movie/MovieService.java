package com.pradeep.menu.bo.movie;

import java.util.List;
import org.springframework.stereotype.Component;
import com.pradeep.menu.bean.to.movie.MovieTO;

@Component("movieService")
public interface MovieService {
	List<String> getAllGenres();
	List<String> getAllDirectors();
	List<String> getAllActors();
	List<MovieTO> getMovies(List<String> movies);
	List<MovieTO> getMovieForGenre(List<String> genres);	 
}
