package server;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import elements.AutoSerializeList;
import elements.Company;
import elements.LogfileManagement;
import elements.Message;
import elements.NonTechSkills;
import elements.Reply;
import elements.Request;
import elements.Student;
import elements.TechSkills;
import elements.TimeManager;
import elements.User;
import elements.VersionControl;
import graphicUI.ServerMainUI;

public class MyServer {
    /* Definition for Server Data File names */
    private final static String companyFileName = "companies.ser";
    private final static String studentFileName = "students.ser";
    private final static String requestsFileName = "requests.ser";
    private final static String messageFilename = "Messagebox.ser";

    private AutoSerializeList<Company> companies;
    private AutoSerializeList<Student> students;
    private AutoSerializeList<Request> requests;
    private AutoSerializeList<Message> messages;

    private ArrayList<String> current_companies;
    private ArrayList<String> current_students;

    private LogfileManagement log;

    private static MyServer instance = null;

    private ServerMainUI mainWindow;

    public ServerMainUI getMainWindow() {
	return mainWindow;
    }

    public String ShowRegisteredStudent() {
	String result;
	System.out.println("[ Registered Company List ]");
	result = "[ Registered Company List ]\n";
	for (Student s : students) {
	    String id = s.getId();
	    String contact = s.getContactNumber();
	    String name = s.getName();
	    String age = Integer.toString(s.getAge());
	    String grade = Integer.toString(s.getGrade());
	    float gpa = s.getGpa();
	    String sex = s.getSex();
	    String tech = "";
	    String nontech = "";
	    for (TechSkills skill : s.getTechSkills()) {
		    tech += skill.name();
		    tech += "  ";
		}
	    for (NonTechSkills skill : s.getNonTechSkills()) {
		    nontech += skill.name();
		    nontech += "  ";
	    }
	    
	    System.out.println("[  ID  ] : " + id);
	    result += "[  ID  ] : " + id + "\n";
	    System.out.println("[ Name ] : " + name);
	    result += "[ Name ] : " + name + "\n";
	    System.out.println("[ Contact ] : " + contact);
	    result += "[ Contact ] : " + contact + "\n";
	    System.out.println("[ Age ] : " + age);
	    result += "[ Age ] : " + age + "\n";
	    System.out.println("[ Grade ] : " + grade);
	    result += "[ Grade ] : " + grade + "\n";
	    System.out.println("[ Gpa ] : " + gpa);
	    result += "[ Gpa ] : " + gpa + "\n";
	    System.out.println("[ Sex ] : " + sex);
	    result += "[ Sex ] : " + sex + "\n";
	    System.out.println("[ TechSkills ] : " + tech);
	    result += "[ TechSkills ] : " + tech + "\n";
	    System.out.println("[ NonTechSkills ] : " + nontech);
	    result += "[ NonTechSkills ] : " + nontech + "\n";
	    System.out.println("");
	    result += "\n";
	}
	
	return result;
    }
    /* ShowRegisteredCompany */
    public String ShowRegisteredCompany() {
	String result;
	System.out.println("[ Registered Company List ]");
	result = "[ Registered Company List ]\n";
	for (Company c : companies) {
	    String id = c.getId();
	    String contact = c.getContactNumber();
	    String name = c.getName();
	    String location = c.getLocation();

	    System.out.println("[  ID  ] : " + id);
	    result += "[  ID  ] : " + id + "\n";
	    System.out.println("[ Name ] : " + name);
	    result += "[ Name ] : " + name + "\n";
	    System.out.println("[ Contact ] : " + contact);
	    result += "[ Contact ] : " + contact + "\n";
	    System.out.println("[ Location ] : " + location);
	    result += "[ Location ] : " + location + "\n";
	    System.out.println("");
	    result += "\n";
	}

	return result;
    }

    /* LogOut */
    public boolean Terminate() {
	boolean result = false;
	System.exit(0);

	result = true;

	return result;
    }

