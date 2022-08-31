import base.TestBase;
import data.TestData;
import lib.AssertLib;
import lib.DriverLib;
import model.Block;
import model.Driver;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import page.CareerOpenPositionPage;
import page.CareerPage;
import page.CareerQualityAssurancePage;
import page.HomePage;

import java.util.ArrayList;

public class ChallengeTest extends TestBase {

    @Test(dataProvider = "driverData", dataProviderClass = TestData.class)
    @DisplayName("Page excepted to opened")
    public void homePageOpenTest(Driver driverName) {
        driver = DriverLib.getWebDriver(driverName);
        driver.get(HomePage.HOME_PAGE_URL);
        AssertLib.equals(this.driver, driver.getTitle(), "Insider personalization engine for seamless customer experiences");
    }

    @Test(dataProvider = "driverData", dataProviderClass = TestData.class)
    @DisplayName("Locations, Teams and Life at Insider blocks are excepted to opened")
    public void careersAndLifeBlockOpenTest(Driver driverName) {
        driver = DriverLib.getWebDriver(driverName);
        driver.get(HomePage.HOME_PAGE_URL);
        homePage = new HomePage(driver);
        CareerPage careerPage = new CareerPage(driver);
        homePage.acceptCookies();
        homePage.gotoCareers();
        careerPage.gotoLocation();
        AssertLib.equals(this.driver, careerPage.getBlockLocation()
                                                .isDisplayed(), true);
        AssertLib.equals(this.driver, careerPage.getLocationList()
                                                .size() > 0, true);
        careerPage.gotoLifeAtInsider();
        AssertLib.equals(this.driver, careerPage.getBlockLifeAtInsider()
                                                .isDisplayed(), true);
        AssertLib.equals(this.driver, careerPage.getBlockLifeAtInsiderPhotos()
                                                .size() > 0, true);
    }

    @Test(dataProvider = "driverAndJobListData", dataProviderClass = TestData.class)
    @DisplayName("Job search")
    public void checkPresenceOfJobList(Driver driverName, String location, String jobPosition) {
        driver = DriverLib.getWebDriver(driverName);
        driver.get(HomePage.HOME_PAGE_URL);
        homePage = new HomePage(driver);
        careerPage = new CareerPage(driver);
        CareerQualityAssurancePage qualityAssurancePage = new CareerQualityAssurancePage(driver);
        CareerOpenPositionPage openPositionPage = new CareerOpenPositionPage(driver);
        homePage.acceptCookies();
        homePage.gotoCareers();
        careerPage.gotoSeeAllTeam();
        careerPage.gotoQualityAssurance();
        qualityAssurancePage.gotoAllQaJobs();
        openPositionPage.setLocation(location);
        openPositionPage.setDepartment(jobPosition);
        AssertLib.equals(driver, openPositionPage.getJobs()
                                                 .size() > 0, true);
        DriverLib.scrollIntoView(driver, openPositionPage.getJobs()
                                                         .get(0), Block.center);
        for (WebElement el : openPositionPage.getJobs()) {
            AssertLib.equals(driver, openPositionPage.getJobTitle(el), jobPosition);
            AssertLib.equals(driver, openPositionPage.getJobLocation(el), location);
        }
        for (WebElement el : openPositionPage.getJobs()) {
            openPositionPage.applyJob(el);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo()
                  .window(tabs.get(1));
            String url = driver.getCurrentUrl();
            AssertLib.equals(driver, url.contains("jobs.lever.co"), true);
            driver.close();
            driver.switchTo()
                  .window(tabs.get(0));
        }
    }
}
