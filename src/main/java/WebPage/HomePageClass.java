package WebPage;

import org.openqa.selenium.JavascriptExecutor;

//This file consists of all the variables/web elements used for Home page of IMDB


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.yaml.snakeyaml.events.Event.ID;

import bsh.This;

public class HomePageClass
{	
	//Initialize objects in the Page
	public HomePageClass(WebDriver driver)
	{	
		PageFactory.initElements(driver, this);		
	}
		public String URL = "http://www.imdb.com";
	//	public String URL = "https://www.imdb.com/search/title?groups=top_250&sort=alpha,asc&count=250";
		@FindBy(id = "quicksearch")public WebElement drpsearchlist;	
		@FindBy(className = "advancedSearch")public WebElement advSearchTab;
}
