package project.runners;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import project.stepDefs.GoogleHomePageStepDef;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyControls = TestsRun.MyStoryControls.class, storyLoader = TestsRun.MyStoryLoader.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = true, verboseFailures = true, /*threads = 2, */metaFilters = "-skip")
@UsingSteps(instances = { GoogleHomePageStepDef.class })
public class TestsRun extends InjectableEmbedder {

    @Test
    public void run() {
        List<String> storyPaths = new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "");
        injectedEmbedder().runStoriesAsPaths(storyPaths);
    }

    public static class MyStoryControls extends StoryControls {
        public MyStoryControls() {
            doDryRun(false);
            doSkipScenariosAfterFailure(false);
        }
    }

    public static class MyStoryLoader extends LoadFromClasspath {
        public MyStoryLoader() {
            super(TestsRun.class.getClassLoader());
        }
    }

//    public static class MyReportBuilder extends StoryReporterBuilder {
//        public MyReportBuilder() {
//            this.withFormats(CONSOLE, TXT, HTML, XML).withDefaultFormats();
//        }
//    }

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

}