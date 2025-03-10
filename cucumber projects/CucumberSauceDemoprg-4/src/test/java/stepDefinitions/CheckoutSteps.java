package stepDefinitions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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

public class CheckoutSteps {
	WebDriver driver;
	WebDriverWait wait;
	@Before
    public void setup() throws InterruptedException {
        System.out.println("Setting up WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10)); // Initialize WebDriverWait

        Thread.sleep(5000);
	}
	@Given("User is on the products page")
	public void user_is_on_the_products_page() throws InterruptedException {
		driver.get("https://www.saucedemo.com");
        System.out.println("User is on the login page");
        Thread.sleep(5000);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        System.out.println("User logs in with valid credentials");
        Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
        System.out.println("User is on the products page");
	    // Write code here that turns the phrase above into concrete actions
	
	}
	@When("User adds a product to the cart")
	public void user_adds_a_product_to_the_cart() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
        Thread.sleep(5000);
        System.out.println("User adds a product to the cart");
	    // Write code here that turns the phrase above into concrete actions
	 
	}
	@When("User proceeds to checkout")
	public void user_proceeds_to_checkout() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		driver.findElement(By.id("checkout")).click();
		System.out.println("User  proceeds to checkout");
		Thread.sleep(5000);
	    // Write code here that turns the phrase above into concrete actions
	
	}
	@When("User fills in the checkout information")
	public void user_fills_in_the_checkout_information(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		String firstName = data.get(0).get("firstName");
		String lastName = data.get(0).get("lastName");
		String postalCode = data.get(0).get("postalCode");
		WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
		firstNameField.sendKeys(firstName);
		WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
		lastNameField.sendKeys(lastName);
		WebElement postalCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
		postalCodeField.sendKeys(postalCode);
		
		WebElement continuebtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue")));
		continuebtn.click();
		
		WebElement finishbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
		finishbtn.click();
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.

	}
	@Then("User should see the order confirmation message")
	public void user_should_see_the_order_confirmation_message() {
	    // Write code here that turns the phrase above into concrete actions
		String success = wait.until(ExpectedConditions.
				visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))).getText();
		Assert.assertTrue(success.equalsIgnoreCase("Checkout: Complete!"));
	
	}
	@After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }


	}