package com.training.Profiles;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnetionToMongo {

    public static MongoCollection<BasicDBObject> connection() //  DBCollection
    {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("Profiles");
        MongoCollection<BasicDBObject> collection = db.getCollection("ProfileDetails", BasicDBObject.class);

        return collection;
    }
}
