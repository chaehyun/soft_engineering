package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ui.Requests;
import database.getcompanybyid;
import database.getstudentbyid;
import database.insertuser;
import database.logindemo;
import database.registercompany;
import database.registerstudent;
import elements.AutoSerializeList;
import elements.Company;
import elements.NonTechSkills;
import elements.Reply;
import elements.Request;
import elements.Student;
import elements.TechSkills;

/**
 *
 * @author szedjani
 */
public class MyServer {

	private final String companyFileName = "companiesDBproject3.ser";
	private final String studentFileName = "studentsDBproject3.ser";
	private final String requestsFileName = "requestsDBproject3.ser";

	private AutoSerializeList<Company> companies;
	private AutoSerializeList<Student> students;
	private AutoSerializeList<Request> requests;

	private static MyServer instance = null;

	private Requests mainWindow;
	
	public Requests getMainWindow()
	{
		return mainWindow;
	}

	public JSONObject login(JSONObject requestMessage) throws JSONException {
		JSONObject response = new JSONObject();

		String userType = requestMessage.getString("usertype");
		String id = requestMessage.getString("ID");
		String pwd = requestMessage.getString("pwd");
		/*
		 * User user = null; if (userType.equals("student")) user =
		 * getStudentById(id); else if (userType.equals("company")) user =
		 * getCompanyById(id);
		 * 
		 * if (user != null && user.getPassword() != null &&
		 * user.getPassword().equals(pwd)) response.put("valid", true); else
		 * response.put("valid", false);
		 */
		boolean loginValidate = (new logindemo().loginchk(id, pwd));

		if (loginValidate == true)
			response.put("valid", true);
		else
			response.put("valid", false);

		return response;
	}

	public JSONObject idValidation(JSONObject requestMessage)
			throws JSONException {
		JSONObject response = new JSONObject();
		String id = requestMessage.getString("ID");

		boolean idValidate = (new insertuser()).idValidation(id);

		if (idValidate == true)
			response.put("valid", true);
		else
			response.put("valid", false);

		return response;
	}

	public JSONObject registerCompany(JSONObject request) throws JSONException {
		JSONObject response = new JSONObject();

		String name = request.getString("CompanyName");
		String id = request.getString("ID");
		String password = request.getString("Password");
		String location = request.getString("Location");
		String contactNumber = request.getString("Contact number");
		/*
		 * Company check = getCompanyById(id); if (check == null) {
		 * companies.add(new Company(name, contactNumber, id, password,
		 * location)); response.put("valid", true); } else response.put("valid",
		 * false); // ID is taken
		 * 
		 * return response;
		 */
		boolean registerValidateforcompany = (new insertuser()).registerchk(id,
				password);
		if (registerValidateforcompany == true) {
			response.put("valid", true);
			// company table insert
			boolean chkinsert = (new registercompany()).insertcompany(id, name,
					location, contactNumber);
			if (chkinsert == true)
				System.out.println("insert into company table (MyServer)");
		} else
			response.put("valid", false);
		return response;
	}

	public JSONObject registerStudent(JSONObject requestMessage)
			throws JSONException {
		JSONObject response = new JSONObject();

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

		/*
		 * Student check = getStudentById(id); if (check == null) {
		 * students.add(new Student(name, contactNumber, id, password, gpa, sex,
		 * grade, age, techSkillsList, nonTechSkillsList));
		 * response.put("valid", true); } else response.put("valid", false); //
		 * ID is taken
		 */

		boolean registerValidate = (new insertuser()).registerchk(id, password);
		if (registerValidate == true) {
			// student table ¿¡ insert
			boolean chkinsert = (new registerstudent()).insertstudent(id, name,
					grade, age, gpa, contactNumber, sex);
			boolean chk_techskills_insert = (new registerstudent())
					.inserttechskills(id, techSkillsList);
			boolean chk_nontechskills_insert = (new registerstudent())
					.insert_nontechskills(id, nonTechSkillsList);

			if (chkinsert == true && chk_techskills_insert == true
					&& chk_nontechskills_insert == true) {
				System.out.println("insert into student table (MyServer)");
				System.out
						.println("insert operation was completed. (table: TechSkills)");
				System.out
						.println("insert operation was completed. (table: NontechSkills)");
				response.put("valid", true);
			} else
				response.put("valid", false);
		} else
			response.put("valid", false);
		return response;
	}

	public JSONObject receiveRequest(JSONObject requestMessage)
			throws JSONException {
		JSONObject response = new JSONObject();

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

		Company company = getCompanyById(companyID);

		System.out.println(companyID);

		if (company != null) {
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

		return response;
	}

	public JSONObject getResults(JSONObject requestMessage)
			throws JSONException {
		
		JSONObject response = new JSONObject(); // ¼±¾ð

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
						//for (TechSkills skill : student.getTechSkills())
						//	studentElement.append("TechSkills", skill.name());
						//for (NonTechSkills skill : student.getNonTechSkills())
						//	studentElement
						//			.append("NonTechSkills", skill.name());

						resultElement.append("Students", studentElement);
					}

				} else
					resultElement.put("Complete", false);

				response.append("Results", resultElement);
			}
		}

		return response;
	}

	public JSONObject getRequests(JSONObject requestMessage)
			throws JSONException {
		JSONObject response = new JSONObject();

		String id = requestMessage.getString("ID");

		for (Request req : requests)
			for (Reply reply : req.getReplies())
				if (reply.getStudent().getId().equals(id)) {
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

		return response;
	}

	public JSONObject receiveAnswer(JSONObject requestMessage)
			throws JSONException {
		JSONObject response = new JSONObject();

		String id = requestMessage.getString("ID");
		int requestId = requestMessage.getInt("RequestID");
		String answer = requestMessage.getString("Answer");
		//System.out.println(answer);
		//String message = requestMessage.getString("Message");

		Request requestElement = getRequestById(requestId);
		for (Reply reply : requestElement.getReplies())
			if (reply.getStudent().getId().equals(id)) 
			{
				reply.setState(Reply.State.valueOf(answer));
				//reply.getMessage().add(message);
			}
		return response;
	}

	private MyServer() {
	}

	void init() {
		try {
			// load data
			companies = new AutoSerializeList<Company>(companyFileName);
			System.out.println(companies.size() + " companies restored.");
			students = new AutoSerializeList<Student>(studentFileName);
			System.out.println(students.size() + " students restored.");
			requests = new AutoSerializeList<Request>(requestsFileName);
			System.out.println(requests.size() + " requests restored.");

			// open main window
			mainWindow = new Requests();
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

	public Company getCompanyById(String id) {
		Company c = (new getcompanybyid()).getcompanybyid(id);
		return c;
	}

	public Student getStudentById(String id) {
		Student s = (new getstudentbyid()).getstudentbyid(id);
		return s;
	}

	public Request getRequestById(int id) {
		for (Request r : requests)
			if (r.getId() == id)
				return r;
		return null;
	}
}
