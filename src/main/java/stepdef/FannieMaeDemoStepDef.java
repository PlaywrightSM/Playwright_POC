package stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefimpl.FannieMaeDemoStepDefImpl;

public class FannieMaeDemoStepDef {

    FannieMaeDemoStepDefImpl impl = new FannieMaeDemoStepDefImpl();
    @Given("User navigates to FannieMae Website")
    public void userNavigatesToFannieMaeWebsite() {
        impl.navigateToFannie();
    }

    @When("User click on Business Partners and selects Single-Family")
    public void userClickOnBusinessPartnersAndSelectsSingleFamily() {
        impl.selectSingleFamily();
    }

    @Then("User views Underwriting Page")
    public void userViewsUnderwritingPage() {
        impl.viewUnderwriting();
    }

    @And("User navigates to Pricing")
    public void userNavigatesToPricing() {
        impl.navigateToPricing();
    }

    @And("User login into Pricing App")
    public void userLoginIntoPricingApp() {
        impl.loginPricingApp();
    }
}
