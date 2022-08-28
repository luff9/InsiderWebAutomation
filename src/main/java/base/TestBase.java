package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import page.CareerPage;
import page.HomePage;

public class TestBase {
    public WebDriver driver;
    public HomePage homePage;
    public CareerPage careerPage;

    @BeforeTest
    public void setUp() {

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
