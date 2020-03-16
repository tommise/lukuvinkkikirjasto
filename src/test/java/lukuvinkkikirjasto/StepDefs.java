package lukuvinkkikirjasto;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import lukuvinkkikirjasto.ui.IO;
import lukuvinkkikirjasto.ui.StubIO;
import lukuvinkkikirjasto.ui.IOService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StepDefs {
    List<String> inputLines;
    StubIO stubIO;

    @Before
    public void setup(){
        inputLines = new ArrayList<>();   
    }

    @Given("a user has chosen command {int}")
    public void aUserHasChosenCommand(Integer int1) {
        inputLines.add("" + int1);
    }

    @When("title {string} is entered")
    public void titleIsEntered(String title) {
        System.out.println(title);
        if(title.isEmpty()) {
            inputLines.add(title);
            inputLines.add("some string to get throw");    
        }
        inputLines.add(title);
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedResponse) throws Exception{
        stubIO = new StubIO(inputLines);
        
        IOService ioService = new IOService(stubIO);
        ioService.init();
        assertTrue(stubIO.getPrints().contains(expectedResponse));
        System.out.print(stubIO.getPrints());
    }
}