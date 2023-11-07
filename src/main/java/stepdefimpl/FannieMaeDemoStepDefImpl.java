package stepdefimpl;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.Scenario;

public class FannieMaeDemoStepDefImpl {

    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false).setSlowMo(0));
    BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 923));

    Page page = context.newPage();
    private Scenario scenario;

    public FannieMaeDemoStepDefImpl(Scenario scenario) {
        this.scenario = scenario;
    }

    public void navigateToFannie() {
        page.navigate("https://www.fanniemae.com/");
        getBytes();
    }

    public void selectSingleFamily() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Business Partners")).click();
        page.getByLabel("Consumer Global Navigation").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Single-Family Business")).click();
        getBytes();
    }

    public void viewUnderwriting() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Originating & Underwriting")).click();
        getBytes();
    }

    public void navigateToPricing() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Pricing & Execution")).click();
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Whole Loan")).click();
        page.getByTitle("Pricing & Execution Whole Loan").click();
        getBytes();
        page.navigate("https://singlefamily.fanniemae.com/pricing-execution");
        page.getByTitle("Pricing & Execution - MBS").click();
        page.navigate("https://singlefamily.fanniemae.com/pricing-execution");
        page.getByTitle("MBS Trading Portal ").click();
        getBytes();
        Page page1 = page.waitForPopup(() -> {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Launch App")).click();
        });
        page1.close();
        page.navigate("https://singlefamily.fanniemae.com/pricing-execution");
        getBytes();
    }

    public void loginPricingApp() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Need help with unlocking your user ID or resetting your password?")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Navigate to acceptance/integration environment")).click();
        getBytes();
    }

    private byte[] getBytes() {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
        return screenshot;
    }
}
