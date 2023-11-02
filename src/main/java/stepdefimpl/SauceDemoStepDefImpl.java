package stepdefimpl;

import com.microsoft.playwright.*;

import java.io.IOException;

import io.cucumber.java.Scenario;

public class SauceDemoStepDefImpl {

    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false).setSlowMo(0));
    BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 923));

    Page page = context.newPage();

    private Scenario scenario;
    public SauceDemoStepDefImpl(Scenario scenario){
        this.scenario =scenario;
    }

    // Generate a unique filename
    public void navigateToSauceDemo() throws IOException {
        page.navigate("https://www.saucedemo.com/");
        scenario.log("Hello");
        /*byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");*/
        byte[] screenshot = getBytes();
    }

    public void enterCredentails() {
        page.locator("[data-test=\"username\"]").click();
        page.locator("[data-test=\"username\"]").fill("standard_user");
        page.locator("[data-test=\"username\"]").press("Tab");
        page.locator("[data-test=\"password\"]").fill("secret_sauce");
        page.locator("[data-test=\"password\"]").press("Enter");
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
    }

    public void addToCart() {
        page.locator("[data-test=\"add-to-cart-sauce-labs-backpack\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-onesie\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]").click();
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
    }

    public void viewCart() {
        page.locator("a").filter(new Locator.FilterOptions().setHasText("3")).click();
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
    }

    public void addAdditionalItem() {
        page.locator("[data-test=\"continue-shopping\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]").click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText("4")).click();
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
    }

    public void removeFromCart() {
        page.locator("[data-test=\"remove-sauce-labs-onesie\"]").click();
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
    }

    public void checkout() {
        byte[] screenshot = getBytes();
        page.locator("[data-test=\"checkout\"]").click();
        page.locator("[data-test=\"firstName\"]").click();
        page.locator("[data-test=\"firstName\"]").fill("Subba");
        page.locator("[data-test=\"firstName\"]").press("Tab");
        page.locator("[data-test=\"lastName\"]").fill("Mettu");
        page.locator("[data-test=\"lastName\"]").press("Tab");
        page.locator("[data-test=\"postalCode\"]").fill("78068");
        page.locator("[data-test=\"continue\"]").click();
        page.locator("[data-test=\"finish\"]").click();
        scenario.attach(screenshot, "image/png", "screenshot");
    }

    private byte[] getBytes() {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
        return screenshot;
    }

}
