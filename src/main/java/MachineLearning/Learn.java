package MachineLearning;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class Learn {
    private DBCollection coll;
    private DB db;
    private MongoClient mongoClient;

    public Learn() {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("googleIO");
        coll = db.getCollection("ai");
    }

    /*
    * Adding data in to the database
    *
    * Name, Price, Location
    * */
    public boolean learn(String name, String price, String location, String brand, String type) {

        BasicDBObject object = new BasicDBObject("type", name.toLowerCase())
                .append("name", name.toLowerCase())
                .append("price", price.toLowerCase())
                .append("brand", brand.toLowerCase())
                .append("type", type.toLowerCase())
                .append("location", location.toLowerCase());
        coll.insert(object);
        return true;
    }
}
