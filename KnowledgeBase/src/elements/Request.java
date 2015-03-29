package elements;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import server.MyServer;

@SuppressWarnings("serial")
public class Request implements Serializable
{
	private int id;
	private String title;
	private transient Company company;
	private int numberOfStudents;
	private int minimumGrade;
	private String dueDate;
	private String startDate;
	private String endDate;
	private String description;
	private String payment;
	private ArrayList<TechSkills> techSkills;
	private ArrayList<NonTechSkills> nonTechSkills;
	private String message;
	private boolean answered;
	
	private ArrayList<Reply> replies;
	
	private void writeObject(ObjectOutputStream oos) throws IOException
	{
		// default serialization
		oos.defaultWriteObject();
		// write company id
		oos.writeObject(company.getId());
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{
		// default serialization
		in.defaultReadObject();
		// read company id
		String companyID;
		companyID = (String) in.readObject();
		// set company
		company = MyServer.getInstance().getCompanyById(companyID);
	}
	
	public Request(String title, Company company, int numberOfStudents,
			int minimumGrade, String dueDate, String startDate, String endDate,
			String description, String payment,
			ArrayList<TechSkills> techSkills,
			ArrayList<NonTechSkills> nonTechSkills, ArrayList<Reply> replies,
			String message, boolean answered)
	{
		super();
		this.id = MyServer.getInstance().getRequests().size();
		this.title = title;
		this.company = company;
		this.numberOfStudents = numberOfStudents;
		this.minimumGrade = minimumGrade;
		this.dueDate = dueDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.setPayment(payment);
		this.techSkills = techSkills;
		this.nonTechSkills = nonTechSkills;
		
		if (replies != null)
		{
			this.replies = replies;
		}
		else
		{
			this.replies = new ArrayList<>();
		}
		
		this.message = message;
		this.answered = answered;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public int getNumberOfStudents()
	{
		return numberOfStudents;
	}
	
	public void setNumberOfStudents(int numberOfStudents)
	{
		this.numberOfStudents = numberOfStudents;
	}
	
	public int getMinimumGrade()
	{
		return minimumGrade;
	}
	
	public void setMinimumGrade(int minimumGrade)
	{
		this.minimumGrade = minimumGrade;
	}
	
	public String getDueDate()
	{
		return dueDate;
	}
	
	public void setDueDate(String dueDate)
	{
		this.dueDate = dueDate;
	}
	
	public String getStartDate()
	{
		return startDate;
	}
	
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	public String getEndDate()
	{
		return endDate;
	}
	
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public ArrayList<TechSkills> getTechSkills()
	{
		return techSkills;
	}
	
	public void setTechSkills(ArrayList<TechSkills> techSkills)
	{
		this.techSkills = techSkills;
	}
	
	public ArrayList<NonTechSkills> getNonTechSkills()
	{
		return nonTechSkills;
	}
	
	public void setNonTechSkills(ArrayList<NonTechSkills> nonTechSkills)
	{
		this.nonTechSkills = nonTechSkills;
	}
	
	public ArrayList<Reply> getReplies()
	{
		return replies;
	}
	
	public void setReplies(ArrayList<Reply> replies)
	{
		this.replies = replies;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public boolean isAnswered()
	{
		return answered;
	}
	
	public void setAnswered(boolean answered)
	{
		this.answered = answered;
	}
	
	public Company getCompany()
	{
		return company;
	}
	
	public void setCompany(Company company)
	{
		this.company = company;
	}
	
	public String getPayment()
	{
		return payment;
	}
	
	public void setPayment(String payment)
	{
		this.payment = payment;
	}
	
}
