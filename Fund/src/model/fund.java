package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class fund {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertfund(String amound, String name, String ownername, String desc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into fund(`fundid`,`fundamound`,`projectNmae`,`ownerNmae`,`descript`)"+ " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	
	 preparedStmt.setDouble(2, Double.parseDouble(amound));
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, ownername);
	 preparedStmt.setString(5, desc);
	// execute the statement
	
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the fund.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readfund()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>fund amound</th><th>project Name</th>" +
	 "<th>owner Nmae</th>" +
	 "<th>descript</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from fund";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String fundid = Integer.toString(rs.getInt("fundid"));
	 String fundamound  = Double.toString(rs.getDouble("fundamound"));
	 String projectNmae = rs.getString("projectNmae");
	 String ownerNmae = rs.getString("ownerNmae");
	 String descript = rs.getString("descript");
	 // Add into the html table
	 output += "<tr><td>" + fundamound + "</td>";
	 output += "<td>" + projectNmae + "</td>";
	 output += "<td>" + ownerNmae + "</td>";
	 output += "<td>" + descript + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='funds.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='fundid' type='hidden' value='" + fundid
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the fund.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updatefund(String ID,String amound, String name, String ownername, String desc)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE fund SET fundamound=?,projectNmae=?,ownerNmae=?,descript=? WHERE fundid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setDouble(1, Double.parseDouble(amound));
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, ownername);
	
	 preparedStmt.setString(4, desc);
	 preparedStmt.setInt(5, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the fund.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteFund(String fundid)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from fund where fundid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fundid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the fund.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
}
