package com.qa.ulearn.pages;

import com.microsoft.playwright.Page;

public class CourseLandingPage {

    private Page page;

    //1. String Locator - OR
    private String courseLabel = "//div[@class='course-format']";
    private String courseAdminSection = "//span[normalize-space()='Course administration']";
    private String userSection = "//span[normalize-space()='Users']";
    private String enrolmentMethod ="//a[normalize-space()='Enrollment methods']";

    //2. Page Constructor:
    public CourseLandingPage(Page page) {
        this.page=page;
    }

    //3. page actions/methods:

    public String getCourseLabel(){
        return page.textContent(courseLabel);
    }
    public void clickCourseAdminSection(){
        if(!page.isVisible(userSection)) {
            page.click(courseAdminSection);
        }
    }
    public void clickUserSection(){
        if(!page.isVisible(enrolmentMethod)) {
            page.click(userSection);
        }
    }
    public EnrollmentMethodPage clickEnrolmentMethod(){
        page.click(enrolmentMethod);
        return new EnrollmentMethodPage(page);
    }
    public String getPageTitle(){
        return page.title();
    }
}
