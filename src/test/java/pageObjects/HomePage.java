package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	@FindBy(id = "search-autocomplete-input")
	private WebElement searchBar;
	
	@FindBy(xpath = "//div[text()='Level']")
	public WebElement levelFilter;

	@FindBy(xpath = "(//input[@class='cds-215'])[1]")
	private WebElement beginnerChk;
	
	@FindBy(xpath = "//div[text()='Language']")
	public WebElement languageFilter;

	@FindBy(xpath = "(//input[contains(@class,'cds')])[2]")
	private WebElement englishChk;

	@FindBy(xpath = "//span[text()='View']")
	public WebElement viewBtn;

	@FindBy(linkText = "For Enterprise")
	private WebElement forEnterpriseLink;

	@FindBy(linkText = "For Campus")
	private WebElement forCampusLink;

	public HomePage(WebDriver driver) {
		super(driver);

	}
	
	

	public void searchWebDevelopmentCourses() {
		searchBar.sendKeys("web development",Keys.ENTER);
	}
	
	
	public void levelClick() {
//        List<WebElement> lt = driver.findElements(By.xpath("//div[@class='css-16tmax3']"));
//		
//		if(lt.size()==12) levelFilter1.click();
//		else levelFilter2.click();
		
		levelFilter.click();
		}
	

	public void beginnerLevelChk() {
		beginnerChk.click();
		
	}
	
	
	public void viewBtnClick() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='View']")));
		viewBtn.click();
	}
	
	
	public void languageClick() {
		languageFilter.click();
	}
	

	public void selectEnglishLanguage() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@class,'cds')])[2]"))).click();
		englishChk.click();
	}
	

	public String[][] getFirstTwoCourses() {

		String[][] arr = new String[2][3];
		for (int i = 0; i <= 1; i++) {

			String name = driver.findElement(By.xpath("(//h3[@class='cds-CommonCard-title css-6ecy9b'])[" + (i + 1) + "]")).getText();
			arr[i][0] = name;
			String ratings = driver.findElement(By.xpath("(//span[@class='css-4s48ix'])[" + (i + 1) + "]")).getText();
			arr[i][1] = ratings;
			String duration = null;
			if (i == 0) {
				duration = driver.findElement(By.xpath("(//p[@class='css-vac8rf'])[2]")).getText();
			} else if (i == 1) {
				duration = driver.findElement(By.xpath("(//p[@class='css-vac8rf'])[4]")).getText();
			}

			arr[i][2] = duration;
		}

		return arr;
	}

	
	public void goToForEnterprise() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		forEnterpriseLink.click();
	}

	public void goToForCampus() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		forCampusLink.click();

	}

}
