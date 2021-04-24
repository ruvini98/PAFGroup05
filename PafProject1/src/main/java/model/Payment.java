package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
			 }
		 return con;
		 }
		public String insertPayment(String code, String cname, String pamount, String pname)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for inserting.";
			 }
		 // create a prepared statement
		 String query = " insert into payments(`paymentID`,`bankCode`,`customerName`,`paymentAmount`,`productName`)"
		 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, code);
		 preparedStmt.setString(3, cname);
		 preparedStmt.setDouble(4, Double.parseDouble(pamount));
		 preparedStmt.setString(5, pname);
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the payment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readPayments()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; 
			 }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Bank Code</th><th>customerName</th>" +
		 "<th>Payment Amount</th>" +
		 "<th>Product Name</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from payments";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String paymentID = Integer.toString(rs.getInt("paymentID"));
		 String bankCode = rs.getString("bankCode");
		 String customerName = rs.getString("customerName");
		 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
		 String productName = rs.getString("productName");
		 // Add into the html table
		 output += "<tr><td>" + bankCode + "</td>";
		 output += "<td>" + customerName + "</td>";
		 output += "<td>" + paymentAmount + "</td>";
		 output += "<td>" + productName+ "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='payments.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + paymentID+ "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the payments.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updatePayment(String ID, String code, String cname, String pamount, String pname)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE payments SET bankCode=?,customerName=?,paymentAmount=?,productName=?WHERE paymentID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, code);
			 preparedStmt.setString(2, cname);
			 preparedStmt.setDouble(3, Double.parseDouble(pamount));
			 preparedStmt.setString(4, pname);
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the payment.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String deletePayment(String paymentID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting."; 
				 }
			 // create a prepared statement
			 String query = "delete from payments where paymentID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(paymentID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the payment.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 

}
