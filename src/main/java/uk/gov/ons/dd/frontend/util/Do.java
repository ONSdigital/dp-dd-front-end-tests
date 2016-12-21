package uk.gov.ons.dd.frontend.util;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.ons.dd.frontend.core.Configuration;

public class Do {

    public static <T> T until(WebDriver driver, ExpectedCondition<T> expectedCondition, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        return webDriverWait.until(expectedCondition);
    }

    public static <T> T until(WebDriver driver, ExpectedCondition<T> expectedCondition) {
        return until(driver, expectedCondition, Configuration.DEFAULT_TIMEOUT_VALUE);
    }
}
