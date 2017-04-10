package com.pradeep.menu.bo.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.util.exception.WebRecommendationsException;
 
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieDAO movieDao;
	
	
	@Override
	public List<String> getAllGenres() throws WebRecommendationsException {
		List <String>list = new ArrayList<>();		
		list.addAll(movieDao.getAllGenreNames());
		return list;
	}

	@Override
	public List<String> getAllDirectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllActors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MoviesDetailTO> getMovies(List<String> movies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MoviesDetailTO> getMovieForGenre(List<String> genres) throws WebRecommendationsException {
		
		return movieDao.getMoviesForGenres(genres);
		
	}

}
