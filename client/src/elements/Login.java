package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class Login {
    private String id;
    private String password;
    private String userType;

    // Default Constructor
    public Login(String id, String password, String userType) {
	this.setId(id);
	this.setPassword(password);
	this.setUserType(userType);
    }

    /*
     * Return Value Description 0 : no Error, Login Request was succeeded 1 :
     * Version Invalid 3 : Server Out 4 : Server Denied with duplicated Login
     * Request 5 : Server Denied with Wrong Password
     */
    public int loginRequest() {
	int result = 6;
	int loginResult;
	int versionValid;
	JSONObject message = new JSONObject();

	// 1st step of Login Request
	// Version Validation
	versionValid = (new VersionControl()).isVersionValid();

	switch (versionValid) {
	case 0:
	    try {
		message.put("MessageType", "login");
		message.put("ID", getId());
		message.put("pwd", getPassword());
		message.put("usertype", getUserType());

		// Send to Server with Login Information
		JSONObject response = Communicator.sendMessage(message);

		// Server Response
		loginResult = response.getInt("login_valid");

		switch (loginResult) {
		case 1:
		    result = 0;
		    break;
		case 2:
		    result = 4;
		    break;
		case 3:
		    result = 5;
		    break;
		default:
		    result = 3;
		    break;
		}

	    } catch (JSONException | IOException e) {
		result = 3;
		e.printStackTrace();
	    }
	    break;
	case 1:
	    result = 1;
	    break;
	default:
	    result = 3;
	    break;
	}

	return result;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUserType() {
	return userType;
    }

    public void setUserType(String userType) {
	this.userType = userType;
    }
}
