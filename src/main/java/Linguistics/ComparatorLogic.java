package Linguistics;

import Communicator.CommObject;
import DBChecker.Attribute;
import DBChecker.AttributeDetector;
import DBChecker.NounValidator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class ComparatorLogic {
    private DBCollection collection;

    // Noun list, comparator cheapest,cheaper,
    public CommObject compare(List<String> nouns, String comparator) {
        // Get the nounds that are in the DB
        List<String> validNouns = (new NounValidator()).getValidNouns(nouns);
        // Detect the attribute of the first noun in the DB
        System.out.println(Arrays.toString(validNouns.toArray()));
        Attribute attribute = (new AttributeDetector()).getAttribute(validNouns.get(0));
        CommObject commObject = new CommObject();
        switch (attribute) {
            case Type:
                if (comparator.toLowerCase().equals("cheapest")) {
                    String item = nouns.get(0);
                    BasicDBObject whereQuery = new BasicDBObject();
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
                        System.out.println(commObject.toString());
                    } else {
                        commObject.error = 1;
                        commObject.message = "We couldn't identify what you said. Please try again.";
                        System.out.println(commObject.toString());
                    }
                }
                break;
            case Name:
                commObject.error = 1;
                commObject.message = "Please try using a type name, eg: What is the cheapest soap.";
                System.out.println(commObject.toString());
                break;
            default:
                commObject.error = 1;
                commObject.message = "I could not understand your query please try again.";
                System.out.println(commObject.toString());
                break;
        }
        return null;
    }
}
