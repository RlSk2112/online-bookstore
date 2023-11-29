package bg.softuni.bookstore.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookStepDefinition {

    @Given("a book with title")
    public void test() {
        assertEquals(1, 1);
    }

    @When("the book")
    public void theBook() {
    }

    @Then("i should see true")
    public void iShouldSeeTrue() {
        assertTrue(true);
    }
}
