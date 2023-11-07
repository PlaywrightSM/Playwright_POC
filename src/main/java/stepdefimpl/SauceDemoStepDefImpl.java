package stepdefimpl;

import com.microsoft.playwright.*;

import java.io.IOException;

import com.pages.SauceDemoCheckoutPage;
import io.cucumber.java.Scenario;

public class SauceDemoStepDefImpl {

    private Scenario scenario;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private SauceDemoCheckoutPage checkoutPage;

    public SauceDemoStepDefImpl(Scenario scenario) {
        this.scenario = scenario;
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(0));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 923));
        page = context.newPage();
        checkoutPage = new SauceDemoCheckoutPage(page);
    }

    // Generate a unique filename
    public void navigateToSauceDemo() throws IOException {
        page.navigate("https://www.saucedemo.com/");
        scenario.log("Demo of Playwright");
        getBytes();
    }

    public void enterCredentails() {
        page.locator("[data-test=\"username\"]").click();
        page.locator("[data-test=\"username\"]").fill("standard_user");
        page.locator("[data-test=\"username\"]").press("Tab");
        page.locator("[data-test=\"password\"]").fill("secret_sauce");
        page.locator("[data-test=\"password\"]").press("Enter");
        getBytes();
    }

    public void addToCart() {
        page.locator("[data-test=\"add-to-cart-sauce-labs-backpack\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-onesie\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]").click();
        getBytes();
    }

    public void viewCart() {
        page.locator("a").filter(new Locator.FilterOptions().setHasText("3")).click();
        getBytes();
    }

    public void addAdditionalItem() {
        page.locator("[data-test=\"continue-shopping\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]").click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText("4")).click();
        getBytes();
    }

    public void removeFromCart() {
        page.locator("[data-test=\"remove-sauce-labs-onesie\"]").click();
        getBytes();
    }

    // Example for page object model.
    public void checkout() {
        getBytes();
        checkoutPage.startCheckout();
        checkoutPage.enterFirstName("firstName");
        checkoutPage.enterLastName("lastName");
        checkoutPage.enterPostalCode("20171");
        getBytes();
        checkoutPage.continueCheckout();
        checkoutPage.finishCheckout();
        getBytes();
    }

    private byte[] getBytes() {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
        return screenshot;
    }

}
