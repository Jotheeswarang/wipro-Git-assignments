package stepDefinitions;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    @Before("@SearchTest")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }
	@Given("User is on the Google homepage")
	public void user_is_on_the_google_homepage() {
		driver.get("https://www.google.co.in");
		// Write code here that turns the phrase above into concrete actions
		
	}
	@When("User searches for {string}")
	public void user_searches_for(String string) {
		driver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).sendKeys("Testing");
		driver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).sendKeys(Keys.ENTER);
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		//driver.findElement(By.name("btnK")).click();
		// Write code here that turns the phrase above into concrete actions
	}
	@Then("Search results are displayed")
	public void search_results_are_displayed() throws InterruptedException {
		//driver.findElement(By.xpath("//*[@id=\"captcha-form\"]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3")));
		boolean presentElement = driver.findElement(By.xpath("//h3")).isDisplayed();
		Assert.assertTrue(presentElement, "Search results were not displayed.");
		//boolean presentElement=driver.findElement(By.linkText("Software testing")).isDisplayed();
		//Assert.assertTrue(presentElement);
		
//		String actualUrl = driver.getCurrentUrl();
//		System.out.println(actualUrl);
//		String expectedUrl = "https://www.google.com/sorry/index?continue=https://www.google.co.in/search%3Fq%3DTesting%26sca_esv%3D63f0b51ce1ca40c5%26source%3Dhp%26ei%3DvMXLZ_rmGa6Q1e8PmcbFyAc%26iflsig%3DACkRmUkAAAAAZ8vTzKE-4Li0BEkIIaLoDnmjJYX5HokE%26ved%3D0ahUKEwj6tNmP0fmLAxUuSPUHHRljEXkQ4dUDCBE%26uact%3D5%26oq%3DTesting%26gs_lp%3DEgdnd3Mtd2l6IgdUZXN0aW5nMggQABiABBixAzIFEAAYgAQyCBAAGIAEGLEDMgUQABiABDIFEAAYgAQyCxAAGIAEGLEDGIMBMgUQABiABDIIEAAYgAQYsQMyBRAAGIAEMgUQABiABEjCAlAAWCpwAHgAkAEAmAGwAaAByQSqAQMxLjO4AQPIAQD4AQGYAgSgAooFwgIREC4YgAQYsQMY0QMYgwEYxwHCAg0QABiABBixAxhGGPkBmAMAkgcDMC40oAfhFw%26sclient%3Dgws-wiz%26sei%3DvsXLZ4P1Oe2bseMP1-GciQE&q=EgQm4OgNGL-Lr74GIjA_MCPH-SEV955g0egEKzM8N_zyubmIuozswRgCuOBon5cIljmKzFT_c0HV3fKAb-AyAXJaAUM";
//		Assert.assertEquals(actualUrl, expectedUrl);
		// Write code here that turns the phrase above into concrete actions
	}
	@After("@SearchTest")
    public void tearDown() {
        //driver.quit();
    }

}
