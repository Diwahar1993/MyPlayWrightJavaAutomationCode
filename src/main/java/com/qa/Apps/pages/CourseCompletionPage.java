package com.qa.ulearn.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class CourseCompletionPage {
    private Page page;
    //1. Selectors
private String selectCompletionRequirement = "//select[@id='id_overall_aggregation']";
private String saveAndDisplayButton = "//input[@id='id_saveanddisplay']";
private String conditionActivityCompletionSection = "(//a[normalize-space()='Condition: Activity completion'])[1]";
private String selectAllOrNone = "(//a[normalize-space()='Select all/none'])[1]";
private String profileAvatar = "//span[@class='avatar current']";
private String logOutButton ="//i[@class='icon fa fa-sign-out fa-fw ']";


    //2. constructor
    public CourseCompletionPage(Page page) {
        this.page = page;
    }


    //3. Page Actions/methods
    public String getTitle(){
        return page.title();
    }

    public void setSelectCompletionRequirement(String value){
        Locator selectComletionType = page.locator(selectCompletionRequirement);
        selectComletionType.selectOption(new SelectOption().setLabel(value));
    }

    public void clickSaveAndDisplay(){
        page.click(saveAndDisplayButton);
        page.waitForLoadState();
    }

    public void selectAllCourseForCompletion(){
        if(!page.isVisible(selectAllOrNone)){
            page.click(conditionActivityCompletionSection);
            page.click(selectAllOrNone);
        }else{
            page.click(selectAllOrNone);
        }
    }
    public HomePage clicklogOut(String url){
        page.click(profileAvatar);
        page.waitForLoadState();
        page.waitForSelector(logOutButton);
        page.click(logOutButton);
        page.waitForLoadState();
        page.navigate(url);
        page.waitForLoadState();
        System.out.println("After logging out :"+page.title());
        return new HomePage(page);
    }
}
