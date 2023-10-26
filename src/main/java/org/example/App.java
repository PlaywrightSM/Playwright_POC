package org.example;

import com.microsoft.playwright.*;

public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            //Browser browser = playwright.firefox().launch();
            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            System.out.println(page.title());
        }
    }
}