package DBChecker;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class NounValidator {
    private DBCollection collection;
    private DB db;
    private MongoClient mongoClient;

    public NounValidator() {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("googleIO");
        collection = db.getCollection("ai");
    }

    public List<String> getValidNouns(List<String> nouns) {
        List<String> strings = new ArrayList<String>();
        DBObject whereQuery = new BasicDBObject();
        for (String i : nouns) {
            whereQuery.put("name", java.util.regex.Pattern.compile(i));
            DBCursor cursor = collection.find(whereQuery);
            if (cursor.hasNext()) {
                strings.add(i);
            } else {
                whereQuery.removeField("name");
                whereQuery.put("type", java.util.regex.Pattern.compile(i));
                if (cursor.hasNext()) {
                    strings.add(i);
                }
            }
        }
        return strings;
    }
}
