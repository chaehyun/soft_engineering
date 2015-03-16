package database;

import java.sql.*;

import elements.Company;

public class getcompanybyid {

	protected final String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	protected final String user = "projectdb";
	protected final String pass = "projectdb";

	Company c;
	String Name;
	String Contactnumber;
	String Id;
	String location;
	String pwd;
	
	public Company getcompanybyid(String id) {
		
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
			/* get everything from company table */

			
			
			
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			String query = null;
			query = "select * from company where id = ?";
			String query2 = "select pwd from userinfo where id = ?";
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();

			while (rs.next()) {
				Name = rs.getString("name");
				Contactnumber = rs.getString("Contactnumber");
				Id = id;
				location = rs.getString("location");
			}

			while (rs2.next()) {
				pwd = rs2.getString("pwd");
			}

			c = new Company(Name, Contactnumber, Id, pwd, location);

			pstmt.close();
			pstmt2.close();
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
		return c;
	}

}