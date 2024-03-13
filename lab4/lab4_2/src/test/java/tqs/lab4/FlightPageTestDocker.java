package tqs.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.github.bonigarcia.seljup.BrowserType.OPERA;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import tqs.lab4.pages.ConfirmationPage;
import tqs.lab4.pages.HomePage;
import tqs.lab4.pages.PurchasePage;
import tqs.lab4.pages.ReservePage;

import org.openqa.selenium.WebDriver;

@ExtendWith(SeleniumJupiter.class)
public class FlightPageTestDocker {

  @Test
  public void flighttest(@DockerBrowser(type = OPERA, vnc = true) WebDriver driver) {
    HomePage homePage = new HomePage(driver);
    ReservePage reservePage = homePage.selectFlight();
    PurchasePage purchasePage = reservePage.reserve();
    ConfirmationPage confirmationPage = purchasePage.purchaseFlight();

    String[] expectedReservation = {"PendingCapture", "555 USD", "xxxxxxxxxxxx1111", "11 /2018", "888888"};

    assertThat(confirmationPage.getReservationText().toArray(), is(expectedReservation));
    
    assertThat(confirmationPage.titleIs("BlazeDemo Confirmation"), is(true));
  }
}
