package seleniumfeatures;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow Row;

	public static String path = null;

	// Constructor
	public ExcelReader() {

		path = System.getProperty(("user.dir") + "testdata\\TestData.xlsx");
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = workbook.getSheetAt(0);

	}

	public int getSheetRows(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		return (sheet.getLastRowNum() + 1);

	}

	public int getSheetColumns(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);

		sheet = workbook.getSheetAt(index);
		Row = sheet.getRow(0);

		return (Row.getLastCellNum());

	}

	public static void main(String args[]) throws IOException {

		ExcelReader reader = new ExcelReader();
		System.out.println(reader.getSheetColumns("LoginTest"));

	}

}
