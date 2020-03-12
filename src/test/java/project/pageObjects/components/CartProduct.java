package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;
import project.settings.Browser;

public class CartProduct extends BaseItem {

    private WebElement name = Browser.getDriver().findElement(By.xpath("//a[@apprzroute and @class='cart-modal__title']"));
    private WebElement price = Browser.getDriver().findElement(By.xpath("//div[@class='cart-modal__coast']/span[@class='cart-modal__coast-digits']"));

    public CartProduct(){
    }

    public WebElement getName(){
        return name;
    }

    public WebElement getPrice(){
        return price;
    }
}
