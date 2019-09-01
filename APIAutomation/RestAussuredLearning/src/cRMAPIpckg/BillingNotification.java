package cRMAPIpckg;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
//import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.w3c.dom.xpath.XPathExpression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Properties;
import java.util.Scanner;

public class BillingNotification {

	public static Document doc = null;
	public static Transformer transformer = null;
	public static String newDate = null;
	public static String File = null;
	public static FileInputStream fs = null;
	public static String OrderID;
	public static int CountOA;
	public static int CountAP;
	public static int j = 1;
	public static String CRD = null;
	public static String[] OAID;
	public static String[] OAType;
	public static String[] NB_APID;
	public static Statement stmt1;
	public static String resu;
	public static Statement stmt;
	public static String Env_Token;
	// public static XSSFWorkbook workbook;
	// public static XSSFSheet worksheet;
	// public static DataFormatter formatter = new DataFormatter();
	public static String file_location;
	// public static String SheetName1 = "UAT2Console";
	// public static String SheetName2 = "T34Console";
	public static String EnvUser;
	public static String endpoint = "http://gbvls-as495:40200/com_amdocs_cih_services_oms_interfaces_manageBillingNotificationResponse/com.amdocs.cih.services.oms.interfaces.manageBillingNotificationResponse?wsdl";
	// Scanner in = new Scanner(System.in);
	//public static String endpoint = "http://ukvtrkvr:43400/com_amdocs_cih_services_oms_interfaces_manageBillingNotificationResponse/com.amdocs.cih.services.oms.interfaces.manageBillingNotificationResponse?wsdl";

