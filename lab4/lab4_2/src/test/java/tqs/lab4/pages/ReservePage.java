package tqs.lab4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReservePage extends BasePage {
    
    private By secondFlight = By.cssSelector("tr:nth-child(2) .btn");

    public ReservePage(WebDriver driver){
        super(driver);
        visit("https://blazedemo.com/reserve.php");
    }

    public PurchasePage reserve(){
        click(secondFlight);
        return new PurchasePage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public By getSecondFlight() {
        return secondFlight;
    }

    public void setSecondFlight(By secondFlight) {
        this.secondFlight = secondFlight;
    }   
}
