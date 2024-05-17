package com.qa.ulearn.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class AddNewURLPage {

    private Page page;


    //1. String Locator - OR
private String addNewURLPageLabel ="//h2[normalize-space()='Adding a new URL']";

//GeneralDetails
    private String addName = "//input[@id='id_name']";
    private String addExternalURL ="//input[@id='id_externalurl']";
    private String addDescription ="//div[@id='id_introeditoreditable']";

    // Appearances
    private String appearancesSection = "(//a[normalize-space()='Appearance'])[1]";
    private String selectDisplay="//select[@id='id_display']";
    private String displayURLDescriptionCheckBox ="//input[@id='id_printintro']";

    /* Module Rating */
    private String moduleRatingSection ="(//a[normalize-space()='Module Rating'])[1]";
    private String moduleRatingCheckBox = "//input[@id='id_modrating']";
    private String saveAndDisplayButton = "//input[@id='id_saveanddisplay']";

    /*
    Activity Completion

     */
    private String activityCompletionSection ="(//a[normalize-space()='Activity completion'])[1]";
    private String selectCompletionTracking = "//select[@id='id_completion']";

    //save
    private String saveAndDisplay = "//input[@id='id_saveanddisplay']";
    //2. Page Constructor:
    public AddNewURLPage(Page page) {
        this.page = page;
    }


    //3. page actions/methods:

    public String getPageTitle(){
        System.out.println("Page Title is : "+page.title());
        return page.title();
    }

    public boolean isAddNewURLPageLabelVisible(){
        return page.isVisible(addNewURLPageLabel);
    }

    public String getAddNewURLPageLabel(){
        return page.textContent(addNewURLPageLabel).trim();
    }

    public void enterName(String urlName){
        page.fill(addName,urlName);
    }
    public void enterURL(String url){
        page.fill(addExternalURL,url);
    }
    public void enterDescription(String description){
        page.fill(addDescription,description);
    }

    public boolean isAppearanceSectionExpanded(){
        String isExpanded = page.getAttribute(appearancesSection,"aria-expanded");
        System.out.println("is appearance Section Expanded ?? "+isExpanded);
        if(isExpanded.equalsIgnoreCase("true")){
            return true;
        }else{
            return false;
        }

    }
    public void clickAppearanceSection(){
        page.click(appearancesSection);
    }
    public boolean isDisplayURLDescriptionChecked(){
       String isdisplayChecked = page.getAttribute(displayURLDescriptionCheckBox,"value");
       if(isdisplayChecked.equalsIgnoreCase("1")){
           return true;
       }else {
           return false;
       }


    }
    public void clickDisplayURLDescriptioncheckBox(){
page.click(displayURLDescriptionCheckBox);
    }

public void setSelectDisplay(String displayType){
    Locator selectTypeOfDisplay = page.locator(selectDisplay);
    selectTypeOfDisplay.selectOption(new SelectOption().setLabel(displayType));
}
public boolean isModuleRatingSectionExpanded(){
        String isExpanded = page.getAttribute(moduleRatingSection,"aria-expanded");
    System.out.println("is moduleRating Section Expanded ?? "+isExpanded);
        if(isExpanded.equalsIgnoreCase("true")){
            return true;
        }else{
           return false;
        }
}

public void clickModuleRatingSection(){
        page.click(moduleRatingSection);
}

    public CourseLandingPage clickSaveAndDisplay() {
        page.waitForLoadState();
        page.click(saveAndDisplayButton);
        return new CourseLandingPage(page);

    }
}
