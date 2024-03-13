package tqs.lab4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage extends BasePage {

    private By inputName = By.id("inputName");
    private By addr = By.id("address");
    private By city = By.id("city");
    private By state = By.id("state");
    private By zipCode = By.id("zipCode");
    private By creditCardNumber = By.id("creditCardNumber");
    private By nameOnCard = By.id("nameOnCard");
    private By checkbox = By.cssSelector(".checkbox");
    private By primaryBtn = By.cssSelector(".btn-primary");

    private By thirdOpt = By.cssSelector(".control-group:nth-child(3) > .control-label");
    private By ninthOpt = By.cssSelector(".control-group:nth-child(9) > .control-label");

    public PurchasePage(WebDriver driver) {
        super(driver);
        visit("https://blazedemo.com/purchase.php");
    }

    public ConfirmationPage purchaseFlight(){
        click(inputName);
        type(inputName, "Test");

        type(addr, "T");
        click(thirdOpt);
        click(addr);
        type(addr, "Test");

        click(city);
        type(city, "Test");

        type(state, "Test");

        type(zipCode, "1234");

        type(creditCardNumber, "111222333");

        click(ninthOpt);

        click(nameOnCard);
        type(nameOnCard, "John Test");
        
        click(checkbox);
        click(primaryBtn);

        return new ConfirmationPage(driver);
    }
}
