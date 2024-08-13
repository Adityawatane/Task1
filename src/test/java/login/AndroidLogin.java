package login;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AndroidLogin {

	
	AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException  {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformname", "Android");
        caps.setCapability("deviceName", "samsung-5554");
        caps.setCapability("platformversion", "12");
        caps.setCapability("automationName","uiautomator2");
        caps.setCapability("appPackage", "io.appium.android.apis");
        caps.setCapability("appActivity", ".io.appium.android.apis.levelsupermind");

        URL url = URI.create("http://127.0.0.1:4723/").toURL();
        
        driver = new AndroidDriver(url, caps);
        
    }

    @Test
    public void testLoginWithValidCredentials() {
        driver.findElement(By.id("com.levelsupermind:id/username")).sendKeys("wataneaditya1@gmail.com");
        driver.findElement(By.id("com.levelsupermind:id/password")).sendKeys("789123");

        driver.findElement(By.id("com.levelsupermind:id/loginButton")).click();
        String homeScreenText = driver.findElement(By.id("com.levelsupermind:id/homeScreenText")).getText();
        Assert.assertEquals(homeScreenText, "Hi,Aditya");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
    	 driver.findElement(By.id("com.levelsupermind:id/username")).sendKeys("asd@gmail.com");
    	 driver.findElement(By.id("com.levelsupermind:id/password")).sendKeys("78asdfs");
    	 driver.findElement(By.id("com.levelsupermind:id/loginButton")).click();

        String errorMessage = driver.findElement(By.id("com.levelsupermind:id/errorMessage")).getText();
        Assert.assertEquals(errorMessage, "Invalid credentials");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
