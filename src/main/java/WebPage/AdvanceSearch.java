package WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvanceSearch 
	{
		public AdvanceSearch(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
		}
		@FindBy(xpath = "//*[@id=\"main\"]/div[2]/div[1]/a")public WebElement advSearchLink;
	}
