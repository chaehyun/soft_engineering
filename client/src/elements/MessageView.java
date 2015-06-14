package elements;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class MessageView {
    private String id;
    private int msgIndex;
    private int msgTotal;
    private ArrayList<Messages> myMsg;

    public MessageView(String id) {
	setId(id);
	setMsgIndex(0);
	setMyMessage();
    }

    public void setMyMessage() {
	JSONObject message = new JSONObject();
	myMsg = new ArrayList<Messages>();

	try {
	    message.put("MessageType", "Message_send");
	    message.put("id", getId());

	    // Request to the server with current user information
	    // Server will response with JSONOjbect
	    JSONObject response = Communicator.sendMessage(message);

	    int myMessageLength = response.getInt("MsgCount");
	    setMsgTotal(myMessageLength);

	    if (myMessageLength == 0) {
		myMsg = null;
	    } else {
		JSONArray msgJSON = response.getJSONArray("data");

		for (int i = 0; i < msgJSON.length(); i++) {
		    JSONObject tmpObject = msgJSON.getJSONObject(i);
		    String sender = tmpObject.getString("Sender");
		    String msg = tmpObject.getString("Msg");
		    int idx = tmpObject.getInt("MsgIndex");
		    String sentTime = tmpObject.getString("SentTime");

		    Messages tmpMsg = new Messages(sender, msg, idx, sentTime);

		    System.out.println(tmpMsg.getMsgIndex() + ": "
			    + tmpMsg.getMsgSender() + tmpMsg.getMsgText()
			    + tmpMsg.getMsgSentTime());
		    myMsg.add(tmpMsg);
		}
	    }
	} catch (JSONException | IOException e) {
	    e.printStackTrace();
	}
    }

    public Messages getOneMessage() {
	Messages currentMsg = null;

	currentMsg = myMsg.get(getMsgIndex());

	return currentMsg;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public int getMsgIndex() {
	return msgIndex;
    }

    public void setMsgIndex(int msgIndex) {
	this.msgIndex = msgIndex;
    }

    public void decreaseMsgIndex() {
	if (this.msgIndex > 0) {
	    this.msgIndex--;
	}
    }

    public void increaseMsgIndex() {
	int msgSize = getMsgTotal();

	if (this.msgIndex + 1 < msgSize) {
	    this.msgIndex++;
	}
    }

    public int getMsgTotal() {
	return msgTotal;
    }

    public void setMsgTotal(int msgTotal) {
	this.msgTotal = msgTotal;
    }
}
