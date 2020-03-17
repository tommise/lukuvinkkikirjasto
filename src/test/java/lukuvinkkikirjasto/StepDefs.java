package lukuvinkkikirjasto;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import lukuvinkkikirjasto.dao.Database;
import lukuvinkkikirjasto.dao.MockTipDao;
import lukuvinkkikirjasto.dao.SqlTipDao;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.TipService;
import lukuvinkkikirjasto.ui.StubIO;
import lukuvinkkikirjasto.ui.IOService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StepDefs {
    List<String> inputLines;
    StubIO stubIO;
    TipService tipService;
    List<Tip> testTips;

    @Before
    public void setup() throws Exception{
        inputLines = new ArrayList<>();
        
        Database database = new Database("jdbc:sqlite:vinkkitietokanta-test.db"); 
        database.createTables();        
        tipService = new TipService(new SqlTipDao(database));
    }

    @After
    public void after() throws Exception{
        Files.deleteIfExists(Paths.get("vinkkitietokanta-test.db"));
    }

    @Given("a user has chosen command 2 to view items")
    public void aUserHasChosenCommand() {
        inputLines.add("2");
    }

    @Given("a user has chosen command one to add items")
    public void aUserHasChosenCommand1() {
        inputLines.add("1");
    }

    @Given("some tip items have been added") 
    public void dataBaseHasBeenInitialized() {
        testTips = new ArrayList<>();
        testTips.add(tipService.createTip("test-title1", "test-link1"));
        testTips.add(tipService.createTip("test-title2", "test-link2"));
        testTips.add(tipService.createTip("test-title3", "test-link3"));
    }

    @Given("no tip items have been added")
    public void noTipItemsHaveBeenAdded() {
        //testTips = new ArrayList<>();
        //tipService = new TipService(new MockTipDao(testTips));
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

    @Then("tip with title {string} can be found from the system")
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
    }

    private void runApp() throws Exception {
        IOService ioService = new IOService(stubIO, tipService);
        ioService.runApp();
    }
}