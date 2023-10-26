package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UIExampleTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://demo.playwright.dev/todomvc/");
            page.navigate("https://demo.playwright.dev/todomvc/#/");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("real TodoMVC app.")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View on GitHub")).click();
            page.getByTitle("Switch branches or tags").click();
            page.getByPlaceholder("Filter branches/tags").fill("cypress-");
            page.getByPlaceholder("Filter branches/tags").press("Enter");
            page.getByRole(AriaRole.MENUITEMRADIO, new Page.GetByRoleOptions().setName("cypress-test-suite")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sam Saccone")).click();
            page.navigate("https://github.com/tastejs/todomvc/tree/cypress-test-suite");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("examples").setExact(true)).click();
            page.getByText("tsconfig.json").click();
            page.getByLabel("file content").click();
            page.getByLabel("file content").dblclick();
            Download download = page.waitForDownload(() -> {
                page.getByTestId("download-raw-button").click();
            });
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
            page.getByLabel("Username or email address").click();
            page.getByLabel("Username or email address").fill("GitHub");
            page.getByLabel("Username or email address").press("Tab");
            page.getByLabel("Password").fill("GitHub");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in").setExact(true)).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Forgot password?")).click();
        }
    }
}