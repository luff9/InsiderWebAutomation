package page;

import lib.DriverLib;
import model.Block;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CareerPage {
    WebDriver driver;

    public CareerPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement getBlockLocation() {
        return driver.findElement(By.id("career-our-location"));
    }

    public List<WebElement> getLocationList() {
        return driver.findElements(By.xpath("//section[@id='career-our-location']//li[contains(@class,'glide__slide')]"));
    }

    public WebElement getBlockLifeAtInsider() {
        return driver.findElement(By.xpath("//h2[contains(text(),'Life at Insider')]//ancestor::section"));
    }

    public List<WebElement> getBlockLifeAtInsiderPhotos() {
        return getBlockLifeAtInsider().findElements(By.xpath("//section[@id='career-our-location']//li[contains(@class,'glide__slide')]"));
    }

    private WebElement getButtonSeeAllTeams() {
        return driver.findElement(By.xpath("//a[contains(text(),'See all teams')]"));
    }

    private WebElement linkQualityAssurance() {
        return driver.findElement(By.xpath("//a[@href='https://useinsider.com/careers/quality-assurance/']"));
    }

    public void gotoLocation() {
        DriverLib.scrollIntoView(driver, getBlockLocation(), Block.start);
    }

    public void gotoLifeAtInsider() {
        DriverLib.scrollIntoView(driver, getBlockLifeAtInsider(), Block.start);
    }


    public void gotoSeeAllTeam() {
        DriverLib.scrollIntoView(driver, getButtonSeeAllTeams(), Block.center);
        getButtonSeeAllTeams().click();
    }

    public void gotoQualityAssurance() {
        DriverLib.scrollIntoView(driver, linkQualityAssurance(), Block.start);
        linkQualityAssurance().click();
    }


}
