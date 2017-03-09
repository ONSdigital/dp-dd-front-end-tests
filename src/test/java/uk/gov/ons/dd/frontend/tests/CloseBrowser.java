package uk.gov.ons.dd.frontend.tests;

import org.testng.annotations.Test;

import java.util.Set;


public class CloseBrowser extends BaseTest {
	@Test
	public void closeBrowser() {
		Set <String> windowHandles = basePage.getDriver().getWindowHandles();
		if (windowHandles.size() > 1) {
			for (String wh : windowHandles) {
				basePage.getDriver().switchTo().window(wh);
				basePage.getDriver().close();
			}
		}
		basePage.getDriver().quit();
	}
}
