package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class VersionControl {
    private static final String currentVersion = "1.0"; // Version begins with
							// 1.0.

    /*
     * Author : Chaehyun Ra Return Type : String Description : Method for
     * returning the value of currentVersion.
     */
    public String getCurrentVersion() {
	return currentVersion;
    }

    /*
     * Return Value Description 0 : no Error, Client is using latest Version 1:
     * Version Invalid, Client is not using latest Version 2: Server Out
     */
    public int isVersionValid() {
	int result = 3;
	boolean ResultVersionValid = false;
	JSONObject message = new JSONObject();

	try {
	    message.put("MessageType", "VersionCheck");
	    message.put("ClientVersion", currentVersion);

	    // Send to Server with current Client Version Information
	    JSONObject versionCheck = Communicator.sendMessage(message);

	    // Server Response
	    ResultVersionValid = versionCheck.getBoolean("valid");
	    if (ResultVersionValid == true) {
		result = 0;
	    } else {
		result = 1;
	    }

	} catch (JSONException | IOException e) {
	    result = 2;
	    e.printStackTrace();
	}

	return result;
    }

}
