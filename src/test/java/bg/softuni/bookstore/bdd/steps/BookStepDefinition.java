package bg.softuni.bookstore.bdd.steps;

import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookStepDefinition {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Gson gson;

    ResponseEntity<Pageable> responseEntity;

    private ImportBookDto book;

    private final String url = "/api/bookstore/books";

    @Given("several books in the database")
    public void givenSeveralBooksInDatabase() {

    }

    @When("a get request is sent")
    public void aGetRequestIsSent() {
        responseEntity = restTemplate.getForEntity(url, Pageable.class);
    }

    @Then("i should receive status ok")
    public void iShouldReceiveStatusOk() {
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    @And("correct book information")
    public void correctGoodInformation() {

    }
}
