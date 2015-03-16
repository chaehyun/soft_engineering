package database;

import java.sql.*;

public class logindemo {

	protected final String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	protected final String user = "projectdb";
	protected final String pass = "projectdb";

	/*
	 * private Connection connect;
	 * 
	 * public Connection getConn() { return connect; }
	 * 
	 * public void setConn(Connection conn) { this.connect = conn; }
	 * 
	 * public void connect() {
	 * 
	 * try{ Class.forName("oracle.jdbc.driver.OracleDriver");
	 * System.out.println("드라이버 검색 성공!"); }catch(ClassNotFoundException e){
	 * System.err.println("error = " + e.getMessage()); System.exit(1); }
	 * 
	 * try{ setConn(DriverManager.getConnection(url, user, pass));
	 * }catch(SQLException e){ System.err.println("sql error = " +
	 * e.getMessage()); System.exit(1); } }
	 */
	public boolean loginchk(String userid, String userpd) {
		String query;
		int result;
		String pwd = null;

		Connection conn = null;
		/*
		 * connect();
		 */
		// System.out.println("userid: " + userid + "userpd: " + userpd);
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

			query = "select pwd from userinfo where id = ?";
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pwd = rs.getString("pwd");
			}

			System.out.println("pwd: " + pwd);

			/*
			 * while(rs.next()){ String id = rs.getString(1); pwd =
			 * rs.getString("pwd"); System.out.println("Id = " + id + " pwd = "
			 * + pwd); }
			 */

			rs.close();
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}

		if (userpd.equals(pwd) == true)
			return true;
		else
			return false;

	}

	/*
	 * try{ conn.setAutoCommit(false); Statement stmt = conn.createStatement();
	 * 
	 * query = "select * from userinfo"; //PreparedStatement pstmt = null;
	 * //pstmt = conn.prepareStatement(query); ResultSet rs =
	 * stmt.executeQuery(query);
	 * 
	 * while(rs.next()){ String id = rs.getString(1); int pwd =
	 * rs.getInt("pwd"); System.out.println("Id = " + id + " pwd = " + pwd); }
	 * rs.close(); conn.commit(); conn.setAutoCommit(true); stmt.close();
	 * conn.close(); }catch(Exception e){ System.err.println("sql error = " +
	 * e.getMessage()); }
	 */
}
