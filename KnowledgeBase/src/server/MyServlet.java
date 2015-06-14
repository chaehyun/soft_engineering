package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class KnowledgeBaseServlet
 */
@WebServlet("/KnowledgeBaseServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// Auto-generated method stub
	response.getWriter().println("works");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	try {
	    PrintWriter out = response.getWriter();

	    JSONObject jsonResponse = new JSONObject();
	    // Reading the request
	    StringBuffer buffer = new StringBuffer();
	    String line = null;
	    BufferedReader reader = request.getReader();

	    while ((line = reader.readLine()) != null) {
		buffer.append(line);
	    }

	    JSONObject requestMessage = new JSONObject(buffer.toString());
	    System.out.println(buffer);

	    jsonResponse = getResponse(requestMessage);

	    System.out.println(jsonResponse);
	    out.println(jsonResponse.toString());
	} catch (IOException | JSONException | ParseException e2) {
	    e2.printStackTrace();
	}
    }

    // JSONObject message type and key is defined here.
    private JSONObject getResponse(JSONObject requestMessage)
	    throws JSONException, ParseException {
	// Parsing MessageType
	String messageType = requestMessage.getString("MessageType");

	// Handling with keys
	switch (messageType) {
	case "login":
	    // record current add user
	    // MyServer.getInstance().addCurrentUser(requestMessage);

	    return MyServer.getInstance().login(requestMessage);
	case "companyregister":
	    return MyServer.getInstance().registerCompany(requestMessage);
	case "studentregister":
	    return MyServer.getInstance().registerStudent(requestMessage);
	case "newrequest":
	    return MyServer.getInstance().receiveRequest(requestMessage);
	case "getresults":
	    return MyServer.getInstance().getResults(requestMessage);
	case "getrequests":
	    return MyServer.getInstance().getRequests(requestMessage);
	case "answer":
	    return MyServer.getInstance().receiveAnswer(requestMessage);
	case "idValidation":
	    return MyServer.getInstance().idValidation(requestMessage);
	case "VersionCheck":
	    return MyServer.getInstance().isVersionValid(requestMessage);
	case "LogOff":
	    return MyServer.getInstance().removeCurrentUser(requestMessage);
	case "Message_receive":
	    return MyServer.getInstance().saveMssage(requestMessage);
	case "Message_send":
	    return MyServer.getInstance().sendMssage(requestMessage);
	case "PasswordValidation":
	    return MyServer.getInstance().passwordValidation(requestMessage);
	default:
	    JSONObject responseMessage = new JSONObject();
	    responseMessage.put("Error", "Invalid message type");
	    return responseMessage;
	}

    }
}
