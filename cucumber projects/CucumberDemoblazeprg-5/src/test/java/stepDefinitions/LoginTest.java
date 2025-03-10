package stepDefinitions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.time.Duration;
import java.util.Map;
public class LoginTest {
    WebDriver driver;
    @Before
    public void setup() throws InterruptedException {
        System.out.println("Setting up WebDriver...");
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        Thread.sleep(1000);
    }
    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() throws InterruptedException {
        driver.findElement(By.id("signin2")).click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.id("signInModal")).isDisplayed());
    }
    @When("I enter username and password")
    public void i_enter_username_and_password(DataTable dataTable) throws InterruptedException {
       
        Map<String, String> credentials = dataTable.asMap(String.class, String.class);
        String username = credentials.get("Username");
        String password = credentials.get("Password");
  
        driver.findElement(By.id("sign-username")).sendKeys(username);
        Thread.sleep(1000);
        
        driver.findElement(By.id("sign-password")).sendKeys(password);
        Thread.sleep(1000);
       
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Thread.sleep(2000);
    }
    
    @Then("I should see a successful registration message")
    public void i_should_see_a_successful_registration_message() throws InterruptedException {
        // Create wait instance for alert
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        
        // Get alert text and verify message
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "Sign up successful.");
        Thread.sleep(1000);
        
        // Accept the alert
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
       
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}