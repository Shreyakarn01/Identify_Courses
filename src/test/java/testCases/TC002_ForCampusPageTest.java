package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ForCampusPage;
import testBase.BaseClass;

import java.util.List;

public class TC002_ForCampusPageTest extends BaseClass {

    @Test
    public void testLanguageLearningDropdown() {
        try {
            logger.info("Navigating to Language Learning section...");
            driver.findElement(By.linkText("Language Learning")).click();

            List<WebElement> languages = driver.findElements(By.xpath("//div[@class='language-option']"));
            List<WebElement> levels = driver.findElements(By.xpath("//div[@class='level-option']"));

            logger.info("Languages count: " + languages.size());
            logger.info("Levels count: " + levels.size());

            // Assertions
            Assert.assertTrue(languages.size() > 0, "Languages list should not be empty");
            Assert.assertTrue(levels.size() > 0, "Levels list should not be empty");

            for (WebElement lang : languages) {
                logger.info("Language: " + lang.getText());
            }
            for (WebElement lvl : levels) {
                logger.info("Level: " + lvl.getText());
            }

        } catch (Exception e) {
            logger.error("Error occurred in Language Learning dropdown test: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test
    public void testFormValidation() {
        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Navigating to For Enterprise...");
            homePage.goToForEnterprise();

            logger.info("Navigating to For Campus...");
            homePage.goToForCampus();

            ForCampusPage campusPage = new ForCampusPage(driver);
            logger.info("Filling form with invalid email...");
            campusPage.submitReadyToTransformForm("John","john@xyz.com","lmnopq","9783729659");

            String errorMsg = campusPage.getFormErrorMessage();
            logger.info("Captured Error Message: " + errorMsg);

            // Assertion: error message should contain "valid email"
            Assert.assertTrue(errorMsg.toLowerCase().contains("email"), 
                "Error message should mention invalid email");

        } catch (Exception e) {
            logger.error("Error occurred in Form Validation test: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
