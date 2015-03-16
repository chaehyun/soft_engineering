package elements;

import java.util.ArrayList;

import skills.NonTechSkills;
import skills.TechSkills;

public class Student
{
	private String studentName;
	private int grade;
	private int gpa;
	private String contactNumber;
	private String sex;
	private int age;
	private ArrayList<TechSkills> techSkills;
	private ArrayList<NonTechSkills> nonTechSkills;

	public Student(String studentName, int grade, int gpa,
			String contactNumber, String sex, int age,
			ArrayList<TechSkills> techSkills,
			ArrayList<NonTechSkills> nonTechSkills)
	{
		super();
		this.studentName = studentName;
		this.grade = grade;
		this.gpa = gpa;
		this.contactNumber = contactNumber;
		this.sex = sex;
		this.age = age;
		this.techSkills = techSkills;
		this.nonTechSkills = nonTechSkills;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public int getGrade()
	{
		return grade;
	}

	public void setGrade(int grade)
	{
		this.grade = grade;
	}

	public int getGpa()
	{
		return gpa;
	}

	public void setGpa(int gpa)
	{
		this.gpa = gpa;
	}

	public String getContactNumber()
	{
		return contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
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
}
