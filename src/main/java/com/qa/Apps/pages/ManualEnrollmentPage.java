package com.qa.ulearn.pages;

import com.microsoft.playwright.Page;

public class ManualEnrollmentPage {
    private Page page;
    // locators
private String addSearch="//input[@id='addselect_searchtext']";
private String matchingUser = "//option[contains(text(), '{firstName}')]";
private String addButton ="//input[@id='add']";
private String courseCompletion = "//a[normalize-space()='Course completion']";


    //constructors
    public ManualEnrollmentPage(Page page) {
        this.page=page;
    }

    //actions
    public void searchUser(String name){
        page.fill(addSearch,name);
        page.press(addSearch,"Enter");
    }

    public String selectMatchingUserWithNameAndEmailId(String firstName, String lastName, String email) {
        //verify if the first name match is visible
        boolean isVisible = page.isVisible(matchingUser.replace("{firstName}",firstName));
        //if visible get the text atribute and see if the email matches
       String matchingContent =  page.textContent(matchingUser.replace("{firstName}",firstName));
       if(matchingContent.contains(email)){
          page.click( matchingUser.replace("{firstName}",firstName));
          page.click(addButton);
          page.waitForLoadState();
          return matchingContent;
       }
       else {
           return "no content matched";
       }

    }
    public CourseCompletionPage clickOnCourseCompletion(){
        page.click(courseCompletion);
        page.waitForLoadState();
        return new CourseCompletionPage(page);
    }
}
