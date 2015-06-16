package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import elements.Company;
import elements.NonTechSkills;
import elements.Reply;
import elements.Request;
import elements.Student;
import elements.TechSkills;

public class MyServerTest {
    
    @Test
    public final void testShowRegisteredStudent() {
	MyServer server = getDummy();
	String test = server.showRegisteredStudent();
	
	assertNotNull(test);
    }

    @Test
    public final void testShowRegisteredCompany() {
	MyServer server = getDummy();
	String test = server.showRegisteredCompany();
	
	assertNotNull(test);
    }

    @Test
    public final void testSendMssage() {
	MyServer server = getDummy();
	JSONObject requestMessage = new JSONObject();
	JSONArray test = null;
	
	try {
	    requestMessage.put("id", "test");
	} catch (JSONException e1) {
	    e1.printStackTrace();
	    fail();
	}
	
	try {
	    JSONObject response = server.sendMssage(requestMessage);
	    test = response.getJSONArray("data");
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(test);
    }

    @Test
    public final void testSaveMssage() {
	MyServer server = getDummy();
	JSONObject requestMessage = new JSONObject();
	boolean result = false;
	
	try {
	    requestMessage.put("source", "test");
	    requestMessage.put("destination", "student");
	    requestMessage.append("data", "testMessage");
	    JSONObject response = server.saveMssage(requestMessage);
	    result = response.getBoolean("valid");
	    
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertEquals(true, result);
	
    }

    @Test
    public final void testAddCurrentUser() {
	MyServer server = getDummy();
	boolean result1 = false;
	boolean result2 = false;
	
	result1 = server.addCurrentUser("test", "company");
	result2 = server.addCurrentUser("student", "student");
	
	assertEquals(true, result1);
	assertEquals(true, result2);
    }

    @Test
    public final void testRemoveCurrentUser() {
	MyServer server = getDummy();
	boolean result1 = false;
	boolean result2 = false;
	JSONObject result3 = null;
	JSONObject result4 = null;
	JSONObject message = new JSONObject();
	JSONObject message2 = new JSONObject();
	
	result1 = server.addCurrentUser("test", "company");
	result2 = server.addCurrentUser("student", "student");
	
	assertEquals(true, result1);
	assertEquals(true, result2);
	
	try {
	    message.put("usertype", "company");
	    message.put("ID", "test");
	    
	    message2.put("usertype", "student");
	    message2.put("ID", "student");
	    
	    result3 = server.removeCurrentUser(message);
	    result4 = server.removeCurrentUser(message2);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(result3);
	assertNotNull(result4);
	
	try {
	    assertEquals(true, result3.getBoolean("valid"));
	    assertEquals(true, result4.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testPrintCurrentUsers() {
	MyServer server = getDummy();
	String test = server.printCurrentUsers();
	
	assertNotNull(test);
    }

    @Test
    public final void testLogin() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject message2 = new JSONObject();
	JSONObject response = null;
	JSONObject response2 = null;
	
	try {
	    message.put("ID", "test");
	    message.put("pwd", "test");
	    message.put("usertype", "company");
	    
	    message2.put("ID", "student");
	    message2.put("pwd", "test");
	    message2.put("usertype", "student");
	    
	    response = server.login(message);
	    response2 = server.login(message2);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	assertNotNull(response2);
	
	try {
	    assertEquals(1, response.getInt("login_valid"));
	    assertEquals(1, response2.getInt("login_valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
    }

    @Test
    public final void testRegisterCompany() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("CompanyName", "testCompany");
	    message.put("ID", "testCompany");
	    message.put("Password", "1234");
	    message.put("Location", "Testcase");
	    message.put("Contact number", "0123456");
	    
	    response = server.registerCompany(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
    }

    @Test
    public final void testRegisterStudent() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("StudentName", "testStudent");
	    message.put("ID", "testStudent");
	    message.put("Password", "test");
	    message.put("Grade", 3);
	    message.put("Gpa", 3.3);
	    message.put("Age", 23);
	    message.put("Contact number", "123456789");
	    message.put("Sex", "male");
	    message.append("TechSkills", TechSkills.JAVA.name());
	    message.append("NonTechSkills", NonTechSkills.DRIVINGLICENCE.name());
	    
	    response = server.registerStudent(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testReceiveRequest() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("ID", "test");
	    message.put("Position", "test");
	    message.put("StartDate", "2014-1-1");
	    message.put("EndDate", "2015-1-1");
	    message.put("Payment", "30000");
	    message.put("NumberOfStudents",1);
	    message.put("DueDate", "2014-2-1");
	    message.put("Grade", 1);
	    message.put("Description", "test case");
	    message.append("TechSkills", TechSkills.JAVA.name());
	    message.append("NonTechSkills", NonTechSkills.DRIVINGLICENCE.name());
	    
	    response = server.receiveRequest(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(false, response.getBoolean("error"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testGetResults() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("ID", "test");
	    
	    response = server.getResults(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertNotNull(response.getJSONArray("Results"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testReceiveAnswer() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("ID", "student");
	    message.put("RequestID", 0);
	    message.put("Answer", Reply.State.YES.name());
	    
	    response = server.receiveAnswer(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testGetCompanyById() {
	MyServer server = getDummy();
	Company c = null;
	c = server.getCompanyById("test");
	
	assertNotNull(c);
    }

    @Test
    public final void testGetStudentById() {
	MyServer server = getDummy();
	Student s = null;
	s = server.getStudentById("student");
	
	assertNotNull(s);
    }

    @Test
    public final void testGetRequestById() {
	MyServer server = getDummy();
	int id = 0;
	Request r = null;
	r = server.getRequestById(id);
	
	assertNotNull(r);
    }

    @Test
    public final void testIdValidation() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("ID", "test1");
	    
	    response = server.idValidation(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testIdValidationforMessage() {
	MyServer server = getDummy();
	boolean result = false;
	
	result = server.idValidationforMessage("test");
	
	assertEquals(true, result);
    }

    @Test
    public final void testIsVersionValid() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("ClientVersion", "1.0");
	    
	    response = server.isVersionValid(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testStudentModiftInfo() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    JSONObject element = new JSONObject();
		 
	    element.put("userID", "student");
	    element.put("Name", "changetest");
	    element.put("ContactNumber", "testcase");
	    element.put("Gpa", 3.1);
	    element.append("TechSkills", TechSkills.JAVA.name());
	    element.append("NonTechSkills", NonTechSkills.COMMUNICATION.name());
	    message.put("student", element);
	    
	    response = server.studentModifyInfo(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testCompanyModifyInfo() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("userID", "test");
	    message.put("Name", "testChange");
	    message.put("ContactNumber", "111111");
	    message.put("Location", "locationtest");
	    
	    response = server.companyModifyInfo(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}

    }

    @Test
    public final void testGetStudent() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("userID", "student");
	    
	    response = server.getStudent(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}

    }

    @Test
    public final void testGetCompany() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("userID", "test");
	    
	    response = server.getCompany(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public final void testPasswordValidation() {
	MyServer server = getDummy();
	JSONObject message = new JSONObject();
	JSONObject response = null;
	
	try {
	    message.put("userType", "company");
	    message.put("userID", "test");
	    message.put("password", "test");
	    
	    response = server.passwordValidation(message);
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
	
	assertNotNull(response);
	
	try {
	    assertEquals(true, response.getBoolean("valid"));
	} catch (JSONException e) {
	    e.printStackTrace();
	    fail();
	}
    }

    public MyServer getDummy() {
	MyServer server = MyServer.getInstance();
	server.init();
	
	return server;
    }
}
