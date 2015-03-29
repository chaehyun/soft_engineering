package elements;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Student extends User implements Comparable<Student>
{
	private float gpa;
	private String sex;
	private int grade;
	private int age;
	private ArrayList<TechSkills> techSkills;
	private ArrayList<NonTechSkills> nonTechSkills;
	
	public Student(String Name, String ContactNumber, String Id,
			String Password, float Gpa, String Sex, int Grade, int Age,
			ArrayList<TechSkills> TechSkills,
			ArrayList<NonTechSkills> NonTechSkills)
	{
		super(Name, ContactNumber, Id, Password);
		gpa = Gpa;
		sex = Sex;
		grade = Grade;
		age = Age;
		techSkills = TechSkills;
		nonTechSkills = NonTechSkills;
	}
	
	public float getGpa()
	{
		return gpa;
	}
	
	public void setGpa(float gpa)
	{
		this.gpa = gpa;
	}
	
	public String getSex()
	{
		return sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public int getGrade()
	{
		return grade;
	}
	
	public void setGrade(int grade)
	{
		this.grade = grade;
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
	
	@Override
	public int compareTo(Student o)
	{
		if (gpa < o.gpa)
		{
			return -1;
		}
		else if (gpa > o.gpa)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
}
