/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.ArrayList;

/**
 *
 * @author szedjani
 */
@SuppressWarnings("serial")
public class Student extends User implements Comparable<Student>
{
	/**
	 * 
	 */
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
	
	/**
	 * @return the gpa
	 */
	public float getGpa()
	{
		return gpa;
	}
	
	/**
	 * @param gpa
	 *            the gpa to set
	 */
	public void setGpa(float gpa)
	{
		this.gpa = gpa;
	}
	
	/**
	 * @return the sex
	 */
	public String getSex()
	{
		return sex;
	}
	
	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	/**
	 * @return the grade
	 */
	public int getGrade()
	{
		return grade;
	}
	
	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	
	/**
	 * @return the age
	 */
	public int getAge()
	{
		return age;
	}
	
	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age)
	{
		this.age = age;
	}
	
	/**
	 * @return the techSkills
	 */
	public ArrayList<TechSkills> getTechSkills()
	{
		return techSkills;
	}
	
	/**
	 * @param techSkills
	 *            the techSkills to set
	 */
	public void setTechSkills(ArrayList<TechSkills> techSkills)
	{
		this.techSkills = techSkills;
	}
	
	/**
	 * @return the nonTechSkills
	 */
	public ArrayList<NonTechSkills> getNonTechSkills()
	{
		return nonTechSkills;
	}
	
	/**
	 * @param nonTechSkills
	 *            the nonTechSkills to set
	 */
	public void setNonTechSkills(ArrayList<NonTechSkills> nonTechSkills)
	{
		this.nonTechSkills = nonTechSkills;
	}
	
	@Override
	public int compareTo(Student o)
	{
		if (gpa < o.gpa)
			return -1;
		else if (gpa > o.gpa)
			return 1;
		else
			return 0;
	}
	
}
