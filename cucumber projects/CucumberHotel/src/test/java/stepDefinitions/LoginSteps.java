package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	 WebDriver driver; 
	@Given("User is an adactin page")
	public void user_is_an_adactin_page() {
		// Write code here that turns the phrase above into concrete actions
		driver = new ChromeDriver();
		driver.get("http://www.adactin.com/HotelApp/"); 
		driver.manage().window().maximize();
		//throw new io.cucumber.java.PendingException();
	}
	@When("User enter {string} and {string} and click login button")
	public void user_enter_and_and_click_login_button(String string, String string2) throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.id("username")).sendKeys("Jothees");
		driver.findElement(By.id("password")).sendKeys("Jone");
		driver.findElement(By.id("login")).click();
		// Write code here that turns the phrase above into concrete actions
		//throw new io.cucumber.java.PendingException();
	}
	@Then("Message displayed Login Successfully")
	public void message_displayed_login_successfully() {
		driver.quit();
		// Write code here that turns the phrase above into concrete actions
		//throw new io.cucumber.java.PendingException();
	}

}


