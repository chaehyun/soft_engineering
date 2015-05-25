package elements;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class MessageView
{
	private ArrayList<String> myMessage;
	private String id;
	private int msgIndex;
	
	public MessageView(String id)
	{
		setId(id);
		setMsgIndex(0);
		setMyMessage();
	}

	public ArrayList<String> getMyMessage()
	{
		return myMessage;
	}

	public void setMyMessage()
	{
		JSONObject message = new JSONObject();
		myMessage = new ArrayList<String>();
		
		try
		{
			message.put("MessageType",  "Message_send");
			message.put("id", getId());
			
			JSONObject response = Communicator.sendMessage(message);
			JSONArray msgJSON = response.getJSONArray("data");
			
			System.out.println(msgJSON.length());
			
			for (int i = 0; i < msgJSON.length(); i++)
			{
				String tmpMsg = msgJSON.getString(i);
				System.out.println(tmpMsg);
				myMessage.add(tmpMsg);
			}
			
		}
		catch (JSONException | IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getOneMessage()
	{
		String currentMsg = null;
		
		currentMsg = myMessage.get(getMsgIndex());
		
		increaseMsgIndex();
		
		return currentMsg;
	}

	public String getPrevMessage()
	{
		String currentMsg = null;
		
		decreaseMsgIndex();
		
		currentMsg = myMessage.get(getMsgIndex());
		
		return currentMsg;
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getMsgIndex()
	{
		return msgIndex;
	}

	public void setMsgIndex(int msgIndex)
	{
		this.msgIndex = msgIndex;
	}
	
	public void decreaseMsgIndex()
	{
		if (this.msgIndex > 0)
		{
			this.msgIndex--;
		}
	}
	
	public void increaseMsgIndex()
	{
		int msgSize = myMessage.size();
		
		if (this.msgIndex + 1 < msgSize)
		{
			this.msgIndex++;
		}
	}
}
