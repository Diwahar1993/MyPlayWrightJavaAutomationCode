package com.qa.ulearn.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ParticipantsPage {
    private Page page;

    //1. String Locator - OR
    private String ParticipantsLabel ="//h2[normalize-space()='Participants']";
    private String enrollUsersButton ="(//input[@value='Enroll users'])[1]";
    private String enrollUserWindowLabel = "//h5[@id='0-modal-title']";
    private String enrollUserWindow ="//div[@class='modal-content']";

private String enrollmentOptions ="//legend[@class='ftoggler']";
private String proceedToCourseButton ="(//button[normalize-space()='Proceed to course content'])[1]";



    //2. Page Constructor:
    public ParticipantsPage(Page page) {
        this.page=page;
    }



    //3. page actions/methods:
public String getPageTitle(){
    System.out.println("Page Title is : "+page.title());
        return page.title();
}

public void clickEnrollUserButton(){
        page.click(enrollUsersButton);
    Locator enrollmentWindow = page.locator(enrollUserWindow);
    enrollmentWindow.page().waitForLoadState();

}

    public boolean isEnrollUserLabelVisible() {
        return  page.isVisible(enrollUserWindowLabel);
    }
    public boolean iselementinEnrollmentOptionsVisible(){
        page.waitForSelector(enrollmentOptions);
        return page.locator(enrollmentOptions).isVisible();
    }

    public AddNewURLPage clickOnProceedToCourseButton(){
        page.click(proceedToCourseButton);
        page.waitForLoadState();
        return new AddNewURLPage(page);
    }
}
