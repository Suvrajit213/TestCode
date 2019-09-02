package TestNGFeature;

import static org.testng.Assert.assertTrue;

import java.awt.Toolkit;

import java.awt.datatransfer.Clipboard;

import java.awt.datatransfer.StringSelection;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;

import javax.xml.transform.TransformerFactory;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

/*import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;*/

import org.testng.ReporterConfig.Property;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Optional;

import org.testng.annotations.Parameters;

import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.w3c.dom.Document;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class CreateXml {

	public static String alp;

	public static String nmrc;

	public static int chlqt;

	public static int alpn;

	public static int nmrcn;

	public static int chlMi;

	public static int chlMa;

	public static String RsrvID = null;

	public static String PIBN = null;

	public static String Num = null;

	public static int j = 1;

	public static Document doc = null;

	public static int rel = 0;

	public static int BNTemp = 0;

	public static String RefTempID = null;

	public static Transformer transformer = null;

	public static String newDate = null;

	public static int tempid = 0;

	public static String File = null;

	public static FileInputStream fs = null;

	// public static int fnltmp ;

	// public static Properties Property = new Properties();

	// @Test(dependsOnMethods = { "getAlphaNumericString" })
	
	// @Test(priority =2)
	

	String filename = "C:\\Users\\Public\\Automation\\ProvideOrder\\In\\ProvideOrder.xml";

	@Test(priority = 1, dataProvider = "RandomAlphanumeric")
  public void UpdatedTempid(int a11, int b12, int c13, int d14)
			throws SAXException, IOException, TransformerException, SQLException, ClassNotFoundException {

		// this.alpn = alpn;

		// Property file logic

		// fs = new FileInputStream(System.getProperty("user.dir") +
		// "\\config.properties");

		// Property.load(fs);

		try {

			//String filename = "C:\\Users\\Public\\Automation\\ProvideOrder\\In\\ProvideOrder.xml";

			DocumentBuilderFactory docFactory = DocumentBuilderFactory

					.newInstance();

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			doc = docBuilder.parse(filename);

			docBuilder = docFactory.newDocumentBuilder();

			// TempID Count

			Node OrderDetails = doc

					.getElementsByTagName(

							"com.amdocs.cih.services.order.lib.CreateAndSubmitOrderInput")

					.item(0);

			NodeList list = OrderDetails.getChildNodes();

			for (int a = 0; a < list.getLength(); a++) {

				Node listchild = list.item(a);

				if ("BehaviorOptions".equals(((Node) listchild).getNodeName())) {

					break;

				}

				if ("OrderActions".equals(((Node) listchild).getNodeName())) {

					int j = 1;

					for (int i = 0; i < j; i++) {

						Node OrderDetails1 = doc.getElementsByTagName(

								"TemporaryID").item(i);

						if (OrderDetails1 != null) {

							if ("TemporaryID".equals(((Node) OrderDetails1)

									.getNodeName())) {

								tempid = tempid + 1;

								j = j + 1;

								Node ParentNode = OrderDetails1.getParentNode();

								if ("ReferencedProduct"

										.equals(((Node) ParentNode)

												.getNodeName())) {

									rel = tempid;

								}

								// / New try

								Node TempParent = OrderDetails1.getParentNode();

								if ("com.amdocs.cih.services.orderingactivities.lib.ImplementedProduct"

										.equals(TempParent.getNodeName())) {

									NodeList TempParentChildList = TempParent

											.getChildNodes();

									for (int e = 0; e < TempParentChildList

											.getLength(); e++) {

										Node TempParentChild = TempParentChildList

												.item(e);

										if ("ImplementedAttributes"

												.equals(((Node) TempParentChild)

														.getNodeName())) {

											NodeList ImplAttrChild = TempParentChild

													.getChildNodes();

											for (int b = 0; b < ImplAttrChild

													.getLength(); b++) {

												Node ImplAttrChildAttr = ImplAttrChild

														.item(b);

												NodeList CatalogAttr = ImplAttrChildAttr

														.getChildNodes();

												for (int c = 0; c < CatalogAttr

														.getLength(); c++) {

													Node CatalogAttrChild = CatalogAttr

															.item(c);

													NodeList CatalogID = CatalogAttrChild

															.getChildNodes();

													for (int d = 0; d < CatalogID

															.getLength(); d++) {

														Node CatalogIDChild = CatalogID

																.item(d);

														String CatalogIDChildVal = CatalogIDChild

																.getTextContent();

														// Billing Number, VF

														// DDI, Port-In DDI

														if ("58479253"

																.equals(CatalogIDChildVal)) {

															Node ParentNode1 = CatalogIDChild

																	.getParentNode()

																	.getParentNode();

															NodeList Child = ParentNode1

																	.getChildNodes();

															for (int f = 0; f < Child

																	.getLength(); f++) {

																Node listchild1 = Child

																		.item(f);

																if ("true"

																		.equals(listchild1

																				.getTextContent())) {

																	BNTemp = tempid;

																}

															}

														}

													}

												}

											}

										}

									}

								}

								// / End new try

							}

						}

					}

					System.out.println("Count of TempIds " + " = " + tempid);

				}

			}

			String[] TempID = new String[tempid];

			for (int a = 0; a < tempid; a++) {

				// 9,5,4,4,4,7

				String Alph = getAlphaNumericString(a11);

				String x = getAlphaNumericString(b12);

				String y = getAlphaNumericString(c13);

				String z = getAlphaNumericString(c13);

				String v = getAlphaNumericString(c13);

				String w = getAlphaNumericString(d14);

				TempID[a] = Alph + "-" + v + "-" + y + "-" + z + "-" + w + x;

				TempID[BNTemp - 1] = RefTempID;

				if (a == rel - 1) {

					RefTempID = TempID[a];

				}

				System.out.println("Updated " + "TempID" + a + " = "

						+ TempID[a]);

			}

			// Update TemporaryID Value

			for (int b = 0; b < list.getLength(); b++) {

				Node listchild = list.item(b);

				if ("BehaviorOptions".equals(((Node) listchild).getNodeName())) {

					break;

				}

				if ("OrderActions".equals(((Node) listchild).getNodeName())) {

					for (int i = 0; i < tempid; i++) {

						// Node OrderDetails1 =list.item(i);

						Node OrderDetails2 = doc.getElementsByTagName(

								"TemporaryID").item(i);

						if (OrderDetails2 != null) {

							if ("TemporaryID".equals(((Node) OrderDetails2)

									.getNodeName())) {

								OrderDetails2.setTextContent(TempID[i]);

							}

						}

					}

				}

			}

			// Number and reservation id Update

			// Number and reservation id Update
			
			Node OrderDetails1 = doc.getElementsByTagName("com.amdocs.cih.services.order.lib.CreateAndSubmitOrderInput")
					.item(0);
			NodeList list1 = OrderDetails1.getChildNodes();
			for (int a = 0; a < list1.getLength(); a++) {
				Node listchild = list1.item(a);
				if ("BehaviorOptions".equals(((Node) listchild).getNodeName())) {
					break;
				}
				if ("OrderActions".equals(((Node) listchild).getNodeName())) {

					// Count Implemented Attributes
					for (int x = 0; x < j; x++) {
						Node CountImplAttr = doc.getElementsByTagName("ImplementedAttributes").item(x);
						if (CountImplAttr != null) {
							if ("ImplementedAttributes".equals(((Node) CountImplAttr).getNodeName())) {
								j = j + 1;
							}
						}
					}

					// fetch OA
					String[] OA = new String[j - 1];
					for (int i = 0; i < j; i++) {
						Node ImplAttr = doc.getElementsByTagName("ImplementedAttributes").item(i);
						String BN = null;
						String Porting = null;
						if (ImplAttr != null) {
							if ("ImplementedAttributes".equals(((Node) ImplAttr).getNodeName())) {
								NodeList ImplAttrChild = ImplAttr.getChildNodes();
								for (int b = 0; b < ImplAttrChild.getLength(); b++) {
									Node ImplAttrChildAttr = ImplAttrChild.item(b);
									NodeList CatalogAttr = ImplAttrChildAttr.getChildNodes();

									for (int c = 0; c < CatalogAttr.getLength(); c++) {
										Node CatalogAttrChild = CatalogAttr.item(c);

										NodeList CatalogID = CatalogAttrChild.getChildNodes();
										for (int d = 0; d < CatalogID.getLength(); d++) {
											Node CatalogIDChild = CatalogID.item(d);
											String CatalogIDChildVal = CatalogIDChild.getTextContent();

											// SIP Billing Number
											if ("58471243".equals(CatalogIDChildVal)) {
												Node ParentNode = CatalogIDChild.getParentNode().getParentNode();
												NodeList Child = ParentNode.getChildNodes();
												for (int e = 0; e < Child.getLength(); e++) {
													Node listchild1 = Child.item(e);
													if ("SelectedValue".equals(listchild1.getNodeName())) {
														OA[i] = "SIP Trunk";

													}
												}
											}
											// Billing Number, VF DDI, Port-In
											// DDI
											if ("58479253".equals(CatalogIDChildVal)) {
												Node ParentNode = CatalogIDChild.getParentNode().getParentNode();
												NodeList Child = ParentNode.getChildNodes();
												for (int e = 0; e < Child.getLength(); e++) {
													Node listchild1 = Child.item(e);

													if ("true".equals(listchild1.getTextContent())) {
														BN = "true";
													} else {

														if ("false".equals(listchild1.getTextContent())) {
															BN = "false";
														}
													}
												}
											} else {
												if ("58471563".equals(CatalogIDChildVal)) {
													Node ParentNode = CatalogIDChild.getParentNode().getParentNode();
													NodeList Child = ParentNode.getChildNodes();
													for (int e = 0; e < Child.getLength(); e++) {
														Node listchild2 = Child.item(e);
														if ("No".equals(listchild2.getTextContent())) {
															Porting = "No";
														} else {
															if ("Yes".equals(listchild2.getTextContent())) {
																Porting = "Yes";
															}
														}
													}
												}
											}
										}
									}

								}
							}
							if ((BN != null) && (Porting != null)) {
								if (BN == "true") {
									OA[i] = "Billing Number";
								}
								if ((BN == "false") && (Porting == "No")) {
									OA[i] = "Vodafone DDI";
								}
								if ((BN == "false") && (Porting == "Yes")) {
									OA[i] = "Porting DDI";
								}
							}
						}
					}
					// Update Data to xml
					int VFrom = 101;
					int VTo = 109;
					int PFrom = 1001;
					int PTo = 1009;
					Num = getRandomNumericString(a11);
					String PIBN = getRandomNumericString(b12);
					int C = getRandomNumberInRange(a11, b12);
					String ChNlQ = Integer.toString(C);
					SimpleDateFormat sdf5 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					// Getting current date

					sdf5.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
					Calendar cal5 = Calendar.getInstance();

					sdf5.format(cal5.getTime());
					cal5.add(Calendar.DAY_OF_MONTH, 1);
					// Date after adding the days to the current date
					String ACT = sdf5.format(cal5.getTime());

					for (int i = 0; i < j; i++) {
						int x = 100;
						int y = 1000;
						String RsrvID = getRandomNumericString(c13);

						Node ImplAttr = doc.getElementsByTagName("ImplementedAttributes").item(i);
						if (ImplAttr != null) {
							if ("ImplementedAttributes".equals(((Node) ImplAttr).getNodeName())) {
								NodeList ImplAttrChild = ImplAttr.getChildNodes();
								for (int b = 0; b < ImplAttrChild.getLength(); b++) {
									Node ImplAttrChildAttr = ImplAttrChild.item(b);
									NodeList CatalogAttr = ImplAttrChildAttr.getChildNodes();

									for (int c = 0; c < CatalogAttr.getLength(); c++) {
										Node CatalogAttrChild = CatalogAttr.item(c);

										NodeList CatalogID = CatalogAttrChild.getChildNodes();
										for (int d = 0; d < CatalogID.getLength(); d++) {
											Node CatalogIDChild = CatalogID.item(d);
											String CatalogIDChildVal = CatalogIDChild.getTextContent();

											if (OA[i] == "Billing Number") {
												if ("58471423".equals(CatalogIDChildVal)) {
													Node ParentNode = CatalogIDChild.getParentNode().getParentNode();
													NodeList Child = ParentNode.getChildNodes();
													for (int e = 0; e < Child.getLength(); e++) {
														Node listchild1 = Child.item(e);

														if ("SelectedValue".equals(listchild1.getNodeName())) {
															listchild1.setTextContent(Num + x);
															System.out.println("Under ImplAttribute" + " = " + i + " "
																	+ " Updated Number From " + "value for " + OA[i]
																	+ " = " + listchild1.getTextContent());
														}
													}
												} else {
													if ("58471433".equals(CatalogIDChildVal)) {
														Node ParentNode = CatalogIDChild.getParentNode()
																.getParentNode();
														NodeList Child = ParentNode.getChildNodes();
														for (int e = 0; e < Child.getLength(); e++) {
															Node listchild1 = Child.item(e);

															if ("SelectedValue".equals(listchild1.getNodeName())) {
																listchild1.setTextContent(Num + x);
																System.out.println("Under ImplAttribute" + " = " + i
																		+ " " + " Updated Number To " + "value for "
																		+ OA[i] + " = " + listchild1.getTextContent());
															}
														}
													} else {
														if ("58471593".equals(CatalogIDChildVal)) {
															Node ParentNode = CatalogIDChild.getParentNode()
																	.getParentNode();
															NodeList Child = ParentNode.getChildNodes();
															for (int e = 0; e < Child.getLength(); e++) {
																Node listchild1 = Child.item(e);

																if ("SelectedValue".equals(listchild1.getNodeName())) {
																	listchild1.setTextContent(RsrvID);
																	System.out.println("Under ImplAttribute" + " = " + i
																			+ " " + " Updated Reservation ID "
																			+ "value for " + OA[i] + " = "
																			+ listchild1.getTextContent());
																}
															}
														}
													}
												}
											} else {
												if (OA[i] == "Vodafone DDI") {
													if ("58471423".equals(CatalogIDChildVal)) {
														Node ParentNode = CatalogIDChild.getParentNode()
																.getParentNode();
														NodeList Child = ParentNode.getChildNodes();
														for (int e = 0; e < Child.getLength(); e++) {
															Node listchild1 = Child.item(e);

															if ("SelectedValue".equals(listchild1.getNodeName())) {
																listchild1.setTextContent(Num + VFrom);
																System.out.println("Under ImplAttribute" + " = " + i
																		+ " Updated Number From " + " value for "
																		+ OA[i] + " = " + listchild1.getTextContent());
																VFrom = VFrom + 10;
															}
														}
													} else {
														if ("58471433".equals(CatalogIDChildVal)) {
															Node ParentNode = CatalogIDChild.getParentNode()
																	.getParentNode();
															NodeList Child = ParentNode.getChildNodes();
															for (int e = 0; e < Child.getLength(); e++) {
																Node listchild1 = Child.item(e);

																if ("SelectedValue".equals(listchild1.getNodeName())) {
																	listchild1.setTextContent(Num + VTo);
																	System.out.println("Under ImplAttribute" + " = " + i
																			+ " " + "Updated Number To " + " value for "
																			+ OA[i] + " = "
																			+ listchild1.getTextContent());
																	VTo = VTo + 10;
																}
															}
														} else {
															if ("58471593".equals(CatalogIDChildVal)) {
																Node ParentNode = CatalogIDChild.getParentNode()
																		.getParentNode();
																NodeList Child = ParentNode.getChildNodes();
																for (int e = 0; e < Child.getLength(); e++) {
																	Node listchild1 = Child.item(e);

																	if ("SelectedValue"
																			.equals(listchild1.getNodeName())) {
																		listchild1.setTextContent(RsrvID);
																		System.out.println("Under ImplAttribute" + " = "
																				+ i + " " + "Updated Reservation ID"
																				+ " value for " + OA[i] + " = "
																				+ listchild1.getTextContent());
																	}
																}
															}
														}
													}
												} else {
													if (OA[i] == "Porting DDI") {
														if ("58471423".equals(CatalogIDChildVal)) {
															Node ParentNode = CatalogIDChild.getParentNode()
																	.getParentNode();
															NodeList Child = ParentNode.getChildNodes();
															for (int e = 0; e < Child.getLength(); e++) {
																Node listchild1 = Child.item(e);

																if ("SelectedValue".equals(listchild1.getNodeName())) {
																	listchild1.setTextContent(PIBN + PFrom);
																	System.out.println("Under ImplAttribute" + " = " + i
																			+ " " + "Updated Number From"
																			+ " value for " + OA[i] + " = "
																			+ listchild1.getTextContent());
																	PFrom = PFrom + 10;
																}
															}
														} else {
															if ("58471433".equals(CatalogIDChildVal)) {
																Node ParentNode = CatalogIDChild.getParentNode()
																		.getParentNode();
																NodeList Child = ParentNode.getChildNodes();
																for (int e = 0; e < Child.getLength(); e++) {
																	Node listchild1 = Child.item(e);

																	if ("SelectedValue"
																			.equals(listchild1.getNodeName())) {
																		listchild1.setTextContent(PIBN + PTo);
																		System.out.println("Under ImplAttribute" + " = "
																				+ i + " " + "Updated Number To"
																				+ " value for " + OA[i] + " = "
																				+ listchild1.getTextContent());
																		PTo = PTo + 10;
																	}
																}
															} else {
																if ("58471593".equals(CatalogIDChildVal)) {
																	Node ParentNode = CatalogIDChild.getParentNode()
																			.getParentNode();
																	NodeList Child = ParentNode.getChildNodes();
																	for (int e = 0; e < Child.getLength(); e++) {
																		Node listchild1 = Child.item(e);

																		if ("SelectedValue"
																				.equals(listchild1.getNodeName())) {
																			listchild1.setTextContent(RsrvID);
																			System.out.println("Under ImplAttribute"
																					+ " = " + i + " "
																					+ "Updated Reservation ID"
																					+ " value for " + OA[i] + " = "
																					+ listchild1.getTextContent());
																		}
																	}
																} else {
																	if ("58471483".equals(CatalogIDChildVal)) {
																		Node ParentNode = CatalogIDChild.getParentNode()
																				.getParentNode();
																		NodeList Child = ParentNode.getChildNodes();
																		for (int e = 0; e < Child.getLength(); e++) {
																			Node listchild1 = Child.item(e);

																			if ("SelectedValue"
																					.equals(listchild1.getNodeName())) {
																				listchild1.setTextContent(ACT + "+DT");
																				System.out.println("Under ImplAttribute"
																						+ " = " + i + " "
																						+ "Updated Activation Date"
																						+ " value for " + OA[i] + " = "
																						+ listchild1.getTextContent());
																			}
																		}
																	} else {
																		if ("58502177".equals(CatalogIDChildVal)) {
																			Node ParentNode = CatalogIDChild
																					.getParentNode().getParentNode();
																			NodeList Child = ParentNode.getChildNodes();
																			for (int e = 0; e < Child
																					.getLength(); e++) {
																				Node listchild1 = Child.item(e);

																				if ("SelectedValue".equals(
																						listchild1.getNodeName())) {
																					listchild1.setTextContent(PIBN + y);
																					System.out.println(
																							"Under ImplAttribute"
																									+ " = " + i + " "
																									+ "Updated Porting Billing Number"
																									+ " value for "
																									+ OA[i] + " = "
																									+ listchild1
																											.getTextContent());
																				}
																			}
																		} else {
																			if ("58502197".equals(CatalogIDChildVal)) {
																				Node ParentNode = CatalogIDChild
																						.getParentNode()
																						.getParentNode();
																				NodeList Child = ParentNode
																						.getChildNodes();
																				for (int e = 0; e < Child
																						.getLength(); e++) {
																					Node listchild1 = Child.item(e);

																					if ("SelectedValue".equals(
																							listchild1.getNodeName())) {
																						listchild1.setTextContent(PIBN);
																						System.out.println(
																								"Under ImplAttribute"
																										+ " = " + i
																										+ " "
																										+ "Updated Porting Prefix"
																										+ " value for "
																										+ OA[i] + " = "
																										+ listchild1
																												.getTextContent());
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													} else {
														if (OA[i] == "SIP Trunk") {
															if ("58471243".equals(CatalogIDChildVal)) {
																Node ParentNode = CatalogIDChild.getParentNode()
																		.getParentNode();
																NodeList Child = ParentNode.getChildNodes();
																for (int e = 0; e < Child.getLength(); e++) {
																	Node listchild1 = Child.item(e);

																	if ("SelectedValue"
																			.equals(listchild1.getNodeName())) {
																		listchild1.setTextContent(Num + x);
																		System.out.println(
																				"Under ImplAttribute" + " = " + i + " "
																						+ "Updated Trunk Billing Number"
																						+ " value for " + OA[i] + " = "
																						+ listchild1.getTextContent());
																	}
																}
															} else {
																if ("58471253".equals(CatalogIDChildVal)) {
																	Node ParentNode = CatalogIDChild.getParentNode()
																			.getParentNode();
																	NodeList Child = ParentNode.getChildNodes();
																	for (int e = 0; e < Child.getLength(); e++) {
																		Node listchild1 = Child.item(e);

																		if ("SelectedValue"
																				.equals(listchild1.getNodeName())) {
																			listchild1.setTextContent(ChNlQ);
																			System.out.println("Under ImplAttribute"
																					+ " = " + i + " "
																					+ "Updated Trunk Channel Quantity"
																					+ " value for " + OA[i] + " = "
																					+ listchild1.getTextContent());
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				// write the content into XML file

				TransformerFactory transformerFactory = TransformerFactory

						.newInstance();

				transformer = transformerFactory.newTransformer();

				DOMSource source = new DOMSource(doc);

				SimpleDateFormat sdf = new SimpleDateFormat(

						"dd_MM_yyyy HH_mm_ss_SSS_");

				sdf.setTimeZone(java.util.TimeZone.getTimeZone("IST"));

				Calendar cal = Calendar.getInstance();

				String D = sdf.format(cal.getTime());

				String Out = "C:\\Users\\Public\\Automation\\ProvideOrder\\Out\\" + "DT_" + D + "_" + "BN_" + 100
						+ ".xml";

				System.out

						.println("*******************************************************************************");

				System.out.println("Path of updated xml file  " + Out);

				StreamResult result = new StreamResult(new File(Out));

				transformer.transform(source, result);

			}
		}

		catch (

		ParserConfigurationException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	@DataProvider(name = "RandomAlphanumeric")

	public static Integer[][] RandomAlphanumeric() {

		return new Integer[][] { { 9, 5,4,7 } };

	}

	// parallel = true

	@DataProvider(name = "Randomnumeric")

	public static Integer[][] Randomnumeric() {

		return new Integer[][] { { 7, 6,5,5 } };

	}

	@DataProvider(name = "RandomChannelQnty")

	public static Integer[][] RandomChannelQnty() {

		return new Integer[][] { { 8, 250 ,8,250 } };

	}
	
	@DataProvider(name = "numbers")
	public Integer[][] numbers() {
	  List<Integer[]> result = Lists.newArrayList();
	  result.addAll(Arrays.asList(RandomAlphanumeric()));
	  result.addAll(Arrays.asList(Randomnumeric()));
	  result.addAll(Arrays.asList(RandomChannelQnty()));
	  System.out.println(result.toArray(new Integer[result.size()][]));
	  return result.toArray(new Integer[result.size()][]);
	  
	 
	}
	
	
	/*@Test(dataProvider = "numbers")
	public void f(int a11, int b12,int c13, int d14) {
	  System.out.println( + a11 +" " +b12 + " " +c13 +" " +d14);
	}*/
	
	

	// @Test(priority =1,dataProvider = "RandomAlphanumeric")

	// public void parameterTest(String myName)

	public static String getAlphaNumericString(int alpn)

	{

		// chose a Character random from this String

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

		// create StringBuffer size of AlphaNumericString

		StringBuilder sb = new StringBuilder(alpn);

		for (int i = 0; i < alpn; i++) {

			// generate a random number between

			// 0 to AlphaNumericString variable length

			int index

					= (int) (AlphaNumericString.length()

							* Math.random());

			// add Character one by one in end of sb

			sb.append(AlphaNumericString.charAt(index));

		}

		alp = sb.toString();

		// System.out.println(alp);

		return alp;

		// return x;

	}

	// @Test(priority=2)

	// getRandomNumericString(6) nme1 = yuu, name2 = sdgjgsdfg

	// @Test(dataProvider = "Randomnumeric")

	public static String getRandomNumericString(int nmrcn) {

		// int n = 10;

		// chose a Character random from this String

		String AlphaNumericString = "123456789";

		// create StringBuffer size of AlphaNumericString

		StringBuilder sb = new StringBuilder(nmrcn);

		for (int i = 0; i < nmrcn; i++) {

			// generate a random number between

			// 0 to AlphaNumericString variable length

			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb

			sb.append(AlphaNumericString.charAt(index));

		}

		nmrc = sb.toString();

		// System.out.println(nmrc);

		return nmrc;

	}

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {

			throw new IllegalArgumentException("max must be greater than min");

		}

		int chlqt = (int) (Math.random() * ((max - min) + 1)) + min;

		return chlqt;

	}

	/*
	 * @Parameters({"chlMi","chlMa"})
	 * 
	 * @Test(priority=5)
	 * 
	 * 
	 * 
	 * public static void getRandomNumberInRange(@Optional int chlMi, @Optional
	 * int chlMa) {
	 * 
	 * 
	 * 
	 * //int min= 8;
	 * 
	 * //int max= 250;
	 * 
	 * chlMi =8;
	 * 
	 * chlMa=250;
	 * 
	 * if (chlMi >= chlMa) {
	 * 
	 * throw new IllegalArgumentException("max must be greater than min");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * int chlqt = (int)(Math.random() * ((chlMa - chlMi) + 1)) + chlMi;
	 * 
	 * System.out.println(chlqt);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

}