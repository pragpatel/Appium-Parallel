package test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;

public class BaseClassThree {

	AppiumDriver<MobileElement> driver ;
	URL url;
	String platform ="android";

	@Parameters({"deviceName", "udid","appiumServer", "platformName","avd","appPackage","appActivity","wdaLocalPort"})
	@BeforeTest
	public void setUP( String deviceName,String udid , String appiumServer, String platformName,String avd,String appPackage,String appActivity, String wdaLocalPort ) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();

		if(platformName.equalsIgnoreCase("android")) {
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "9");
			cap.setCapability("automationName", "UiAutomator2");
			cap.setCapability("avd", avd);
			cap.setCapability("appPackage", appPackage);
			cap.setCapability("appActivity", appActivity);
		}else {
			System.out.println("--- Inside else of SetUp () ");
			cap.setCapability("platformName", "iOS");
			cap.setCapability("platformVersion", "14.2");
			cap.setCapability("automationName", "XCUITest");
			cap.setCapability("deviceName", deviceName);
			cap.setCapability("udid", udid);
			//			cap.setCapability("app","/Users/prag/Library/Developer/Xcode/DerivedData/WebDriverAgent-ciegwgvxzxdrqthilmrmczmqvrgu/Build/Products/Debug-iphonesimulator/IntegrationApp.app");

			cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.apple.news");
			cap.setCapability("wdaLocalPort", wdaLocalPort);
		}

		url = new URL(appiumServer);
		driver = new AppiumDriver<MobileElement>(url,cap);

		//Nexus_4_API_30

		System.out.println("Application started .....");

	}

	@Test
	public void testCalculatorAppInParallel() {
		
		if(platform.equalsIgnoreCase("android")) {
			MobileElement two = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
			MobileElement three = driver.findElement(By.id("com.android.calculator2:id/digit_3"));

			MobileElement equals = driver.findElement(By.id("com.android.calculator2:id/eq"));
			MobileElement add = driver.findElement(By.id("com.android.calculator2:id/op_add"));
			MobileElement sum = driver.findElement(By.id("com.android.calculator2:id/digit_5"));
			two.click();
			add.click();
			three.click();
			equals.click();
			System.out.println("sum.getText() = "+ sum.getText());
			sum.getText().equals("5");
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {

			System.out.println("Alright, test started ....");

			MobileElement news=  driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"News+\"]"));
			news.click();
			MobileElement today= driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Today\"]"));
			today.click();
			System.out.println("it's clicked yay !!  ....");
		}

		System.out.println("Application ended .....");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}



