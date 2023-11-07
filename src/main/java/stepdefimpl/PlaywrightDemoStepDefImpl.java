package stepdefimpl;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;

public class PlaywrightDemoStepDefImpl {

    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false).setSlowMo(0));
    BrowserContext context = browser.newContext();

    Page page = context.newPage();
    private Scenario scenario;

    public PlaywrightDemoStepDefImpl(Scenario scenario) {
        this.scenario = scenario;
    }

    public void navigateToMVC() {
        page.navigate("https://demo.playwright.dev/todomvc/");
        page.navigate("https://demo.playwright.dev/todomvc/#/");
        getBytes();
    }

    public void clickMVCLink() {
        page.getByPlaceholder("What needs to be done?").click();
        page.getByPlaceholder("What needs to be done?").fill("Hello this script is to demo Playwright");
        page.getByPlaceholder("What needs to be done?").press("Enter");
        getBytes();
        page.getByLabel("Toggle Todo").check();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear completed")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("real TodoMVC app.")).click();
        getBytes();
    }

    public void clickGithubLink() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View on GitHub")).click();
        getBytes();
    }

    public void clickDownloads() {
        Download download = page.waitForDownload(() -> {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Download")).click();
        });
        getBytes();
    }

    public void clickBlog() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Blog")).click();
        getBytes();
    }

    public void navigateMVC() {
        page.navigate("https://todomvc.com/");
    }

    public void performActions() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("package.json")).click(new Locator.ClickOptions()
                .setButton(MouseButton.RIGHT));
        Page page1 = context.newPage();
        getBytes();
        page1.navigate("https://github.com/tastejs/todomvc/blob/master/package.json");
        page1.navigate("https://github.com/tastejs/todomvc/blob/master/package.json");
        page1.close();
        getBytes();
    }

    public void gitHubLogin() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.getByLabel("Username or email address").fill("Github");
        page.getByLabel("Username or email address").press("Tab");
        page.getByLabel("Password").fill("Github");
        getBytes();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in").setExact(true)).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Dismiss this message")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Create an account")).click();
        page.getByLabel("Enter your email*").fill("Github@gmail.com");
        getBytes();
    }

    private byte[] getBytes() {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "screenshot");
        return screenshot;
    }
}

