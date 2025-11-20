package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	// Search bar to enter search query
	@FindBy(id = "search-autocomplete-input")
	private WebElement searchBar;

	// Search button
	@FindBy(xpath = "(//div[@class='magnifier-wrapper'])[2]")
	private WebElement searchButton;

	// Click level filter
	@FindBy(xpath = "(//div[@class='css-16tmax3'])[6]")
	public WebElement levelFilter;

	// Select Beginner level
	@FindBy(xpath = "(//input[@class='cds-215'])[1]")
	private WebElement beginnerChk;

	// Click language filter
	@FindBy(xpath = "(//div[@class='css-16tmax3'])[5]")
	public WebElement languageFilter;

	// Filter for English language
	@FindBy(xpath = "(//input[contains(@class,'cds')])[2]")
//	@FindBy(xpath = "//span[@class='cds-268']//input")
//	@FindBy(xpath = "//span[@class='cds-321']/input[@type='checkbox']")
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
		searchBar.sendKeys("web development");
		searchButton.click();
	}

	public void beginnerLevelChk() {
		levelFilter.click();
		beginnerChk.click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='View']")));
		viewBtn.click();
	}

	public void selectEnglishLanguage() {
		languageFilter.click();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[starts-with(@class,'cds')])[2]"))).click();

		englishChk.click();
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='View']")));
		viewBtn.click();

	}

	// Extract first 2 course names, hours, ratings
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

	//Navigation to "For Enterprise"
	public void goToForEnterprise() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		forEnterpriseLink.click();
	}

	//Navigation to "For Campus"
	public void goToForCampus() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		forCampusLink.click();

	}

}
