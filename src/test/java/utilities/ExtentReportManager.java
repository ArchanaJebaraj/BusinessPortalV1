package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
public ExtentSparkReporter sparkReporter;
public ExtentReports extent;
public ExtentTest test;

String repName;
public void onStart(ITestContext testContext) {
	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	repName="Test-Report-"+timeStamp+".html";
	sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
	
	sparkReporter.config().setDocumentTitle("Business portal");
	sparkReporter.config().setReportName("Functional Business portal");
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent =new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "Merchent portal");
	extent.setSystemInfo("module", "enroll");
	
	String os=testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os);
	
	String browser=testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());
	}
	
	}

public void onTestSuccess(ITestResult result) {
	 String className = result.getTestClass().getRealClass().getSimpleName();
	    String methodName = result.getMethod().getMethodName();
	   // test = extent.createTest(className + " :: " + methodName);
	    String coloredTestName = "<span style='color:green;font-size:16px';>" +"<b>"+ className +"</b>"+ " : " + methodName + "</span>";

	test = extent.createTest(coloredTestName);
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.PASS, result.getName()+"got sucessfully exicuted");
	}

public void onTestFailure(ITestResult result) {
	 String className = result.getTestClass().getRealClass().getSimpleName();
	    String methodName = result.getMethod().getMethodName();
	    	    String coloredTestName = "<span style='color:red;font-size:16px;'>" +"<b>"+ className+"</b>" + " : " + methodName + "</span>";
	    	   // test = extent.createTest(className + " :: " + methodName);

	test = extent.createTest(coloredTestName);
	   // test.assignCategory(result.getMethod().getGroups());
	test.log(Status.FAIL, result.getName()+"got Failed");
	test.log(Status.INFO,result.getThrowable().getMessage());
	
	try {
		String imgPath=new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
	}
	
	catch(Exception e1) {
		e1.getStackTrace();
		}
}

public void onTestSkipped(ITestResult result) {
	test=extent.createTest(result.getClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.FAIL, result.getName()+"got Skipped");
	test.log(Status.INFO,result.getThrowable().getMessage());
}

public void onFinish(ITestContext testContext) {
	extent.flush();
	String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
	File extentReport=new File(pathOfExtentReport);
	
	try {
		
		Desktop.getDesktop().browse(extentReport.toURI());
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	
	/*try {
		URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		ImageHtmlEmail email=new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smpt.googlemail.com");
		email.setSmtpPort(465);
		
	}
	catch(Exception e2) {
}*/

}
