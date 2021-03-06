package project.runners;

import org.assertj.core.util.Lists;
import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import project.settings.BrowserType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyControls = TestsRunAnnotation.MyStoryControls.class, storyLoader = TestsRunAnnotation.MyStoryLoader.class)
@UsingEmbedder(ignoreFailureInStories = true, ignoreFailureInView = true, verboseFailures = true, metaFilters = "-skip")
@UsingSteps(packages = "project.stepDefs")
public class TestsRunAnnotation extends InjectableEmbedder {

    private List<BrowserType> browserList;

    @Test
    public void run() {
        String browsers = System.getProperty("browser", "chrome");
        List<String> metaFilters = Lists.newArrayList(System.getProperty("meta", "-skip").split(" "));
        int threadsCount = Integer.parseInt(System.getProperty("threads", "1"));
        if (!browsers.isEmpty()) {
            browserList = parse(browsers);
        }
        for (BrowserType browserType : browserList) {
            System.setProperty("browser", browserType.name());
            Embedder embedder = injectedEmbedder();
            embedder.useMetaFilters(metaFilters);
            embedder.embedderControls().useThreads(threadsCount);
            embedder.configuration().useStoryReporterBuilder(new MyReportBuilder());
            try {
                embedder.runStoriesAsPaths(storyPaths());
            } finally {
                embedder.generateCrossReference();
            }
        }
    }

    public static class MyStoryControls extends StoryControls {
        public MyStoryControls() {
            doDryRun(false);
            doSkipScenariosAfterFailure(false);
        }
    }

    public static class MyStoryLoader extends LoadFromClasspath {
        public MyStoryLoader() {
            super(TestsRunAnnotation.class.getClassLoader());
        }
    }

    private List<BrowserType> parse(String browsers) {
        return Arrays.stream(browsers.split(","))
                .map(s -> s.trim().toUpperCase())
                .map(BrowserType::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(
                        this.getClass()),
                        Collections.singletonList("**/*.story"),
                        Collections.singletonList(""));
    }
}