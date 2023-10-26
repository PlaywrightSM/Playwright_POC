package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import java.util.*;

public class UIFannieMaePage {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(2000));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920,923));
            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            Page page = context.newPage();
            page.navigate("https://www.fanniemae.com/");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Business Partners")).click();
            page.getByLabel("Consumer Global Navigation").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Single-Family Business")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Originating & Underwriting")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Pricing & Execution")).click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Whole Loan")).click();
            page.getByTitle("Pricing & Execution Whole Loan").click();
            page.navigate("https://singlefamily.fanniemae.com/pricing-execution");
            page.getByTitle("Pricing & Execution - MBS").click();
            page.navigate("https://singlefamily.fanniemae.com/pricing-execution");
            page.getByTitle("MBS Trading Portal ").click();
            Page page1 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Launch App")).click();
            });
            page1.close();
            page.navigate("https://singlefamily.fanniemae.com/pricing-execution");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Need help with unlocking your user ID or resetting your password?")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Navigate to acceptance/integration environment")).click();

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace3.zip")));
        }
    }
}