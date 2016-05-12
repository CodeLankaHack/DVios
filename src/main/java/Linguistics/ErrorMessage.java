package Linguistics;

import Communicator.CommObject;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class ErrorMessage {
    private ErrorMessage() {
    }

    public static CommObject getError(ErrorType errorType) {
        CommObject commObject = new CommObject();
        commObject.error = 1;
        switch (errorType) {
            case NotFound:
                commObject.message = "We couldn't find the requested product. Please try again.";
                break;
            case NotUnderstood:
                commObject.message = "We couldn't identify what you said. Please try again.";
                break;
            default:
                break;
        }
        return commObject;
    }

    public static CommObject getError(String error) {
        CommObject commObject = new CommObject();
        commObject.error = 1;
        commObject.message = error;

        return commObject;
    }
}
