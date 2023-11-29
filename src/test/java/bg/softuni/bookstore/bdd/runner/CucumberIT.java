package bg.softuni.bookstore.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "bg/softuni/bookstore/bdd/steps")
public class CucumberIT {
}
