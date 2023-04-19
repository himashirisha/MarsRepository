package com.vz.extentreportlistener;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vz.util.TestUtil;

public class ExtentReportNG implements IReporter {

	private static final String OUTPUT_FOLDER = "test-output/";
	private static final String FILE_NAME = "Extent.html";

	private ExtentReports extent;
	private ExtentTest test;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		init();

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
				buildTestNodes(context.getPassedTests(), Status.PASS);

			}
		}

		for (String s : Reporter.getOutput()) {
			extent.addTestRunnerOutput(s);
		}

		extent.flush();
	}

	
	 private void init() { 
		 ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		 spark.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
		 spark.config().setReportName("ExtentReports - Created by TestNG Listener");
		 spark.config().setTheme(Theme.STANDARD);
	  
	  extent = new ExtentReports(); extent.attachReporter(spark);
	  extent.setReportUsesManualConfiguration(true); }
	  
	
	private void buildTestNodes(IResultMap tests, Status status) {

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, result.getMethod().getMethodName());
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));
			}
		}
	}

	public void down(ITestResult result) throws IOException {
		String screenshotPath ="";

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
																					// report

			screenshotPath = TestUtil.takeScreenshotAtEndOfTest();
			test.fail("Test Case failed check screenshot below" + test.addScreenCaptureFromPath(screenshotPath));
			// extentTest.log(Status.FAIL,
			// MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()); //to
			// add screenshot in extent report
			 test.fail("details").addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
			 test.pass("details").addScreenCaptureFromPath(screenshotPath);

		}
		extent.flush();
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}