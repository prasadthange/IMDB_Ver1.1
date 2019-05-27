package WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;

import org.openqa.selenium.JavascriptExecutor;

public class TitlePageClass 
{
	public TitlePageClass(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//JavascriptExecutor js = (JavascriptExecutor)Driver;
	@FindBy(id = "groups-2")public WebElement chkbximdb;

	@FindBy(id = "search-count")public WebElement search_count;
	
	@FindBy(xpath = "//*[@id=\"main\"]/div[25]/div/select[3]")public WebElement search_sort;

	@FindBy(xpath = "//*[@id=\"main\"]/p[3]/button")public WebElement btnSearch;
}
