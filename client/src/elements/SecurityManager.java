package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class SecurityManager {
    public boolean isPasswordValidate(String userID, String passwd,
	    String userType) {
	boolean result = false;
	JSONObject message = new JSONObject();

	try {
	    message.put("MessageType", "PasswordValidation");
	    message.put("userType", userType);
	    message.put("userID", userID);
	    message.put("password", passwd);

	    JSONObject response = Communicator.sendMessage(message);

	    result = response.getBoolean("valid");

	} catch (JSONException | IOException e) {
	    e.printStackTrace();
	}
	return result;
    }
}