	@Test(priority = 2, groups = { "Provide Order Details" })
	public static void UserDetails() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter OrderID [Example: 123A or 123]");
		OrderID = in.next();
		for (int z = 0; z < OrderID.length(); z++) {
			OrderID = OrderID.replace("A", "");
		}
	}

	// DB Connection

	@Test(priority = 1, groups = { "SOM And AO DB Connect" })
	public void AoSOMdb() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Scanner in = new Scanner(System.in);

		// Get input String
		System.out.println("Please enter an Environment:like UAT2 / T34 / T31 ... ");
		EnvUser = in.nextLine();

		String[] strArray3 = new String[2];
		strArray3[0] = "UAT2";
		strArray3[1] = "T34";

		if (EnvUser.equals(strArray3[0])) {
			// SOM DB Connection
			Connection SomCon = DriverManager.getConnection("jdbc:oracle:thin:@GBVLS-DB096:1521:csomu2", "CSOMU2",
					"CSOMU2");
			stmt = SomCon.createStatement();

			// AO DB Connection

			Connection AOCon = DriverManager.getConnection("jdbc:oracle:thin:@crmdbtst01:1521:COMST1", "VFK2oms",
					"VFK2oms");
			stmt1 = AOCon.createStatement();
		}

		if (EnvUser.equals(strArray3[1])) {
			Connection AOCon = DriverManager.getConnection("jdbc:oracle:thin:@crm-db-tst-04:1521:COMST3", "VFK34OMS",
					"VFK34OMS");
			stmt1 = AOCon.createStatement();

			Connection SomCon = DriverManager.getConnection("jdbc:oracle:thin:@crm-db-tst-04:1521:CSOMT34", "CSOMT34",
					"CSOMT34");
			stmt = SomCon.createStatement();
		}
		// in.close();
	}

	// Get Order Details

	// Count OA

	@Test(priority = 3, groups = { "Get Order Details" })
	public void OACount() throws SQLException {

		String ST8 = "select count(order_unit_id) as OA_Count from tborder_action where order_id = ";
		String Order_Actions = ST8 + OrderID;
		ResultSet rs = stmt1.executeQuery(Order_Actions);
		while (rs.next()) {
			String CountOAres = rs.getString("OA_Count");
			CountOA = Integer.parseInt(CountOAres);
		}

	}

	// New Queries Start
	@Test(priority = 4, groups = { "Get Order Action ID" })
	public void OADetails() throws SQLException {
		OAID = new String[CountOA];
		OAType = new String[CountOA];
		String ST8 = "select order_unit_id as OA_ID, action_type as Order_Action_Type from tborder_action where order_id = ";
		String Order_Actions = ST8 + OrderID;
		ResultSet rs = stmt1.executeQuery(Order_Actions);
		int z = 0;
		String OAIDres;
		String OATyperes;
		while (rs.next()) {
			int rowcount = rs.getRow();
			for (int i = 0; i < rowcount; i++) {

				if (i == z) {
					OAIDres = rs.getString("OA_ID");
					OAID[i] = OAIDres;
					OATyperes = rs.getString("Order_Action_Type");
					OAType[i] = OATyperes;
					z = z + 1;

				}
			}
		}

	}

	@Test(priority = 5, groups = { "Generate Token" })
	public void TokenGen() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException,
			TransformerException {
		String endpoint1 = "http://gbvls-as495:30200/WebServices/amdocs.aif.uams.sessions.interfaces.api.ASM3LoginServices?wsdl";
		// String endpoint1="http://10.196.208.81:33400/WebServices/amdocs.aif.uams.sessions.interfaces.api.ASM3LoginServices?WSDL";
		String filepath = "C:\\Users\\Public\\Automation\\SearchOrder\\TokenGenerationUAT2.xml";
		//String filepath = "C:\\Users\\Public\\Automation\\SearchOrder\\TokenGenerationT34.xml";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint1);
		post.setEntity(new InputStreamEntity(new FileInputStream(filepath)));
		post.setHeader("Content-Type", "text/xml; charset=utf-8");
		HttpResponse response = client.execute(post);
		System.out.println(response.getStatusLine());

		System.out.println(" Response:" + response.toString());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document doc1 = builder.parse(new InputSource(new InputStreamReader(response.getEntity().getContent())));
		// System.out.println(doc1);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source1 = new DOMSource(doc1);

		String responseFilePath = "C:\\Users\\Public\\Automation\\SearchOrder\\SearchAPLog\\TokengenerationResponse_"
				+ ".xml";

		System.out.println("*******************************************************************************");
		System.out.println("Path of Tokem response xml file  " + responseFilePath);
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
			System.out.println("Security token " + Env_Token);

		}

	}

	@Test(priority = 6, groups = { "Create Billing Notification" })
	public static void NotifyBilling() throws SAXException, IOException, TransformerException, SQLException,
			ClassNotFoundException, XPathExpressionException, ParserConfigurationException {

		for (int i = 0; i < CountOA; i++) {
			String ST8 = "select count(ap_id) as AP_Count from tbap_item where order_action_id = ";
			String Order_Actions = ST8 + OAID[i];
			ResultSet rs = stmt1.executeQuery(Order_Actions);
			while (rs.next()) {
				String CountAPres = rs.getString("AP_Count");
				CountAP = Integer.parseInt(CountAPres);
			}

			NB_APID = new String[CountAP];
			String ST9 = "select ap.ap_id as AP_ID from tbap_item ap, tborder_action oa where ap.order_action_id=oa.order_unit_id and oa.order_unit_id = ";
			String AP_Details = ST9 + OAID[i];
			ResultSet rs1 = stmt1.executeQuery(AP_Details);

			int z = 0;
			String APIDres;
			while (rs1.next()) {
				int rowcount = rs1.getRow();
				for (int j = 0; j < rowcount; j++) {

					if (j == z) {
						APIDres = rs1.getString("AP_ID");
						NB_APID[j] = APIDres;
						z = z + 1;

					}
				}
			}

			// Create Notify Billing XML

			String filepath = "C:\\Users\\Public\\Automation\\BillingNotification\\In\\NotifyBilling.xml";

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node Sec_Token = doc.getElementsByTagName("securityToken").item(0);
			Sec_Token.setTextContent(Env_Token);

			Node OrderActionID = doc.getElementsByTagName("orderActionIdX9").item(0);
			OrderActionID.setTextContent(OAID[i]);

			Node OrderActionType = doc.getElementsByTagName("orderActionTypeX9").item(0);
			OrderActionType.setTextContent(OAType[i]);

			// Add serviceStatusInfoList = APID's in OrderAction.
			Node serviceStatusInfoListChildAdd;
			Node serviceStatusInfoListMain = doc.getElementsByTagName("serviceStatusInfoListX9").item(0);
			NodeList list = serviceStatusInfoListMain.getChildNodes();
			for (int j = 0; j < list.getLength(); j++) {

				Node serviceStatusInfoListChild = list.item(j);
				if ("serviceStatusInfoListX9".equals(serviceStatusInfoListChild.getNodeName())) {
					for (int k = 1; k < CountAP; k++) {
						serviceStatusInfoListChildAdd = serviceStatusInfoListChild.cloneNode(true);
						serviceStatusInfoListMain.appendChild(serviceStatusInfoListChildAdd);
					}
					break;
				}

			}

			// Update serviceStatusInfoList with APID's in OrderAction.
			for (int j = 0; j < CountAP; j++) {
				Node APID = doc.getElementsByTagName("apIDX9").item(j);
				APID.setTextContent(NB_APID[j]);
				Node BillingStatus = doc.getElementsByTagName("billingStatusX9").item(j);
				//if (OAType[i] == "CE") {
				if ("CE".equals(OAType[i])) {
					BillingStatus.setTextContent("Ceased");
				} else {
					BillingStatus.setTextContent("Live");
				}
			}

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			// Create request file with the required parameters.
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
			String dateAndTime = formatter.format(new Date().getTime());
			String requestFilePath = "C:\\Users\\Public\\Automation\\BillingNotification\\Out\\BillingNotification-"
					+ OAID[i] + "-" + dateAndTime + ".xml";
			File requestFile = new File(requestFilePath);
			FileOutputStream outStream = new FileOutputStream(requestFile);
			transformer.transform(new DOMSource(doc), new StreamResult(outStream));
			System.out.println("######## Request ##########");
			System.out.println(" Request:" + requestFile);
			System.out.println(" Request file location:  " + requestFilePath);

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(endpoint);
			post.setEntity(new InputStreamEntity(new FileInputStream(requestFilePath)));
			post.setHeader("Content-Type", "text/xml; charset=utf-8");
			HttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine());

			System.out.println(" Response:" + response.toString());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document doc1 = builder.parse(new InputSource(new InputStreamReader(response.getEntity().getContent())));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			DOMSource source1 = new DOMSource(doc1);

			String responseFilePath = "C:\\Users\\Public\\Automation\\BillingNotification\\Out\\BillingNotificationResponse-"
					+ OAID[i] + "-" + dateAndTime + ".xml";

			System.out.println("*******************************************************************************");
			System.out.println("Path of response xml file  " + responseFilePath);
			StreamResult result1 = new StreamResult(new File(responseFilePath));
			transformer.transform(source1, result1);

		}

	}
	// New Queries End

}
