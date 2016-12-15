package com.pradeep.menu.dao.movie.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonString;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.util.mongodb.MongoDBConnection;

public class MovieDAOImpl implements MovieDAO {

	static enum MovieDetailsFields {
		TITLE("title"), ACTORS("actors"), DIRECTOR("director"), GENRES("genres"), OBJECT_ID("_id"), YEAR("year"), TYPE(
				"type"), IMDB("imdb");
		private String actualFieldName;

		MovieDetailsFields(String str) {
			this.actualFieldName = str;
		}

		public String getActualFieldName() {
			return actualFieldName;
		}
	}

	static enum MongoDBCollections {
		MOVIES("movies"), MOVIES_DETAILS("movieDetails");
		private String collectionName;

		MongoDBCollections(String str) {
			this.collectionName = str;
		}

		public String getActualCollectionsName() {
			return collectionName;
		}
	}

	static enum MongoDBDatabase {
		MOVIES_DB("movieDatabase");
		private String dbName;

		MongoDBDatabase(String str) {
			this.dbName = str;
		}

		public String getDBName() {
			return dbName;
		}
	}

	static final Logger log = LogManager.getLogger(MovieDAOImpl.class);

	@Override
	public List<MovieTO> getMovie(Map<String, Object> filtersMap) {
		final MongoDBConnection conn = new MongoDBConnection(MongoDBDatabase.MOVIES_DB.getDBName());
		List<MovieTO> resultMovies = null;
		try {
			if (conn != null) {
				resultMovies = new ArrayList<MovieTO>();
				log.debug("Got Connection!!!");
				final MongoCollection<Document> moviesCollection = conn
						.getMongoDBCollection(MongoDBCollections.MOVIES.getActualCollectionsName());
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
		final MongoDBConnection conn = new MongoDBConnection(MongoDBDatabase.MOVIES_DB.getDBName());
		try {
			if (conn != null) {
				movies = new ArrayList<MovieTO>();
				log.debug("Got Connection!!!");
				final MongoCollection<Document> moviesCollection = conn
						.getMongoDBCollection(MongoDBCollections.MOVIES.getActualCollectionsName());
				final Document bdb = new Document();
				bdb.append(MovieDetailsFields.TITLE.getActualFieldName(), new Document("$in", titles));				
				MongoCursor<Document> resultsCursor = moviesCollection.find(bdb).iterator();
				while (resultsCursor.hasNext()) {
					final Document result = resultsCursor.next();
					MovieTO movie = getPopulatedMovieTO(result);
					movies.add(movie);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			conn.closeConnection();
		}

		return null;
	}

	@Override
	public Set<String> getAllGenreNames() {
		final Document projections = new Document()
				.append(MovieDetailsFields.GENRES.getActualFieldName(), new Integer(1))
				.append(MovieDetailsFields.OBJECT_ID.getActualFieldName(), new Integer(0));
		final Set<String> genres = fetchAllLists(projections);
		return genres;
	}

	private Set<String> fetchAllLists(final Document projections) {
		final Set<String> genres = new TreeSet<>();
		final MongoDBConnection conn = new MongoDBConnection(MongoDBDatabase.MOVIES_DB.getDBName());
		try {
			if (conn != null) {

				log.debug("Got Connection!!!");
				final MongoCollection<Document> moviesCollection = conn
						.getMongoDBCollection(MongoDBCollections.MOVIES_DETAILS.getActualCollectionsName());
				final FindIterable<Document> results = moviesCollection.find().projection(projections);
				MongoCursor<Document> resultsCursor = results.iterator();
				while (resultsCursor.hasNext()) {
					Iterator<Object> itr = resultsCursor.next().values().iterator();
					while (itr.hasNext()) {
						List<String> l = (List<String>) itr.next();
						genres.addAll(l);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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
			movie.setTitle(document.getString(MovieDetailsFields.TITLE.getActualFieldName()));
			movie.setYear(String.valueOf(document.getInteger(MovieDetailsFields.YEAR.getActualFieldName())));
			movie.setType(document.getString(MovieDetailsFields.TYPE.getActualFieldName()));
			movie.setImdbRating(document.getString(MovieDetailsFields.IMDB.getActualFieldName()));
		}
		return movie;
	}

	private Document getCriteriaPopulatedDocument(Map<String, Object> criteriasMap) {
		Document doc = null;
		if (criteriasMap != null) {
			doc = new Document();
			for (String key : criteriasMap.keySet()) {
				if (key.equalsIgnoreCase(MovieDetailsFields.TITLE.getActualFieldName())) {
					doc.append(MovieDetailsFields.TITLE.getActualFieldName(),
							Pattern.compile((String) criteriasMap.get(key)));// Like
					// Search
				} else if (key.equalsIgnoreCase("TYPE")) {
					doc.append(MovieDetailsFields.TYPE.getActualFieldName(), criteriasMap.get(key));
				} else if (key.equalsIgnoreCase("YEAR")) {
					doc.append(MovieDetailsFields.YEAR.getActualFieldName(), criteriasMap.get(key));
				}
			}
		}
		return doc;
	}

	@Override
	public Set<String> getAllDirectors() {
		return fetchAllSetForField(MovieDetailsFields.DIRECTOR.getActualFieldName());
	}

	@Override
	public Set<String> getAllActors() {
		return fetchAllSetForField(MovieDetailsFields.ACTORS.getActualFieldName());
	}

	@Override
	public List<MoviesDetailTO> getMoviesForActors(List<String> actors) {

		final List<Pattern> likeSearchList = new ArrayList<>();
		actors.forEach(a -> {
			likeSearchList.add(java.util.regex.Pattern.compile(a));
		});
		List<MoviesDetailTO> moviesByActors = null;
		final MongoDBConnection conn = new MongoDBConnection(MongoDBDatabase.MOVIES_DB.getDBName());
		try {
			if (conn != null) {
				moviesByActors = new ArrayList<MoviesDetailTO>();
				log.debug("Got Connection!!!");

				final MongoCollection<Document> moviesCollection = conn
						.getMongoDBCollection(MongoDBCollections.MOVIES_DETAILS.getActualCollectionsName());
				final Document bdb = new Document();
				bdb.put(MovieDetailsFields.ACTORS.getActualFieldName(), new Document("$in", likeSearchList));
				MongoCursor<Document> resultsCursor = moviesCollection.find(bdb).iterator();
				while (resultsCursor.hasNext()) {
					final Document result = resultsCursor.next();
					MoviesDetailTO moviesDetails = getPopulatedMovieDetailsTO(result);
					moviesByActors.add(moviesDetails);
				}
				log.debug("Movie : " + moviesByActors);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}
		return moviesByActors;
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

	private Set<String> fetchAllSetForField(String searchField) {
		Set<String> directors = null;
		final MongoDBConnection conn = new MongoDBConnection(MongoDBDatabase.MOVIES_DB.getDBName());
		try {
			if (conn != null) {
				log.debug("Got Connection!!!");

				final MongoCollection<Document> moviesCollection = conn
						.getMongoDBCollection(MongoDBCollections.MOVIES_DETAILS.getActualCollectionsName());
				List<BsonValue> directors1 = moviesCollection.distinct(searchField, BsonValue.class)
						.into(new ArrayList<BsonValue>());
				directors = new TreeSet<>();
				for (BsonValue temp : directors1) {
					if (temp.getBsonType() == BsonType.STRING) {
						directors.add(((BsonString) temp).getValue());
					}
				}
				log.debug("Movie : " + directors);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.closeConnection();
		}
		return directors;
	}

	private MoviesDetailTO getPopulatedMovieDetailsTO(Document result) {
		MoviesDetailTO moviedDetailTO = null;
		if (result != null) {
			moviedDetailTO = new MoviesDetailTO();
			moviedDetailTO.setId(result.getObjectId(MovieDetailsFields.OBJECT_ID.getActualFieldName()).toString());
			moviedDetailTO.setTitle(result.getString(MovieDetailsFields.TITLE.getActualFieldName()));
		}
		return moviedDetailTO;
	}

	@Override
	public List<MoviesDetailTO> getMoviesByRating(List<String> genres, List<String> directors, List<String> actors) {
		final Document newQuery = new Document();
		boolean isAtLeastOneParamsListPassed = false;
		List<MoviesDetailTO> finalResults = null;
		if(genres!=null && genres.size()>0){
			newQuery.append(MovieDetailsFields.GENRES.getActualFieldName(), 
					new Document().append("$in", genres.stream().map(m -> Pattern.compile(m)).collect(Collectors.toList())));
			isAtLeastOneParamsListPassed = true;
		}
		
		if(directors!=null && directors.size()>0){
			newQuery.append(MovieDetailsFields.DIRECTOR.getActualFieldName(), 
					new Document().append("$in", directors.stream().map(m -> Pattern.compile(m)).collect(Collectors.toList())));
			isAtLeastOneParamsListPassed = true;
		}
		
		if(actors!=null && actors.size()>0){
			newQuery.append(MovieDetailsFields.ACTORS.getActualFieldName(),
					new Document().append("$in", actors.stream().map(m -> Pattern.compile(m)).collect(Collectors.toList())));
			isAtLeastOneParamsListPassed = true;
		}
		
		if(isAtLeastOneParamsListPassed){
			final MongoDBConnection conn = new MongoDBConnection(MongoDBDatabase.MOVIES_DB.getDBName());
			try {
				if (conn != null) {
					log.debug("Got Connection!!!");

					final MongoCollection<Document> moviesCollection = conn
							.getMongoDBCollection(MongoDBCollections.MOVIES_DETAILS.getActualCollectionsName());
					List<Document> result = moviesCollection.find(newQuery).sort(new Document().append("imdb.rating", 1))
							.into(new ArrayList<>());
					final List<MoviesDetailTO> results = new ArrayList<>();
					if(result!=null){
						result.forEach(m->{
							MoviesDetailTO mTemp = this.getPopulatedMovieDetailsTO(m);
							results.add(mTemp);
						});
						
						finalResults  = results;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.closeConnection();
			}
		}	
		
		return finalResults;
	}
	
	
	
}
