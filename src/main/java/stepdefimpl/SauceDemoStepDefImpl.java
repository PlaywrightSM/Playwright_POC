package stepdefimpl;

import com.microsoft.playwright.*;

public class SauceDemoStepDefImpl {

    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false).setSlowMo(0));
    BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 923));

    Page page = context.newPage();
    public void navigateToSauceDemo() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void enterCredentails() {
        page.locator("[data-test=\"username\"]").click();
        page.locator("[data-test=\"username\"]").fill("standard_user");
        page.locator("[data-test=\"username\"]").press("Tab");
        page.locator("[data-test=\"password\"]").fill("secret_sauce");
        page.locator("[data-test=\"password\"]").press("Enter");
    }

    public void addToCart() {
        page.locator("[data-test=\"add-to-cart-sauce-labs-backpack\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-onesie\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]").click();
    }

    public void viewCart() {
        page.locator("a").filter(new Locator.FilterOptions().setHasText("3")).click();
    }

    public void addAdditionalItem() {
        page.locator("[data-test=\"continue-shopping\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]").click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText("4")).click();
    }

    public void removeFromCart() {
        page.locator("[data-test=\"remove-sauce-labs-onesie\"]").click();
    }

    public void checkout() {
        page.locator("[data-test=\"checkout\"]").click();
        page.locator("[data-test=\"firstName\"]").click();
        page.locator("[data-test=\"firstName\"]").fill("Subba");
        page.locator("[data-test=\"firstName\"]").press("Tab");
        page.locator("[data-test=\"lastName\"]").fill("Mettu");
        page.locator("[data-test=\"lastName\"]").press("Tab");
        page.locator("[data-test=\"postalCode\"]").fill("78068");
        page.locator("[data-test=\"continue\"]").click();
        page.locator("[data-test=\"finish\"]").click();
    }
}