    /* Message Send */
    // Description : getting JSON Format [ "Message_send" , id]
    // return JSON Format ["data" , String msg] * ...
    public JSONObject sendMssage(JSONObject requestMessage)
	    throws JSONException {
	JSONObject response = new JSONObject();
	int msgCount = 0;

	generateRequestLogMessage(requestMessage.toString());

	// parsing data with keys
	String id = requestMessage.getString("id");

	for (int i = 0; i < messages.size(); i++) {
	    // Destination == id means message for the id.
	    if (id.equals(messages.get(i).getDest())) {
		JSONObject msgObject = new JSONObject();
		msgCount++;
		msgObject.put("MsgIndex", msgCount);
		msgObject.put("Sender", messages.get(i).getSource());
		msgObject.put("Msg", messages.get(i).getData().toString());
		if (messages.get(i).getSentTime() == null) {
		    msgObject.put("SentTime", "null");
		} else {
		    msgObject.put("SentTime", messages.get(i).getSentTime());
		}

		response.append("data", msgObject);
	    }
	}

	response.put("MsgCount", msgCount);
	generateResponseLogMessage(response.toString());

	return response;
    }

    /* Message Receive */
    // Description : JSON Format [ "Message_receive" , source , destination ,
    // data(ArrayList<String>) ]
    public JSONObject saveMssage(JSONObject requestMessage)
	    throws JSONException {
	JSONObject response = new JSONObject();
	boolean dstValid = false;

	generateRequestLogMessage(requestMessage.toString());

	// parsing data with keys
	String source = requestMessage.getString("source");
	String destination = requestMessage.getString("destination");
	String sentTime = (new TimeManager()).getMsgTime();
	JSONArray dataJSON = requestMessage.getJSONArray("data");
	ArrayList<String> data = new ArrayList<String>();
	for (int i = 0; i < dataJSON.length(); i++) {
	    String tmpMsg = dataJSON.getString(i);
	    data.add(tmpMsg);
	}

	// check validity of Destination Id
	dstValid = idValidationforMessage(destination);

	if (dstValid == true) {
	    // write message to the file
	    messages.add(new Message(source, destination, data, false, sentTime));
	    response.put("valid", true);
	} else {
	    response.put("valid", false);
	}

	generateResponseLogMessage(response.toString());

	return response;
    }

    /* addCurruentUser */
    public boolean addCurrentUser(String id, String userType) {

	// if it is student
	if (userType.equals("student")) {
	    current_students.add(id);
	    System.out.println("enter [ " + id + " ] student user");

	    return true;
	}
	// else then company
	else if (userType.equals("company")) {
	    current_companies.add(id);
	    System.out.println("enter [ " + id + " ] company user");

	    return true;
	}

	return false;
    }

    /* deleting logout user from ArrayList */
    /* return : true -> successfully deleted */
    /* return : false -> fail to delete */
    public JSONObject removeCurrentUser(JSONObject requestMessage)
	    throws JSONException {

	// create return value
	JSONObject response = new JSONObject();

	// Parsing data with keys
	String userType = requestMessage.getString("usertype");
	String id = requestMessage.getString("ID");

	if (userType.equals("student")) {
	    for (int i = 0; i < current_students.size(); i++) {
		if (id.equals(current_students.get(i))) {
		    // delete
		    current_students.remove(i);
		    response.put("valid", true);

		    return response;
		}
	    }
	} else if (userType.equals("company")) {
	    for (int i = 0; i < current_companies.size(); i++) {
		if (id.equals(current_companies.get(i))) {
		    // delete
		    current_companies.remove(i);
		    response.put("valid", true);

		    return response;
		}
	    }
	}

	response.put("valid", false);
	return response;
    }

    /* Print Current Users */
    public String printCurrentUsers() {
	String result;

	System.out.println("< Student List >");
	result = "< Student List >\n";
	for (int i = 0; i < current_students.size(); i++) {
	    String id = current_students.get(i);
	    System.out.println("[" + i + "] " + id);
	    result += "[" + i + "] " + id + "\n";
	}

	System.out.println("< Company List >");
	result += "< Company List >\n";
	for (int i = 0; i < current_companies.size(); i++) {
	    String id = current_companies.get(i);
	    System.out.println("[" + i + "] " + id);
	    result += "[" + i + "] " + id + "\n";
	}

	return result;
    }

