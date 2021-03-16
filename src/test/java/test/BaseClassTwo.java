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

public class BaseClassTwo {

	AppiumDriver<MobileElement> driver ;
	URL url;

	@Parameters({"deviceName", "udid","appiumServer" ,"wdaLocalPort"})
	@BeforeTest
	public void setUP( String deviceName,String udid , String appiumServer , String wdaLocalPort) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "iOS");
		cap.setCapability("platformVersion", "14.2");
		cap.setCapability("automationName", "XCUITest");

		cap.setCapability("deviceName", deviceName);
		cap.setCapability("udid", udid);
		//cap.setCapability("app","/Users/prag/Library/Developer/Xcode/DerivedData/WebDriverAgent-ciegwgvxzxdrqthilmrmczmqvrgu/Build/Products/Debug-iphonesimulator/IntegrationApp.app");
		cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.apple.news");
		cap.setCapability("wdaLocalPort", wdaLocalPort);//wdaLocalPort: 8002,


		url = new URL(appiumServer);
		driver = new AppiumDriver<MobileElement>(url,cap);

		//Nexus_4_API_30

		System.out.println("Application started .....");

	}

	@Test
	public void testCalculatorAppInParallel() {

		System.out.println("Alright, test started ....");

		MobileElement news=  driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"News+\"]"));
		news.click();
		MobileElement today= driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Today\"]"));
		today.click();
		System.out.println("it's clicked yay !!  ....");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}


