package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class MessageSend
{
	private String id;

	public MessageSend(String id)
	{
		setId(id);
	}
	
	public boolean sendMsg(String dst, String msg)
	{
		JSONObject message = new JSONObject();
		boolean result = false;
		
		try
		{
			message.put("MessageType", "Message_receive");
			message.put("source", getId());
			message.put("destination", dst);
			message.append("data", msg);
			
			JSONObject response = Communicator.sendMessage(message);
			
			result = response.getBoolean("valid");
			
		}
		catch (JSONException | IOException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	

}
