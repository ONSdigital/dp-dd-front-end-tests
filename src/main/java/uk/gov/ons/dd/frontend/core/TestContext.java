package uk.gov.ons.dd.frontend.core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.ons.dd.frontend.selenium.Browser;
import uk.gov.ons.dd.frontend.util.CacheService;

public final class TestContext {
    public static Configuration configuration = new Configuration();

    public static WebDriver getDriver() {
        return Browser.initDriver(configuration);
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static CacheService getCacheService() {
        return CacheService.getInstance();
    }

    public static WebDriverWait getWebDriverWait() {
        return Browser.getWebDriverWait();
    }
    // Add Reports
    // Keeping everything for the basepage in one class
}
