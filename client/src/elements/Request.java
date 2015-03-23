package elements;

import java.io.Serializable;
import java.util.ArrayList;

import skills.NonTechSkills;
import skills.TechSkills;

@SuppressWarnings("serial")
public class Request implements Serializable
{

	public static enum State
	{
		YES, NO, AskForDetails, Unanswered
	}

	private int id;
	private String name;
	private String title;
	private String startDate;
	private String endDate;
	private String description;
	private String payment;
	private ArrayList<TechSkills> techSkills;
	private ArrayList<NonTechSkills> nonTechSkills;
	private State state;

	public Request(int id, String name, String title, String startDate,
			String endDate, String description, String payment,
			ArrayList<TechSkills> techSkills,
			ArrayList<NonTechSkills> nonTechSkills, State state)
	{
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.payment = payment;
		this.techSkills = techSkills;
		this.nonTechSkills = nonTechSkills;
		this.state = state;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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

	public String getPayment()
	{
		return payment;
	}

	public void setPayment(String payment)
	{
		this.payment = payment;
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

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}
}
