package server;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import elements.*;
import graphicUI.RequestsUI;

public class MyServer
{
	/* Definition for Server Data File names */
	private final static String companyFileName = "companies.ser";
	private final static String studentFileName = "students.ser";
	private final static String requestsFileName = "requests.ser";
	
	private AutoSerializeList<Company> companies;
	private AutoSerializeList<Student> students;
	private AutoSerializeList<Request> requests;
	
	private ArrayList<String> current_companies;
	private ArrayList<String> current_students;
	
	private LogfileManagement log;
	
	private static MyServer instance = null;
	
	private RequestsUI mainWindow;
	
	public RequestsUI getMainWindow()
	{
		return mainWindow;
	}
	
	/* addCurruentUser */
	public boolean addCurrentUser(String id, String userType) throws JSONException
	{

		//if it is student
		if(userType.equals("student"))
		{
			current_students.add(id);
			System.out.println("enter [ " + id + " ] student user");
			
			return true;
		}
		//else then company
		else if(userType.equals("company"))
		{
			current_companies.add(id);
			System.out.println("enter [ " + id + " ] company user");
			
			return true;
		}
		
		return false;
	}
	
	/* Print Current Users*/
	public void printCurrentUsers(String userType)
	{
		if(userType.equals("student"))
		{
			for(int i=0; i < current_students.size(); i++)
			{
				String id = current_students.get(i);
				System.out.println("[" + i + "] " + id);
			}
		}
		else if(userType.equals("company"))
		{
			for(int i=0; i < current_companies.size(); i++)
			{
				String id = current_companies.get(i);
				System.out.println("[" + i + "] " + id);
			}
		}
		
		
	}
	
	/* Login method */
	public JSONObject login(JSONObject requestMessage) throws JSONException
	{
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
		if (userType.equals("student"))
		{
			for(int i = 0; i<current_students.size(); i++)
			{
				if(id.equals(current_students.get(i)))
				{
					System.out.println("Login Faile => double Login[Students]");
					response.put("valid", false);
					
					return response;
				}
			}
			//if it is the first time to login with the ID. pass
			user = getStudentById(id);
		}
		else if (userType.equals("company"))
		{
			for(int i = 0; i<current_companies.size(); i++)
			{
				if(id.equals(current_companies.get(i)))
				{
					System.out.println("Login Faile => double Login[Companies]");
					response.put("valid", false);
					
					return response;
				}
			}
			
			//if it is the first time to login with the ID. pass
			user = getCompanyById(id);
		}
		
		// check user passwords from server data
		if (user != null && user.getPassword() != null
				&& user.getPassword().equals(pwd))
		{
			if(addCurrentUser(id, userType))
			{
				response.put("valid", true);
			}
			else
			{
				response.put("valid", false);
			}
		}
		else
		{
			response.put("valid", false);
		}
		
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
		return response;
	}
	
	// RegisterCompany Method
	public JSONObject registerCompany(JSONObject request) throws JSONException
	{
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
		if (check == null)
		{
			companies.add(new Company(name, contactNumber, id, password,
					location));
			response.put("valid", true);
		}
		else
		{
			response.put("valid", false); // ID is taken
		}
			
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
				
		return response;
	}
	
	// RegisterStudent Method
	public JSONObject registerStudent(JSONObject requestMessage)
			throws JSONException
	{
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
		for (int i = 0; i < techSkills.length(); i++)
		{
			String skillString = techSkills.getString(i);
			TechSkills skill = TechSkills.valueOf(skillString);
			techSkillsList.add(skill);
		}
		
		ArrayList<NonTechSkills> nonTechSkillsList = new ArrayList<>();
		for (int i = 0; i < nonTechSkills.length(); i++)
		{
			String skillString = nonTechSkills.getString(i);
			NonTechSkills skill = NonTechSkills.valueOf(skillString);
			nonTechSkillsList.add(skill);
		}
		
		// check validity
		Student check = getStudentById(id);
		if (check == null)
		{
			students.add(new Student(name, contactNumber, id, password, gpa,
					sex, grade, age, techSkillsList, nonTechSkillsList));
			response.put("valid", true);
		}
		else
		{
			response.put("valid", false); // ID is taken
		}
			
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
		
		return response;
	}
	
