package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class RegisterStudent {
    private JSONObject studentInfo;

    public boolean registerStudent() {
	boolean result = false;

	try {
	    JSONObject response = Communicator.sendMessage(getStudentInfo());

	    result = response.getBoolean("valid");

	} catch (IOException | JSONException e) {
	    e.printStackTrace();
	}

	return result;
    }

    public JSONObject getStudentInfo() {
	return studentInfo;
    }

    public void setStudentInfo(JSONObject studentInfo) {
	this.studentInfo = studentInfo;
    }
}
