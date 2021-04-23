package model;

import java.sql.*;

public class User {
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertUser(String code, String name, String phone, String mail)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " INSERT INTO `users`(`userID`, `username`, `password`, `phoneNo`, `email`)"
		 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, code);
		 preparedStmt.setString(3, name);
		 preparedStmt.setString(4, phone);
		 preparedStmt.setString(5, mail);
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the user.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readUsers()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Username</th><th>Password</th>" +
		 "<th>Phone NO</th>" +
		 "<th>Email</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from users";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String userID = Integer.toString(rs.getInt("userID"));
		 String username = rs.getString("username");
		 String password = rs.getString("password");
		 String phoneNo = rs.getString("phoneNo");
		 String email = rs.getString("email");
		 // Add into the html table
		 output += "<tr><td>" + username + "</td>";
		 output += "<td>" + password + "</td>";
		 output += "<td>" + phoneNo + "</td>";
		 output += "<td>" + email + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='userID' type='hidden' value='" + userID+ "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the users.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updateUser(String ID, String code, String name, String phone, String mail)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE users SET username=?,password=?,phoneNo=?,email=?WHERE userID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, code);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, mail);
			 preparedStmt.setString(4, phone);
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the user.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String deleteUser(String userID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from users where userID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the user.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 

}
