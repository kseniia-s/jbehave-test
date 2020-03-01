package project.stepDefs;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import project.pageObjects.HomePage;
import project.pageObjects.components.popups.LoginWindow;
import project.settings.Browser;
import project.settings.ScenarioContext;

import java.util.Map;

public class MainStepDef extends Steps {
    @Given("an opened browser with an $URL")
    public void anOpenedBrowserWithABaseURL(String URL) {
        Browser.getDriver().navigate().to(URL);
    }

    @When("page $pagename is fully loaded")
    public void pageIsFullyLoaded(String pageName) {
        switch (pageName){
            case "home page":
                new HomePage();
                break;
            case "":
                break;
            default:
        }
    }

    @When("user fills required fields in login form: $tableData")
    public void userFillsRequiredFieldsInLoginForm(ExamplesTable tableData) {
        for (Map<String,String> row : tableData.getRows()) {
            String email = row.get("email");
            String password = row.get("password");
            new LoginWindow().fillLoginForm(email, password);
        }
    }

    @When("user clicks on the $buttonName button")
    //переделать. Это можно делать с разных страниц..
    public void userClicksOnTheButtonNameButton(String buttonName){
        ((HomePage)ScenarioContext.context().getCurrentPage()).clickOnButton(buttonName);
    }

    @When("user clicks on the 'Log in' button on the login popup")
    public void userClicksOnTheLogInButtonOnTheLoginPopup(){
        new LoginWindow().clickOnLogInButton();
    }

    @Then("popup with login form is shown")
    public void popupWithLoginFormIsShown() {
        LoginWindow loginWindow = new LoginWindow();
        WebElement loginPopup = loginWindow.getLoginPopup();
        Assert.assertTrue("The modal window with login form is not shown", new LoginWindow(loginPopup).isShown());
    }

    @Then("user $user is logged in")
    public void userIsLoggedIn(String user) {
        Boolean loggedIn = ((HomePage)ScenarioContext.context().getCurrentPage()).isUserLoggedIn(user);
        Assert.assertTrue("The user is not logged in.", loggedIn);
    }
}
