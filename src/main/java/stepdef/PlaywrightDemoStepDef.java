package stepdef;

import common.SharedDataUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefimpl.PlaywrightDemoStepDefImpl;

public class PlaywrightDemoStepDef {

    PlaywrightDemoStepDefImpl impl = new PlaywrightDemoStepDefImpl(SharedDataUtil.getScenario());

    @Given("User navigates to Playwright Demo Website")
    public void userNavigatesToPlaywrightDemoWebsite() {
        impl.navigateToMVC();
    }

    @When("User clicks on MVC Link")
    public void userClicksOnMVCLink() {
        impl.clickMVCLink();
    }

    @Then("User clicks on Github Link")
    public void userClicksOnGithubLink() {
        impl.clickGithubLink();
    }

    @And("User clicks on Downloads button")
    public void userClickOnDownloadsButton() {
        impl.clickDownloads();
    }

    @And("User clicks on Blog button")
    public void userClicksOnBlogButton() {
        impl.clickBlog();
    }

    @And("User navigates back to MVC page")
    public void userNavigatesBackToMVCPage() {
        impl.navigateMVC();
    }

    @And("User performs certain actions on the Master Repo")
    public void userPerformsCertainActionsOnTheMasterRepo() {
        impl.performActions();
    }

    @And("User login into Github account")
    public void userLoginIntoGithubAccount() {
        impl.gitHubLogin();
    }
}
