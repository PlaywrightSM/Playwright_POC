package org.example;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class Test1 {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      //Browser browser = playwright.webkit().launch();
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
      Page page = browser.newPage();
      page.navigate("https://playwright.dev/");
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
    }
  }
}