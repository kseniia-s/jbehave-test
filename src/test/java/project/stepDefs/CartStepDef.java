package project.stepDefs;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

public class CartStepDef extends Steps {
    @When("user clicks on @buttonName button in the top")
    public void userClicksOnButtonNameButtonInTheTop(String buttonName) {

    }

    @When("user clicks on cart icon at the random 2 items")
    public void userClicksOnIconAtTheRandomItems(String iconName, String itemsNumber) {

    }

    @When("user opens a cart by clicking on the $iconName icon in the top")
    public void userOpensACartByClickingOnTheIconInTheTop(String iconName) {

    }

    @When("user clicks on $symbol near any product in the cart")
    public void userClicksOnNearAnyProductInTheCart(String symbol) {

    }

    @Then("the chosen products are in the cart")
    public void theChosenProductsAreInTheCart() {

    }

    @Then("the cart is not empty")
    public void theCartIsNotEmpty() {

    }

    @Then("the sum is changed and it is correct")
    public void theSumIsChangedAndItIsCorrect() {

    }
}
