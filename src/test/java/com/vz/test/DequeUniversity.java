package com.vz.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import com.aventstack.extentreports.ExtentTest;
import com.deque.html.axecore.axeargs.AxeRunOnlyOptions;
import com.deque.html.axecore.axeargs.AxeRunOptions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.vz.base.TestBase;
import com.vz.extentreportlistener.ExtentReportNG;
import com.vz.pages.Homepage;
import com.vz.util.TestUtil;


public class DequeUniversity extends TestBase {

	Homepage Home;
	TestUtil Test;
	static ExtentTest test;
	static ExtentReportNG report;
    Logger log = Logger.getLogger(DequeUniversity.class);
    
	
	
	// Super Keyword will call Super Class Constructor Method
	public DequeUniversity() {

		super();
	}

	@BeforeMethod
	public void setup() {

		Initialization();
		Home = new Homepage();
		Test = new TestUtil();
		report = new ExtentReportNG();

	}
	

	@Test(priority = 1)
	public void VerifyHomePageHeader() {
		
		log.info("****************************** Starting test cases execution  *****************************************");
		log.info("****************************** Verify HomePageHeader *****************************************");

		log.info("Checking HomePage Header is Available or Not");
		//test.log(Status.INFO,"Checking HomePage Header is Available or Not");
		Home.verifyHeader();
		
		log.warn("Hey this just a warning message");
		log.fatal("Hey this is just fatal error message");
		log.debug("This is debug message");
		
		log.info("****************************** Ending test case *****************************************");
		log.info("****************************** Verify HomePageHeader *****************************************");

	}
	
	@Test(priority = 2)
	public void VerifyRadioButtons() {
		
		log.info("****************************** Starting test cases execution  *****************************************");
		log.info("****************************** Verify Radio Buttons *****************************************");

		log.info("Checking Number of Radio Buttons under “Let the Adventure Begin” is 5 or Not");
		//test.log(Status.INFO,"Checking Number of Radio Buttons under “Let the Adventure Begin” is 5 or Not");
		int numOfRadioButtons = Home.verifyAdventureRadioButtons();
		assertEquals(numOfRadioButtons, 5);
		
		
		log.warn("Hey this just a warning message");
		log.fatal("Hey this is just fatal error message");
		log.debug("This is debug message");
		
		log.info("****************************** Ending test case *****************************************");
		log.info("****************************** Verify Radio Buttons *****************************************");

	}
	
	@Test(priority = 3)
	public void VerifyAddaTraveler() {
		
		log.info("****************************** Starting test cases execution  *****************************************");
		log.info("****************************** Verify Add a Traveler *****************************************");

		log.info("Checking the Appearance of Dropdown on clicking Add a traveler button or Not");
		//test.log(Status.INFO,"Checking the Appearance of Dropdown on clicking Add a traveler button or Not");
		Home.clickAddaTraveler();
		boolean ddflag = Home.verifyAddedPassengerDropdown();
		//test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
		assertEquals(ddflag, true);
		
		
		log.warn("Hey this just a warning message");
		log.fatal("Hey this is just fatal error message");
		log.debug("This is debug message");
		
		log.info("****************************** Ending test case *****************************************");
		log.info("****************************** Verify Add a Traveler *****************************************");

	}
	
	@Test(priority = 4)
	public void VerifyVideoText() {
		
		log.info("****************************** Starting test cases execution  *****************************************");
		log.info("****************************** Verify Video Text *****************************************");

		log.info("Checking the change of Video text after clicking right arrow");
		
		String videoTxtBeforeChange = Home.getVideoText();
		//test.log(Status.INFO,"Video text before clicking right arrow"+videoTxtBeforeChange);
		Home.clickRightArrow();
		//test.log(Status.INFO,"Click on right arrow");
		String videoTxtAfterChange = Home.getVideoText();
		//test.log(Status.INFO,"Video text after clicking right arrow"+videoTxtAfterChange);
		assertNotEquals(videoTxtBeforeChange, videoTxtAfterChange);
		
		
		
		log.warn("Hey this just a warning message");
		log.fatal("Hey this is just fatal error message");
		log.debug("This is debug message");
		
		log.info("****************************** Ending test case *****************************************");
		log.info("****************************** Verify Video Text *****************************************");

	}
	
	@Test(priority = 5)
	public void AccessibilityTest()  throws IOException {
		
		log.info("****************************** Starting test cases execution  *****************************************");
		log.info("****************************** Verify Video Text *****************************************");

		log.info("Checking the change of Video text after clicking right arrow");
		
		  AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
		  runOnlyOptions.setType("tag");
		  runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));
		  
		  AxeRunOptions options = new AxeRunOptions();
		  options.setRunOnly(runOnlyOptions);
		    
		    AxeBuilder axe = new AxeBuilder().withOptions(options);
	        Results results = axe.analyze(driver);
	        List<Rule> violations = results.getViolations();
	        for (Rule i : violations) {
	            String description = i.getDescription();
	            System.out.println(description);
	        }
	        Assert.assertEquals(0, violations.size());
		log.info("****************************** Ending test case *****************************************");
		log.info("****************************** Verify Video Text *****************************************");

	}

	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
		log.info("****************************** Browser is closed *****************************************");

	}

}
