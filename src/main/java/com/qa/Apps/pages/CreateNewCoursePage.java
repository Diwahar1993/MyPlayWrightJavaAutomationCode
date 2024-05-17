package com.qa.ulearn.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

public class CreateNewCoursePage {

    private Page page;

    //1. String Locator - OR

    //General
private String addNewCourseLabel = "//h2[normalize-space()='Add a new course']";
private String fillCourseFullName = "//input[@id='id_fullname']";
private String fillCourseShortName ="//input[@id='id_shortname']";
private String category ="//select[@id='id_category']";

private String categoryOptions = "//div[@id='fitem_id_category']//option[text()='{categoryOptions}']";

private String courseVisiblity="//select[@id='id_visible']";
private String courseIdNumber = "//input[@id='id_idnumber']";

// Descriptions
private String courseSummary="//div[@id='id_summary_editoreditable']";
private String typeOfActvity = "//label[@id='id_activitytype_label']";

//CourseFormat
    private String expandorCollapseCourseFormat = "//a[normalize-space()='Course format' and @aria-expanded='false'][1]";
    private String format = "//select[@id='id_format']";

 //Save & Display
    private String saveAndDisplayButton = "//input[@id='id_saveanddisplay']";

    //2. Page Constructor:
    public CreateNewCoursePage(Page page) {
        this.page=page;
    }


    //3. page actions/methods:

    public String getCourseLabel(){
        System.out.println("course label is "+page.textContent(addNewCourseLabel));
        return page.textContent(addNewCourseLabel);
    }


    public void fillCourseFullName(String coursename) {
        page.fill(fillCourseFullName,coursename);
    }

    public void fillCourseShortName(String courseShortName) {
        page.fill(fillCourseShortName,courseShortName);
    }

    public void selectCourseCategory(String categoryName) {
        //click category
        //select from dropdown
   /*page.click(category);
     page.click(categoryOptions.replace("{categoryOptions}",categoryName));*/
       Locator categorySelect=  page.locator(category);
        categorySelect.selectOption(new SelectOption().setLabel(categoryName));


    }


    public void selectCourseVisibility(String visiblity) {

       /* page.selectOption(courseVisiblity,"0");*/
       Locator courseVisibilitySelect = page.locator(courseVisiblity);
        courseVisibilitySelect.selectOption(new SelectOption().setLabel(visiblity));


    }

    public void fillCourseIDNumber(String idnumber) {
        page.fill(courseIdNumber,idnumber);

    }

    public void fillCourseSummary(String summary){
        page.fill(courseSummary,summary);
    }

    public boolean isCourseFormatCollapsedByDefault(){
      Locator courseFormatExpansion =  page.locator(expandorCollapseCourseFormat);
       boolean isCollapsed=  courseFormatExpansion.isVisible();
       return isCollapsed;
    }


    public void clickCourseFormat() {
        page.click(expandorCollapseCourseFormat);
    }

    public void selectTopicFormat(String singleActivityFormat) {
        Locator selectFormat = page.locator(format);
        selectFormat.selectOption(new SelectOption().setLabel(singleActivityFormat));
        page.waitForLoadState();

    }
    public boolean isTypeOfActivityDisplayed(){
        return page.locator(typeOfActvity).isVisible();
    }

    public void selectTypeOfActivity(String activityName) {
        Locator selectTypeOfActivity = page.locator(typeOfActvity);
        selectTypeOfActivity.selectOption(new SelectOption().setLabel(activityName));
    }

    public ParticipantsPage clickSaveAndDisplayButton(){
        page.click(saveAndDisplayButton);
        page.waitForLoadState();
        // this navigates  to Participants page
        System.out.println("page title after clicking on browseCourse is : "+page.title());
        return new ParticipantsPage(page);
    }
}