	//ReceiveRequest Method
	@SuppressWarnings("unused")
	public JSONObject receiveRequest(JSONObject requestMessage)
			throws JSONException
	{
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
		for (int i = 0; i < techSkills.length(); i++)
		{
			String skillString = techSkills.getString(i);
			TechSkills skill = TechSkills.valueOf(skillString);
			techSkillsList.add(skill);
		}
		
		ArrayList<NonTechSkills> nonTechSkillsList = new ArrayList<>();
		for (int i = 0; i < nonTechSkills.length(); i++)
		{
			String skillString = nonTechSkills.getString(i);
			NonTechSkills skill = NonTechSkills.valueOf(skillString);
			nonTechSkillsList.add(skill);
		}
		
		// get company by id
		Company company = getCompanyById(companyID);
		System.out.println(company.getName());
		
		if (company != null)
		{
			// create a new request and put into company
			Request request = new Request(position, company, numberOfStudents,
					grade, dueDateString, startDateString, endDateString,
					description, payment, techSkillsList, nonTechSkillsList,
					null, null, false);
			requests.add(request);
			mainWindow.addNewRequest(request);
			response.put("error", false);
		}
		else
		{
			response.put("error", true);
		}
		
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
				
		return response;
	}
	
	// GetResults Method
	public JSONObject getResults(JSONObject requestMessage)
			throws JSONException
	{
		JSONObject response = new JSONObject();
		
		// Write Log
		generateRequestLogMessage(requestMessage.toString());
		
		String id = requestMessage.getString("ID");
		
		for (Request req : requests)
		{
			System.out.println("list: " + req.getCompany().getId());
			if (req.getCompany().getId().equals(id))
			{
				JSONObject resultElement = new JSONObject();
				
				resultElement.put("Title", req.getTitle());
				resultElement.put("Date", req.getDueDate());
				
				if (req.isAnswered())
				{
					
					resultElement.put("Complete", true);
					
					for (Reply reply : req.getReplies())
					{
						Student student = reply.getStudent();
						JSONObject studentElement = new JSONObject();
						
						studentElement.put("StudentName", student.getName());
						studentElement.put("Grade", student.getGrade());
						studentElement.put("GPA", student.getGpa());
						studentElement.put("ContactNumber",
								student.getContactNumber());
						studentElement.put("Sex", student.getSex());
						studentElement.put("Age", student.getAge());
						for (TechSkills skill : student.getTechSkills())
							studentElement.append("TechSkills", skill.name());
						for (NonTechSkills skill : student.getNonTechSkills())
							studentElement
									.append("NonTechSkills", skill.name());
						
						resultElement.append("Students", studentElement);
					}
					
				}
				else
				{
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
			throws JSONException
	{
		JSONObject response = new JSONObject();
		
		// Write Log
		generateRequestLogMessage(requestMessage.toString());
		
		String id = requestMessage.getString("ID");
		
		for (Request req : requests)
		{
			for (Reply reply : req.getReplies())
			{
				if (reply.getStudent().getId().equals(id))
				{
					JSONObject requestJSON = new JSONObject();
					
					requestJSON.put("RequestID", req.getId());
					requestJSON.put("Name", req.getCompany().getName());
					requestJSON.put("Position", req.getTitle());
					requestJSON.put("StartDate", req.getStartDate());
					requestJSON.put("EndDate", req.getEndDate());
					requestJSON.put("Payment", req.getPayment());
					for (TechSkills skill : req.getTechSkills())
						requestJSON.append("TechSkills", skill.name());
					for (NonTechSkills skill : req.getNonTechSkills())
						requestJSON.append("NonTechSkills", skill.name());
					
					response.append("Requests", requestJSON);
				}
			}
		}
		
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
				
		return response;
	}
	
	public JSONObject receiveAnswer(JSONObject requestMessage)
			throws JSONException
	{
		JSONObject response = new JSONObject();
		
		// Write Log
		generateRequestLogMessage(requestMessage.toString());
		
		String id = requestMessage.getString("ID");
		int requestId = requestMessage.getInt("RequestID");
		String answer = requestMessage.getString("Answer");
		// String message = requestMessage.getString("Message");
		
		Request requestElement = getRequestById(requestId);
		for (Reply reply : requestElement.getReplies())
		{
			if (reply.getStudent().getId().equals(id))
			{
				reply.setState(Reply.State.valueOf(answer));
				// reply.getMessage().add(message);
			}
		}
		
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
				
		return response;
	}
	
	private MyServer()
	{
	}
	
	void init()
	{
		try
		{
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
			
			current_students = new ArrayList<String>();
			current_companies = new ArrayList<String>();
			
			// open main window
			mainWindow = new RequestsUI();
			mainWindow.setVisible(true);
		}
		catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static MyServer getInstance()
	{
		if (instance == null)
		{
			instance = new MyServer();
		}
		
		return instance;
	}
	
	public AutoSerializeList<Company> getCompanies()
	{
		return companies;
	}
	
	public AutoSerializeList<Student> getStudents()
	{
		return students;
	}
	
	public AutoSerializeList<Request> getRequests()
	{
		return requests;
	}

	
	public Company getCompanyById(String id)
	{
		for (Company c : companies)
		{
			if (c.getId().equals(id))
			{
				return c;
			}
		}
		
		return null;
	}
	
	public Student getStudentById(String id)
	{
		for (Student s : students)
		{
			if (s.getId().equals(id))
			{
				return s;
			}
		}
		
		return null;
	}
	
	public Request getRequestById(int id)
	{
		for (Request r : requests)
		{
			if (r.getId() == id)
			{
				return r;
			}
		}
		
		return null;
	}
	
	public JSONObject studentIdValidation(JSONObject requestMessage)
			throws JSONException
	{
		JSONObject response = new JSONObject();
		
		// Write Log
		generateRequestLogMessage(requestMessage.toString());
		
		String id = requestMessage.getString("ID");
		
		Student check = getStudentById(id);
		if (check == null)
		{
			response.put("valid", true);
		}
		else
		{
			response.put("valid", false); // ID is taken
		}
			
		
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
				
		return response;
	}
	
	public JSONObject companyIdValidation(JSONObject requestMessage)
			throws JSONException
	{
		JSONObject response = new JSONObject();
		
		// Write Log
		generateRequestLogMessage(requestMessage.toString());
		
		String id = requestMessage.getString("ID");
		
		Company check = getCompanyById(id);
		if (check == null)
		{
			response.put("valid", true);
		}
		else
		{
			response.put("valid", false); // ID is taken
		}
		
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
				
		return response;
	}
	
	public JSONObject isVersionValid(JSONObject requestMessage) throws JSONException
	{
		JSONObject response = new JSONObject();
		
		// Write Log
		generateRequestLogMessage(requestMessage.toString());
		
		String clientVersion = requestMessage.getString("ClientVersion");
		
		// Version Check between Client and Server
		boolean validVersion = (new VersionControl()).isVersionValid(clientVersion);
		if (validVersion == true)
		{
			response.put("valid", true);
		}
		else
		{
			response.put("valid", false);
		}
		
		// Write Server Response Log
		generateResponseLogMessage(response.toString());
		
		return response;
	}
	
	public void generateLogMessage(String Message)
	{
		try
		{
			log.generateLog(Message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void generateRequestLogMessage(String Message)
	{
		try
		{
			log.generateRequestLog(Message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void generateResponseLogMessage(String Message)
	{
		try
		{
			log.generateResponseLog(Message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
