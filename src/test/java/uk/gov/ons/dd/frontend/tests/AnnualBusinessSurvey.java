package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;


public class AnnualBusinessSurvey extends BaseTest {
	// Filters
	public By abs = basePage.getElementLocator("annual_business_survey_linkText");
	public String sic07ABS = basePage.getTextFromProperty("sic07abs_filter_text");
	public String uk_Business_value = basePage.getTextFromProperty("uk_business_value_filter_text");
	public String year = basePage.getTextFromProperty("year_filter_text");
	public String searchKey1 = basePage.getTextFromProperty("abs_searchkey_text");


	HierarchySelector hierarchySelector = new HierarchySelector();

	@BeforeTest
	public void init() {
		openPage(abs);
	}

	@Test
	public void customiseSIC() {
		try {
			hierarchySelector.hierarchyJourney(sic07ABS, searchKey1);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}

	@AfterClass
	public void closeTest() {
		TestContext.getDriver().close();
	}

}
