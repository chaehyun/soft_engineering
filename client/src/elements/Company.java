package elements;

import java.io.IOException;

import graphicUI.MainCompanyUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class Company {
    private String userId;
    private String name;
    private String location;
    private String contactNumber;
    private JSONArray result;

    public Company(String userId) {
	setUserId(userId);
	setResult();
    }

    public JSONArray getResult() {
	return this.result;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public void setResult() {
	JSONObject message = new JSONObject();
	this.result = null;

	try {
	    message.put("MessageType", "getresults");
	    message.put("ID", getUserId());

	    JSONObject responseJSON = Communicator.sendMessage(message);

	    result = responseJSON.getJSONArray("Results");
	} catch (JSONException | IOException e) {
	    e.printStackTrace();
	}
    }

    public boolean logOut() {
	JSONObject message = new JSONObject();
	boolean result = false;

	try {
	    message.put("MessageType", "LogOff");
	    message.put("usertype", "company");
	    message.put("ID", getUserId());

	    JSONObject response = Communicator.sendMessage(message);

	    result = response.getBoolean("valid");

	} catch (JSONException | IOException e) {
	    e.printStackTrace();
	}

	return result;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getContactNumber() {
	return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
    }
}
