package com.pages;

import com.microsoft.playwright.Page;

// Example for pageobject model
public class SauceDemoLoginPage {

    private Page page;

    public SauceDemoLoginPage(Page page) {
        this.page = page;
    }

    public void enterUsername(String username) {
        page.locator("[data-test=\"username\"]").click();
        page.locator("[data-test=\"username\"]").fill(username);
    }

    public void enterPassword(String password) {
        page.locator("[data-test=\"password\"]").fill(password);
    }

    public void clickLoginButton() {
        page.locator("[data-test=\"password\"]").press("Enter");
    }
}
