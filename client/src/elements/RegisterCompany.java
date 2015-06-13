package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class RegisterCompany
{
	private JSONObject companyInfo;
	
	public boolean registerCompany()
	{
		boolean result = false;
		
		try
		{
			JSONObject response = Communicator.sendMessage(getCompanyInfo());
			
			result = response.getBoolean("valid");
			
		}
		catch (IOException | JSONException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean isIdValidate(String id)
	{
		JSONObject message = new JSONObject();
		boolean valid = false;

		// Server will check current ID Validation
		try
		{
			message.put("MessageType", "companyidValidation");
			message.put("ID", id);

			JSONObject response = Communicator.sendMessage(message);

			valid = response.getBoolean("valid");

		}
		catch (JSONException | IOException e1)
		{
			e1.printStackTrace();
		}
		
		return valid;
	}

	public JSONObject getCompanyInfo()
	{
		return companyInfo;
	}

	public void setCompanyInfo(JSONObject companyInfo)
	{
		this.companyInfo = companyInfo;
	}
}
