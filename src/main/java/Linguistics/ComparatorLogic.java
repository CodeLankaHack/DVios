package Linguistics;

import Communicator.CommObject;
import Communicator.Message;
import DBChecker.Attribute;
import DBChecker.AttributeDetector;
import DBChecker.NounValidator;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class ComparatorLogic {
    private DBCollection collection;
    private DB db;
    private MongoClient mongoClient;
    private List<String> validNouns;

    // Noun list, comparator cheapest,cheaper,
    public CommObject compare(List<String> nouns, String comparator) {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("googleIO");
        collection = db.getCollection("ai");
        // Get the nounds that are in the DB
        validNouns = (new NounValidator()).getValidNouns(nouns);
        // Detect the attribute of the first noun in the DB
        Attribute attribute = (new AttributeDetector()).getAttribute(validNouns.get(0));
        CommObject commObject = new CommObject();
        switch (attribute) {
            case Type:
                if (comparator.toLowerCase().equals("cheapest")) {
                    String item = validNouns.get(0);
                    BasicDBObject whereQuery = new BasicDBObject();
                    if (validNouns.size() == 0) {
                        return ErrorMessage.getError(ErrorType.NotFound);
                    }
                    whereQuery.put("type", item);
                    BasicDBObject sort = new BasicDBObject();
                    sort.put("price", 1);
                    // Find the cheapest item
                    DBCursor cursor = collection.find(whereQuery);
                    if (cursor.hasNext()) {
                        cursor.sort(sort);
                        DBObject dbObject = cursor.next();
                        commObject.mainItem = "" + dbObject.get("name");
                        commObject.message = dbObject.get("brand") + " is the cheapest " + dbObject.get("type") + " type.";
                        commObject.location = "" + dbObject.get("location");
                        commObject.price = "" + dbObject.get("price");
                        commObject.type = "" + dbObject.get("type");
                        commObject.brand = "" + dbObject.get("brand");
                        commObject.messageType = Message.Comparision;
                        return commObject;
                    } else {
                        return ErrorMessage.getError(ErrorType.NotUnderstood);
                    }
                }else{
                    return ErrorMessage.getError(ErrorType.NotUnderstood);
                }
            case Name:
                return ErrorMessage.getError(ErrorType.NotUnderstood);
            default:
                return ErrorMessage.getError(ErrorType.NotUnderstood);
        }
    }
}
