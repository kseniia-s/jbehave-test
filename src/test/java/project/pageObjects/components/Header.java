package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;

public class Header extends BaseItem {

    private By searchField = By.xpath("//input[@search-input]");
    private By searchButton = By.xpath("//button[contains(@class,'search-form__submit')]");

    public Header(){}

    public Header(WebElement root) {
        super(root);
    }

    public void searchByKeyword(String searchingKeyword){
        root.findElement(searchField).sendKeys(searchingKeyword);
//        root.findElement(searchButton).click();
    }
}
