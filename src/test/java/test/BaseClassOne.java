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

public class BaseClassOne {
	
		AppiumDriver<MobileElement> driver ;
		URL url;

		@Parameters({"avd","appPackage","appActivity","appiumServer"})
		@BeforeTest
		public void setUP( String avd, String appPackage, String appActivity, String appiumServer ) throws MalformedURLException {

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "9");
			cap.setCapability("automationName", "UiAutomator2");
						
			cap.setCapability("avd", avd);
			cap.setCapability("appPackage", appPackage);
			cap.setCapability("appActivity", appActivity);

			url = new URL(appiumServer);
			driver = new AppiumDriver<MobileElement>(url,cap);
			
			//Nexus_4_API_30

			System.out.println("Application started .....");

		}

		@Test
		public void testCalculatorAppInParallel() {
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
			System.out.println("Application ended .....");
		}

		@AfterTest
		public void tearDown() {
			driver.quit();
		}
	}


