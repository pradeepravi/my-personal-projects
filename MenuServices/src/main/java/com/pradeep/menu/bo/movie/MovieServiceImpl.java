package com.pradeep.menu.bo.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.util.exception.WebRecommendationsException;
 
@Component("movieServiceImpl")
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieDAO movieDao;
	
	
	@Override
	public List<String> getAllGenres() {
		List <String>list = new ArrayList<>();
		
		try {
			list.addAll(movieDao.getAllGenreNames());
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public List<MovieTO> getMovies(List<String> movies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieTO> getMovieForGenre(List<String> genres) {
		// TODO Auto-generated method stub
		return null;
	}

}
