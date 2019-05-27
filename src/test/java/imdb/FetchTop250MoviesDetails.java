
package imdb;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import DB.DBConnect;
import WebPage.AdvanceSearch;
import WebPage.HomePageClass;
import WebPage.TopListClass;
import GlobalVariables.GlobalVarClass;
import WebPage.TitlePageClass;

@Test
public class FetchTop250MoviesDetails 
{
	@BeforeTest
	public void droptable() throws SQLException
	{
		
		objGlobal.strQuery = "DELETE FROM IMDB_List";
		objDBConnect.Connect();
		objDBConnect.exeQuery(objGlobal.strQuery);
		System.out.println("Records Deleted");
	}
	//Class Variables from each page
	public WebDriver driver = new ChromeDriver();
	HomePageClass objHomeClass = new HomePageClass(driver);
	TopListClass objTopListClass = new TopListClass(driver);
	GlobalVarClass objGlobal = new GlobalVarClass();
	DBConnect objDBConnect = new DBConnect();
	AdvanceSearch objAdvSearch = new AdvanceSearch(driver);
	TitlePageClass objTitle = new TitlePageClass(driver);
	TitlePageClass objsearch_count = new TitlePageClass(driver);
	TitlePageClass objPerPageList = new TitlePageClass(driver);
	TitlePageClass objSearchSort = new TitlePageClass(driver);
	TitlePageClass objBtnSearch = new TitlePageClass(driver);	
		
	//Opens the IMDB Web site final page
	public void GetURL() 
	{
		driver.manage().window().maximize();
		driver.get(objHomeClass.URL);
	}
	//Navigate to Top 250 Page(final Page)
	@Test(dependsOnMethods = ("GetURL"))
	public void NavigateInIMDB() throws InterruptedException
	{
		objHomeClass.drpsearchlist.click();
		objHomeClass.advSearchTab.click();
		objAdvSearch.advSearchLink.click();								
		//Thread.sleep(1000);		
		JavascriptExecutor js = (JavascriptExecutor)driver;						// using javascript executor helper class	
		js.executeScript("window.scrollBy(0,800)");								//scrolling current window till get checkbox by pixel
		objTitle.chkbximdb.click();
		//Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,3000)");							//scrolling current window till get checkbox by pixel
		objsearch_count.search_count.click();
		Select objSelect = new Select(driver.findElement(By.id("search-count")));
		objSelect.selectByVisibleText("250 per page");
		objPerPageList.search_sort.click();
		Select objSort = new Select(driver.findElement(By.xpath("//*[@id=\"main\"]/div[25]/div/select[3]")));				//xpath for sorting dropdownlist
		objSort.selectByVisibleText("A-Z Ascending");
		objSearchSort.search_sort.click();
		objBtnSearch.btnSearch.click();											//click on search button for list of movies
	}	
	//Connect to the database and fetch the data from the Web Page
	@Test(dependsOnMethods = {"GetURL","NavigateInIMDB"})
	public void FetchData() throws SQLException
	{
		objDBConnect.Connect();
		System.out.println("Creating database");	
		//for loop to find required the child objects
	      for (objTopListClass.year_count = 0;objTopListClass.year_count< 250;objTopListClass.year_count++)
	      {		
	    	    //Find the All elements of Movies																
		    	objTopListClass.movie_Name = objTopListClass.tablemvList.findElements(By.xpath("//div[@class='lister-list']//div//h3//a")).get(objTopListClass.movie_count).getText();
	    	   If(objTopListClass.movie_Name.contains("'"));
	    	   {	//if Movie has ' in between words
	    	   		objTopListClass.movie_Name = objTopListClass.movie_Name.replaceAll("'", "''");							
	    	   }	       	   	    	   
	    	   	//Find Movie Year and remove the brackets
		    	objTopListClass.movie_Year = objTopListClass.tablemvList.findElements(By.xpath("//div[@class='lister-list']//div//span[contains(@class,'lister-item-year')]")).get(objTopListClass.year_count).getText();
		    	objTopListClass.movie_Year = objTopListClass.movie_Year.substring(1,5);										
		    	//Find the Movie Ratings
	    	  	objTopListClass.movie_Rating = objTopListClass.tablemvList.findElements(By.name("ir")).get(objTopListClass.year_count).getText();   	  	
		    	//Update the SQLite database with the all the result Fetching all the records
	    	  		objGlobal.strQuery = "INSERT INTO IMDB_List (Sr_No,Movie_Name,Movie_Year,Movie_Rating) " +
			               "VALUES ("+(objTopListClass.year_count+1)+",'"+objTopListClass.movie_Name+"','"+objTopListClass.movie_Year+"','"+objTopListClass.movie_Rating+"')"; 
		    		
					objDBConnect.exeQuery(objGlobal.strQuery);
					objTopListClass.movie_count=objTopListClass.movie_count+1;					
	    	 }
	}
	private void If(boolean contains){}	
	@AfterTest
    public void closeBrowser(){
        driver.close();
    }
}