package tqs.lab4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class HelloWorldRefactored {

    @Test
    void test(ChromeDriver driver){
        String sutUrl = "https://duckduckgo.com/";
        driver.get(sutUrl); 
        String title = driver.getTitle(); 
        
        assertThat(title).isEqualTo("DuckDuckGo — Privacy, simplified."); 
    }

}