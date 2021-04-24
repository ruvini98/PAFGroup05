package com;
import model.fund;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

	@Path("/Fund")
	public class fundServise
	{
		fund fundObj = new fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFund()
	 {
		return fundObj.readfund();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertfund(@FormParam("fundamound") String fundamound,
	@FormParam("projectNmae") String projectNmae,
	@FormParam("ownerNmae") String ownerNmae,
	@FormParam("descript") String descript)
	{
	String output = fundObj.insertfund(fundamound, projectNmae, ownerNmae, descript);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData)
	{
	//Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	//Read the values from the JSON object
	 String fundid = fundObject.get("fundid").getAsString();
	 String fundamound = fundObject.get("fundamound").getAsString();
	 String projectNmae = fundObject.get("projectNmae").getAsString();
	 String ownerNmae = fundObject.get("ownerNmae").getAsString();
	 String descript = fundObject.get("descript").getAsString();
	 String output = fundObj.updatefund(fundid, fundamound, projectNmae, ownerNmae, descript);
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String fundid = doc.select("fundid").text();
	 String output = fundObj.deleteFund(fundid);
	return output;
	}
	
	}


