package com.pradeep.menu.util.mongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
	final Logger log = LogManager.getLogger(this);
	private final String mongoDBURL = "mongodb://localhost";
	private MongoClient mongoClient;
	final MongoDatabase mongoDB;

	public MongoDBConnection(String mongoDBName) {
		mongoClient = new MongoClient(new MongoClientURI(mongoDBURL));
		mongoDB = mongoClient.getDatabase(mongoDBName);
	}

	public MongoCollection<Document> getMongoDBCollection(String collectionName) {
		final MongoCollection<Document> collection = mongoDB.getCollection(collectionName);

		return collection;
	}

	public void closeConnection() {
		try {
			if(mongoClient!=null){
				mongoClient.close();
			}
		} catch (Exception e) {
			log.error("Error When trying to close the Mongo DB collection - "
					+ "" + e.getMessage(), e);
		}
	}

}
