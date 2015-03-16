package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import elements.NonTechSkills;
import elements.TechSkills;

public class registerstudent {

	protected final String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	protected final String user = "projectdb";
	protected final String pass = "projectdb";

	public boolean insertstudent(String id, String name, int grade, int age,
			float gpa, String contactNumber, String sex) {

		int result = 0;
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
			/* insert into studnet table */
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			String sql = null;
			String sql2 = null;
			// id, name, gpa, sex, grade, age, contactnumber
			sql = "insert into student values(?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setFloat(3, gpa);
			pstmt.setString(4, sex);
			pstmt.setInt(5, grade);
			pstmt.setInt(6, age);
			pstmt.setString(7, contactNumber);
			result = pstmt.executeUpdate();

			pstmt.close();
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
		if (result == 1) {
			return true;
		} else
			return false;
	}

	public boolean insert_nontechskills(String id,
			ArrayList<NonTechSkills> nontechSkills) {
		int result = 0;
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
			/* insert into studnet table */
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			String sql = null;
			// id, driving licence, quick learning, teamwork
			sql = "insert into nontechskills values(?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, "f");
			pstmt.setString(3, "f");
			pstmt.setString(4, "f");

			/* skills */
			for (int i = 0; i < nontechSkills.size(); i++) {
				NonTechSkills tech = nontechSkills.get(i);
				if (tech.toString() == "Driving licence")
					pstmt.setString(2, "t");
				if (tech.toString() == "Quick learning")
					pstmt.setString(3, "t");
				if (tech.toString() == "Teamwork")
					pstmt.setString(4, "t");
			}

			result = pstmt.executeUpdate();

			pstmt.close();

			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
		if (result == 1) {
			return true;
		} else
			return false;
	}

	public boolean inserttechskills(String id, ArrayList<TechSkills> techSkills) {
		int result = 0;
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
			/* insert into studnet table */
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			String sql = null;
			// id, name, gpa, sex, grade, age, contactnumber
			sql = "insert into techskills values(?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			pstmt.setString(2, "f");
			pstmt.setString(3, "f");
			pstmt.setString(4, "f");
			pstmt.setString(5, "f");
			/* skills */
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

			result = pstmt.executeUpdate();

			pstmt.close();

			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
		if (result == 1) {
			return true;
		} else
			return false;
	}
}
