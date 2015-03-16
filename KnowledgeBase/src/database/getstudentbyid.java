package database;

import java.util.ArrayList;
import java.sql.*;

import elements.Company;
import elements.NonTechSkills;
import elements.Student;
import elements.TechSkills;

public class getstudentbyid
{
	
	protected final String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	protected final String user = "projectdb";
	protected final String pass = "projectdb";
	private Student tmp_student;

	/*
	 * private String Name; private String Contactnumber; private String Id;
	 * private String location; private String pwd; private Company c;
	 */
	public Student getstudentbyid(String id)
	{
		
		Connection conn = null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 검색 성공!");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}
		
		try
		{
			conn = DriverManager.getConnection(url, user, pass);
		}
		catch (SQLException e)
		{
			System.err.println("sql error = " + e.getMessage());
			System.exit(1);
		}
		
		try
		{
			String Id = null;
			String Name = null;
			String pwd = null;
			float gpa = 0;
			String sex = null;
			int grade = 0;
			int age = 0;
			String Contactnumber = null;
			ArrayList<TechSkills> techSkills = new ArrayList<TechSkills>();
			ArrayList<NonTechSkills> nonTechSkills = new ArrayList<NonTechSkills>();
			
			/* get everything from company table */
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			
			String query = null;
			query = "select * from student where id = ?";
			String query2 = "select pwd from userinfo where id = ?";
			String query3 = "select * from techSkills where id = ?";
			String query4 = "select * from nonTechSkills where id = ?";
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			PreparedStatement pstmt4 = null;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, id);
			
			pstmt3 = conn.prepareStatement(query2);
			pstmt3.setString(1, id);
			
			pstmt4 = conn.prepareStatement(query2);
			pstmt4.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();
			ResultSet rs3 = pstmt3.executeQuery();
			ResultSet rs4 = pstmt4.executeQuery();
			
			while (rs.next())
			{
				Name = rs.getString("name");
				Contactnumber = rs.getString("Contactnumber");
				Id = id;
				gpa = rs.getFloat("gpa");
				grade = rs.getInt("grade");
				sex = rs.getString("sex");
				age = rs.getInt("age");
			}
			
			while (rs2.next())
			{
				pwd = rs2.getString("pwd");
			}
			
			ArrayList<TechSkills> techSkillsList = new ArrayList<>();
			int i = 1;
			while (rs3.next())
			{
				String skillString = rs3.getString(i);
				
				if (skillString.equals("t") && i == 1)
				{
					skillString = TechSkills.JAVA.name();
					TechSkills skill = TechSkills.valueOf(skillString);
					techSkillsList.add(skill);
				}
				if (skillString.equals("t") && i == 2)
				{
					skillString = TechSkills.APACHE.name();
					TechSkills skill = TechSkills.valueOf(skillString);
					techSkillsList.add(skill);
				}
				if (skillString.equals("t") && i == 3)
				{
					skillString = TechSkills.SQL.name();
					TechSkills skill = TechSkills.valueOf(skillString);
					techSkillsList.add(skill);
				}
				if (skillString.equals("t") && i == 4)
				{
					skillString = TechSkills.OOP.name();
					TechSkills skill = TechSkills.valueOf(skillString);
					techSkillsList.add(skill);
				}
				
				i++;
			}
			techSkills = techSkillsList;
			
			ArrayList<NonTechSkills> nontechSkillsList = new ArrayList<>();
			i = 1;
			while (rs4.next())
			{
				String skillString = rs3.getString(i);
				
				if (skillString.equals("t") && i == 1)
				{
					skillString = NonTechSkills.DRIVINGLICENCE.name();
					NonTechSkills skill = NonTechSkills.valueOf(skillString);
					nontechSkillsList.add(skill);
				}
				if (skillString.equals("t") && i == 2)
				{
					skillString = NonTechSkills.QUICKLEARNING.name();
					NonTechSkills skill = NonTechSkills.valueOf(skillString);
					nontechSkillsList.add(skill);
				}
				if (skillString.equals("t") && i == 3)
				{
					skillString = NonTechSkills.TEAMWORK.name();
					NonTechSkills skill = NonTechSkills.valueOf(skillString);
					nontechSkillsList.add(skill);
				}
				
				i++;
			}
			nonTechSkills = nontechSkillsList;
			
			setTmp_student(new Student(Name, Contactnumber, Id, pwd, gpa, sex, grade, age,
					techSkills, nonTechSkills));
			
			pstmt.close();
			pstmt2.close();
			pstmt3.close();
			pstmt4.close();
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("sql error = " + e.getMessage());
		}
		
		return getTmp_student();
	}
	
	public Student getTmp_student()
	{
		return tmp_student;
	}

	public void setTmp_student(Student tmp_student)
	{
		this.tmp_student = tmp_student;
	}
}