package elements;

import java.util.ArrayList;

public class Result
{
	private String title;
	private String startDate;
	private boolean complete;
	private ArrayList<Student> students;

	public Result(String title, String startDate, boolean complete, ArrayList<Student> students)
	{
		super();
		this.title = title;
		this.startDate = startDate;
		this.setComplete(complete);
		this.students = students;
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

	public ArrayList<Student> getStudents()
	{
		return students;
	}

	public void setStudents(ArrayList<Student> students)
	{
		this.students = students;
	}

	public boolean isComplete()
	{
		return complete;
	}

	public void setComplete(boolean complete)
	{
		this.complete = complete;
	}
}
