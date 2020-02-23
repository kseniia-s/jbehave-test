package project.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import project.settings.Browser;

public class WebElementExtensions {

    public static WebElement scrollToElement( WebElement element )
    {
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript( "arguments[0].scrollIntoView(false);", element );

        return element;
    }

    public static WebElement focusOnElement( WebElement element )
    {
        new Actions( Browser.getDriver() )
                .moveToElement( element )
                .build()
                .perform();

        return element;
    }
}
