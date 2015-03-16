package database;

import java.sql.*;
import java.util.ArrayList;

import elements.NonTechSkills;
import elements.Student;
import elements.TechSkills;

public class searchstudent {

	protected final String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	protected final String user = "projectdb";
	protected final String pass = "projectdb";
	
	private ArrayList<Student> selectStudent = new ArrayList();
	
	public ArrayList<Student> getstudentdb(int minimumGrade,
			ArrayList<TechSkills> techSkills,
			ArrayList<NonTechSkills> nonTechSkills) {

		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 검색 성공!");
		} catch (ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.exit(1);
		}
		
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			String query = null;
			query = "select * from student s, techskills t, nontechskills n"
					+ " where s.id=t.id and s.id=n.id"
					+ " and s.gpa>=?" 
					+ " and t.java=?" + " and t.apacheserver=?"
					+ " and t.oraclesql=?" + " and t.oopdesign = ?" 
					+ " and n.driving_license = ?" + " and n.quicklearning = ?" 
					+ " and n.teamwork = ? ";
			
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, minimumGrade);
			
			// 처음엔 모두 f로 설정
			pstmt.setString(2, "f"); pstmt.setString(3, "f"); pstmt.setString(4, "f");
			pstmt.setString(5, "f"); pstmt.setString(6, "f"); pstmt.setString(7, "f");
			pstmt.setString(8, "f");
			
			for (int i = 0; i < techSkills.size(); i++) {
				TechSkills tech = techSkills.get(i);
				if (tech.toString() == "Java")
					pstmt.setString(2, "t");
				if (tech.toString() == "Apache server")
					pstmt.setString(3, "t");
				if (tech.toString() == "Oracle SQL")
					pstmt.setString(4, "t");
				if (tech.toString() == "Object-Oriented Programming")
					pstmt.setString(5, "t");
			}
			
			for (int i = 0; i < nonTechSkills.size(); i++) {
				NonTechSkills tech = nonTechSkills.get(i);
				if (tech.toString() == "Driving licence")
					pstmt.setString(6, "t");
				if (tech.toString() == "Quick learning")
					pstmt.setString(7, "t");
				if (tech.toString() == "Teamwork")
					pstmt.setString(8, "t");
			}
			
			System.out.println("work!");
			// Result 읽어옴
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				float gpa = rs.getFloat("gpa");
				String sex = rs.getString("sex");
				int grade = rs.getInt("grade");
				int age = rs.getInt("age");
				String contactnumber = rs.getString("contactnumber");
				
				Student s  = new Student(name, contactnumber, id, null, gpa, sex, grade, age, null, null);
				selectStudent.add(s);
			}
			
			pstmt.close();
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
		
		return selectStudent;

	}
}
