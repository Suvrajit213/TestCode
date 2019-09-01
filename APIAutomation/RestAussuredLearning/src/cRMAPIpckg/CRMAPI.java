package cRMAPIpckg;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class CRMAPI {
	
    @Test
	public void RetriveBillingAccount() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {

                            
                String customerIdX2 = "860772";//mandatory
                String rateClassX2 = "";
                String systemNameX2 = "SSBS";
                                
                //url to send the soap request.  
                String endpoint = "http://gbvls-as495.ad.plc.cwintra.com:30200/CRMServices/com.vfuk.crm.services.billing.interfaces.XIBillingServices?wsdl";
                String tokenUserName = "Asmsa1";
                String tokenPassword = "London@18";
                
                
                String filepath = "C:\\Users\\Public\\Automation\\SearchOrder\\retriveBillingAccountsRequest.xml";
                File inputFile = new File(filepath);             
                if(!inputFile.exists())
                {
                                System.out.println("Input File 'retriveBillingAccountsRequest.xml' not found!");   
                }
                else
                {                                                                              
                                if(customerIdX2 == ""){
                                                System.out.println("customerIdX2 is Mandatory!"); 
                                }
                                else{

                                                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                                                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                                                Document doc = docBuilder.parse(filepath);
                                                
                                                //set username and password in the request file.                              
                                                XPath xpath = XPathFactory.newInstance().newXPath();
                                                XPathExpression exp = xpath.compile("/Envelope/Header/Security/UsernameToken/Username");
                                                Object userNameObject = exp.evaluate(doc, XPathConstants.NODESET);                                
                                                NodeList userName= (NodeList) userNameObject;
                                                userName.item(0).setTextContent(tokenUserName);                      
                                                exp = xpath.compile("/Envelope/Header/Security/UsernameToken/Password");
                                                Object passwordObject = exp.evaluate(doc, XPathConstants.NODESET);
                                                NodeList password= (NodeList) passwordObject;               
                                                password.item(0).setTextContent(tokenPassword);
                                                                                                                
                                                //set input parameters in the request file.                            
                                                Node requestAttributes = doc.getElementsByTagName("XBillingRequestADT").item(0);                   
                                                NodeList list = requestAttributes.getChildNodes();                            
                                                for(int i=0;i< list.getLength();i++){
                                                                if(list.item(i).getNodeType()==Node.ELEMENT_NODE){
                                                                                if(list.item(i).getNodeName()=="customerIdX2"){
                                                                                                list.item(i).setTextContent(customerIdX2);
                                                                }
                                                                if(list.item(i).getNodeName()=="rateClassX2"){
                                                                                                list.item(i).setTextContent(rateClassX2);
                                                                }
                                                                if(list.item(i).getNodeName()=="systemNameX2"){
                                                                                                list.item(i).setTextContent(systemNameX2);
                                                                }                                                                                                            
                                                }
                                                }
                                                TransformerFactory tf = TransformerFactory.newInstance();
                                                Transformer transformer = tf.newTransformer();
                                                //Create request file with the required parameters.                         
                                                SimpleDateFormat formatter= new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
                                                String dateAndTime = formatter.format(new Date().getTime());
                                                String requestFilePath = "C:\\Users\\Public\\Automation\\SearchOrder\\SearchAPLog\\retriveBillingAccountsRequest_"+dateAndTime+".xml";
                                                String requestFile  = new File(requestFilePath).toString();
                                                FileOutputStream outStream = new FileOutputStream(requestFile);
                                                transformer.transform(new DOMSource(doc), new StreamResult(outStream));
                                                System.out.println("######## Request ##########");
                                                //System.out.println(" Request:"+requestFile);
                                                System.out.println(" Request file location:  "+requestFilePath);                                         
                                                
                                                HttpClient client = HttpClientBuilder.create().build();
                                                HttpPost post = new HttpPost (endpoint);
                                                post.setEntity(new InputStreamEntity(new FileInputStream(requestFilePath)));
                                                post.setHeader("Content-Type","text/xml; charset=utf-8");
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

                                        		String responseFilePath = "C:\\Users\\Public\\Automation\\SearchOrder\\SearchAPLog\\retriveBillingAccountsResponse_"+dateAndTime+".xml";

                                        		System.out.println("*******************************************************************************");
                                        		System.out.println("Path of response xml file  " + responseFilePath);
                                        		StreamResult result1 = new StreamResult(new File(responseFilePath));
                                        		transformer.transform(source1, result1);
                                                
                                        		
                                                
                                                
                                                
                                                //fetch required data from the response file
                                               /* GPathResult responseText = new XmlSlurper().parseText(response.toString());                                      
                                                NodeList getBillingAccountsList = responseText.Body.retrieveBillingAccountsResponse.getBillingDetailsForCustomer.getBillingAccountsX2;
                                                System.out.println("No of records in response file : "+getBillingAccountsList.getLength();*/                                                                                                                                                                                                                
                                }
                }
                                

                                

	}
}
