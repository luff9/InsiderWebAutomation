package lib;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.Block;
import model.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class DriverLib {
    DriverLib() {
    }

    public static WebDriver getWebDriver(Driver webDriver) {
        WebDriver driver = switch (webDriver) {
            case FIREFOX -> getFirefoxDriver();
            case EDGE -> getEdgeDriver();
            case CHROME -> getChromeDriver();
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    private static WebDriver getEdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-infobars");
        return WebDriverManager.edgedriver().capabilities(edgeOptions).create();
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        return WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
    }

    private static WebDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--window-position=2000,0");
        chromeOptions.addArguments("--disable-notifications");
        return WebDriverManager.chromedriver().capabilities(chromeOptions).create();
    }

    public static void scrollIntoView(WebDriver driver, WebElement el, Block block) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"" + block + "\"});", el);
        sleep(3000);
    }

    public static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenShot(WebDriver driver) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath();
            File destFile = new File(reportDirectory + "/screenshots/" + "_" + formatter.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void moveToWebElement(WebDriver driver, WebElement el) {
        Actions actions = new Actions(driver);
        actions.moveToElement(el).click().perform();
    }

}
