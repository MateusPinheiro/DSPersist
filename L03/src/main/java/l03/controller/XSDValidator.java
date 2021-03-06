package l03.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XSDValidator extends DefaultHandler{

	String valid;
	String xvalid;
	private void parseDocument () {
		//get a factory 
		SAXParserFactory spf = SAXParserFactory.newInstance();	
		try	{
			// get a new instance of parser	
			SAXParser sp = spf.newSAXParser();
			//parse	the	file and also register this	class for call backs
			sp.parse(xvalid, this);

		} catch(SAXException se)	{
			se.printStackTrace();
		} catch(ParserConfigurationException pce)	{ 
			pce.printStackTrace();
		} catch	(IOException ie){	
			ie.printStackTrace();
		}	

	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)	throws SAXException {	
		for(int i = 0; i < attributes.getLength(); i++){
			if(attributes.getQName(i).equalsIgnoreCase("xsi:schemaLocation")){
				valid = attributes.getValue(i);
			}
		}
	}

	public void validar(String xml){
		xvalid = xml;
		parseDocument();
		boolean isValid = validateXMLSchema(valid,xvalid);
		
		if(isValid){
			System.out.println("IS VALID!!");
		} else {
			System.out.println("IS NOT VALID!!");
		}
	}

	public static boolean validateXMLSchema(String xsdPath, String xmlPath){
		try {
			SchemaFactory factory =
					SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
		} catch (IOException e){
			System.out.println("Exception: "+e.getMessage());
			return false;
		}catch(SAXException e1){
			System.out.println("SAX Exception: "+e1.getMessage());
			return false;
		}

		return true;

	}
}
