package elements;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import communication.Communicator;

public class RegisterStudent
{
	private JSONObject studentInfo;
	
	public boolean registerStudent()
	{
		boolean result = false;
		
		try
		{
			JSONObject response = Communicator.sendMessage(getStudentInfo());
			
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
			message.put("MessageType", "studentidValidation");
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

	public JSONObject getStudentInfo()
	{
		return studentInfo;
	}

	public void setStudentInfo(JSONObject studentInfo)
	{
		this.studentInfo = studentInfo;
	}
}
