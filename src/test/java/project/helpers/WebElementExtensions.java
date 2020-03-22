package project.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import project.settings.ScenarioContext;

public class WebElementExtensions {

    public static WebElement scrollToElement( WebElement element )
    {
        JavascriptExecutor js = (JavascriptExecutor) ScenarioContext.context().getBrowser();
        js.executeScript( "arguments[0].scrollIntoView(false);", element );

        return element;
    }

    public static WebElement focusOnElement( WebElement element )
    {
        new Actions( ScenarioContext.context().getBrowser() )
                .moveToElement( element )
                .build()
                .perform();

        return element;
    }
}
