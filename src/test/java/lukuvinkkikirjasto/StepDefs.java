package lukuvinkkikirjasto;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import lukuvinkkikirjasto.dao.Database;
import lukuvinkkikirjasto.dao.SqlTagDao;
import lukuvinkkikirjasto.dao.SqlTipDao;
import lukuvinkkikirjasto.domain.Tag;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.TipService;
import lukuvinkkikirjasto.ui.StubIO;
import lukuvinkkikirjasto.ui.IOService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
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
        tipService = new TipService(new SqlTipDao(database), new SqlTagDao(database));
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

    @Given("some tips with descriptions have been added")
    public void someTipsWithDescriptionsHaveBeenAdded() throws SQLException{
        dataBaseHasBeenInitialized();
    }

    @Given("some tip items have been added") 
    public void dataBaseHasBeenInitialized() throws SQLException {
        testTips = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        testTips.add(tipService.createTip(date, "test-title1", "test-link1", "test-desciption1", "test-tag"));
        testTips.add(tipService.createTip(date, "test-title2", "test-link2", "test-desciption2", "test-tag"));
        testTips.add(tipService.createTip(date, "test-title3", "test-link3", "test-desciption3", "test-tag"));
    }

    @Given("no tip items have been added")
    public void noTipItemsHaveBeenAdded() {
        //testTips = new ArrayList<>();
        //tipService = new TipService(new MockTipDao(testTips));
    }

    @When("title {string} is entered")
    public void titleIsEntered(String title) {
        if(title.isEmpty()) {
            inputLines.add(title);
            inputLines.add("some string to get throw");    
        }
        inputLines.add(title);
    }

    @When("a link {string} is entered")
    public void linkIsEntered(String link) {
        inputLines.add(link);
    }

    @When("description {string} is entered")
    public void descriptionIsEntered(String description) {
        inputLines.add(description);
    }

    @When("tag {string} is entered")
    public void TagIsEntered(String tag) {
        inputLines.add(tag);
    }

    @When("new tip fields are filled")
    public void newTipFieldsAreFilled() {
        inputLines.add("title");
        inputLines.add("link");
        inputLines.add("description");
        inputLines.add("tag");
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedResponse) throws Exception{
        runApp();
        assertTrue(stubIO.getPrints().contains(expectedResponse));
    }

    @Then("tip with title {string} can be found from the system")
    public void tipIsSavedToDatabase(String string) throws Exception{
        List<Tip> entries = tipService.getAll();
        List<String> titles = entries.stream().map(tip -> tip.getTitle()).collect(Collectors.toList());
        assertTrue(titles.contains(string));         
    }

    @Then("tip with link {string} can be found from the system") 
    public void tipWithLinkCanBeFound(String link) throws Exception{
        List<Tip> entries = tipService.getAll();
        List<String> titles = entries.stream().map(tip -> tip.getLink()).collect(Collectors.toList());
        assertTrue(titles.contains(link));
    }

    @Then("tip with description {string} can be found from the system") 
    public void tipWithDescriptionCanBeFound(String description) throws Exception{
        List<Tip> entries = tipService.getAll();
        List<String> desciptions = entries.stream().map(tip -> tip.getDescription()).collect(Collectors.toList());
        assertTrue(desciptions.contains(description));
    }

    @Then("tip with tag {string} can be found from the system") 
    public void tipWithTagCanBeFound(String tag) throws Exception{
        List<Tip> entries = tipService.getAll();        
        assertTrue(entries.get(0).getTags().get(0).getTag().contains(tag));
    }

    @Then("the tip is saved with timestamp")
    public void theTipIsSavedWithRightTimestamp() throws Exception {
        runApp();
        List<Tip> entries = tipService.getAll();
        assertTrue(entries.get(0).getDate().getClass() == java.sql.Date.class);
    }

    @Then("a list containing right items is shown")
    public void aListContainingRightItemsIsShown() throws Exception {        
        runApp();
        testTips.forEach(tip -> {
            assertTrue(stubIO.getPrints().contains(tip.toString()));
        });
    }

    @Then("a list containing items with right descriptions is shown")
    public void aListContainingItemsWithRightDescriptionsIsShown() throws Exception {        
        aListContainingRightItemsIsShown();
    }
    
    @Then("a list containing items with dates is shown")
    public void aListContainingItemsWithDatesIsShown() throws Exception {       
        aListContainingRightItemsIsShown();
    }

    @Then("a list containing tags is shown")
    public void aListContainingTagsIsShown() throws Exception {       
        aListContainingRightItemsIsShown();
    }

    private void runApp() throws Exception {
        stubIO = new StubIO(inputLines);
        IOService ioService = new IOService(stubIO, tipService);
        ioService.runApp();
    }
}