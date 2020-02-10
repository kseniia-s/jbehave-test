package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.settings.Browser;

import java.util.List;

public class GoogleResultsPage extends BasePage {

    public boolean isTextPresent(String text) {
        List<WebElement> elementsList = Browser.getDriver().findElements(By.xpath("//*[contains(text(), \"" + text + "\")]"));

        return !elementsList.isEmpty();
    }
}
