package com.ibm.rpe.json2xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class JSON2XML
{
	/*
	public void convert( String jsonStr)
	{
		JsonParser parser = new JsonParser();
		JsonObject o = (JsonObject)parser.parse( jsonStr);
		
		System.out.println( o.toString());
	}
	*/

	public void convert( String jsonStr)
	{
		JSONObject o = new JSONObject(jsonStr);
		String xml = org.json.XML.toString(o);
		
		System.out.println( xml);
	}

	public static void main(String[] args) throws IOException
	{
		InputStream stream =  JSON2XML.class.getResourceAsStream("/test/test1.json");
		StringWriter writer = new StringWriter();
		IOUtils.copy(stream, writer, "UTF-8");
		String jsonStr = writer.toString();

		System.out.println( jsonStr);
		
		new JSON2XML().convert( jsonStr);

	}

}
