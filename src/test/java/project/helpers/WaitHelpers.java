package project.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.settings.Browser;

import java.util.List;

public class WaitHelpers {

    private static int pageLoadTimeout = 5;

    public static void waitDefaultTimeToWait() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPageSourcesToBeCompletelyLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
    }

    public static WebElement waitUntilElementIsVisible(By element) {
        return new WebDriverWait(Browser.getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitPageToBeCompletelyLoaded() {
        new WebDriverWait(Browser.getDriver(), pageLoadTimeout)
                .until(wait -> ((JavascriptExecutor) Browser.getDriver()).executeScript("return document.readyState").equals("complete"));
    }
}
