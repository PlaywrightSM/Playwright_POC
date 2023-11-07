package api.testing;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MockAPI {
    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false).setSlowMo(0));
    BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 923));

    Page page = context.newPage();

    @Test
    public void mockAPI() {
        // Intercept the route to the fruit API
        page.route("https://fruit.ceo/api/breeds/image/random", route -> {
            List<Dictionary<String, Object>> data = new ArrayList<Dictionary<String, Object>>();
            Hashtable<String, Object> dict = new Hashtable<String, Object>();
            dict.put("name", "Strawberry");
            dict.put("id", 21);
            data.add(dict);
            // fulfill the route with the mock data
            route.fulfill((Route.FulfillOptions) RequestOptions.create().setData(data));
        });

// Go to the page
        page.navigate("https://demo.playwright.dev/api-mocking");

// Assert that the Strawberry fruit is visible
        assertThat(page.getByText("Strawberry")).isVisible();

    }

}
