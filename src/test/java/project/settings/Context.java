package project.settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import project.pageObjects.BasePage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Context {

    private BrowserType type;
    private Map<String, Object> data = new HashMap<>();
    private WebDriver browser;
    private BasePage currentPage;

    public Context(BrowserType type) {
        this.type = type;
        System.out.println(this.type);
    }

    public BasePage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(BasePage currentPage) {
        this.currentPage = currentPage;
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public WebDriver getBrowser() {
        if (browser == null) {
            browser = Context.setupDriver(type);
        }
        return browser;
    }

    public void cleanup() {
        if (browser != null) {
            browser.quit();
        }
    }

    private static WebDriver setupDriver(BrowserType type) {
        WebDriverManager.globalConfig().setForceCache(false);
        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("enable-automation");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-notifications");
                return new ChromeDriver(options);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxDriver firefoxDriver = new FirefoxDriver();
                firefoxDriver.manage().window().maximize();
                firefoxDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
                return firefoxDriver;
            case IE:
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.ignoreZoomSettings()
                        .destructivelyEnsureCleanSession()
                        .setPageLoadStrategy(PageLoadStrategy.EAGER)
                        .elementScrollTo(ElementScrollBehavior.BOTTOM);
                WebDriver driver = new InternetExplorerDriver(ieOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            return driver;
        }
        throw new RuntimeException("Unsupported browser type");
    }
}
