package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class CompanyInfo {
    private String userId;
    private String name;
    private String location;
    private String contactNumber;

    public void getInfo(String id) {
	JSONObject message = new JSONObject();

	try {
	    message.put("MessageType", "getCompany");
	    message.put("userID", id);

	    JSONObject response = Communicator.sendMessage(message);
	    if (response.getBoolean("valid")) {
		setUserId(response.getString("userID"));
		setName(response.getString("Name"));
		setLocation(response.getString("Location"));
		setContactNumber(response.getString("ContactNumber"));
		
		System.out.println("Received data: " + userId + name + location + contactNumber);
	    }

	} catch (JSONException | IOException e) {
	    e.printStackTrace();
	}
    }
    
    public boolean setInfo(String companyName, String contactNumber, String location) {
	JSONObject message = new JSONObject();
	boolean result = false;
	
	try {
	    message.put("MessageType", "ModifyCompany");
	    message.put("userID", getUserId());
	    message.put("Name", companyName);
	    message.put("ContactNumber", contactNumber);
	    message.put("Location", location);
	    
	    JSONObject response = Communicator.sendMessage(message);
	    result = response.getBoolean("valid");
	    
	} catch (JSONException | IOException e) {
	    e.printStackTrace();
	}
	
	return result;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
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
