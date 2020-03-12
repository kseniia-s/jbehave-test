package project.runners;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.*;
import project.settings.Browser;
import project.stepDefs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestsRun2 extends JUnitStories {

    @Override
    public Configuration configuration() {
//        new MostUsefulConfiguration().useCompositePaths(Collections.singletonList("Composite.steps"));
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()));
//                .useStoryReporterBuilder(new StoryReporterBuilder()
//                        .withDefaultFormats()
//                        .withFormats(Format.HTML, Format.CONSOLE)
//                        .withRelativeDirectory("jbehave-report")
//                        .withFailureTrace(true)
//                );
// Не работает репортер
//                .useStoryReporterBuilder(
//                        new StoryReporterBuilder()
//                                .withDefaultFormats()
//                                .withFormats(Format.HTML, Format.CONSOLE)
//                                .withRelativeDirectory("jbehave-report")
//                );
    }

//    @Override
//    public EmbedderControls embedderControls() {
//        return new EmbedderControls().doIgnoreFailureInStories(true).doIgnoreFailureInView(true);
//    }

    @Override
    public InjectableStepsFactory stepsFactory() {
//        ArrayList<Steps> stepFileList = new ArrayList<Steps>();
        ArrayList<Steps> stepFileList = new ArrayList<>();
        stepFileList.add(new GoogleHomePageStepDef());
        stepFileList.add(new FiltersStepDef());
        stepFileList.add(new MainStepDef());
        stepFileList.add(new SearchStepDef());
        stepFileList.add(new CartStepDef());

        return new InstanceStepsFactory(configuration(), stepFileList);
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().
                findPaths(CodeLocations.codeLocationFromClass(
                        this.getClass()),
//                        Arrays.asList("**/*.story"),
                        Arrays.asList("**/cart*.story"),
                        Arrays.asList(""));

    }

    @AfterStories
    public void tearDown() {
        if(Browser.getDriver() != null)
            Browser.getDriver().quit();
    }

//    public Automation() {
//        this.configuredEmbedder()
//                .stepsFactory()
//                .createCandidateSteps()
//                .add(new GoogleHomePageStepDef());
//    }

//    @Override
//    protected List<String> storyPaths() {
//        return Arrays.asList("project/stories/automation.story");
//    }


//    @Override
//        protected List<String> storyPaths() {
//        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "");
//    }


//    @Override
//    public Configuration configuration() {
//        return new MostUsefulConfiguration()
//                .
//                .useStepFinder(new StepFinder());
//    }
//
//
//    @Override
//    @Test
//    public void run() throws Throwable{
//        super.run();
//    }

//    @Override
//    public Configuration configuration() {
//        return new MostUsefulConfiguration()
//                // where to find the stories
//                .useStoryLoader(new LoadFromURL())//"classpath:stories/automation.story"))
////                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))//.loadStoryAsText("classpath:stories/automation.story"))
////                .useStoryLoader("")
//                // CONSOLE and TXT reporting
//                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
//    }
////
//    // Here we specify the steps classes
//    @Override
//    public InjectableStepsFactory stepsFactory() {
//        // varargs, can have more that one steps classes
//        return new InstanceStepsFactory(configuration(), Arrays.asList("**/*.story"));
//    }

}