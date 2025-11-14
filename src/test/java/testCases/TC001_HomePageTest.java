package testCases;

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
            homePage.selectBeginnerLevel();

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
                Assert.assertFalse(courses[i][1].isEmpty(), "Course rating should not be empty");
            }

        } catch (Exception e) {
            logger.error("Error occurred in TC001_IdentifyCourses: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
