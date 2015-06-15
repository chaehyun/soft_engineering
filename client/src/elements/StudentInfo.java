package elements;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skills.NonTechSkills;
import skills.TechSkills;
import communication.Communicator;

public class StudentInfo {
    private String userId;
    private String name;
    private String contactNumber;
    private String sex;
    private int grade;
    private float gpa;
    private int age;
    private ArrayList<TechSkills> techSkills;
    private ArrayList<NonTechSkills> nonTechSkills;
    
    public void getInfo(String id) {
	JSONObject message = new JSONObject();

	try {
	    message.put("MessageType", "getStudent");
	    message.put("userID", id);

	    JSONObject response = Communicator.sendMessage(message);
	    if (response.getBoolean("valid")) {
		JSONObject element = response.getJSONObject("Student");
		setUserId(element.getString("userID"));
		setName(element.getString("StudentName"));
		setContactNumber(element.getString("ContactNumber"));
		setSex(element.getString("Sex"));
		setGrade(element.getInt("Grade"));
		setGpa((float)element.getDouble("Gpa"));
		setAge(element.getInt("Age"));
		JSONArray tech = element.getJSONArray("TechSkills");
		ArrayList<TechSkills> techSkills = new ArrayList<TechSkills>();
		for (int i = 0; i < tech.length(); i++) {
		    String skillString = tech.getString(i);
		    TechSkills skill = TechSkills.valueOf(skillString);
		    techSkills.add(skill);
		}
		setTechSkills(techSkills);
		JSONArray nontech = element.getJSONArray("NonTechSkills");
		ArrayList<NonTechSkills> nonTechSkills = new ArrayList<NonTechSkills>();
		for (int i = 0; i < nontech.length(); i++) {
		    String skillString = nontech.getString(i);
		    NonTechSkills skill = NonTechSkills.valueOf(skillString);
		    nonTechSkills.add(skill);
		}
		setNonTechSkills(nonTechSkills);
		
		System.out.println("Received: "+ getUserId() + getName() + getContactNumber() + getSex() + getGrade() + getGpa() + getAge() + getTechSkills() + getNonTechSkills());
	    }

	} catch (JSONException | IOException e) {
	    e.printStackTrace();
	}
    }
    
    public boolean setInfo(String studentName, String contactNumber, String pgpa, ArrayList<TechSkills> tech, ArrayList<NonTechSkills> nontech) {
	JSONObject message = new JSONObject();
	boolean result = false;
	try {
	    message.put("MessageType", "ModifyStudent");
	    
	    JSONObject element = new JSONObject();
	    element.put("userID", getUserId());
	    element.put("Name", studentName);
	    element.put("ContactNumber", contactNumber);
	    element.put("Gpa", pgpa);
	    for (TechSkills skill : tech) {
		    element.append("TechSkills", skill.name());
	    }
	    for (NonTechSkills skill : nontech) {
		    element.append("NonTechSkills", skill.name());
	    }
	    
	    message.put("student", element);
	    
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

    public String getContactNumber() {
	return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<TechSkills> getTechSkills() {
        return techSkills;
    }

    public void setTechSkills(ArrayList<TechSkills> techSkills) {
        this.techSkills = techSkills;
    }

    public ArrayList<NonTechSkills> getNonTechSkills() {
        return nonTechSkills;
    }

    public void setNonTechSkills(ArrayList<NonTechSkills> nonTechSkills) {
        this.nonTechSkills = nonTechSkills;
    }
}
