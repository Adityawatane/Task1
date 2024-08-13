package login;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class IOSLogin {

	IOSDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException  {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformname", "iOS");
        caps.setCapability("deviceName", "iPhone-15 Simulator");
        
        caps.setCapability("bundleId", "com.levelsupermind");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), caps);
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
