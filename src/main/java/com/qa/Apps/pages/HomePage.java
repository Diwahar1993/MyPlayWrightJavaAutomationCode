package com.qa.ulearn.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	//1. String Locators - OR (Object Repository)
	private String username ="//input[@id='username']";
	private String password ="//input[@id='password']";
	private String loginBtn ="//input[@id='loginbtn']";

	//2. Page Constructor:
	public HomePage(Page page) {
		this.page=page;
	}
	//3. page actions/methods:
	public String getHomePageTitle() {
		String title =page.title();
		System.out.println("page title is "+title);
		return title;
	}
	
	public String getHomePageUrl() {
		String pageUrl = page.url();
		System.out.println("page url is "+pageUrl);
		return pageUrl ;
	}
	public DashBoardPage navigateToDashBoardPage(String usrname,String pwd) {
		page.fill(username, usrname);
		page.fill(password, pwd);
		System.out.println("is login button clickable "+page.isEnabled(loginBtn));
		page.click(loginBtn);
		page.waitForLoadState();
		System.out.println("page title after clicking on login is : "+page.title());
		return new DashBoardPage(page);
		
		
	}

}
