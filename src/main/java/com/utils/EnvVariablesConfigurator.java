package com.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class EnvVariablesConfigurator {
	
	private String data;
	
	public String readAppUrl(String env)
	{
		String appUrl = "appURL";
		appUrl = readDataInXML(env,appUrl);
		return appUrl;
	}
	
	public String readUsername(String env)
	{
		String username = "username";
		username = readDataInXML(env,username);
		return username;
	}
	
	public String readPassword(String env)
	{
		String password = "password";
		password = readDataInXML(env,password);
		return password;
	}

	public String readDataInXML(String env, String envVarData) {
		
		try {

			File fXmlFile = new File(Const.Path_TestData + Const.File_EnvVarData);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("environment");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Staff id : " + eElement.getAttribute("name"));
					
					if(env.equalsIgnoreCase(eElement.getAttribute("name")))
					{
						data = eElement.getElementsByTagName(envVarData).item(0).getTextContent();
					}
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		return data;		
	}	
}	