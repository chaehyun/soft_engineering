package elements;

import java.io.IOException;

import graphicUI.MainCompanyUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class Company
{
	private String userId;
	private JSONArray result;
	
	public Company(String userId)
	{
		setUserId(userId);
		setResult();
	}
	
	public JSONArray getResult()
	{	
		return this.result;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public void setResult()
	{
		JSONObject message = new JSONObject();
		
		try
		{
			message.put("MessageType", "getresults");
			message.put("ID", getUserId());
			
			JSONObject responseJSON = Communicator.sendMessage(message);

			result = responseJSON.getJSONArray("Results");
		}
		catch (JSONException | IOException e)
		{
			e.printStackTrace();
		}
	}
}
