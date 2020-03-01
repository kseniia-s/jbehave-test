package project.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.settings.Browser;

public class WaitHelpers {

    public static void waitDefaultTimeToWait()  {
        try {
            Thread.sleep( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPageSourceToBeCompletelyLoaded(){
        JavascriptExecutor js = (JavascriptExecutor)Browser.getDriver();
        js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
    }

//        public static void waitForPageSourceToBeCompletelyLoaded(final BasePage page){
    //        try {
    //            page.waitFor(
    //                    v -> page.evaluateJavascript("return window.jQuery != undefined && jQuery.active == 0;"));
    //        } catch (TimeoutException te) {
    //
    //        }
//        }

    public static void waitUntilElementIsVisible(By element){
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
