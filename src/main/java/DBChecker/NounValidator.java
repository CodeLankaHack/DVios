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
        DBObject whereQuery1 = new BasicDBObject();
        DBObject whereQuery2 = new BasicDBObject();
        for (String i : nouns) {
            whereQuery1.put("name", java.util.regex.Pattern.compile(i));
            DBCursor cursor1 = collection.find();
            if (cursor1.hasNext()) {
                strings.add(i);
            }
            whereQuery2.put("type", java.util.regex.Pattern.compile(i));
            DBCursor cursor2 = collection.find(whereQuery2);
            if (cursor2.hasNext() && !strings.contains(i)) {
                strings.add(i);
            }
        }
        return strings;
    }
}
