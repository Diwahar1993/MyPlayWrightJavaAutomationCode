package com.qa.Apps.tests;

import com.qa.Apps.base.BaseTest;
import net.sourceforge.tess4j.TesseractException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class IRCTCLoginPageTest extends BaseTest {
    @Test
    public void homePageTitleTest() throws TesseractException, IOException {
        String actualTitle = irctcLoginPage.getHomePageTitle();
        irctcLoginPage.clickLoginButton();
        Assert.assertEquals(actualTitle, "IRCTC Next Generation eTicketing System");
        String loginName = irctcLoginPage.loginAsUser("Diwahar93", "@Diwa1993");

        Assert.assertEquals(loginName, "Welcome Diwahar Pandian (Diwahar93)");
        // choose origin
        //choose destination
        // select tatkal from dropdown



    }
}
