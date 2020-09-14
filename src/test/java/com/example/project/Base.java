package com.example.project;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {
	public static WebDriver driver;
	
	public static void initialization() {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\usa-srgopalakrishnan\\Downloads\\geckodriver.exe");
		File pathBinary = new File("C:\\Program Files\\Mozilla Firefox.exe");
		FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
		DesiredCapabilities desired = DesiredCapabilities.firefox();
		FirefoxOptions options = new FirefoxOptions();
		desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
	    driver = new FirefoxDriver(options); 
	}
	
	public static void  captureScreenShot(String screenshotName) {
		try {
			TakesScreenshot ts =(TakesScreenshot)driver;			
			File file = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(file,new File("C:\\Users\\bimalla\\git\\repository\\junit-jenkin-test-master\\Screenshots\\" + screenshotName + ".png"));
				System.out.println("Screenshot Taken");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Exeception while taking screen shot" + e.getMessage());
			
		}
	} 
}
