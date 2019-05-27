package WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopListClass
{	
	//Initialize Top 250 Page objects
	public TopListClass(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public String strTopPageTitle = "IMDb \"Top 250\" (Sorted by Title Ascending)";
	public String movie_Name,movie_Year,movie_Rating;
	public int movie_count,year_count,rat;
	@FindBy(xpath = "//*[@id='main']/div")public WebElement tablemvList;
}
