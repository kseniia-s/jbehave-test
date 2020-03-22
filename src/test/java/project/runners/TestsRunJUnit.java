package project.runners;

import org.assertj.core.util.Lists;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.BatchFailures;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import project.settings.BrowserType;

import java.util.List;

@RunWith(JUnit4.class)
public class TestsRunJUnit {

    private List<BrowserType> browserList = Lists.list(BrowserType.CHROME, BrowserType.FIREFOX);
    private BatchFailures failuresList = new BatchFailures();

    @Test
    public void runTests(){

        for (BrowserType browserType : browserList) {
            try {
                BrowserRunner runner = new BrowserRunner(browserType);
                runner.run();
                failuresList.putAll(runner.getFailuresList());
            } catch (Embedder.RunningStoriesFailed e) {
                // pass execution to next browser
            }
        }
        if (failuresList.size() > 0) {
            Assert.fail();
        }
    }
}