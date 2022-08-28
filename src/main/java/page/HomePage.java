package page;

import lib.DriverLib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    public static final String HOME_PAGE_URL = "https://useinsider.com/";
    WebDriver driver;

    public HomePage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    WebElement getButtonAcceptCookies() {
        return driver.findElement(By.id("wt-cli-accept-all-btn"));
    }

    WebElement getNavMore() {
        return driver.findElement(By.xpath("//span[contains(text(),'More')]//parent::a[@id='mega-menu-1']"));
    }

    WebElement getNavCareers() {
        return driver.findElement(By.xpath("//a[@href='https://useinsider.com/careers/']"));
    }

    public void gotoCareers() {
        getNavMore().click();
        getNavCareers().click();
    }

    public void acceptCookies() {
        getButtonAcceptCookies().click();
        DriverLib.sleep(2000);
    }


}
