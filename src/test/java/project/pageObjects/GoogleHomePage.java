package project.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleHomePage extends BasePage {

    @FindBy(how = How.NAME, using = "q")
    private WebElement searchInput;

    public void Search(String keyword) {
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
    }
}
