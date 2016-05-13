package Test;

import DBChecker.NounValidator;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class Test1 {
    public static void main(String[] args) {
        NounValidator nounValidator =new NounValidator();
        List<String> strings=new ArrayList<String>();
        strings.add("soap");
        System.out.println(Arrays.toString(nounValidator.getValidNouns(strings).toArray()));
        
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        DB db = mongoClient.getDB("googleIO");
//        DBCollection collection = db.getCollection("ai");
//
//        DBObject obj = new BasicDBObject();
//        String i = "purple";
//        obj.put("name",java.util.regex.Pattern.compile(i));
//        DBCursor c =collection.find(obj);
//        while(c.hasNext()){
//            System.out.println(c.next());
//        }
    }
}
