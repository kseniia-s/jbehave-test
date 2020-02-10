package project.pageObjects;

import org.openqa.selenium.support.PageFactory;
import project.settings.Browser;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Browser.getDriver(), this);
    }
}