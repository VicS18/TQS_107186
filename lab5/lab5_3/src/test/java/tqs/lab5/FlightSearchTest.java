package tqs.lab5;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightSearchTest {

    private WebDriver driver;

    @Given("I'm on the flight search page {string}")
    public void flightPage(String url){
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("I select {string} as the departure city and {string} as the destination")
    public void selectCities(String departure, String destination) {
        String formatDepart = String.format("/html/body/div[3]/form/select[@name=\"fromPort\"]/option[@value=\"%s\"]", departure);
        String formatDest = String.format("/html/body/div[3]/form/select[@name=\"toPort\"]/option[@value=\"%s\"]", destination);

        driver.findElement(By.xpath(formatDepart)).click();
        driver.findElement(By.xpath(formatDest)).click();
    }

    @And("I request to find flights")
    public void findFlights(){
        driver.findElement(By.xpath("/html/body/div[3]/form/div/input[@value=\"Find Flights\"]")).click();
    }

    @Then("I get the following flights by ID:")
    public void flightsFound(List<Integer> ids) {
        Integer lastId = null;
        try {
            for(int id : ids){
                lastId = id;
                driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[position() = 2 and text() = '" + id + "' ]"));
            }
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "ID \"" + lastId + "\" not available in results");
        } finally {
            driver.quit();
        }
    }

}
