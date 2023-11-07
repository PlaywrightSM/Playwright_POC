package com.pages;

import com.microsoft.playwright.Page;

// Example for pageobject model
public class SauceDemoCheckoutPage {

    private Page page;

    public SauceDemoCheckoutPage(Page page) {
        this.page = page;
    }

    public void enterFirstName(String firstName) {
        page.locator("[data-test=\"firstName\"]").click();
        page.locator("[data-test=\"firstName\"]").fill(firstName);
    }

    public void enterLastName(String lastName) {
        page.locator("[data-test=\"lastName\"]").fill(lastName);
    }

    public void enterPostalCode(String postalCode) {
        page.locator("[data-test=\"postalCode\"]").fill(postalCode);
    }

    public void continueCheckout() {
        page.locator("[data-test=\"continue\"]").click();
    }

    public void finishCheckout() {
        page.locator("[data-test=\"finish\"]").click();
    }

    public void startCheckout() {
        page.locator("[data-test=\"checkout\"]").click();
    }
}
