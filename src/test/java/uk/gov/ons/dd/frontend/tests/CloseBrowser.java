package uk.gov.ons.dd.frontend.tests;

import org.testng.annotations.Test;


public class CloseBrowser extends BaseTest {
	@Test
	public void closeBrowser() {
		basePage.getDriver().close();
		basePage.getDriver().quit();
	}
}
