package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class RegisterCompany {
    private JSONObject companyInfo;

    public boolean registerCompany() {
	boolean result = false;

	try {
	    JSONObject response = Communicator.sendMessage(getCompanyInfo());

	    result = response.getBoolean("valid");

	} catch (IOException | JSONException e) {
	    e.printStackTrace();
	}

	return result;
    }

    public JSONObject getCompanyInfo() {
	return companyInfo;
    }

    public void setCompanyInfo(JSONObject companyInfo) {
	this.companyInfo = companyInfo;
    }
}
