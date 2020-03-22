package project.stepDefs;

import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import project.settings.BrowserType;
import project.settings.Context;
import project.settings.ScenarioContext;

public class CommonStepDef extends Steps {

    @BeforeStory
    public void setup() {
        ScenarioContext.initContext(new Context(BrowserType.valueOf(System.getProperty("browser").toUpperCase())));
    }

    @AfterStory
    public void tearDown() {
        ScenarioContext.context().cleanup();
    }
}
