package com.vz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.vz.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\NEW-PC\\OneDrive\\Documents\\SeleniumPageObjectModel\\VzAutomationTest\\src\\main\\java\\com\\vz\\testdata\\VzAutomationTest.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; //
		System.out.println(sheet.getLastRowNum() + "--------" + //
				sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString(); //
				System.out.println(data[i][k]);
			}
		}
		return data;
	}

	//Add this Method Inside WebEventListener Method for any fails
	public static String takeScreenshotAtEndOfTest() throws IOException {
		//File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String currentDir = System.getProperty("user.dir");
		//FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    TakesScreenshot ts = (TakesScreenshot)driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String destination = System.getProperty("user.dir") + "/screenshots/" +  dateName
	            + ".png";
	    File finalDestination = new File(destination);
	    FileHandler.copy(source, finalDestination);
	    return destination;
	}

	public void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This will scroll down the page by 250 pixel vertical
		js.executeScript("window.scrollBy(0,250)");
	}

	public void ScrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This will scroll down the page by 250 pixel vertical
		js.executeScript("window.scrollBy(0,-250)");
	}

}
