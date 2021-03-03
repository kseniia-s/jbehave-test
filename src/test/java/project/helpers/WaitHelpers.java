package project.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.settings.ScenarioContext;

public class WaitHelpers {

    private static int pageLoadTimeout = 10;

    public static void waitDefaultTimeToWait() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPageSourcesToBeCompletelyLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) ScenarioContext.context().getBrowser();
        js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
    }

    public static WebElement waitUntilElementIsVisible(By element) {
        return new WebDriverWait(ScenarioContext.context().getBrowser(), 5).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitPageToBeCompletelyLoaded() {
        new WebDriverWait(ScenarioContext.context().getBrowser(), pageLoadTimeout)
                .until(wait -> ((JavascriptExecutor) ScenarioContext.context().getBrowser()).executeScript("return document.readyState").equals("complete"));
    }

    public static WebElement waitElementToBeClickable(WebElement element){
        return new WebDriverWait(ScenarioContext.context().getBrowser(), 10)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
