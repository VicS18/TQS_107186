package tqs.lab4.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Adapted from:
 * https://learning.oreilly.com/library/view/hands-on-selenium-webdriver/9781098109998/ch07.html#idm45849709881264
 * Example 7-6
 */

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    int timeoutSec = 5; // wait timeout (5 seconds by default)

    public BasePage(WebDriver driver, int timeout){
        this.driver = driver;
        this.timeoutSec = timeout;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    }

    public void setTimeoutSec(int timeoutSec) { 
        this.timeoutSec = timeoutSec;
    }

    public void visit(String url) { 
        driver.get(url);
    }

    public WebElement find(By element) {
        return driver.findElement(element);
    }

    public void click(By element) {
        find(element).click();
    }

    public void type(By element, String text) {
        find(element).sendKeys(text);
    }

    public String text(By element) {
        return find(element).getText();
    }   

    public boolean isDisplayed(By locator) { 
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean titleIs(final String title) {
        try {
            wait.until(ExpectedConditions.titleIs(title));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
