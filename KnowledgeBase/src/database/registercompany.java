package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class registercompany {
	protected final String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	protected final String user = "projectdb";
	protected final String pass = "projectdb";

	public boolean insertcompany(String id, String name, String location,
			String contactNumber) {
		System.out.println("insert to company table");

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
			/* insert into company table */
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			String sql = null;
			// id, name, password, location,contactnumber
			sql = "insert into company values(?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, location);
			pstmt.setString(4, contactNumber);
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
