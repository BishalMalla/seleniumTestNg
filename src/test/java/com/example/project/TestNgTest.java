package com.example.project;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.openqa.selenium.By;

@Listeners(ListenerTest.class)
public class TestNgTest extends Base {
	
	/*@Test(priority=1)
	public void doLogin() {
		System.out.println("DO login test");
	}
	
	@Test(priority=2)
	public void doPasswordChange() {
		System.out.println("changing password");
	}
	
	@Test(priority=3)
	public void doLogout() {
		System.out.println("Logging out");
	}
	
	@Test(priority=4)
	public void valueMatch() {
		String expVal = "A";
		String actVal = "A";
		Assert.assertEquals(actVal, expVal);
	}*/
	
	
	@BeforeMethod
	public void beforeTest() {
		 initialization();
	}
	@Test(priority=6)
	void negativeTestCase() {
		driver.manage().window().maximize();
		driver.get("https://www.twitter.com");
		captureScreenShot("captureScreenShot1");
		Assert.assertTrue(1>2,"The item is lesser than");
	}
	/*@Test(priority=5)
	public void captureScreenShot() throws Exception { 
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com");
		captureScreenShot("captureScreenShot1");
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("bishal.malla@gmail.com");
		captureScreenShot("captureScreenShot2");
	}*/
	@AfterMethod
	public void afterTest() {
		driver.quit();
	}
	
}
