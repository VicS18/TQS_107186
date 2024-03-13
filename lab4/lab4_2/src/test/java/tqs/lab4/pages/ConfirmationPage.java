package tqs.lab4.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    private List<By>reservationInfo;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        visit("https://blazedemo.com/confirmation.php");

        // Get reservation data locators, starting at the 2nd table row
        this.reservationInfo = new ArrayList<By>();
        for(int i = 0; i < 5; i++)
            reservationInfo.add(By.cssSelector(String.format("tr:nth-child(%d) > td:nth-child(2)", i+2)));
    }

    public List<String> getReservationText() {
        return reservationInfo.stream().map(this::text).toList();
    }
}
