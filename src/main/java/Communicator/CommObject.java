package Communicator;

import com.google.gson.Gson;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class CommObject {
    public int error = 0;
    public String message = null;
    public String mainItem = null;
    public String location = null;
    public String price = null;
    public String type = null;
    public String brand = null;
    public CommObject[] related = null;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
