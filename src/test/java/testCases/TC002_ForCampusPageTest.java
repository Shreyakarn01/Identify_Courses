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
    public void testFormValidation() {
        try {
            HomePage homePage = new HomePage(driver);
//            logger.info("Navigating to For Enterprise...");
//            homePage.goToForEnterprise();
            
           

            logger.info("Navigating to For Campus...");
            homePage.goToForCampus();
            
            
            ForCampusPage campusPage = new ForCampusPage(driver);
            
            String heading = campusPage.getHeading();
            Assert.assertEquals(heading, "Coursera for Campus","User is not on the campus page");
            
            logger.info("Filling form with invalid email...");
            campusPage.submitReadyToTransformForm("John","Doe","test1@gmail.com","9876543210","University/4 Year College","XYZ","Student","Strategic Planning","Courses for myself","India","Maharashtra");

            String errorMsg = campusPage.getFormErrorMessage();
            logger.info("Captured Error Message: " + errorMsg);

            String expectedErrMsg = "Please enter your work email address";
            Assert.assertEquals(errorMsg, expectedErrMsg,"Email validation error message not correct");

        } catch (Exception e) {
            logger.error("Error occurred in Form Validation test: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
