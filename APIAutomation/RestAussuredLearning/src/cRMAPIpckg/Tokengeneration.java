package cRMAPIpckg;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import groovy.util.XmlSlurper;
//import groovy.util.slurpersupport.GPathResult;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class Tokengeneration {

	public static String endpoint;
	public static String filepath;
	public static String responseFilePath;
	public static String Env_Token;
	public static String EnvUser;

	@Test(groups = { "Tokengeneration" })
	  @Parameters({"SelectEnv"})
	public void TokenGen(@Optional("NA") String SelectEnv) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException,
			TransformerException {
		//Scanner in = new Scanner(System.in);

		// Get input String
		/*System.out.println("Please enter an Environment:like UAT2 / T34 / T31 ... ");
		EnvUser = in.nextLine();*/
		EnvUser=SelectEnv;
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");
        prop.load(input);
        String[] strArray3 = new String[3];
		strArray3[0] = "UAT2";
		strArray3[1] = "T34";
		strArray3[2] = "T11";

		if (EnvUser.equals(strArray3[0])) {
			endpoint = prop.getProperty("EndpointUAT2");
			filepath = prop.getProperty("FilepathUAT2");

		}

		if (EnvUser.equals(strArray3[1])) {

			endpoint = prop.getProperty("EndpointT34");
			filepath = prop.getProperty("FilepathT34");
		}

		if (EnvUser.equals(strArray3[2])) {

			endpoint = prop.getProperty("EndpointT11");
			filepath = prop.getProperty("FilepathT11");
		}

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(filepath)));
		post.setHeader("Content-Type", "text/xml; charset=utf-8");
		HttpResponse response = client.execute(post);
		System.out.println(response.getStatusLine());

		System.out.println(" Response:" + response.toString());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document doc1 = builder.parse(new InputSource(new InputStreamReader(response.getEntity().getContent())));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source1 = new DOMSource(doc1);

		if (EnvUser.equals(strArray3[0])) {
			responseFilePath = prop.getProperty("ResponsefilepathUAT2");
		}

		if (EnvUser.equals(strArray3[1])) {
			responseFilePath = prop.getProperty("ResponsefilepathT34");

		}
		
		if (EnvUser.equals(strArray3[2])) {
			responseFilePath = prop.getProperty("ResponsefilepathT11");

		}

		System.out.println("*******************************************************************************");
		//System.out.println("Path of Token response xml file  " + responseFilePath);
		StreamResult result1 = new StreamResult(new File(responseFilePath));
		transformer.transform(source1, result1);

		Document doc2 = builder.parse(responseFilePath);
		builder = factory.newDocumentBuilder();
		Node token = doc2.getElementsByTagName("ticket").item(0);
		if (token != null) {
			// System.out.println("Token :" + token.getTextContent());
			Env_Token = token.getTextContent();

			String str1 = "EXT<";
			String str2 = ";appId=?;>";
			String STR3 = Env_Token.replace(str1, "");
			String Str4 = STR3.replace(str2, "");
			Env_Token = Str4;
			System.out.println("Security token for Env " +EnvUser+" --> " + Env_Token);

		}

	}
}
