package tqs.lab4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.util.*;

import java.net.MalformedURLException;
import java.net.URL;

public class FlighttestTest {
  private WebDriver driver;
  private Map<String, Object> vars;

  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void flighttest() {
    // Test name: flight_test
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://blazedemo.com/");
    // 2 | setWindowSize | 1288x1039 | 
    driver.manage().window().setSize(new Dimension(1288, 1039));
    // 3 | click | name=fromPort | 
    driver.findElement(By.name("fromPort")).click();
    // 4 | select | name=fromPort | label=Philadelphia
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
    }
    // 5 | click | name=toPort | 
    driver.findElement(By.name("toPort")).click();
    // 6 | select | name=toPort | label=Berlin
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Berlin']")).click();
    }
    // 7 | click | css=.btn-primary | 
    driver.findElement(By.cssSelector(".btn-primary")).click();
    // 8 | click | css=tr:nth-child(2) .btn | 
    driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
    // 9 | click | id=inputName | 
    driver.findElement(By.id("inputName")).click();
    // 10 | type | id=inputName | Test
    driver.findElement(By.id("inputName")).sendKeys("Test");
    // 11 | type | id=address | T
    driver.findElement(By.id("address")).sendKeys("T");
    // 12 | click | css=.control-group:nth-child(3) > .control-label | 
    driver.findElement(By.cssSelector(".control-group:nth-child(3) > .control-label")).click();
    // 13 | click | id=address | 
    driver.findElement(By.id("address")).click();
    // 14 | type | id=address | Test
    driver.findElement(By.id("address")).sendKeys("Test");
    // 15 | click | id=city | 
    driver.findElement(By.id("city")).click();
    // 16 | type | id=city | Test
    driver.findElement(By.id("city")).sendKeys("Test");
    // 17 | type | id=state | Test
    driver.findElement(By.id("state")).sendKeys("Test");
    // 18 | type | id=zipCode | 1234
    driver.findElement(By.id("zipCode")).sendKeys("1234");
    // 19 | type | id=creditCardNumber | 111222333
    driver.findElement(By.id("creditCardNumber")).sendKeys("111222333");
    // 20 | click | css=.control-group:nth-child(9) > .control-label | 
    driver.findElement(By.cssSelector(".control-group:nth-child(9) > .control-label")).click();
    // 21 | click | id=nameOnCard | 
    driver.findElement(By.id("nameOnCard")).click();
    // 22 | type | id=nameOnCard | John Test
    driver.findElement(By.id("nameOnCard")).sendKeys("John Test");
    // 23 | click | css=.checkbox | 
    driver.findElement(By.cssSelector(".checkbox")).click();
    // 24 | click | css=.btn-primary | 
    driver.findElement(By.cssSelector(".btn-primary")).click();
    // 25 | click | css=pre | 
    driver.findElement(By.cssSelector("pre")).click();
  }
}
