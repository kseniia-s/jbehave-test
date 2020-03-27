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
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import project.settings.BrowserType;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyControls = TestsRunAnnotation.MyStoryControls.class, storyLoader = TestsRunAnnotation.MyStoryLoader.class)
@UsingEmbedder(threads = 3, ignoreFailureInView = false, verboseFailures = true, metaFilters = "-skip")
@UsingSteps(packages = "project.stepDefs")
//@UsingSteps(packages = "project.stepDefs", matchingNames = ".*steps", notMatchingNames = "")
public class TestsRunAnnotation extends InjectableEmbedder {
//public class TestsRunAnnotation extends InjectableEmbedder, implements Embeddable{

    private List<BrowserType> browserList = Lists.list(BrowserType.CHROME, BrowserType.FIREFOX);

    @Test
    public void run() {
        String browsers = System.getProperty("browser", "");
        if (!browsers.isEmpty()) {
            browserList = parse(browsers);
        }
        for (BrowserType browserType : browserList) {
            System.setProperty("browser", browserType.name());
            List<String> storyPaths = new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/m*.story", "");
            Embedder embedder = injectedEmbedder();
            embedder.configuration().useStoryReporterBuilder(new MyReportBuilder());
            embedder.runStoriesAsPaths(storyPaths);
            embedder.generateCrossReference();
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



//    public static class MyRegexPrefixCapturingPatternParser extends RegexPrefixCapturingPatternParser {
//        public MyRegexPrefixCapturingPatternParser() {
//            super("%");
//        }
//    }
//
//    public static class MyDateConverter extends ParameterConverters.DateConverter {
//        public MyDateConverter() {
//            super(new SimpleDateFormat("yyyy-MM-dd"));
//        }
//    }

    private List<BrowserType> parse(String browsers) {
        return Arrays.stream(browsers.split(","))
                .map(s -> s.trim().toUpperCase())
                .map(BrowserType::valueOf)
                .collect(Collectors.toList());
    }
}