    /* Login method */
    public JSONObject login(JSONObject requestMessage) throws JSONException {
	// create return value
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	// Parsing data with keys
	String userType = requestMessage.getString("usertype");
	String id = requestMessage.getString("ID");
	String pwd = requestMessage.getString("pwd");

	// create temporary user
	User user = null;

	// check and comparison with server data
	// check user type
	// because, Student and Company has different files

	// + check double login at the same time
	if (userType.equals("student")) {
	    for (int i = 0; i < current_students.size(); i++) {
		if (id.equals(current_students.get(i))) {
		    System.out.println("Login Faile => double Login[Students]");
		    response.put("login_valid", 2);

		    return response;
		}
	    }
	    // if it is the first time to login with the ID. pass
	    user = getStudentById(id);
	} else if (userType.equals("company")) {
	    for (int i = 0; i < current_companies.size(); i++) {
		if (id.equals(current_companies.get(i))) {
		    System.out
			    .println("Login Faile => double Login[Companies]");
		    response.put("login_valid", 2);

		    return response;
		}
	    }

	    // if it is the first time to login with the ID. pass
	    user = getCompanyById(id);
	}

	// check user passwords from server data
	if (user != null && user.getPassword() != null
		&& user.getPassword().equals(pwd)) {
	    if (addCurrentUser(id, userType)) {
		response.put("login_valid", 1);
	    } else {
		response.put("login_valid", 3);
	    }
	} else {
	    response.put("login_valid", 3);
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());
	return response;
    }

