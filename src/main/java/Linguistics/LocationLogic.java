package Linguistics;

import Communicator.CommObject;
import DBChecker.NounValidator;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.util.List;

/**
 * Created by anuradhawick on 5/13/16.
 */
public class LocationLogic {
    private DBCollection collection;
    private DB db;
    private MongoClient mongoClient;
    private List<String> validNouns;

    public CommObject location(List<String> nouns) {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("googleIO");
        collection = db.getCollection("ai");
        // Get the valid nouns
        validNouns = (new NounValidator()).getValidNouns(nouns);
        return null;
    }
}
