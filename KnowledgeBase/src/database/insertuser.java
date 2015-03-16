package database;

import java.sql.*;

public class insertuser {

	protected final String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	protected final String user = "projectdb";
	protected final String pass = "projectdb";

	public boolean registerchk(String userid, String userpd) {
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
			/* insert into user table */
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			String sql = null;
			sql = "insert into userinfo values(?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpd);
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

	public boolean idValidation(String userid) {
		Connection conn = null;
		int count = 0;

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
			query = "select * from userinfo where id = ?";
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
			}

			pstmt.close();

			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}

		if (count == 0) {
			return true;
		} else
			return false;
	}
}
