package Linguistics;

import Communicator.CommObject;
import Communicator.Message;
import DBChecker.Attribute;
import DBChecker.AttributeDetector;
import DBChecker.NounValidator;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class PriceLogic {
    private DBCollection collection;
    private DB db;
    private MongoClient mongoClient;
    private List<String> validNouns;

    public CommObject price(List<String> nouns) {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("googleIO");
        collection = db.getCollection("ai");
        // Get the valid nouns
        validNouns = (new NounValidator()).getValidNouns(nouns);
        if (validNouns.size() == 0) {
            return ErrorMessage.getError(ErrorType.NotFound);
        } else {
            Attribute attribute = (new AttributeDetector()).getAttribute(validNouns.get(0));
            DBObject object;
            DBCursor cursor;
            switch (attribute) {
                case Type:
                    return getPriceList("type");
                case Name:
                    // Get the price of the
                    object = new BasicDBObject();
                    object.put("name", java.util.regex.Pattern.compile(validNouns.get(0)));
                    cursor = collection.find(object);
                    if (cursor.hasNext()) {
                        DBObject dbObject = cursor.next();
                        CommObject commObject = new CommObject();
                        commObject.messageType = Message.Location;
                        commObject.mainItem = "" + dbObject.get("name");
                        commObject.price = "" + dbObject.get("price");
                        commObject.type = "" + dbObject.get("type");
                        commObject.brand = "" + dbObject.get("brand");
                        commObject.message = "The price of " + commObject.mainItem + " is Rs." + commObject.price;
                        return commObject;
                    } else {
                        return ErrorMessage.getError(ErrorType.NotFound);
                    }
                case Brand:
                    return getPriceList("brand");
                default:
                    return ErrorMessage.getError(ErrorType.NotFound);
            }
        }
    }

    private CommObject getPriceList(String type) {
        BasicDBObject object = new BasicDBObject();
        object.put(type, java.util.regex.Pattern.compile(validNouns.get(0)));
        DBCursor cursor = collection.find(object);
        CommObject outObject = new CommObject();
        outObject.messageType = Message.PriceList;
        List<CommObject> commObjects = new ArrayList<CommObject>();
        while (cursor.hasNext()) {
            DBObject object1 = cursor.next();
            CommObject commObject = new CommObject();
            commObject.messageType = Message.Location;
            commObject.mainItem = "" + object1.get("name");
            commObject.price = "" + object1.get("price");
            commObject.type = "" + object1.get("type");
            commObject.brand = "" + object1.get("brand");
            commObject.message = "The price of " + commObject.mainItem + " is Rs." + commObject.price;
            commObjects.add(commObject);
        }
        if (commObjects.size() != 0) {
            CommObject[] arr = new CommObject[commObjects.size()];
            arr = commObjects.toArray(arr);
            outObject.related = arr;
            return outObject;
        } else {
            return ErrorMessage.getError(ErrorType.NotFound);
        }
    }
}
