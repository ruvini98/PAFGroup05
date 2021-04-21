package model;
import java.sql.*;

public class Project {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertProject(String code, String projectname, String desc, String startdate, String enddate)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into project (`projectCode`,`projectName`,`projectDesc`,`projectstartdate`,`projectenddate`,`projectID`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values

	 preparedStmt.setString(1, code);
	 preparedStmt.setString(2, projectname);
	 preparedStmt.setString(3, desc);
	 preparedStmt.setString(4, startdate);
	 preparedStmt.setString(5, enddate);
	 preparedStmt.setInt(6, 0);
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the project.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readProject()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Project code</th>" +
	 "<th>Project Name</th>" +
	 "<th>Project Description</th>" +
	 "<th>Start Date</th>" +
	 "<th>End Date</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from Project";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String projectID = Integer.toString(rs.getInt("projectID"));
	 String projectCode = rs.getString("projectCode");
	 String projectName = rs.getString("projectName");
	 String projectDesc = rs.getString("projectDesc");
	 String projectstartdate = rs.getString("projectstartdate");
	 String projectenddate = rs.getString("projectenddate");
	 // Add into the html table
	 output += "<tr><td>" + projectCode + "</td>";
	 output += "<td>" + projectName + "</td>";
	 output += "<td>" + projectDesc + "</td>";
	 output += "<td>" + projectstartdate + "</td>";
	 output += "<td>" + projectenddate + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update 'class=btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='projectID' type='hidden' value='" + projectID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the project.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateProject(String ID, String code, String projectname, String desc, String startdate, String enddate)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE project SET projectCode=?,projectName=?,projectDesc=?,projectstartdate=?,projectenddate=? WHERE projectID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, code);
		 preparedStmt.setString(2, projectname);
		 preparedStmt.setString(3, desc);
		 preparedStmt.setString(4, startdate);
		 preparedStmt.setString(5, enddate);
		 preparedStmt.setInt(6, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the project.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteProject(String projectID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from project where projectID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(projectID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the project.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

}
