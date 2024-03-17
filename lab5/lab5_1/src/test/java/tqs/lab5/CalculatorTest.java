package tqs.lab5;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private Calculator calc;

    @Given("a functioning calculator")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        log.debug("Adding {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I subtract {int} from {int}")
    public void substract(int arg1, int arg2) {
        log.debug("Subtracting {} from {}", arg1, arg2);
        calc.push(arg2);
        calc.push(arg1);
        calc.push("-");
    }

    @When("I divide {int} by {int}")
    public void divide(int arg1, int arg2) {
        log.debug("Dividing {} by {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }

    @When("I multiply {int} by {int}")
    public void multiply(int arg1, int arg2) {
        log.debug("Multiply {} by {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @Then("I obtain {int}")
    public void the_result_is(double expected) {
        Number value = calc.value();
        log.debug("Result: {} (expected {})", value, expected);
        assertEquals(expected, value);
    }

}
