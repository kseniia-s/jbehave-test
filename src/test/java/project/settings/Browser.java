package project.settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Browser {

    private static ChromeDriver driver;

    private Browser () {}

//    public static WebDriver getDriver() {
//        if (instance == null) {
//            instance = new ChromeDriver();
//        }
//        return instance;
//    }
//

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.globalConfig().setForceCache(false);
            WebDriverManager.chromedriver().version("79.0.3945.36").setup();

            ChromeOptions options = new ChromeOptions();

//            Map<String, Object> prefs = new HashMap<>();
//            prefs.put("profile.default_content_setting_values.notifications", 2);

            options.addArguments("start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-notifications");
//            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
            System.out.println(driver.getCapabilities().getVersion());
        }
        return driver;
    }

    public static void tearDown(){
        driver.quit();
    }
}
