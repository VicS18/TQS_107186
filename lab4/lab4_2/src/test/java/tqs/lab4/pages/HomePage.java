package tqs.lab4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    
    private By fromPort = By.name("fromPort");
    private By philyButton = By.xpath("//option[. = 'Philadelphia']");

    private By toPort = By.name("toPort");
    private By berlinButton = By.xpath("//option[. = 'Berlin']");

    private By submitBtn = By.cssSelector(".btn-primary");

    public HomePage(WebDriver driver){
        super(driver);
        visit("https://blazedemo.com/");
    }

    public ReservePage selectFlight(){
        click(fromPort);
        {
            WebElement dropdown = find(fromPort);
            dropdown.findElement(philyButton).click();
        }

        click(toPort);
        {
            WebElement dropdown = find(toPort);
            dropdown.findElement(berlinButton).click();
        }
        click(submitBtn);

        return new ReservePage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public By getFromPort() {
        return fromPort;
    }

    public void setFromPort(By fromPort) {
        this.fromPort = fromPort;
    }

    public By getPhilyButton() {
        return philyButton;
    }

    public void setPhilyButton(By philyButton) {
        this.philyButton = philyButton;
    }

    public By getToPort() {
        return toPort;
    }

    public void setToPort(By toPort) {
        this.toPort = toPort;
    }

    public By getBerlinButton() {
        return berlinButton;
    }

    public void setBerlinButton(By berlinButton) {
        this.berlinButton = berlinButton;
    }
}
