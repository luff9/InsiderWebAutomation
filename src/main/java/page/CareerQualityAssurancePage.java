package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CareerQualityAssurancePage {
    WebDriver driver;

    public CareerQualityAssurancePage(WebDriver webDriver) {
        this.driver = webDriver;
    }


    private WebElement getButtonSeeAllQaJobs() {
        return driver.findElement(By.xpath("//a[contains(text(),'See all QA jobs')]"));
    }

    public void gotoAllQaJobs() {
        getButtonSeeAllQaJobs().click();
    }


}
