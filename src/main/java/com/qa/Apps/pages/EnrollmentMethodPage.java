package com.qa.ulearn.pages;

import com.microsoft.playwright.Page;

public class EnrollmentMethodPage {
    private Page page;

    // 1. Locators
    private String enrollUserButton ="//i[@title='Enroll users']";

    //2 page constructor
    public EnrollmentMethodPage(Page page) {
        this.page=page;
    }

    // 3 actions or methods

    public String getPageTitle(){
        page.waitForLoadState();
        return page.title();
    }
    public ManualEnrollmentPage clickEnrollUserButton(){
        page.click(enrollUserButton);
        page.waitForLoadState();
        return new ManualEnrollmentPage(page);
    }
}
