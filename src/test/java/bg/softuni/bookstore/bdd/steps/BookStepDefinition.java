package bg.softuni.bookstore.bdd.steps;

import bg.softuni.bookstore.domain.dto.book.ExportBookDto;
import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class BookStepDefinition {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Gson gson;

    private ResponseEntity<String> responseEntity;

    private ImportBookDto book;


    @Given("a book with title {string} to be inserted to database")
    public void givenBookToBeInsertedToDatabase(String string) {
        book = new ImportBookDto("The boogeyman", "1234567891230", null);
    }

    @When("the book is inserted")
    public void theBookIsInserted() {
        String url = "/books";
        responseEntity = restTemplate.postForEntity(url, book, String.class);
    }

    @Then("the status should be created")
    public void theStatusShouldBeCreated() {
        assertEquals(201, responseEntity.getStatusCode().value());
    }

    @And("verify that book with the given title {string} exists")
    public void verifyThatBookWithTheGivenTitleStringExists(String string) {
        String url = "/books/by-title?title=" + string;
        responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        ExportBookDto[] books = gson.fromJson(body, ExportBookDto[].class);
        ExportBookDto foundBook = books[0];
        assertEquals(string, foundBook.getTitle());
    }

    @Given("a book with isbn {string} to be inserted to database")
    public void bookWithIsbnToBeInsertedToDatabase(String string) {
        book = new ImportBookDto("The boogeyman", "1234567891230", null);
        String url = "/books";
        restTemplate.postForEntity(url, book, String.class);
    }

    @When("i try to add a book with the same isbn {string}")
    public void addABookWithTheSameIsbn(String string) {
        book = new ImportBookDto("The boogeyman", "1234567891230", null);
        String url = "/books";
        responseEntity = restTemplate.postForEntity(url, book, String.class);
    }

    @Then("the response status should be bad request")
    public void theResponseStatusShouldBeBadRequest() {
        assertEquals(400, responseEntity.getStatusCode().value());
    }
}
