package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.ArrayList;

// Helper class for storing course details
class CourseInfo {
    public String name;
    public String hours;
    public String rating;
    public CourseInfo(String name, String hours, String rating) {
        this.name = name;
        this.hours = hours;
        this.rating = rating;
    }
}

public class HomePage extends BasePage {
	
    // 1. Search bar to enter search query
    @FindBy(xpath = "//input[@id='search-autocomplete-input']")
    private WebElement searchBar;

    // 2. Search button
    @FindBy(xpath = "//button[contains(@class,'search-button')]")
    private WebElement searchButton;

    // 3. Filter for Beginners level 
    @FindBy(xpath = "//*[@id=\"cds-react-aria8243744419-:re:\"]")
    private WebElement levelFilter;

    // 4. Filter for English language 
    @FindBy(xpath = "//*[@id=\"cds-react-aria8345656109-:r6f:\"]")
    private WebElement languageFilter;

    // 5. Courses result card for first 2 courses (change locator to match Coursera's DOM)
    @FindBy(xpath = "(//ul[@class='cds-9 css-5t8l4v cds-10'])[1]")
    private List<WebElement> courseCards;

//    // 6. Name inside course card
//    @FindBy(xpath = "(//div[@class='course-card']//h2)")
//    private List<WebElement> courseNames;

    // 7. Learning hours inside course card
    @FindBy(xpath = "(//div[@class='course-card']//span[contains(@class,'hours')])")
    private List<WebElement> courseHours;

    // 8. Rating inside course card
    @FindBy(xpath = "(//div[@class='course-card']//span[contains(@class,'rating')])")
    private List<WebElement> courseRatings;

    // 9. Navigation links
    @FindBy(linkText = "For Enterprise")
    private WebElement forEnterpriseLink;
    
    @FindBy(linkText = "For Campus")
    private WebElement forCampusLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    // Step 1: Search "web development"

    public void searchWebDevelopmentCourses() {
        searchBar.sendKeys("web development");
        searchButton.click();

    }

    // Step 2: Select Beginner level filter
    public void selectBeginnerLevel() {
        levelFilter.sendKeys("Beginner");

    }

    // Step 3: Select English language filter
    public void selectEnglishLanguage() {
        languageFilter.sendKeys("English");

    }

    // Step 4: Extract first 2 course names, hours, ratings
    public String[][] getFirstTwoCourses() {
//        List<CourseInfo> courses = new ArrayList<>();
//        for (int i = 0; i < 2 && i < courseCards.size(); i++) {
//            String name = courseNames.get(i).getText();
//            String hours = courseHours.get(i).getText();
//            String rating = courseRatings.get(i).getText();
//            courses.add(new CourseInfo(name, hours, rating));
//        }
//        return courses;
    	
    	String[][] arr = new String[2][3];
    	for(int i=0;i<=1;i++) {
    		WebElement course = courseHours.get(i);
    		String name = driver.findElement(By.xpath("(//h3[@class='cds-CommonCard-title css-6ecy9b'])[1]")).getText();
    		arr[i][0] = name;
    		String ratings = driver.findElement(By.xpath("(//span[@class='css-4s48ix'])[1]")).getText();
    		arr[i][1] = ratings;
    		String duration = driver.findElement(By.xpath("(//p[@class='css-vac8rf'])[2]")).getText();
    		arr[i][2] = duration;
    		
    	}
    	
    	return arr;

    }

    // Step 5: Navigation to "For Enterprise"
    public void goToForEnterprise() {
        forEnterpriseLink.click();
    }

    // Step 6: Navigation to "For Campus"
    public void goToForCampus() {
        forCampusLink.click();

    }

}
 