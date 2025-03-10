package stepsDefinition;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductfilterSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("User is on the products page")
    public void user_is_on_the_products_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        System.out.println("User is on the products page.");
    }

    @When("User selects the filter option {string}")
    public void user_selects_the_filter_option(String filterOption) throws InterruptedException {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));
        Select select = new Select(dropdown);
        select.selectByVisibleText(filterOption);

        System.out.println("User selected filter option: " + filterOption);
        Thread.sleep(3000);
    }

    @Then("User should see products sorted by {string}")
    public void user_should_see_products_sorted_by(String filterOption) throws InterruptedException {
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        Thread.sleep(3000);
        List<Double> prices = priceElements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
        Thread.sleep(3000);
        List<Double> sortedPrices = filterOption.contains("low to high") 
                ? prices.stream().sorted().collect(Collectors.toList()) 
                : prices.stream().sorted((a, b) -> Double.compare(b, a)).collect(Collectors.toList());
        Thread.sleep(3000);
        Assert.assertEquals(prices, sortedPrices, "Products are not sorted correctly!");
        System.out.println("Verified products are sorted by: " + filterOption);
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("Before Step: Execution in progress...");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("After Step: Step execution completed.");
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Closes the browser after the test execution
            System.out.println("Browser closed.");
        }
    }

}
