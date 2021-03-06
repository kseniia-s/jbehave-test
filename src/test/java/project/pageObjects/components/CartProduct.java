package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.helpers.WaitHelpers;
import project.pageObjects.BaseItem;

public class CartProduct extends BaseItem {

    private By nameBy = By.xpath("//a[@apprzroute and @class='cart-product__title']");
    private By priceBy = By.xpath("//div[@class='cart-product__coast']//p[@class='cart-product__price']");
    private By sumBy = By.xpath("//div[@class='cart-receipt']//div[@class='cart-receipt__sum-price']");
    private By plusIcon = By.xpath("//button[@class='button button_color_white button_size_medium cart-counter__button' and contains(@aria-label,'Добавить')]");
    private By minusIcon = By.xpath("//button[@class='button button_color_white button_size_medium cart-counter__button' and contains(@aria-label,'Убрать')]");

    private WebElement name;
    private WebElement price;
    private WebElement sum;

    public CartProduct() {
    }

    public CartProduct(WebElement root) {
        super(root);
        this.name = root.findElement(nameBy);
        this.price = root.findElement(priceBy);
        this.sum = root.findElement(sumBy);
    }

    public WebElement getName() {
        return name;
    }

    public int getSum() {
        return Integer.parseInt(sum.getText().replaceAll("\\s", ""));
    }

    public void addOneProduct() {
        WaitHelpers.waitDefaultTimeToWait();
        root.findElement(plusIcon).click();
        WaitHelpers.waitDefaultTimeToWait();
    }

    public void subtractOneProduct() {
        WaitHelpers.waitDefaultTimeToWait();
        root.findElement(minusIcon).click();
        WaitHelpers.waitDefaultTimeToWait();
    }
}
