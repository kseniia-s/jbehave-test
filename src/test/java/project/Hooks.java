package project;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import project.settings.Browser;

@RunWith(JUnit4.class)
public class Hooks {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = Browser.getDriver();
    }

    @After
    public void tearDown() {
        if(driver != null)
        driver.quit();
    }

//    @BeforeScenario
//    public void beforeEachScenario() {
//
//    }

//    @BeforeScenario(uponType=ScenarioType.EXAMPLE)
//    public void beforeEachExampleScenario() {
//        // ...
//    }

//    @AfterScenario // equivalent to  @AfterScenario(uponOutcome=AfterScenario.Outcome.ANY)
//    public void afterAnyScenario() {
//
//    }

//    @AfterScenario(uponType= ScenarioType.EXAMPLE)
//    public void afterEachExampleScenario() {
//        // ...
//    }

//    @AfterScenario(uponOutcome=AfterScenario.Outcome.SUCCESS)
//    public void afterSuccessfulScenario() {
//        // ...
//    }
//
//    @AfterScenario(uponOutcome=AfterScenario.Outcome.FAILURE)
//    public void afterFailedScenario() {
//        // ...
//    }
}
