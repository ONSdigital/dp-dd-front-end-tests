package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.By;
import uk.gov.ons.dd.frontend.core.Configuration;
import uk.gov.ons.dd.frontend.pages.BasePage;

public class BaseTest {
	BasePage basePage = new BasePage();
	Configuration config = new Configuration();

	public void openPage(By dataSet) {
		basePage.navigateToUrl(config.getBaseURL());
		basePage.click(dataSet);
		basePage.switchToLatestWindow();
		basePage.click(basePage.customise_data_set);
	}

	public void checkForDS(By link) throws Exception {
		String baseUrl = basePage.getConfig().getBaseURL();
		basePage.navigateToUrl(baseUrl);
		basePage.click(link);
		basePage.switchToLatestWindow();
	}

}
