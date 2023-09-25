package mavenDemo.ExOfMaven;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class EaappLoginTest {
	
  WebDriver driver;
	
  @Test
  public void VerifyIfUserCanGetAlertMessageOrNotAfterEnteringOnlyUserName() {
	  String userName = "Admin1234";
	  String userPassword = "";
	  
	  driver.findElement(By.id("loginLink")).click();
	  Reporter.log("navigate to login");
	  
	  driver.findElement(By.id("UserName")).sendKeys(userName);
	  Reporter.log("\r\nHome Login Button clicked and login page open");
	  
	  driver.findElement(By.id("Password")).sendKeys(userPassword);
	  Reporter.log("userName input");
	  
	  driver.findElement(By.xpath("//*[@id=\'loginForm\']/form/div[4]/div/input")).click();
	  Reporter.log("Click on Login button");
	  
	  WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/form/div[2]/div/span/span"));
	  assertNotNull(errorMessage, "Error message for userPassword");
	  Reporter.log("\r\nSucess!! error message for userPassword");
  }
  
  @Test
  public void VerifyIfUserIsAbleToInputUsernameOrNot() {
	  driver.get("https://www.google.com/");
	  Reporter.log("Verify username can input");  
  }
  
  @Test
  public void VerifyIfUserIsAbleToInputPasswordOrNot() {
	  driver.get("https://wikipedia.com/");
	  Reporter.log("Verify password can input");  
  }
  
  @Test
  public void VerifyIfUserIsAbleToClickLoginButtonOrNot() {
	  driver.get("https://yahoo.com/");
	  Reporter.log("Verify username can click login button or not");  
  }
  
  @Parameters({"url", "browser"})
  @BeforeTest
  public void setUp(String url, String browser) throws MalformedURLException{
	  DesiredCapabilities dc = new DesiredCapabilities();
	  
	  if(browser.equals("firefox")) {
		 dc.setBrowserName("");
		 
	  }else if(browser.equals("chrome")) {
		  dc.setBrowserName("chrome");
		  
	  }else {
		  driver = new EdgeDriver();
	  }
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
	  
	  driver.get(url);
	  driver.manage().window().maximize();
	  Reporter.log("Browser Init and navigate to http://eaapp.somee.com/Account/Login");
  }

  @AfterTest
  public void cleanUp() {
	  driver.quit();
  }

  @AfterMethod
  public void failureSetUp(ITestResult result) {
	  Reporter.setCurrentTestResult(result);
	  if(result.getStatus()==2) {
		  Reporter.log("Failed Test Case!!", true);
	  }else {
		  Reporter.log("Success!!", true);
	  }
  }
}
