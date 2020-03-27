package project.runners;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import project.settings.BrowserType;

import java.util.Properties;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public class MyReportBuilder extends StoryReporterBuilder {
    private final CrossReference xref = new CrossReference();

    public MyReportBuilder() {
        this(BrowserType.valueOf(System.getProperty("browser", "CHROME")));
    }

    public MyReportBuilder(BrowserType browserType) {
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");

        this.withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
                .withDefaultFormats()
                .withRelativeDirectory(browserType.name().toLowerCase() + "-report")
                .withPathResolver(new FilePrintStreamFactory.ResolveToPackagedName())
                .withViewResources(viewResources)
                .withFormats(CONSOLE, HTML)
                .withFailureTrace(true)
                .withFailureTraceCompression(true)
                .withCrossReference(xref);
    }
}
