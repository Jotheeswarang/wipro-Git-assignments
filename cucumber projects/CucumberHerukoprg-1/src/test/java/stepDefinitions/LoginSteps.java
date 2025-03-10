package stepDefinitions;

import java.time.Duration;

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

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Before("@LoginTest")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        String successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash"))).getText();
        Assert.assertTrue(successMessage.contains("You logged into a secure area!"));
    }

    @After("@LoginTest")
    public void tearDown() {
        driver.quit();
    }
}