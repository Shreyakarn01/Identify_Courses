package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pageObjects.ForCampusPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ExcelUtility;

public class TC_Coursera extends BaseClass {

	@Test
	public void testIdentifyCourses() {
		try {
			HomePage homePage = new HomePage(driver);

			logger.info("Searching for Web Development courses...");
			test.log(Status.INFO,"Searching for Web Development courses...");
			homePage.searchWebDevelopmentCourses();

			logger.info("Applying Beginner level filter...");
			test.log(Status.INFO,"Applying Beginner level filter...");
			homePage.levelClick();

			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			List<WebElement> levels = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'cds-formGroup-groupWrapper')]//div[@class='css-ksf52d']")));
			Assert.assertTrue(levels.size()>0,"Different levels not extracted");
			logger.info("Levels count: " + levels.size());
			test.log(Status.PASS,"Total levels counted = "+levels.size());
			
			
			System.out.println("Levels count: " + levels.size());
			for (WebElement lvl : levels) {
				logger.info("Level: " + lvl.getText());
				System.out.println("Level: " + lvl.getText());
			}
			
			
			homePage.beginnerLevelChk();
			homePage.viewBtnClick();

			
			logger.info("Applying English language filter...");
			test.log(Status.INFO,"Applying English language filter...");
			homePage.languageClick();
			
			
			List<WebElement> languages = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'cds-formGroup-groupWrapper')]//div[@class='css-ksf52d']")));
			Assert.assertTrue(languages.size()>0,"Different languages not extracted");
			logger.info("Languages count: " + languages.size());
			test.log(Status.PASS,"Total languages counted = "+languages.size());
			
			
			System.out.println("Languages count: " + languages.size());
			for (WebElement lang : languages) {
				logger.info("Language: " + lang.getText());
				System.out.println("Language: " + lang.getText());
			}
			
			
			homePage.selectEnglishLanguage();
			homePage.viewBtnClick();
			

			String[][] courses = homePage.getFirstTwoCourses();
			Assert.assertTrue(courses.length==2,"First 2 courses are not extracted");

			for (int i = 0; i < courses.length; i++) {
				logger.info("Course " + (i + 1));
				logger.info("Name: " + courses[i][0]);
				logger.info("Rating: " + courses[i][1]);
				logger.info("Duration: " + courses[i][2]);
				System.out.println("Course " + (i + 1));
				System.out.println("Name: " + courses[i][0]);
				System.out.println("Rating: " + courses[i][1]);
				System.out.println("Duration: " + courses[i][2]);
			}
			test.log(Status.PASS,"First 2 courses name,rating and duration extracted");
			
			logger.info("Navigating to For Campus...");
			test.log(Status.INFO,"Navigating to For Campus...");
            homePage.goToForCampus();
            
            
            ForCampusPage campusPage = new ForCampusPage(driver);
            
            String heading = campusPage.getHeading();
            test.log(Status.INFO, "Captured heading: " + heading);
            Assert.assertEquals(heading, "Coursera for Campus","User is not on the campus page");
            test.log(Status.PASS, "Successfully navigated to Coursera for Campus page");
            
            logger.info("Filling form with invalid email...");
            test.log(Status.INFO, "Filling form with invalid email...");
            ExcelUtility eu = new ExcelUtility();
            String[][] data = eu.getInputValues();
            
            campusPage.submitReadyToTransformForm(data[0][0],data[1][0],data[2][0],data[3][0],data[4][0],data[5][0],data[6][0],data[7][0],data[8][0],data[9][0],data[10][0]);

            String errorMsg = campusPage.getFormErrorMessage();

            test.log(Status.INFO, "Captured Error Message: " + errorMsg);

            logger.info("Captured Error Message: " + errorMsg);
            System.out.println("Captured Error Message: " + errorMsg);

            String expectedErrMsg = "Please enter your work email address";
            Assert.assertEquals(errorMsg, expectedErrMsg,"Email validation error message not correct");
            test.log(Status.PASS, "Error message displayed successfully");

		} catch (Exception e) {
//			test.log(Status.FAIL,"Test failed due to exception: " + e.getMessage());
			logger.error("Error occurred in TC001_IdentifyCourses: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}
}
