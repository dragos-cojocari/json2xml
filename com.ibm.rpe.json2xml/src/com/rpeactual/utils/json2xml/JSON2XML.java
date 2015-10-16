/*******************************************************************************
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 *******************************************************************************/
package com.rpeactual.utils.json2xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.inst2xsd.Inst2Xsd;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.json.JSONObject;

/**
 * Utility to convert JSON to XML
 * 
 * @author Dragos Cojocari
 *
 */
public class JSON2XML
{
	private static final String UTF_8 = "UTF-8";

	/**
	 * Takes an JSON input stream and returns an XML output stream 
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 * @throws XmlException 
	 */
	public void json2xml( InputStream jsonStream, String outputFolder) throws IOException, XmlException
	{
		StringWriter writer = new StringWriter();
		IOUtils.copy( jsonStream, writer, UTF_8);
		String jsonStr = writer.toString();
		
		JSONObject o = new JSONObject(jsonStr);
		
		String xml = org.json.XML.toString(o);
		
		File xmlFile = new File(outputFolder, "output.xml");
		OutputStream xmlStream =  new FileOutputStream( xmlFile );
		
		IOUtils.write(xml, xmlStream, UTF_8);
		
		File xsdFile = new File(outputFolder, "output.xsd");
		xml2xsd( xmlFile.getAbsolutePath(), xsdFile.getAbsolutePath());
	}
	
	public void xml2xsd(String xmlPath, String pathToSaveXSD) throws XmlException, IOException
	{
		Inst2XsdOptions inst2XsdOptions = new Inst2XsdOptions();
		inst2XsdOptions.setDesign(Inst2XsdOptions.DESIGN_RUSSIAN_DOLL);
		 
		File[] xmlFiles = new File[1];
		xmlFiles[0] = new File(xmlPath);

		XmlObject[] xmlInstance = new XmlObject[1];
		xmlInstance[0] = XmlObject.Factory.parse(xmlFiles[0]);

		SchemaDocument[] schemaDocuments = Inst2Xsd.inst2xsd(xmlInstance, inst2XsdOptions);
		if (schemaDocuments != null && schemaDocuments.length > 0)
		{
			SchemaDocument schema = schemaDocuments[0];
			String schemaStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //$NON-NLS-1$
			if (!schema.toString().startsWith(schemaStr))
			{
				schemaStr += schema.toString();
			}
			else
			{
				schemaStr = schema.toString();
			}

			IOUtils.write(schemaStr, new FileOutputStream(pathToSaveXSD), UTF_8); 
		}
	}

	public static void main(String[] args) throws IOException, XmlException
	{
		if ( args.length != 2)
		{
			System.err.println( "Invalid number of arguments. Syntax is: json2xml <jsonInputFile> <xmlOutputFolder>");
			return;
		}
		
		InputStream jsonStream =  new FileInputStream( new File(args[0]));
		
		new JSON2XML().json2xml( jsonStream, args[1]);
	}

}
