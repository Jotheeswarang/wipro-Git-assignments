package stepDefinitions;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CartSteps {
    WebDriver driver;

    @Before
    public void setup() throws InterruptedException {
        System.out.println("Setting up WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @Given("User is on the login page")
    public void user_is_on_the_login_page() throws InterruptedException {
        driver.get("https://www.saucedemo.com");
        System.out.println("User is on the login page");
        Thread.sleep(5000);
    }

    @When("User logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        System.out.println("User logs in with valid credentials");
        Thread.sleep(5000);
    }

    @Given("User is on the products page")
    public void user_is_on_the_products_page() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
        System.out.println("User is on the products page");
        Thread.sleep(5000);
    }

    @When("User adds a product to the cart")
    public void user_adds_a_product_to_the_cart() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
        Thread.sleep(5000);
    }

    @Then("The product should be added successfully")
    public void the_product_should_be_added_successfully() throws InterruptedException {
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount, "1");
        Thread.sleep(5000);
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("Executing BeforeStep Hook...");
    }
    @AfterStep
    public void afterStep() {
        System.out.println("Executing AfterStep Hook...");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
