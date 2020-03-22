package project.runners;

import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.BatchFailures;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.Steps;
import project.settings.BrowserType;
import project.stepDefs.*;

import java.util.*;

public class BrowserRunner extends ConfigurableEmbedder {

    private BrowserType browserType;
    private BatchFailures failuresList = new BatchFailures();

    BrowserRunner(BrowserType type) {
        System.out.println(Thread.currentThread().getName() + " TestExecution constructor: " + type);
        browserType = type;
    }

    BatchFailures getFailuresList() {
        return failuresList;
    }

    @Override
    public void run() {
        System.setProperty("browser", String.valueOf(this.browserType));
        Embedder embedder = configuredEmbedder();
        embedder.useEmbedderFailureStrategy(new MyFailureStrategy());
        embedder.embedderControls().useThreads(4)
//                .doIgnoreFailureInStories(true)
//                .doIgnoreFailureInView(true)
        ;
        try {
            embedder.runStoriesAsPaths(storyPaths());
        } finally {
            embedder.generateCrossReference();
            embedder.generateSurefireReport();
        }
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
//                .useCompositePaths(Sets.newHashSet("Composite.steps"))
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
//                .useStoryReporterBuilder(new StoryReporterBuilder()
//                        .withDefaultFormats()
//                        .withFormats(Format.HTML, Format.CONSOLE)
//                        .withRelativeDirectory("jbehave-report")
//                        .withFailureTrace(true)
//                );
                ;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new CommonStepDef(), new FiltersStepDef(), new MainStepDef(), new SearchStepDef(), new CartStepDef());

//        ArrayList<Steps> stepFileList = new ArrayList<>();
//        stepFileList.add(new CommonStepDef());
//        stepFileList.add(new FiltersStepDef());
//        stepFileList.add(new MainStepDef());
//        stepFileList.add(new SearchStepDef());
//        stepFileList.add(new CartStepDef());

//        return new InstanceStepsFactory(configuration(), factory);
    }

    private List<String> storyPaths() {
        return new StoryFinder().
                findPaths(CodeLocations.codeLocationFromClass(
                        this.getClass()),
                        Collections.singletonList("**/*.story"),
                        Collections.singletonList(""));

    }

    private class MyFailureStrategy extends Embedder.ThrowingRunningStoriesFailed {

        MyFailureStrategy() {
        }

        @Override
        public void handleFailures(BatchFailures failures) {
            failuresList.putAll(failures);
            failures.forEach((key, value) -> {
                System.out.println("Failed: " + key);
                value.printStackTrace();
            });
            throw new Embedder.RunningStoriesFailed(failures);
        }
    }
}
