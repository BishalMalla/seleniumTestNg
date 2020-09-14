package com.example.project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ListenerTest extends Base implements ITestListener {
	private String jenkinEndppoint = "http://10.25.33.47:8084/ords/prod/twb/service/jenkin";
	
	
	@Override
	public void onTestFailure(ITestResult tr) {
		try {
			// fetch the object instance
			
			Object testObj = tr.getInstance();
			System.out.println(testObj.getClass().desiredAssertionStatus());
			// try to fetch the test plan key & test case key
			
			Field testPlanKeyField = null;
			Field testCaseKeyField = null;
			Object testPlanKeyValue = null;
			Object testCaseKeyValue = null;
			try {
				testPlanKeyField = testObj.getClass().getDeclaredField("testPlanKey");
				testPlanKeyValue = testPlanKeyField.get(testObj);
			} catch (NoSuchFieldException e) {
				testPlanKeyField = null;
			}
			
			try {
				testCaseKeyField = testObj.getClass().getDeclaredField("testCaseKey");
				testCaseKeyValue = testCaseKeyField.get(testObj);
			} catch (NoSuchFieldException e) {
				testCaseKeyField = null;
			}
			
			// if test plan key / test case key is null then try to find the bamboo build number
			if(testPlanKeyValue == null || testCaseKeyValue == null) {
				InputStream stream = this.getClass().getResourceAsStream("/proj.properties"); 
				Properties prop = new Properties();
				prop.load(stream);
				
				String jenkinJobName = prop.getProperty("jenkin.job.name");
				String jenkinBuildId = prop.getProperty("jenkin.build.id");
				String testMethod = tr.getName();
				
				System.out.println(this.getClass().getResource("/proj.properties").getPath());
				System.out.println(jenkinJobName);
				System.out.println(jenkinBuildId);
				System.out.println("Failed");
				System.out.println(testMethod);
				
				List<FileInformation> images = new ArrayList<FileInformation>();
				File file = new File("C:\\Users\\usa-srgopalakrishnan\\Screenshots");
		        File[] files = file.listFiles();
		        for(File f: files){
		        	FileInformation image = new FileInformation();
		        	byte[] fileContent = FileUtils.readFileToByteArray(new File("C:\\Users\\usa-srgopalakrishnan\\Screenshots\\"+f.getName()));
					String encodedString = Base64.getEncoder().encodeToString(fileContent);
					System.out.println(encodedString);
					image.setFilename(f.getName());
					image.setBase64Img(encodedString);
					System.out.println(image.toString());
					images.add(image);
		        }
		        System.out.println(images);
				this.logTestRunJenkinResult(jenkinJobName, jenkinBuildId, "FAIL", testMethod, images);
		       // for(File f: files){
				//	f.delete();
		       // }
		        
			} else {
				//this.logTestRunResult(testPlanKeyValue.toString(), testCaseKeyValue.toString(), "FAIL");
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	  public void onTestSkipped(ITestResult tr) {
		System.out.println("Skipped");
	  }
	  
	 @Override
	  public void onTestSuccess(ITestResult tr) {
			// TODO Auto-generated method stub
			try {
				// fetch the object instance
			    
				Object testObj = tr.getInstance();
				System.out.println(testObj.getClass().desiredAssertionStatus());
				// try to fetch the test plan key & test case key
				Field testPlanKeyField = null;
				Field testCaseKeyField = null;
				Object testPlanKeyValue = null;
				Object testCaseKeyValue = null;
				
				try {
					testPlanKeyField = testObj.getClass().getDeclaredField("testPlanKey");
					testPlanKeyValue = testPlanKeyField.get(testObj);
				} catch (NoSuchFieldException e) {
					testPlanKeyField = null;
				}
				
				try {
					testCaseKeyField = testObj.getClass().getDeclaredField("testCaseKey");
					testCaseKeyValue = testCaseKeyField.get(testObj);
				} catch (NoSuchFieldException e) {
					testCaseKeyField = null;
				}
				
				// if test plan key / test case key is null then try to find the bamboo build number
				if(testPlanKeyValue == null || testCaseKeyValue == null) {
					InputStream stream = this.getClass().getResourceAsStream("/proj.properties"); 
					Properties prop = new Properties();
					prop.load(stream);
					
					String jenkinJobName = prop.getProperty("jenkin.job.name");
					String jenkinBuildId = prop.getProperty("jenkin.build.id");
					String testMethod = tr.getName();
					
					System.out.println(this.getClass().getResource("/proj.properties").getPath());
					System.out.println(jenkinJobName);
					System.out.println(jenkinBuildId);
					System.out.println("Passed");
					System.out.println(testMethod);
					List<FileInformation> images = new ArrayList<FileInformation>();
					File file = new File("C:\\Users\\bimalla\\git\\repository\\junit-jenkin-test-master\\Screenshots");
			        File[] files = file.listFiles();
			        for(File f: files){
			        	FileInformation image = new FileInformation();
			        	byte[] fileContent = FileUtils.readFileToByteArray(new File("C:\\Users\\bimalla\\git\\repository\\junit-jenkin-test-master\\Screenshots\\"+f.getName()));
						String encodedString = Base64.getEncoder().encodeToString(fileContent);
						System.out.println(encodedString);
						image.setFilename(f.getName());
						image.setBase64Img(encodedString);
						images.add(image);
			        }
			        System.out.print(images);
					this.logTestRunJenkinResult(jenkinJobName, jenkinBuildId, "PASS", testMethod,images);
			        for(File f: files){
						//FileUtils.de; 
			        	f.delete();
			        }
				} else {
					//this.logTestRunResult(testPlanKeyValue.toString(), testCaseKeyValue.toString(), "PASS");
				}			
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	 
	 private void logTestRunJenkinResult(String jenkinJobName, String jenkinBuildId, String status, String testMethod, List<FileInformation> base64_image) {
			URL url;
			try {
				url = new URL(this.jenkinEndppoint);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("PUT");
				conn.setRequestProperty("Content-Type", "application/json");
				
				TestRunJenkinRequestBody bodyObj = new TestRunJenkinRequestBody(jenkinJobName, jenkinBuildId, status, testMethod, base64_image);
							
				ObjectMapper mapper = new ObjectMapper();
				String body = mapper.writeValueAsString(bodyObj);			
				System.out.println(body);
				OutputStream os = conn.getOutputStream();
				os.write(body.getBytes());
				os.flush();
				
				int resp = conn.getResponseCode();
				
				System.out.println("PUT Request SENT " + resp);
				
				conn.disconnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
