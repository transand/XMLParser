package project_tortoise;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class Main {

	private ArrayList<File> xmlFiles;
	private ArrayList<File> xsdFiles;
	
	public boolean validateFile(String filename, String type) {
		File f = new File(",/" +filename);
			
		if (f.exists()) {
			if (type == "xml")
				xmlFiles.add(f);
			else
				xsdFiles.add(f);
			return true;
		} else {
			return false;
		}
	}
	
	static boolean validateAgainstXSD(InputStream xml, InputStream xsd) {
		try {
			SchemaFactory factory =
					SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			
					Schema schema = factory.newSchema(new StreamSource(xsd));
					Validator validator = schema.newValidator();
					validator.validate(new StreamSource(xml));;
					return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		for (String filename: args) {
			System.out.println("Read in file: " + filename);
			if (filename.matches("(?i).*.xml")) {
				m.validateFile(filename, "xml");
			} else if (filename.matches("(?i).*.xsd")) {
				m.validateFile(filename,  "xsd");
			} else {
				System.out.println("Invalid file name. Files must be in the format *.xml or *.xsd.");
				
			}	
		}
		System.out.println("xml files: " + m.xmlFiles.size());
		System.out.println("xsd files: " + m.xsdFiles.size());
	}

}
