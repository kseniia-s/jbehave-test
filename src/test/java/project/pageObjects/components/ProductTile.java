package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;

public class ProductTile extends BaseItem {

    public String title = root.findElement( By.xpath( ".//div[@class='title-text-owerflow']/p" ) ).getText().trim();

    public ProductTile( WebElement root ) {
        super(root);
    }
}
