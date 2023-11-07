package stepdef;

import common.SharedDataUtil;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import stepdefimpl.SauceDemoStepDefImpl;
import java.io.IOException;
import io.cucumber.java.Scenario;


public class SauceDemoStepDef {

    @Before(order = 1)
    public void before(Scenario scenario) {
        // Pass the scenario object to the class
        SharedDataUtil.setScenario(scenario);
        impl = new SauceDemoStepDefImpl(SharedDataUtil.getScenario());
    }

    SauceDemoStepDefImpl impl = new SauceDemoStepDefImpl(SharedDataUtil.getScenario());
    @Given("User navigate to SauceDemo Website")
    public void userNavigateToSauceDemoWebsite() throws IOException {
        impl.navigateToSauceDemo();
    }

    @When("User enters the Username and password")
    public void userEntersTheUsernameAndPassword() {
        impl.enterCredentails();
    }

    @And("User adds the items into the cart")
    public void userAddsTheItemsIntoTheCart() {
        impl.addToCart();
    }

    @And("User clicks on view cart button")
    public void userClicksOnViewCartButton() {
        impl.viewCart();
    }

    @And("User clicks on continue shopping and additional item")
    public void userClicksOnContinueShoppingAndAdditionalItem() {
        impl.addAdditionalItem();
    }

    @And("User removes item from the cart")
    public void userRemovesItemFromTheCart() {
        impl.removeFromCart();
    }

    @And("User Checkout the cart items and place and order")
    public void userCheckoutTheCartItems() {
        impl.checkout();
    }

}
