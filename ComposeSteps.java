package steps;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComposeSteps {

    private WebDriver driver;

    @Given("I am on the Gmail login page")
    public void iAmOnTheGmailLoginPage() {
    	// Setup GeckoDriver using WebDriverManager
        WebDriverManager.firefoxdriver().setup();

        // Initialize FirefoxDriver
        WebDriver driver = new FirefoxDriver();

    	
    	
    	
      //  WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mail.google.com/");
    }

    @When("I log in with valid credentials")
    public void iLogInWithValidCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));
        emailInput.sendKeys(System.getenv("GMAIL_USERNAME"));  // Use environment variables

        WebElement nextButton = driver.findElement(By.id("identifierNext"));
        nextButton.click();

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordInput.sendKeys(System.getenv("GMAIL_PASSWORD"));  // Use environment variables

        WebElement passwordNextButton = driver.findElement(By.id("passwordNext"));
        passwordNextButton.click();
    }

    @When("I click on the compose button")
    public void iClickOnTheComposeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement composeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Compose']")));
        composeButton.click();
    }

    @When("I fill in the email subject and body")
    public void iFillInTheEmailSubjectAndBody() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement toInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));
        toInput.sendKeys("anjudeepak512@gmail.com");  // Replace with a valid recipient email

        WebElement subjectInput = driver.findElement(By.name("subjectbox"));
        subjectInput.sendKeys("Incubyte");

        WebElement bodyInput = driver.findElement(By.xpath("//div[@role='textbox']"));
        bodyInput.sendKeys("Automation QA test for Incubyte");
    }

    @When("I click on the send button")
    public void iClickOnTheSendButton() {
        WebElement sendButton = driver.findElement(By.xpath("//div[text()='Send']"));
        sendButton.click();
    }

    @Then("the email should be sent successfully")
    public void theEmailShouldBeSentSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Message sent')]")));
        assert sentMessage.isDisplayed();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
