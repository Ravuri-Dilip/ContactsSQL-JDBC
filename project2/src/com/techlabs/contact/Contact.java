package com.techlabs.contact;

import java.sql.*;

public class Contact {
	private Connection connection = null;
	private Statement stmt = null;
	private java.sql.PreparedStatement pstmt = null;
	private ResultSet res = null;

	public void createConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/swabhav_techlabs?user=root&password=root");
			System.out.println("Successful Connection.");
			stmt = connection.createStatement();
			pstmt = connection.prepareStatement("");
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful");
			e.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException {
		if (res != null)
			res.close();
		if (connection != null)
			connection.close();
		System.out.println("Connection closed successfully!");
	}

	public void display() throws SQLException {
		if (stmt == null) {
			System.err.println("Connection doesn't exist!");
		}
		pstmt = connection.prepareStatement("select * from contact order by fname, lname;");
		res = pstmt.executeQuery();
		while (res.next())
			System.out.println(
					res.getString(1) + "  " + res.getString(2) + "  " + res.getLong(3) + "  " + res.getString(4));
		res.close();
	}

	public void add(String fname, String lname, Long number, String email) throws SQLException {
		if (stmt == null) {
			System.err.println("Connection doesn't exist!");
		}
		String query = "insert into contact(fname,lname,number,email)\r\n" + "values(?,?,?,?);";
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, fname);
		pstmt.setString(2, lname);
		pstmt.setLong(3, number);
		pstmt.setString(4, email);
		int rows = pstmt.executeUpdate();
		System.out.println("Insertion successful");
	}

	public void update(String fname, String lname, Long number, String email) throws SQLException {

		String query;
		if (number != 0) {
			query = "Update contact set number=? where fname = ? and lname =?";
			pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, number);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			int rows = pstmt.executeUpdate();
			System.out.println("updation successful");
		}
		if (!email.contentEquals("no")) {
			query = "Update contact set email= ? where fname = ? and lname =?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			int rows = pstmt.executeUpdate();
			System.out.println("Updation successful");
		}
	}

	public void delete(String fname, String lname) throws SQLException {
		String query = "delete from contact where fname =  ?  and lname =?;";
		pstmt = connection.prepareStatement(query);
		pstmt.setString(1, fname);
		pstmt.setString(2, lname);
		int rows = pstmt.executeUpdate();
		pstmt.execute();
		System.out.println(pstmt);
		System.out.println("Deletion successful");
	}

	public boolean exists(String fname, String lname) throws SQLException, ClassNotFoundException {
		if (stmt == null) {
			return false;
		}
		res = stmt.executeQuery("select * from contact where fname = \"" + fname + "\" and lname =\"" + lname + "\";");
		boolean result = res.next();
		res.close();
		return result;
	}

	public boolean numberExists(Long number) throws SQLException, ClassNotFoundException {
		if (stmt == null) {
			return false;
		}
		res = stmt.executeQuery("select * from contact where number = " + number + ";");
		boolean result = res.next();
		res.close();
		return result;
	}

	public boolean emailExists(String email) throws SQLException, ClassNotFoundException {
		if (stmt == null) {
			return false;
		}
		res = stmt.executeQuery("select * from contact where email = \"" + email + "\";");
		boolean result = res.next();
		res.close();
		return result;
	}

}
