package Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class Classifier {
    private HashMap<String, List<String>> hashMap;

    public Classifier() {
        hashMap = new HashMap<String, List<String>>();
    }

    public boolean addWord(String tag, String word) {
        if (hashMap.containsKey(tag)) {
            hashMap.get(tag).add(word);
        } else {
            hashMap.put(tag, new ArrayList<String>());
            hashMap.get(tag).add(word);
        }
        return true;
    }

    public HashMap<String, List<String>> getHashMap() {
        return hashMap;
    }

    public void resetClassifier() {
        hashMap = new HashMap<String, List<String>>();
    }
}
