package project.stepDefs;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Composite;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import project.pageObjects.CabinetPage;
import project.pageObjects.HomePage;
import project.pageObjects.ProductsPage;
import project.pageObjects.components.popups.LoginWindow;
import project.settings.ScenarioContext;

import java.util.Map;

public class MainStepDef extends Steps {
    @Given("an opened browser with an $URL")
    public void anOpenedBrowserWithABaseURL(String URL) {
        ScenarioContext
                .context()
                .getBrowser()
                .navigate()
                .to(URL);
    }

    @When("page $pagename is fully loaded")
    public void pageIsFullyLoaded(String pageName) {
        switch (pageName) {
            case "home page":
                new HomePage();
                break;
            case "products":
                new ProductsPage();
                break;
            default:
        }
    }

    @When("user fills required fields in login form: $tableData")
    public void userFillsRequiredFieldsInLoginForm(ExamplesTable tableData) {
        for (Map<String, String> row : tableData.getRows()) {
            String email = row.get("email");
            String password = row.get("password");
            ScenarioContext.context().getCurrentPage().getLoginPopup().fillLoginForm(email, password);
        }
    }

    @When("user clicks on search button")
    public void userClicksOnTheSearchButton() {
        ScenarioContext.context().getCurrentPage().getHeader().clickSearchButton();
    }

    @When("user clicks on the $buttonName button")
    public void userClicksOnTheButtonNameButton(String buttonName) {
        ScenarioContext.context().getCurrentPage().getHeader().clickOnButtonByName(buttonName);
    }

    @When("user clicks on the 'Log in' button on the login popup")
    public void userClicksOnTheLogInButtonOnTheLoginPopup() {
        ScenarioContext.context().getCurrentPage().getLoginPopup().clickOnLogInButton();
    }

    @Then("popup with login form is shown")
    public void popupWithLoginFormIsShown() {
        LoginWindow loginWindow = ScenarioContext.context().getCurrentPage().getLoginPopup();
        Assert.assertTrue("The modal window with login form is not shown", loginWindow.isShown());
    }

    @When("user searches for $productName")
    @Alias("user searches for <productName>")
    @Composite(steps = {
            "When user types <productName> in the search field",
            "And user clicks on search button"})
    public void whenUserSearchesForProductName(@Named("productName") String productName) {
    }

    @Then("user $text is logged in")
    public void userSeesTextOnTheCabinetPage(String userName) {
        Boolean textIsShown = new CabinetPage().isTextShown(userName);
        Assert.assertTrue("The user is not logged in", textIsShown);
    }

    @Then("user clicks on the menu button in the top right side")
    public void userClicksOnTheMenuButtonInTheTopRightSide() {
        ScenarioContext.context().getCurrentPage().getHeader().clickOnCabinetMenuButton();
    }
}
