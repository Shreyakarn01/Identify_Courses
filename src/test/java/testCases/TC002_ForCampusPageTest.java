package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ForCampusPage;
import testBase.BaseClass;
import utilities.ExcelUtility;


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
            ExcelUtility eu = new ExcelUtility();
            String[][] data = eu.getInputValues();
            
            campusPage.submitReadyToTransformForm(data[0][0],data[1][0],data[2][0],data[3][0],data[4][0],data[5][0],data[6][0],data[7][0],data[8][0],data[9][0],data[10][0]);

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