    // RegisterCompany Method
    public JSONObject registerCompany(JSONObject request) throws JSONException {
	// create return value
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(request.toString());

	// Parsing data with keys
	String name = request.getString("CompanyName");
	String id = request.getString("ID");
	String password = request.getString("Password");
	String location = request.getString("Location");
	String contactNumber = request.getString("Contact number");

	// check validity
	Company check = getCompanyById(id);
	if (check == null) {
	    companies.add(new Company(name, contactNumber, id, password,
		    location));
	    response.put("valid", true);
	} else {
	    response.put("valid", false); // ID is taken
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    // RegisterStudent Method
    public JSONObject registerStudent(JSONObject requestMessage)
	    throws JSONException {
	// create return value
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	// Parsing data with keys
	String name = requestMessage.getString("StudentName");
	String id = requestMessage.getString("ID");
	String password = requestMessage.getString("Password");
	int grade = requestMessage.getInt("Grade");
	int age = requestMessage.getInt("Age");
	float gpa = (float) requestMessage.getDouble("Gpa");
	String contactNumber = requestMessage.getString("Contact number");
	String sex = requestMessage.getString("Sex");
	JSONArray techSkills = requestMessage.getJSONArray("TechSkills");
	JSONArray nonTechSkills = requestMessage.getJSONArray("NonTechSkills");

	// Type casting JSONArray into ArrayList
	ArrayList<TechSkills> techSkillsList = new ArrayList<>();
	for (int i = 0; i < techSkills.length(); i++) {
	    String skillString = techSkills.getString(i);
	    TechSkills skill = TechSkills.valueOf(skillString);
	    techSkillsList.add(skill);
	}

	ArrayList<NonTechSkills> nonTechSkillsList = new ArrayList<>();
	for (int i = 0; i < nonTechSkills.length(); i++) {
	    String skillString = nonTechSkills.getString(i);
	    NonTechSkills skill = NonTechSkills.valueOf(skillString);
	    nonTechSkillsList.add(skill);
	}

	// check validity
	Student check = getStudentById(id);
	if (check == null) {
	    students.add(new Student(name, contactNumber, id, password, gpa,
		    sex, grade, age, techSkillsList, nonTechSkillsList));
	    response.put("valid", true);
	} else {
	    response.put("valid", false); // ID is taken
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    // ReceiveRequest Method
    @SuppressWarnings("unused")
    public JSONObject receiveRequest(JSONObject requestMessage)
	    throws JSONException {
	// create return value
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	// Parsing data with keys
	String companyID = requestMessage.getString("ID");
	String position = requestMessage.getString("Position");
	String startDateString = requestMessage.getString("StartDate");
	String endDateString = requestMessage.getString("EndDate");
	String payment = requestMessage.getString("Payment");
	int numberOfStudents = requestMessage.getInt("NumberOfStudents");
	String dueDateString = requestMessage.getString("DueDate");
	int grade = requestMessage.getInt("Grade");
	String description = requestMessage.getString("Description");
	JSONArray techSkills = requestMessage.getJSONArray("TechSkills");
	JSONArray nonTechSkills = requestMessage.getJSONArray("NonTechSkills");

	// Type casting JSONArrays into ArrayList
	ArrayList<TechSkills> techSkillsList = new ArrayList<>();
	for (int i = 0; i < techSkills.length(); i++) {
	    String skillString = techSkills.getString(i);
	    TechSkills skill = TechSkills.valueOf(skillString);
	    techSkillsList.add(skill);
	}

	ArrayList<NonTechSkills> nonTechSkillsList = new ArrayList<>();
	for (int i = 0; i < nonTechSkills.length(); i++) {
	    String skillString = nonTechSkills.getString(i);
	    NonTechSkills skill = NonTechSkills.valueOf(skillString);
	    nonTechSkillsList.add(skill);
	}

	// get company by id
	Company company = getCompanyById(companyID);
	System.out.println(company.getName());

	if (company != null) {
	    // create a new request and put into company
	    Request request = new Request(position, company, numberOfStudents,
		    grade, dueDateString, startDateString, endDateString,
		    description, payment, techSkillsList, nonTechSkillsList,
		    null, null, false);
	    requests.add(request);
	    mainWindow.addNewRequest(request);
	    response.put("error", false);
	} else {
	    response.put("error", true);
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    // GetResults Method
    public JSONObject getResults(JSONObject requestMessage)
	    throws JSONException {
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	String id = requestMessage.getString("ID");

	for (Request req : requests) {
	    System.out.println("list: " + req.getCompany().getId());
	    if (req.getCompany().getId().equals(id)) {
		JSONObject resultElement = new JSONObject();

		resultElement.put("Title", req.getTitle());
		resultElement.put("Date", req.getDueDate());

		if (req.isAnswered()) {

		    resultElement.put("Complete", true);

		    for (Reply reply : req.getReplies()) {
			Student student = reply.getStudent();
			JSONObject studentElement = new JSONObject();

			studentElement.put("StudentName", student.getName());
			studentElement.put("Grade", student.getGrade());
			studentElement.put("GPA", student.getGpa());
			studentElement.put("ContactNumber",
				student.getContactNumber());
			studentElement.put("Sex", student.getSex());
			studentElement.put("Age", student.getAge());
			for (TechSkills skill : student.getTechSkills()) {
			    studentElement.append("TechSkills", skill.name());
			}
			for (NonTechSkills skill : student.getNonTechSkills()) {
			    studentElement
				    .append("NonTechSkills", skill.name());
			}

			resultElement.append("Students", studentElement);
		    }

		} else {
		    resultElement.put("Complete", false);
		}

		response.append("Results", resultElement);
	    }
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    public JSONObject getRequests(JSONObject requestMessage)
	    throws JSONException {
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	String id = requestMessage.getString("ID");

	for (Request req : requests) {
	    for (Reply reply : req.getReplies()) {
		if (reply.getStudent().getId().equals(id)) {
		    JSONObject requestJSON = new JSONObject();

		    requestJSON.put("RequestID", req.getId());
		    requestJSON.put("Name", req.getCompany().getName());
		    requestJSON.put("Position", req.getTitle());
		    requestJSON.put("StartDate", req.getStartDate());
		    requestJSON.put("EndDate", req.getEndDate());
		    requestJSON.put("Payment", req.getPayment());
		    for (TechSkills skill : req.getTechSkills()) {
			requestJSON.append("TechSkills", skill.name());
		    }
		    for (NonTechSkills skill : req.getNonTechSkills()) {
			requestJSON.append("NonTechSkills", skill.name());
		    }
		    requestJSON.put("Answer", reply.getState());

		    response.append("Requests", requestJSON);
		}
	    }
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    public JSONObject receiveAnswer(JSONObject requestMessage)
	    throws JSONException {
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	String id = requestMessage.getString("ID");
	int requestId = requestMessage.getInt("RequestID");
	String answer = requestMessage.getString("Answer");
	// String message = requestMessage.getString("Message");

	Request requestElement = getRequestById(requestId);
	for (Reply reply : requestElement.getReplies()) {
	    if (reply.getStudent().getId().equals(id)) {
		reply.setState(Reply.State.valueOf(answer));
		// reply.getMessage().add(message);
	    }
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    private MyServer() {

    }

    void init() {
	try {
	    // initialize Logfile Management
	    log = new LogfileManagement();

	    // load data
	    companies = new AutoSerializeList<Company>(companyFileName);
	    System.out.println(companies.size() + " companies restored.");
	    generateLogMessage(companies.size() + " companies restored.");
	    students = new AutoSerializeList<Student>(studentFileName);
	    System.out.println(students.size() + " students restored.");
	    generateLogMessage(students.size() + " students restored.");
	    requests = new AutoSerializeList<Request>(requestsFileName);
	    System.out.println(requests.size() + " requests restored.");
	    generateLogMessage(requests.size() + " requests restored.");

	    messages = new AutoSerializeList<Message>(messageFilename);
	    System.out.println(messages.size() + " messages restored.");
	    generateLogMessage(messages.size() + " messages restored.");

	    current_students = new ArrayList<String>();
	    current_companies = new ArrayList<String>();

	    // open main window
	    mainWindow = new ServerMainUI();
	    mainWindow.setVisible(true);
	} catch (ClassNotFoundException | IOException e) {
	    e.printStackTrace();
	}
    }

    public static MyServer getInstance() {
	if (instance == null) {
	    instance = new MyServer();
	}

	return instance;
    }

    public AutoSerializeList<Company> getCompanies() {
	return companies;
    }

    public AutoSerializeList<Student> getStudents() {
	return students;
    }

    public AutoSerializeList<Request> getRequests() {
	return requests;
    }

    public AutoSerializeList<Message> getMessages() {
	return messages;
    }

    public Company getCompanyById(String id) {
	for (Company c : companies) {
	    if (c.getId().equals(id)) {
		return c;
	    }
	}

	return null;
    }

    public Student getStudentById(String id) {
	for (Student s : students) {
	    if (s.getId().equals(id)) {
		return s;
	    }
	}

	return null;
    }

    public Request getRequestById(int id) {
	for (Request r : requests) {
	    if (r.getId() == id) {
		return r;
	    }
	}

	return null;
    }

    public JSONObject idValidation(JSONObject requestMessage)
	    throws JSONException {
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	String id = requestMessage.getString("ID");

	Student checkStudent = getStudentById(id);
	Company checkCompany = getCompanyById(id);
	if (checkStudent == null && checkCompany == null) {
	    response.put("valid", true);
	} else {
	    response.put("valid", false); // ID is taken
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    public boolean idValidationforMessage(String id) {
	Student checkStudent = getStudentById(id);
	Company checkCompany = getCompanyById(id);
	if (checkStudent == null && checkCompany == null) {
	    return false;
	} else {
	    return true;
	}
    }

    public JSONObject isVersionValid(JSONObject requestMessage)
	    throws JSONException {
	JSONObject response = new JSONObject();

	// Write Log
	generateRequestLogMessage(requestMessage.toString());

	String clientVersion = requestMessage.getString("ClientVersion");

	// Version Check between Client and Server
	boolean validVersion = (new VersionControl())
		.isVersionValid(clientVersion);
	if (validVersion == true) {
	    response.put("valid", true);
	} else {
	    response.put("valid", false);
	}

	// Write Server Response Log
	generateResponseLogMessage(response.toString());

	return response;
    }

    public JSONObject studentModiftInfo(JSONObject requestMessage) {
	JSONObject response = new JSONObject();
	Student s;

	generateRequestLogMessage(requestMessage.toString());

	try {
	    JSONObject element = requestMessage.getJSONObject("student");
	    
	    String id = element.getString("userID");

	    s = getStudentById(id);
	    if (s == null) {
		response.put("valid", false);
	    } else {
		s.setName(element.getString("Name"));
		s.setContactNumber(element.getString("ContactNumber"));
		s.setGpa((float) element.getDouble("Gpa"));
		JSONArray tech = element.getJSONArray("TechSkills");
		ArrayList<TechSkills> techSkills = new ArrayList<TechSkills>();
		for (int i = 0; i < tech.length(); i++) {
		    String skillString = tech.getString(i);
		    TechSkills skill = TechSkills.valueOf(skillString);
		    techSkills.add(skill);
		}
		s.setTechSkills(techSkills);
		JSONArray nonTechSkills = element
			.getJSONArray("NonTechSkills");
		ArrayList<NonTechSkills> nonTechSkillsList = new ArrayList<>();
		for (int i = 0; i < nonTechSkills.length(); i++) {
		    String skillString = nonTechSkills.getString(i);
		    NonTechSkills skill = NonTechSkills.valueOf(skillString);
		    nonTechSkillsList.add(skill);
		}
		s.setNonTechSkills(nonTechSkillsList);
		
		response.put("valid", true);
	    }
	} catch (JSONException e) {
	    e.printStackTrace();
	}

	generateResponseLogMessage(response.toString());

	return response;
    }
    
    public JSONObject companyModifyInfo(JSONObject requestMessage) {
	JSONObject response = new JSONObject();
	Company c;
	
	generateRequestLogMessage(requestMessage.toString());
	
	try {
	    String id = requestMessage.getString("userID");
	    
	    c = getCompanyById(id);
	    
	    if (c == null) {
		response.put("valid", false);
	    } else {
		c.setName(requestMessage.getString("Name"));
		c.setContactNumber(requestMessage.getString("ContactNumber"));
		c.setLocation(requestMessage.getString("Location"));
		
		response.put("valid", true);
	    }
	} catch (JSONException e) {
	    e.printStackTrace();
	}
	
	generateResponseLogMessage(response.toString());
	
	return response;
    }

    public JSONObject getStudent(JSONObject requestMessage) {
	JSONObject response = new JSONObject();
	
	generateRequestLogMessage(requestMessage.toString());
	
	try {
	    String id = requestMessage.getString("userID");
	    Student student = getStudentById(id);
	    
	    if (student == null) {
		response.put("valid", false);
	    } else {
		JSONObject studentElement = new JSONObject();
		
		response.put("valid", true);
		studentElement.put("userID", student.getId());
		studentElement.put("StudentName", student.getName());
		studentElement.put("Grade", student.getGrade());
		studentElement.put("Gpa", student.getGpa());
		studentElement.put("ContactNumber",
			student.getContactNumber());
		studentElement.put("Sex", student.getSex());
		studentElement.put("Age", student.getAge());
		for (TechSkills skill : student.getTechSkills()) {
		    studentElement.append("TechSkills", skill.name());
		}
		for (NonTechSkills skill : student.getNonTechSkills()) {
		    studentElement
			    .append("NonTechSkills", skill.name());
		}
		
		response.put("Student", studentElement);
	    }
	} catch (JSONException e) {
	    
	    e.printStackTrace();
	}
	
	generateResponseLogMessage(response.toString());
	
	return response;
    }
    
    public JSONObject getCompany(JSONObject requestMessage) {
	JSONObject response = new JSONObject();
	
	generateRequestLogMessage(requestMessage.toString());
	try {
	    String id = requestMessage.getString("userID");
	    Company company = getCompanyById(id);
	    
	    if (company == null) {
		response.put("valid", false);
	    } else {
		response.put("valid", true);
		response.put("userID", company.getId());
		response.put("Name", company.getName());
		response.put("Location", company.getLocation());
		response.put("ContactNumber", company.getContactNumber());
	    }
	} catch (JSONException e) {
	    e.printStackTrace();
	}
	
	generateResponseLogMessage(response.toString());
	
	return response;
    }
    
    public JSONObject passwordValidation(JSONObject requestMessage) {
	JSONObject response = new JSONObject();

	generateRequestLogMessage(requestMessage.toString());

	try {
	    // Parsing data with key
	    String userType = requestMessage.getString("userType");
	    String id = requestMessage.getString("userID");
	    String passwd = requestMessage.getString("password");
	    User user = null;

	    // Find User object
	    if (userType.equals("student")) {
		user = getStudentById(id);
	    } else if (userType.equals("company")) {
		user = getCompanyById(id);
	    } else {
		user = null;
	    }

	    // Check password validity
	    if (user != null && user.getPassword() != null
		    && user.getPassword().equals(passwd)) {
		response.put("valid", true);
	    } else {
		response.put("valid", false);
	    }
	} catch (JSONException e) {
	    e.printStackTrace();
	}

	generateResponseLogMessage(response.toString());

	return response;
    }

    public void generateLogMessage(String Message) {
	try {
	    log.generateLog(Message);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void generateRequestLogMessage(String Message) {
	try {
	    log.generateRequestLog(Message);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void generateResponseLogMessage(String Message) {
	try {
	    log.generateResponseLog(Message);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
