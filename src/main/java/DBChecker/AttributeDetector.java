package DBChecker;

import com.mongodb.*;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class AttributeDetector {
    private DBCollection collection;
    private DB db;
    private MongoClient mongoClient;

    public AttributeDetector() {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("googleIO");
        collection = db.getCollection("ai");
    }

    public Attribute getAttribute(String noun) {
        noun = noun.toLowerCase();
        DBObject object = new BasicDBObject();
        object.put("type", java.util.regex.Pattern.compile(noun));
        DBCursor cursor = collection.find(object);
        if (cursor.hasNext()) {
            return Attribute.Type;
        } else {
            object = new BasicDBObject();
            object.put("name", java.util.regex.Pattern.compile(noun));
            cursor = collection.find(object);
            if (cursor.hasNext()) {
                return Attribute.Name;
            } else {
                object = new BasicDBObject();
                object.put("brand", java.util.regex.Pattern.compile(noun));
                cursor = collection.find(object);
                if (cursor.hasNext()) {
                    return Attribute.Brand;
                } else {
                    return null;
                }
            }
        }
    }
}
