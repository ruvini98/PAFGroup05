package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Project;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Project") 

public class ProjectService {
	
	Project projObj = new Project();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProject()
	 {
		return projObj.readProject();
	 
	 }
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectCode") String projectCode,
	 @FormParam("projectName") String projectName,
	 @FormParam("projectDesc") String projectDesc,
	 @FormParam("projectstartdate") String projectstartdate,
	 @FormParam("projectenddate") String projectenddate)

	{
	 String output = projObj.insertProject(projectCode, projectName, projectDesc, projectstartdate, projectenddate);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData)
	{
	//Convert the input string to a JSON object
	 JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
	//Read the values from the JSON object
	 String projectID = projectObject.get("projectID").getAsString();
	 String projectCode = projectObject.get("projectCode").getAsString();
	 String projectName = projectObject.get("projectName").getAsString();
	 String projectDesc = projectObject.get("projectDesc").getAsString();
	 String projectstartdate = projectObject.get("projectstartdate").getAsString();
	 String projectenddate = projectObject.get("projectenddate").getAsString();
	 String output = projObj.updateProject(projectID, projectCode, projectName, projectDesc, projectstartdate, projectenddate);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String projectID = doc.select("projectID").text();
	 String output = projObj.deleteProject(projectID);
	return output;
	}

}
