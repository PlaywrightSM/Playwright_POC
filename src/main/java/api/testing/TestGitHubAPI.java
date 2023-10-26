package api.testing;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestGitHubAPI {
    private static final String REPO = "test-repo-2";
    private static final String USER = "PlaywrightSM";
    private static final String API_TOKEN = "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";

    private Playwright playwright;
    private APIRequestContext request;

    void createPlaywright() {
        playwright = Playwright.create();
    }

    void createAPIRequestContext() {
        Map<String, String> headers = new HashMap<>();
        // We set this header per GitHub guidelines.
        headers.put("Accept", "application/vnd.github.v3+json");
        // Add authorization token to all requests.
        // Assuming personal access token available in the environment.
        headers.put("Authorization", "token " + API_TOKEN);

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                // All requests we send go to this API endpoint.
                .setBaseURL("https://api.github.com")
                .setExtraHTTPHeaders(headers));
    }

    void createTestRepository() {
        APIResponse newRepo = request.post("/user/repos",
                RequestOptions.create().setData(Collections.singletonMap("name", REPO)));
        assertTrue(newRepo.ok(), newRepo.text());
    }

    @BeforeAll
    void beforeAll() {
        createPlaywright();
        createAPIRequestContext();
        createTestRepository();
    }

    void deleteTestRepository() {
        if (request != null) {
            APIResponse deletedRepo = request.delete("/repos/" + USER + "/" + REPO);
            assertTrue(deletedRepo.ok());
        }
    }

    void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    @AfterAll
    void afterAll() {
        deleteTestRepository();
        disposeAPIRequestContext();
        closePlaywright();
    }

    @Test
    void shouldCreateBugReport() {
        Map<String, String> data = new HashMap<>();
        data.put("title", "[Bug] report 1");
        data.put("body", "Bug description");
        APIResponse newIssue = request.post("/repos/" + USER + "/" + REPO + "/issues",
                RequestOptions.create().setData(data));
        assertTrue(newIssue.ok());

        APIResponse issues = request.get("/repos/" + USER + "/" + REPO + "/issues");
        assertTrue(issues.ok());
        JsonArray json = new Gson().fromJson(issues.text(), JsonArray.class);
        JsonObject issue = null;
        for (JsonElement item : json) {
            JsonObject itemObj = item.getAsJsonObject();
            if (!itemObj.has("title")) {
                continue;
            }
            if ("[Bug] report 1".equals(itemObj.get("title").getAsString())) {
                issue = itemObj;
                break;
            }
        }
        assertNotNull(issue);
        assertEquals("Bug description", issue.get("body").getAsString(), issue.toString());
    }

    @Test
    void shouldCreateFeatureRequest() {
        Map<String, String> data = new HashMap<>();
        data.put("title", "[Feature] request 1");
        data.put("body", "Feature description");
        APIResponse newIssue = request.post("/repos/" + USER + "/" + REPO + "/issues",
                RequestOptions.create().setData(data));
        assertTrue(newIssue.ok());

        APIResponse issues = request.get("/repos/" + USER + "/" + REPO + "/issues");
        assertTrue(issues.ok());
        JsonArray json = new Gson().fromJson(issues.text(), JsonArray.class);
        JsonObject issue = null;
        for (JsonElement item : json) {
            JsonObject itemObj = item.getAsJsonObject();
            if (!itemObj.has("title")) {
                continue;
            }
            if ("[Feature] request 1".equals(itemObj.get("title").getAsString())) {
                issue = itemObj;
                break;
            }
        }
        assertNotNull(issue);
        assertEquals("Feature description", issue.get("body").getAsString(), issue.toString());
    }

    //The following test creates a new issue via API and then navigates to the list of all issues in the project to check that it appears at the top of the list.
    // The check is performed using LocatorAssertions.
    @Test
    void lastCreatedIssueShouldBeFirstInTheList() {
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        Map<String, String> data = new HashMap<>();
        data.put("title", "[Feature] request 1");
        data.put("body", "Feature description");
        APIResponse newIssue = request.post("/repos/" + USER + "/" + REPO + "/issues",
                RequestOptions.create().setData(data));
        assertTrue(newIssue.ok());

        page.navigate("https://github.com/" + USER + "/" + REPO + "/issues");
        Locator firstIssue = page.locator("a[data-hovercard-type='issue']").first();
        assertThat(firstIssue).hasText("[Feature] request 1");
    }

}