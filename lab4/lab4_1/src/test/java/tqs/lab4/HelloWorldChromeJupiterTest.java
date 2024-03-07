package tqs.lab4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class HelloWorldChromeJupiterTest {
    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void test() {
        String sutUrl = "https://duckduckgo.com/";
        driver.get(sutUrl); 
        String title = driver.getTitle(); 
        
        assertThat(title).isEqualTo("DuckDuckGo — Privacy, simplified."); 
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}