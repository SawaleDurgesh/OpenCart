package utitlities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTest.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	 public ExtentSparkReporter sparkReporter;
	    public ExtentReports extent;
	    public ExtentTest test;

	    String repName;

	    // Runs once, before the suite starts
	    public void onStart(ITestContext testContext) {

	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	        repName = "Test-Report-" + timeStamp + ".html";
	        sparkReporter = new ExtentSparkReporter("./reports/" + repName); // location of the report

	        sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
	        sparkReporter.config().setReportName("opencart Functional Testing");   // name of the report
	        sparkReporter.config().setTheme(Theme.DARK);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);

	        extent.setSystemInfo("Application", "opencart");
//	        extent.setSystemInfo("Module", "Admin");
	        extent.setSystemInfo("Tester", "Durgesh Sawale");
//	        extent.setSystemInfo("Sub Module", "Customers");
//	        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
//	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environment", "QA");

	        // Values passed from testng.xml <parameter> tags (may be null if not defined)
	        String os = testContext.getCurrentXmlTest().getParameter("os");
	        extent.setSystemInfo("Operating System (param)", os);

	        String browser = testContext.getCurrentXmlTest().getParameter("browser");
	        extent.setSystemInfo("Browser", browser);

	        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	        if (!includedGroups.isEmpty()) {
	            extent.setSystemInfo("Groups", includedGroups.toString());
	        }
	    }

	    public void onTestSuccess(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.assignCategory(result.getMethod().getGroups()); // show groups in the report
	        test.log(Status.PASS, result.getName() + " got successfully executed");
	    }

	    public void onTestFailure(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.assignCategory(result.getMethod().getGroups());

	        test.log(Status.FAIL, result.getName() + " got failed");
	        test.log(Status.INFO, result.getThrowable().getMessage());

	        try {
	            String imgPath = new BaseClass().captureScreen(result.getName());
	            test.addScreenCaptureFromPath(imgPath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void onTestSkipped(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.assignCategory(result.getMethod().getGroups());

	        test.log(Status.SKIP, result.getName() + " got skipped");
	        if (result.getThrowable() != null) {
	            test.log(Status.INFO, result.getThrowable().getMessage());
	        }
	    }

	    // Runs once, after the suite finishes
	    public void onFinish(ITestContext testContext) {
	        extent.flush(); // writes everything to the HTML file

	        // Optional: automatically open the report in the default browser
	        String pathOfExtentReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;
	        File extentReport = new File(pathOfExtentReport);

	        try {
	            Desktop.getDesktop().browse(extentReport.toURI());
	        } catch (Exception e) {
	            e.printStackTrace(); // e.g. headless/CI machines have no Desktop support
	        }
	        
	    }
	
	

}
