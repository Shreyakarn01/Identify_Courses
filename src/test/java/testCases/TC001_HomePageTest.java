package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_HomePageTest extends BaseClass {

    @Test
    public void testIdentifyCourses() {
        try {
            HomePage homePage = new HomePage(driver);

            logger.info("Searching for Web Development courses...");
            homePage.searchWebDevelopmentCourses();

            logger.info("Applying Beginner level filter...");
            homePage.beginnerLevelChk();

            logger.info("Applying English language filter...");
            homePage.selectEnglishLanguage();

            String[][] courses = homePage.getFirstTwoCourses();

            // Assertions for first two courses
//            Assert.assertNotNull(courses[0][0], "Course 1 name should not be null");
//            Assert.assertNotNull(courses[1][0], "Course 2 name should not be null");

            for (int i = 0; i < courses.length; i++) {
                logger.info("Course " + (i+1));
                logger.info("Name: " + courses[i][0]);
                logger.info("Rating: " + courses[i][1]);
                logger.info("Duration: " + courses[i][2]);

                // Example assertion: rating should not be empty
//                Assert.assertFalse(courses[i][1].isEmpty(), "Course rating should not be empty");
            }
            
//            homePage.goToForCampus();

        } catch (Exception e) {
            logger.error("Error occurred in TC001_IdentifyCourses: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
    
    
    @Test
    public void testLanguageLearningDropdown() {
        try {
        	 
        	    HomePage hp = new HomePage(driver);
        	    hp.searchWebDevelopmentCourses();
        	    
        	    hp.levelFilter.click();
        	    List<WebElement> levels = driver.findElements(By.xpath("//div[contains(@class,'cds-formGroup-groupWrapper')]//div[@class='css-ksf52d']"));
        	    logger.info("Levels count: " + levels.size());
        	    for (WebElement lvl : levels) {
                    logger.info("Level: " + lvl.getText());
                }
        	    driver.findElement(By.xpath("//span[text()='View']")).click();
        	    
        	    
        	    hp.languageFilter.click();
        	    List<WebElement> languages= driver.findElements(By.xpath("//div[contains(@class,'cds-formGroup-groupWrapper')]//div[@class='css-ksf52d']"));
        	    logger.info("Languages count: " + languages.size());
        	    for (WebElement lang : languages) {
                    logger.info("Language: " + lang.getText());
                }
        	    driver.findElement(By.xpath("//span[text()='View']")).click();



            // Assertions
            Assert.assertTrue(languages.size() > 0, "Languages list should not be empty");
            Assert.assertTrue(levels.size() > 0, "Levels list should not be empty");


        } catch (Exception e) {
            logger.error("Error occurred in Language Learning dropdown test: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
