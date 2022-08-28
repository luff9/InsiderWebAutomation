package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lib.DriverLib.moveToWebElement;

public class CareerOpenPositionPage {
    WebDriver driver;

    public CareerOpenPositionPage(WebDriver webDriver) {
        this.driver = webDriver;
    }


    public List<WebElement> getDropboxSelection() {
        return driver.findElements(By.xpath("//span[@class='selection']/span"));
    }

    public WebElement getOptionLocation(String location) {
        return driver.findElement(By.xpath("//select[@name='filter-by-location']//option[contains(text(),'" + location + "')]"));
    }

    public WebElement getOptionDepartment(String department) {
        return driver.findElement(By.xpath("//select[@name='filter-by-department']//option[contains(text(),'" + department + "')]"));
    }

    public List<WebElement> getJobs() {
        return driver.findElements(By.xpath("//div[@id='jobs-list']/div"));
    }


    public void setLocation(String location) {
        getDropboxSelection().get(0).click();
        getOptionLocation(location).click();
        getDropboxSelection().get(0).click();
    }

    public void setDepartment(String department) {
        getDropboxSelection().get(1).click();
        getOptionDepartment(department).click();
        getDropboxSelection().get(1).click();
    }

    public String getJobTitle(WebElement el) {
        return el.findElement(By.xpath("//span[contains(@class,'position-department')]")).getText();
    }

    public String getJobLocation(WebElement el) {
        return el.findElement(By.xpath("//div[contains(@class,'position-location')]")).getText();
    }

    public void applyJob(WebElement el) {
        moveToWebElement(driver, el);
        el.findElement(By.xpath("//a[contains(text(),'Apply Now')]")).click();
    }


}
