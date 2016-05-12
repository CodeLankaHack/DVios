package Linguistics;

import Communicator.CommObject;
import Communicator.Message;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class Engine {
    private DBCollection collection;
    private DB db;
    private MongoClient mongoClient;

    public Engine() {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("googleIO");
        collection = db.getCollection("ai");
    }

    public String run(HashMap<String, List<String>> input) {
        // Check is the user wants to get the location
        String itemName = null;
        String itemType = null;
        List<String> nouns = new ArrayList<String>();
        List<CommObject> output = new ArrayList<CommObject>();
        if (wrbAnalyzer(input)) {
            try {
                itemName = input.get("NN").get(0);
            } catch (Exception e) {

            }
            try {
                if (itemName == null) {
                    itemName = input.get("NNS").get(0);
                }
            } catch (Exception e) {

            }
            if (itemName == null) {
                System.out.println("Item not available at the moment");
                return ErrorMessage.getError("Item not available at the moment").toString();
            }
            // read data and generate the search query
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("name", itemName);
            DBCursor cursor = collection.find(whereQuery);
            if (cursor.hasNext()) {
                CommObject object = new CommObject();
                DBObject dbObject = cursor.next();
                object.message = "You can find " + itemName + " at " + dbObject.get("location");
                object.mainItem = itemName;
                object.location = "" + dbObject.get("location");
                object.price = "" + dbObject.get("price");
                object.type = "" + dbObject.get("type");
                object.messageType = Message.Location;
                output.add(object);
                System.out.println(output.toString());
                return object.toString();
            } else {

                return ErrorMessage.getError("Item not available at the moment").toString();
            }

        }
        // Check if the user seeks WHAT kind information
        else if (wpAnalyzer(input)) {
            // Comparison CHEAPER, EXPENSIVE
            if (jjjjrAnalyzer(input)) {
                // Comparison of few items
                if (input.containsKey("NN"))
                    nouns = input.get("NN");
                if (input.containsKey("NNS"))
                    nouns.addAll(input.get("NNS"));
                // Get the cheaper item out of two

            } else if (jjsAnalyzer(input)) {
                // Get the superlative item
                if (input.containsKey("NN"))
                    nouns = input.get("NN");
                if (input.containsKey("NNS"))
                    nouns.addAll(input.get("NNS"));
                ComparatorLogic comparatorLogic = new ComparatorLogic();
                String comparator = (input.get("JJS").size() != 0) ? input.get("JJS").get(0) : null;
                if (comparator != null) {
                    return comparatorLogic.compare(nouns, comparator).toString();
                } else {
                    return ErrorMessage.getError(ErrorType.NotUnderstood).toString();
                }
//                if (nouns.size() == 0) {
//                    return ();
//                } else {
//                    BasicDBObject whereQuery = new BasicDBObject();
//                    itemType = nouns.get(0);
//                    whereQuery.put("type", itemType);
//                    BasicDBObject sort = new BasicDBObject();
//                    sort.put("price", 1);
//                    // Find the cheapest item
//                    DBCursor cursor = collection.find(whereQuery);
//                    if (cursor.hasNext()) {
//                        cursor.sort(sort);
//                        DBObject dbObject = cursor.next();
//                        CommObject commObject = new CommObject();
//                        commObject.mainItem = "" + dbObject.get("name");
//                        commObject.message = dbObject.get("brand") + " is the cheapest " + dbObject.get("type") + " type";
//                        commObject.location = "" + dbObject.get("location");
//                        commObject.price = "" + dbObject.get("price");
//                        commObject.type = "" + dbObject.get("type");
//                        commObject.brand = "" + dbObject.get("brand");
//                        System.out.println(commObject.toString());
//                        return commObject.toString();
//                    } else {
//                        return failureMessage();
//                    }
//                }
            } else {

            }
        }
        return null;

    }

    /*
    * Check for nouns
    * NN, NNS
    *
    * */
    private boolean nounAnalyzer(HashMap<String, List<String>> hashMap) {
        return hashMap.containsKey("NN") || hashMap.containsKey("NNS");
    }

    /*
    * Check for WHERE, HOW
    * WRB
    *
    * */
    private boolean wrbAnalyzer(HashMap<String, List<String>> hashMap) {
        return hashMap.containsKey("WRB");
    }

    /*
    * Check for WHAT
    * WP
    *
    * */
    private boolean wpAnalyzer(HashMap<String, List<String>> hashMap) {
        return hashMap.containsKey("WP");
    }

    /*
    * Check CHEAP, CHEAPER, EXPENSIVE
    * JJ, JJR
    *
    * */
    private boolean jjjjrAnalyzer(HashMap<String, List<String>> hashMap) {
        return hashMap.containsKey("JJ") || hashMap.containsKey("JJR");
    }

    /*
    * Check CHEAPEST, LATEST
    * JJS
    *
    * */
    private boolean jjsAnalyzer(HashMap<String, List<String>> hashMap) {
        return hashMap.containsKey("JJS");
    }
}
