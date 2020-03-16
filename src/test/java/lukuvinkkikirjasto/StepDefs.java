package lukuvinkkikirjasto;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import lukuvinkkikirjasto.dao.MockTipDao;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.TipService;
import lukuvinkkikirjasto.ui.StubIO;
import lukuvinkkikirjasto.ui.IOService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StepDefs {
    List<String> inputLines;
    StubIO stubIO;
    TipService tipService;
    List<Tip> testTips;

    @Before
    public void setup(){
        inputLines = new ArrayList<>();
        tipService = new TipService(new MockTipDao(new ArrayList<>()));
    }

    @Given("a user has chosen command {int}")
    public void aUserHasChosenCommand(Integer int1) {
        inputLines.add("" + int1);
    }

    @Given("some tip items have been added") 
    public void dataBaseHasBeenInitialized() {
        testTips = new ArrayList<>();
        testTips.add(new Tip("test-title1", "test-link1", 1));
        testTips.add(new Tip("test-title2", "test-link2", 2));
        testTips.add(new Tip("test-title3", "test-link3", 3));
        tipService = new TipService(new MockTipDao(testTips));
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
        runApp();
        assertTrue(stubIO.getPrints().contains(expectedResponse));
        System.out.print(stubIO.getPrints());
    }

    @Then("Tip with title {string} can be found from database")
    public void tipIsSavedToDatabase(String string) throws Exception{
        List<Tip> entries = tipService.getAll();
        List<String> titles = entries.stream().map(tip -> tip.getTitle()).collect(Collectors.toList());
        assertTrue(titles.contains(string));         
    }

    @Then("a list containing right items is shown")
    public void aListContainingRightItemsIsShown() throws Exception{
        stubIO = new StubIO(inputLines);        
        runApp();
        testTips.forEach(tip -> {
            assertTrue(stubIO.getPrints().contains(tip.toString()));
        });
        System.out.print(stubIO.getPrints());

    }

    private void runApp() throws Exception {
        IOService ioService = new IOService(stubIO, tipService);
        ioService.runApp();
    }
}