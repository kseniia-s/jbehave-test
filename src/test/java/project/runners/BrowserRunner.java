package project.runners;

import org.assertj.core.util.Lists;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.BatchFailures;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ScanningStepsFactory;
import project.settings.BrowserType;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class BrowserRunner extends ConfigurableEmbedder {

    private BrowserType browserType;
    private BatchFailures failuresList = new BatchFailures();
    private final CrossReference xref = new CrossReference();

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
        List<String> metaFilters = Lists.newArrayList(System.getProperty("meta", "-skip").split(" "));
        int threadsCount = Integer.parseInt(System.getProperty("threads", "3"));
        Embedder embedder = configuredEmbedder();
        embedder.useEmbedderFailureStrategy(new MyFailureStrategy());
        embedder.useMetaFilters(metaFilters);
        embedder.embedderControls()
                .useThreads(threadsCount)
                .doIgnoreFailureInView(true)
                .doIgnoreFailureInStories(true)
                .verboseFailures();
        try {
            embedder.runStoriesAsPaths(storyPaths());
        } finally {
            embedder.generateCrossReference();
            embedder.generateSurefireReport();
        }
    }

    @Override
    public Configuration configuration() {
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");

        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
                .useStoryReporterBuilder(new MyReportBuilder())
                .useViewGenerator(new FreemarkerViewGenerator());
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new ScanningStepsFactory(configuration(), "project.stepDefs");
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
