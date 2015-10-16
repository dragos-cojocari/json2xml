package com.ibm.rpe.json2xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 * Utility to convert JSON to XML
 * 
 * @author Dragos Cojocari
 *
 */
public class JSON2XML
{
	/**
	 * Takes an JSON input stream and returns an XML output stream 
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
	public void json2xml( InputStream inputStream, OutputStream outputStream) throws IOException
	{
		StringWriter writer = new StringWriter();
		IOUtils.copy( inputStream, writer, "UTF-8");
		String jsonStr = writer.toString();
		
		JSONObject o = new JSONObject(jsonStr);
		
		String xml = org.json.XML.toString(o);
		
		IOUtils.write(xml, outputStream, "UTF-8");
	}

	public static void main(String[] args) throws IOException
	{
		if ( args.length != 2)
		{
			System.err.println( "Invalid number of arguments. Syntax is: json2xml <jsonInputPath> <xmlOutputPath>");
			return;
		}
		
		InputStream jsonStream =  new FileInputStream( new File(args[0]));
		
		OutputStream outputStream =  new FileOutputStream( new File(args[1]));
		
		new JSON2XML().json2xml( jsonStream, outputStream);
	}

}
