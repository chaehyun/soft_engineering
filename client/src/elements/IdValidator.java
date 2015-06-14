package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class IdValidator {
    public boolean isIdValidate(String id) {
	JSONObject message = new JSONObject();
	boolean valid = false;

	// Server will check current ID Validation
	try {
	    message.put("MessageType", "idValidation");
	    message.put("ID", id);

	    JSONObject response = Communicator.sendMessage(message);

	    valid = response.getBoolean("valid");

	} catch (JSONException | IOException e1) {
	    e1.printStackTrace();
	}

	return valid;
    }
}
