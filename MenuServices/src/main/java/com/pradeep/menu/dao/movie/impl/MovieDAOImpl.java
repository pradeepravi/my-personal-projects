package com.pradeep.menu.dao.movie.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.util.mongodb.MongoDBConnection;

public class MovieDAOImpl implements MovieDAO {

	static final Logger log = LogManager.getLogger(MovieDAOImpl.class);

	@Override
	public List<MovieTO> getMovie(Map<String, Object> filtersMap) {
		final MongoDBConnection conn = new MongoDBConnection("movieDatabase");
		List<MovieTO> resultMovies = null;
		try {
			if (conn != null) {
				resultMovies = new ArrayList<MovieTO>();
				log.debug("Got Connection!!!");
				final MongoCollection<Document> moviesCollection = conn.getMongoDBCollection("movies");
				final Document filter = getCriteriaPopulatedDocument(filtersMap);
				ArrayList<Document> resultsItr = moviesCollection.find(filter).into(new ArrayList<Document>());
				for (Document doc : resultsItr) {
					MovieTO movie = getPopulatedMovieTO(doc);
					resultMovies.add(movie);
				}
				log.debug("Movie : " + resultMovies);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}

		return resultMovies;
	}

	@Override
	public List<MovieTO> getMovies(List<String> titles) {
		List<MovieTO> movies = null;
		final MongoDBConnection conn = new MongoDBConnection("movieDatabase");
		try {
			if (conn != null) {
				movies = new ArrayList<MovieTO>();
				log.debug("Got Connection!!!");
				final MongoCollection<Document> moviesCollection = conn.getMongoDBCollection("movies");
				final Document bdb = new Document();
				bdb.put("title", new Document("$in", titles));
				MongoCursor<Document> resultsCursor = moviesCollection.find(bdb).iterator();
				while (resultsCursor.hasNext()) {
					final Document result = resultsCursor.next();
					MovieTO movie = getPopulatedMovieTO(result);
					movies.add(movie);
				}
			}
		} catch (Exception e) {
			// log.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			conn.closeConnection();
		}

		return null;
	}

	@Override
	public List<String> getAllGenreNames() {
		List<String> genres = null;
		final MongoDBConnection conn = new MongoDBConnection("movieDatabase");
		try {
			if (conn != null) {
				genres = new ArrayList<String>();
				log.debug("Got Connection!!!");
				final MongoCollection<Document> moviesCollection = conn.getMongoDBCollection("movieDetails");
				final MongoCursor<String> resultsCursor = moviesCollection.distinct("genres", String.class).iterator();
				while (resultsCursor.hasNext()) {
					genres.add(resultsCursor.next());
				}
				log.debug("Movie : " + genres);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}

		return genres;
	}

	private MovieTO getPopulatedMovieTO(Document document) {
		MovieTO movie = null;
		if (document != null) {
			movie = new MovieTO();
			movie.setTitle(document.getString("title"));
			movie.setYear(String.valueOf(document.getInteger("year")));
			movie.setType(document.getString("type"));
			movie.setImdbRating(document.getString("imdb"));
		}
		return movie;
	}

	private Document getCriteriaPopulatedDocument(Map<String, Object> criteriasMap) {
		Document doc = null;
		if (criteriasMap != null) {
			doc = new Document();
			for (String key : criteriasMap.keySet()) {
				if (key.equalsIgnoreCase("TITLE")) {
					doc.append("title", Pattern.compile((String) criteriasMap.get(key)));// Like
																							// Search
				} else if (key.equalsIgnoreCase("TYPE")) {
					doc.append("type", criteriasMap.get(key));
				} else if (key.equalsIgnoreCase("YEAR")) {
					doc.append("year", criteriasMap.get(key));
				}
			}
		}
		return doc;
	}

	@Override
	public List<String> getAllDirectors() {
		List<String> directors = null;
		final MongoDBConnection conn = new MongoDBConnection("movieDatabase");
		try {
			if (conn != null) {
				directors = new ArrayList<String>();
				log.debug("Got Connection!!!");

				final MongoCollection<Document> moviesCollection = conn.getMongoDBCollection("movieDetails");
				directors = moviesCollection.distinct("director", String.class).into(new ArrayList<String>());

				log.debug("Movie : " + directors);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}
		return directors;
	}

	@Override
	public List<String> getAllActors() {
		List<String> actors = null;
		final MongoDBConnection conn = new MongoDBConnection("movieDatabase");
		try {
			if (conn != null) {
				actors = new ArrayList<String>();
				log.debug("Got Connection!!!");

				final MongoCollection<Document> moviesCollection = conn.getMongoDBCollection("movieDetails");
				actors = moviesCollection.distinct("actors", String.class).into(new ArrayList<String>());

				log.debug("Movie : " + actors);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}
		return actors;
	}

	@Override
	public List<MoviesDetailTO> getMoviesForActors(List<String> actors) {
		List<MoviesDetailTO> moviesByActors = null;
		final MongoDBConnection conn = new MongoDBConnection("movieDatabase");
		try {
			if (conn != null) {
				moviesByActors = new ArrayList<MoviesDetailTO>();
				log.debug("Got Connection!!!");
				
				final MongoCollection<Document> moviesCollection = conn.getMongoDBCollection("movieDetails");
				final Document bdb = new Document();
				bdb.put("title", new Document("$in", actors));
				MongoCursor<Document> resultsCursor = moviesCollection.find(bdb).iterator();
				while (resultsCursor.hasNext()) {
					final Document result = resultsCursor.next();
					MoviesDetailTO moviesDetails = getPopulatedMovieDetailsTO(result);
					moviesByActors.add(moviesDetails);
				}
				log.debug("Movie : " + moviesByActors);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}
		return moviesByActors;
	}

	private MoviesDetailTO getPopulatedMovieDetailsTO(Document result) {
		MoviesDetailTO  moviedDetailTO = null;
		if(result!=null){
			
		}
		
		return moviedDetailTO ;
	} 

	@Override
	public List<MoviesDetailTO> getMoviesForDirectors(List<String> actors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MoviesDetailTO> getMoviesForGenres(List<String> actors) {
		// TODO Auto-generated method stub
		return null;
	}

}
