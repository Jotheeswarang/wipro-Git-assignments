package stepDefinitions;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EcommercepurchaseSteps {
	static WebDriver driver;

    @Before
    public static void setup() throws InterruptedException {
        System.out.println("Setting up WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }
	@Given("User is logged into the e-commerce site")
	public void user_is_logged_into_the_e_commerce_site() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://www.saucedemo.com/");
		Thread.sleep(5000);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("inventory_list"))
        		.isDisplayed(), "Login failed");
        Thread.sleep(5000);
	}
	@When("User searches for {string}")
	public void user_searches_for(String productName) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("User searches for: " + productName);
		Thread.sleep(5000);
	}
	@Then("User should see the product in search results")
	public void user_should_see_the_product_in_search_results() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assert.assertTrue(product.isDisplayed(), "Product not found in search results");
        Thread.sleep(5000);
	}
	@Then("User views product details")
	public void user_views_product_details() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		 driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
	     Assert.assertTrue(driver.findElement(By
	    		 .className("inventory_details_name"))
	    		 .isDisplayed(), "Product details not displayed");
	     Thread.sleep(5000);
	}
	@When("User adds {string} to cart")
    public void user_adds_product_to_cart(String productName) throws InterruptedException {
        driver.findElement(By.id("add-to-cart")).click();
        Thread.sleep(5000);
    }
	@Then("User should see the product in the cart")
    public void user_sees_product_in_cart() throws InterruptedException {
        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertTrue(driver.findElement(By.className("cart_item"))
        								.isDisplayed(), "Cart is empty");
        Thread.sleep(5000);
    }
	@When("User proceeds to checkout")
    public void user_proceeds_to_checkout() throws InterruptedException {
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(5000);
    }
	 @When("User enters checkout details")
	    public void user_enters_checkout_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
	        List<List<String>> details = dataTable.asLists(String.class);
	        driver.findElement(By.id("first-name")).sendKeys(details.get(1).get(0));
	        driver.findElement(By.id("last-name")).sendKeys(details.get(1).get(1));
	        driver.findElement(By.id("postal-code")).sendKeys(details.get(1).get(2));
	        driver.findElement(By.id("continue")).click();
	        Thread.sleep(5000);
	    }
	 @When("User completes the purchase")
	    public void user_completes_the_purchase() throws InterruptedException {
	        driver.findElement(By.id("finish")).click();
	        Thread.sleep(5000);
	    }
	 @Then("User should see a purchase confirmation message")
	    public void user_sees_purchase_confirmation() throws InterruptedException {
	        WebElement confirmation = driver.findElement(By.className("complete-header"));
	        Assert.assertTrue(confirmation.isDisplayed(), "Purchase confirmation not displayed");
	        System.out.println("Purchase completed successfully!");
	        Thread.sleep(5000);
	    }
	 @After
	    public static void teardown() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("Browser closed.");
	        }
	    }
}

