package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasswordResetSteps {
	WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        System.out.println("Setting up WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }
	

	@Given("User is on the password reset page")
	public void user_is_on_the_password_reset_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://the-internet.herokuapp.com/forgot_password");
        System.out.println("User is on the password reset page");
        Assert.assertTrue(driver.findElement(By.tagName("h2")).getText().contains("Forgot Password"));
	
	}
	@When("User enters the email and submits the form")
	public void user_enters_the_email_and_submits_the_form() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("testuser@example.com");

        WebElement retrieveButton = driver.findElement(By.id("form_submit"));
        retrieveButton.click();
        System.out.println("User entered email and submitted the form");

	}
	@Then("User should see a confirmation message")
	public void user_should_see_a_confirmation_message() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/h1")));
        Assert.assertTrue(message.isDisplayed());
        System.out.println("Password reset confirmation message displayed.");
	
	}
	@After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }

		

}
