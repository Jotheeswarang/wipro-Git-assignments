package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.Alert;
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

public class WishlistSteps {
	WebDriver driver;
	WebDriverWait wait;
	@Before
	public void setup() {
		System.out.println("Setting up WebDriver...");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}

	@Given("User is logged into the application")
	public void user_is_logged_into_the_application() {
		// Write code here that turns the phrase above into concrete actions
		driver.get("https://www.demoblaze.com");
		System.out.println("User is on the login page");
		// Click on Login button
		driver.findElement(By.id("login2")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("JONE");
		driver.findElement(By.id("loginpassword")).sendKeys("JONE@123");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		// Verify login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		System.out.println("User successfully logged in");
	}
	@When("User navigates to a product page")
	public void user_navigates_to_a_product_page() {
		// Write code here that turns the phrase above into concrete actions
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Samsung galaxy s6"))).click();
		System.out.println("User navigated to the product page");
	}
	@When("User adds the product to the wishlist")
	public void user_adds_the_product_to_the_wishlist() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		//there is no wishlist so we are adding to the cart
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to wishlist']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
		System.out.println("User added the product to the wishlist");
		Thread.sleep(3000);
		Alert a=driver.switchTo().alert();
		System.out.println("Alert box message from simple alert: "+a.getText());
		a.accept();
	}
	@Then("User should see the product in the wishlist")
	public void user_should_see_the_product_in_the_wishlist() {
		// Write code here that turns the phrase above into concrete actions
		//checking in cart since there is no wishlist
		driver.findElement(By.linkText("Cart")).click();
		WebElement wishlistItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Samsung galaxy s6']")));
		Assert.assertTrue(wishlistItem.isDisplayed());
		System.out.println("Product is visible in the wishlist");
	}

	@After public void teardown() { 
		if (driver != null) { driver.quit();
		System.out.println("Browser closed."); } }

}

