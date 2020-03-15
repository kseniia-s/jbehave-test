package project.stepDefs;

import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import project.pageObjects.ProductsPage;
import project.pageObjects.components.CartProduct;
import project.pageObjects.components.popups.Cart;
import project.settings.ScenarioContext;

import java.util.List;
import java.util.stream.Collectors;

public class CartStepDef extends Steps {
    @When("user clicks on @buttonName button in the top")
    public void userClicksOnButtonNameButtonInTheTop(String buttonName) {

    }

    @When("user clicks on cart icon of $productsNumber products")
    public void userClicksOnCartIconOfProducts(Integer productsNumber) {
        ((ProductsPage) ScenarioContext.context().getCurrentPage()).addProductsToCart(productsNumber);
    }

    @When("user opens a cart")
    public void userOpensACartByClickingOnTheIconInTheTop() {
        Cart cart = ScenarioContext.context().getCurrentPage().getHeader().openCart();
        ScenarioContext.context().setData("cart", cart);
    }

    @Then("the cart is opened")
    public void theCartIsOpened() {
        Cart cart = ScenarioContext.context().getCurrentPage().getCart();
        boolean cartIsOpened = cart.isOpened();
        Assert.assertTrue(cartIsOpened);
        ScenarioContext.context().setData("cart", cart);
    }

    @When("user clicks on $symbol near any product in the cart")
    public void userClicksOnNearAnyProductInTheCart(String symbol) {
        Cart cart = ScenarioContext.context().getCurrentPage().getCart();
        CartProduct cartProduct = cart.getProducts().stream().findFirst().get();
        if (symbol.equals("plus")) {
            cartProduct.addOneProduct();
        } else {
            cartProduct.subtractOneProduct();
        }
    }

    @Then("$productsNumber products are in the cart")
    public void productsAreInTheCart(int productsNumber) {
        Cart cart = (Cart) ScenarioContext.context().getData("cart");
        int productsNumberInCart = cart.getProducts().size();
        Assert.assertEquals("Unexpected products number in the cart", productsNumberInCart, productsNumber);
    }

    @Then("the cart is not empty")
    public void theCartIsNotEmpty() {
        Cart cart = (Cart) ScenarioContext.context().getData("cart");
        boolean cartIsEmpty = cart.getProducts().isEmpty();
        Assert.assertFalse(cartIsEmpty);
    }

    @Then("amount to be paid for the first product is greater than $amount")
    public void thenAmountToBePaidForTheFirstProductIsGreaterThan(int amount) {
        Cart cart = (Cart) ScenarioContext.context().getData("cart");
        CartProduct firstCartProduct = cart.getProducts().stream().findFirst().get();
        int fullPriceForProduct = firstCartProduct.getSum();

        System.out.println("full price for product: " + fullPriceForProduct);
        Assert.assertTrue(fullPriceForProduct > amount);
        ScenarioContext.context().setData("full price for product", fullPriceForProduct);
    }

    @Then("the amount to be paid is changed")
    public void theAmountToBePaidIsChanged() {
        int initialFullPriceForProduct = (int)ScenarioContext.context().getData("full price for product");
        Cart cart = (Cart) ScenarioContext.context().getData("cart");
        CartProduct firstCartProduct = cart.getProducts().stream().findFirst().get();
        int currentFullPriceForProduct = firstCartProduct.getSum();

        System.out.println("initial: " + initialFullPriceForProduct);
        System.out.println("current: " + currentFullPriceForProduct);

        Assert.assertTrue(currentFullPriceForProduct != initialFullPriceForProduct);
        ScenarioContext.context().setData("full price for product", currentFullPriceForProduct);
    }

    @When("a <product> is added to the cart")
    public void aProductIsAddedToCart(@Named("product") String product) {
        Cart cart = ScenarioContext.context().getCurrentPage().getHeader().openCart();
        boolean productInCart = cart.getProducts().stream().noneMatch(item -> item.getName().getText().contains(product));

        Assert.assertTrue("Product was not added to the cart", productInCart);
    }
}
