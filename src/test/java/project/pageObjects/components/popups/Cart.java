package project.pageObjects.components.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.helpers.WaitHelpers;
import project.helpers.CollectionUtils;
import project.pageObjects.BaseItem;
import project.pageObjects.components.CartProduct;

import java.util.List;

public class Cart extends BaseItem {

    private By cartProducts = By.xpath("//li[@class='cart-modal__item']");

    public Cart(WebElement root) {
        super(root);
        WaitHelpers.waitDefaultTimeToWait();
    }

    public boolean isOpened(){
        return root.isEnabled() && root.isDisplayed() && root.getSize().height > 0;
    }

    public List<CartProduct> getProducts(){

        return CollectionUtils.getItemsList(root.findElements(cartProducts), CartProduct.class);
    }
}
