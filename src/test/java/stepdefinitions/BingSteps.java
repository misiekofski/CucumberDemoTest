package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BingHomePage;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BingSteps {
    WebDriver driver;

    @Given("User is on bing.com webpage")
    public void userIsOnBingComWebpage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.bing.com");
    }

    @When("User searches for {string}")
    public void userSearchesFor(String queryString) {
        BingHomePage bhp = new BingHomePage(driver);
        bhp.searchFor(queryString);
    }

    @Then("First three results contains {string}")
    public void firstThreeResultsContains(String resultString) {
        List<WebElement> headersWithSearchResult = driver.findElements(By.cssSelector("h2"));

        String firstResult = headersWithSearchResult.get(0).getText();
        // assertJ - check the actual result
        assertThat(firstResult).containsIgnoringCase(resultString);

        System.out.println(headersWithSearchResult.get(1).getText());
        System.out.println(headersWithSearchResult.get(2).getText());
    }

    @Then("there are not search results in bing")
    public void thereAreNotSearchResultsInBing() {
        System.out.println("No searches found");
    }
}
