package tqs.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import org.openqa.selenium.By;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


@ExtendWith(SeleniumJupiter.class)
public class FlightRefactoredTest {

  @Test
  public void flighttest(ChromeDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1288, 1039));

    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
    }

    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Berlin']")).click();
    }

    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Test");
    driver.findElement(By.id("address")).sendKeys("T");
    driver.findElement(By.cssSelector(".control-group:nth-child(3) > .control-label")).click();
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Test");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Test");
    driver.findElement(By.id("state")).sendKeys("Test");
    driver.findElement(By.id("zipCode")).sendKeys("1234");
    driver.findElement(By.id("creditCardNumber")).sendKeys("111222333");
    driver.findElement(By.cssSelector(".control-group:nth-child(9) > .control-label")).click();
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("John Test");
    driver.findElement(By.cssSelector(".checkbox")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("pre")).click();

    assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText(), is("PendingCapture"));
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText(), is("555 USD"));
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(2)")).getText(), is("xxxxxxxxxxxx1111"));
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(5) > td:nth-child(2)")).getText(), is("11 /2018"));
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(6) > td:nth-child(2)")).getText(), is("888888"));
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}
