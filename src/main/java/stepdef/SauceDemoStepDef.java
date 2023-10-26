package stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefimpl.SauceDemoStepDefImpl;

public class SauceDemoStepDef {

    SauceDemoStepDefImpl impl = new SauceDemoStepDefImpl();
    @Given("User navigate to SauceDemo Website")
    public void userNavigateToSauceDemoWebsite() {
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
