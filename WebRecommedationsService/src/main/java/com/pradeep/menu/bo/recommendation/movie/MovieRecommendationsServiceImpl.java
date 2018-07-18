package com.pradeep.menu.bo.recommendation.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.bo.recommendation.Recommendation;
import com.pradeep.menu.bo.recommendation.RecommendationParam;
import com.pradeep.menu.bo.recommendation.RecommendationService;
import com.pradeep.menu.bo.recommendation.RecommendationType;
import com.pradeep.menu.dao.movie.ContentsDAOFactory;
import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.util.exception.WebRecommendationsException;

@Component("movieRecommendationServiceImpl")
public class MovieRecommendationsServiceImpl implements RecommendationService {

	@Override
	public List<Recommendation> getRecommendations(Map<RecommendationParam, List<String>> params)
			throws NoSuchMethodException {
		List<Recommendation> recommendations = null;
		for (RecommendationParam temp : params.keySet()) {
			switch (temp) {
			case MOVIE_TITLES:
				recommendations = getRecommendationsForMoviesAlreadySeen(params.get(temp));
				break;

			default:
				break;
			}
		}
		return recommendations;
	}

	private List<Recommendation> getRecommendationsForMoviesAlreadySeen(List<String> movieTitles) {
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
						// TODO - How could I apply the Actors and the directors
						// ??
						if (n.getMovieDetails().getActors() != null) {
							actors.addAll(Arrays.asList(n.getMovieDetails().getActors()));
						}

						if (n.getMovieDetails().getDirectors() != null) {
							System.out.println("Director - " + n.getMovieDetails().getDirectors());
							directors.addAll(Arrays.asList(n.getMovieDetails().getDirectors()));
						}

						alreadyWatchedMovie.add(n.getTitle());
					});

			List<MoviesDetailTO> movies2 = movieDao.getMoviesByRating(genres, directors, null, alreadyWatchedMovie);
			System.out.println("********************** Excluded " + movies2.size());

			if (movies2 != null) {
				// Since already found in the next search we do not want the
				// same movies to be returned
				movies2.forEach(m -> alreadyWatchedMovie.add(m.getTitle()));
				// TODO Same Genres + Actors individually Searched
			}

			Recommendation recomm = getPopulatedRecommendations(movies2);

			recommendations = new ArrayList<>();
			recommendations.add(recomm);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return recommendations;
	}

	private Recommendation getPopulatedRecommendations(List<MoviesDetailTO> movies) {
		Recommendation recomm = null;
		if (movies != null) {
			recomm = new Recommendation();
			recomm.setRecommendationType(RecommendationType.MOVIES);
			recomm.setRecommendationList(movies);
		}
		return recomm;
	}

	@Override
	public List<Recommendation> getRecommendations(Map<RecommendationParam, List<String>> parameters,
			List<String> exceptions) throws NoSuchMethodException {
		throw new NoSuchMethodException("Not Yet Implemented");
	}

	@Override
	public List<Recommendation> getMockTestRecommendations() throws NoSuchMethodException {
		List<Recommendation> recommendations = null;
		// for (RecommendationParam temp : params.keySet()) {
		final MovieDAO movieDao = ContentsDAOFactory.getMoviesDAOInstance("Movies");
		MoviesDetailTO searchParameter = new MoviesDetailTO();
		searchParameter.setTitle("The");
		try {
			List<MoviesDetailTO> fetchedDetails = movieDao.getMovie(searchParameter);
			Recommendation recomm = getPopulatedRecommendations(fetchedDetails);

			recommendations = new ArrayList<>();
			recommendations.add(recomm);
		} catch (WebRecommendationsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }
		// return recommendations;
		return recommendations;
	}
}
