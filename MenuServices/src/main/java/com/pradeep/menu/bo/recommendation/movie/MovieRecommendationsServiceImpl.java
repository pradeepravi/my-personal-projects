package com.pradeep.menu.bo.recommendation.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.bo.recommendation.Recommendation;
import com.pradeep.menu.bo.recommendation.RecommendationParam;
import com.pradeep.menu.bo.recommendation.RecommendationService;
import com.pradeep.menu.bo.recommendation.RecommendationType;
import com.pradeep.menu.dao.movie.ContentsDAOFactory;
import com.pradeep.menu.dao.movie.MovieDAO;

public class MovieRecommendationsServiceImpl implements RecommendationService {

	@Override
	public List<Recommendation> getRecommendations(Map<RecommendationParam, List<String>> params)
			throws NoSuchMethodException {
		List<Recommendation> recommendations = null;
		for (RecommendationParam temp : params.keySet()) {
			switch (temp) {
			case MOVIE_TITLES:
				recommendations = fetchRecommendationsForMoviesWatched(params.get(temp));
				break;

			default:
				break;
			}
		}
		return recommendations;
	}

	private List<Recommendation> fetchRecommendationsForMoviesWatched(List<String> movieTitles) {
		List<Recommendation> recommendations = null;
		try {
			// TODO Replace with proper TYPE passed in Param
			final MovieDAO movieDao = ContentsDAOFactory.getMoviesDAOInstance("Movies");
			List<MovieTO> fetchedDetails = movieDao.getMovies(movieTitles);

			final Set<String> genres = new HashSet<>();
			final Set<String> actors = new HashSet<>();
			final Set<String> directors = new HashSet<>();
			final Set<String> alreadyWatchedMovie = new HashSet<>();

			fetchedDetails.stream()
					.filter(m -> (m.getMovieDetails() != null && m.getMovieDetails().getGenres() != null))
					.forEach(n -> {
						genres.addAll(Arrays.asList(n.getMovieDetails().getGenres()));
						actors.addAll(Arrays.asList(n.getMovieDetails().getActors()));
						directors.addAll(Arrays.asList(n.getMovieDetails().getDirectors()));
						alreadyWatchedMovie.add(n.getTitle());
					});

			List<MoviesDetailTO> movies = movieDao.getMoviesByRating(genres, directors, actors);
			System.out.println("********************** NON Excluded " + movies.size());

			if (movies != null) {
				recommendations = new ArrayList<>();
				final Recommendation recomm = new Recommendation();
				recomm.setRecommendationType(RecommendationType.MOVIES);
				recomm.setRecommendation(movies);
			
				recommendations.add(recomm);
				
			}
			// List<MoviesDetailTO> movies2 = movieDao.getMoviesByRating(genres,
			// directors, actors, alreadyWatchedMovie);
			// System.out.println("********************** Excluded " +
			// movies2.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recommendations;
	}

	@Override
	public List<Recommendation> getRecommendations(Map<RecommendationParam, List<String>> parameters,
			List<String> exceptions) throws NoSuchMethodException {
		throw new NoSuchMethodException("Not Yet Implemented");
	}
}
