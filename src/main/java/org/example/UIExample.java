package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UIExample {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(3000));
            BrowserContext context = browser.newContext();

            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page page = context.newPage();
            page.navigate("https://demo.playwright.dev/todomvc/");
            page.navigate("https://demo.playwright.dev/todomvc/#/");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example1.png")));
            page.screenshot();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("real TodoMVC app.")).click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example2.png")));
            Download download = page.waitForDownload(() -> {
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Download")).click();
            });
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example3.png")));
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Blog")).click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example4.png")));
            page.navigate("https://todomvc.com/");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example5.png")));
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View on GitHub")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("index.html")).click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example6.png")));
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Actions Actions")).click();
            page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("Operating systems and containers")).click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example7.png")));

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace2.zip")));
        }
    }
}