package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForCampusPage extends BasePage{
//this is the For Campus page
	
	public ForCampusPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='search-autocomplete-input']")
	WebElement searchBar;
	
	public void searchBarAction(String course) {
		searchBar.sendKeys(course);
	}
	
}
