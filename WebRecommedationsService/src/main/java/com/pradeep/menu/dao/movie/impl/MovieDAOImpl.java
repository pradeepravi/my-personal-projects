package com.pradeep.menu.dao.movie.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonString;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.pradeep.menu.bean.to.movie.MovieTO;
import com.pradeep.menu.bean.to.movie.MoviesDetailTO;
import com.pradeep.menu.dao.movie.MovieDAO;
import com.pradeep.menu.util.exception.WebRecommendationsException;
import com.pradeep.menu.util.mongodb.MongoDBConnection;
 
@Component("movieDAOImpl")
public class MovieDAOImpl implements MovieDAO {
	

	static enum MovieDetailsFields {
		TITLE("title"), ACTORS("actors"), DIRECTOR("director"), GENRES("genres"), OBJECT_ID("_id"), YEAR("year"), TYPE(
				"type"), IMDB("imdb"), POSTER("poster");
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
	public List<MoviesDetailTO> getMovie(MoviesDetailTO searchParameters) {
		final MongoDBConnection conn = new MongoDBConnection(MongoDBDatabase.MOVIES_DB.getDBName());
		List<MoviesDetailTO> resultMovies = null;
		try {
			if (conn != null) {
				resultMovies = new ArrayList<MoviesDetailTO>();
				log.debug("Got Connection!!!");
				final MongoCollection<Document> moviesCollection = conn
						.getMongoDBCollection(MongoDBCollections.MOVIES_DETAILS.getActualCollectionsName());
				final Document filter = constructSearchParametersDoc(searchParameters);
				ArrayList<Document> resultsItr = moviesCollection.find(filter).into(new ArrayList<Document>());
				for (Document doc : resultsItr) {
					MoviesDetailTO movie = getPopulatedMovieDetailsTO(doc);
					resultMovies.add(movie);
				}
				log.debug("Movie : " + resultMovies);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace(); 
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
						.getMongoDBCollection(MongoDBCollections.MOVIES_DETAILS.getActualCollectionsName());
				final Document bdb = new Document();
				bdb.append(MovieDetailsFields.TITLE.getActualFieldName(), new Document(
						"$in", titles.stream().map(m -> Pattern.compile(m)).collect(Collectors.toList())));				
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

		return movies;
	}

	@Override
	public Set<String> getAllGenreNames() {
		final Document projections = new Document()
				.append(MovieDetailsFields.GENRES.getActualFieldName(), new Integer(1))
				.append(MovieDetailsFields.OBJECT_ID.getActualFieldName(), new Integer(0));
		final Set<String> genres = fetchAllLists(projections);
		return genres;
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	private MovieTO getPopulatedMovieTO(Document document) {
		MovieTO movie = null;
		if (document != null) {
			movie = new MovieTO();
			movie.setTitle(document.getString(MovieDetailsFields.TITLE.getActualFieldName()));
			movie.setYear(String.valueOf(document.getInteger(MovieDetailsFields.YEAR.getActualFieldName())));
			movie.setType(document.getString(MovieDetailsFields.TYPE.getActualFieldName()));		
			
			final MoviesDetailTO movieDetails = new MoviesDetailTO();//TODO Populate
			final ArrayList<String > genres = (ArrayList<String>)document.get(MovieDetailsFields.GENRES.getActualFieldName());
			
			final ArrayList<String > actors = (ArrayList<String>)document.get(MovieDetailsFields.ACTORS.getActualFieldName());
			final List<String > directors =Arrays.asList(document.getString(MovieDetailsFields.DIRECTOR.getActualFieldName()));
			
			//final ArrayList<String > genres = (ArrayList<String>)document.get(MovieDetailsFields.GENRES.getActualFieldName());
			
			movieDetails.setGenres(genres.toArray(new String [genres.size()]));  
			movieDetails.setActors(actors.toArray(new String [actors.size()]));  
			movieDetails.setDirectors(directors.toArray(new String [directors.size()]));  
			//movieDetails.set(genres.toArray(new String [genres.size()]));  
			
			movie.setMovieDetails(movieDetails);
		}
		return movie;
	}

	private Document constructSearchParametersDoc(MoviesDetailTO searchParameters) {
		final Document doc = new Document();
		/*if (criteriasMap != null) {
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
		}*/
		
		if(searchParameters!=null){
			if (searchParameters.getTitle() != null) {
				doc.append(MovieDetailsFields.TITLE.getActualFieldName(),
						Pattern.compile((String) searchParameters.getTitle()));// Like
			} else if (searchParameters.getYear() != null) {
				doc.append(MovieDetailsFields.YEAR.getActualFieldName(), searchParameters.getYear());
			} 
			
			//TODO Cover the ID search
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
	public List<MoviesDetailTO> getMoviesForGenres(List<String> genres) {
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
		//TODO Properly populate this 
		MoviesDetailTO moviedDetailTO = null;
		if (result != null) {
			moviedDetailTO = new MoviesDetailTO();
			moviedDetailTO.setId(result.getObjectId(MovieDetailsFields.OBJECT_ID.getActualFieldName()).toString());
			moviedDetailTO.setTitle(result.getString(MovieDetailsFields.TITLE.getActualFieldName()));
			moviedDetailTO.setGenres(this.getStringArrayForArayList(MovieDetailsFields.GENRES.getActualFieldName(), result));
			moviedDetailTO.setActors(this.getStringArrayForArayList(MovieDetailsFields.ACTORS.getActualFieldName(), result));
			//moviedDetailTO.setWriters(this.getStringArrayForArayList(MovieDetailsFields..getActualFieldName(), result));
			String [] posters = {
					result.getString(MovieDetailsFields.POSTER.getActualFieldName())
			};
			moviedDetailTO.setPosters(posters);
			
			String [] director = {
					result.getString(MovieDetailsFields.DIRECTOR.getActualFieldName())
			};
			moviedDetailTO.setDirectors(director);
			//moviedDetailTO.setDirectors(this.getStringArrayForArayList(MovieDetailsFields.PLOT.getActualFieldName(), result));
			
		}
		return moviedDetailTO;
	}
	
	private String [] getStringArrayForArayList(String resultKey, Document result){
		//System.out.println("resultKey:"+resultKey);
		List<String> listOfString = (List<String>)result.get(resultKey);
		String [] stringArray= new String[listOfString.size()];
		stringArray= listOfString.toArray(stringArray);
		//moviedDetailTO.setGenres(genres);
		return stringArray;
	}
	
	@Override
	public List<MoviesDetailTO> getMoviesByRating(Set<String> genres, Set<String> directors, Set<String> actors) throws WebRecommendationsException { 
		return this.getMoviesByRating(genres, directors, actors, null);		
	}


	@Override
	public List<MoviesDetailTO> getMoviesByRating(Set<String> genres, Set<String> directors, Set<String> actors, Set <String> excludeMovies) 
			throws WebRecommendationsException { 
		final Document newQuery = new Document();
		boolean isAtLeastOneParamsPassed = false;
		List<MoviesDetailTO> finalResults = null; 
		if(genres!=null && genres.size()>0){
			newQuery.append(MovieDetailsFields.GENRES.getActualFieldName(), 
					new Document().append("$in", genres.stream().
							filter(m -> m!=null).
								map(m -> Pattern.compile(m)).
									collect(Collectors.toList())));
			isAtLeastOneParamsPassed = true;
		}
		
		if(directors!=null && directors.size()>0){
			newQuery.append(MovieDetailsFields.DIRECTOR.getActualFieldName(), 
					new Document().append("$in", directors.stream().
							filter(m -> m!=null).
								map(m -> Pattern.compile(m)).
									collect(Collectors.toList())));
			
			isAtLeastOneParamsPassed = true;
		}
		
		if(actors!=null && actors.size()>0){
			newQuery.append(MovieDetailsFields.ACTORS.getActualFieldName(),
					new Document().append("$in", actors.stream().
							filter(m -> m!=null).
								map(m -> Pattern.compile(m)).
									collect(Collectors.toList())));
			isAtLeastOneParamsPassed = true;
		}
		
		if(excludeMovies!=null && excludeMovies.size()>0){
			newQuery.append(MovieDetailsFields.TITLE.getActualFieldName(),
					new Document().append("$nin", excludeMovies.stream().
							filter(m -> m!=null).
								map(m -> Pattern.compile(m)).
									collect(Collectors.toList())));
		}
		
		if(isAtLeastOneParamsPassed){
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
		}	else {
			throw new WebRecommendationsException("No Parameters available, to generate recommendations based on");
		}
		
		return finalResults;
	}
	
	
	
}
