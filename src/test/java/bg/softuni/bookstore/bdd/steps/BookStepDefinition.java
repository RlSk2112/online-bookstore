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

    //ToDo

}
