package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;
import uk.gov.ons.dd.frontend.filters.OptionSelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;

import java.util.ArrayList;


public class AnnualBusinessSurvey extends BaseTest {
	// Filters
	public By abs = basePage.getElementLocator("annual_business_survey_linkText");
	public String sic07ABS = basePage.getTextFromProperty("sic07abs_filter_text");
	public String uk_Business_value = basePage.getTextFromProperty("uk_business_value_filter_text");
	public String year = basePage.getTextFromProperty("year_filter_text");
	public String searchKey1 = basePage.getTextFromProperty("abs_searchkey_text");

	ArrayList <WebElement> selectedChkBox = new ArrayList <>();
	HierarchySelector hierarchySelector = new HierarchySelector();
	SummarySelector summarySelector = new SummarySelector();
	OptionSelector optionSelector = new OptionSelector();
	ArrayList <String> sicCodes = new ArrayList <>();
	ArrayList <String> ukBizVal = new ArrayList <>();

	@BeforeTest
	public void init() {
		openPage(abs);
	}

	@Test(groups = {"sic"})
	public void customiseSIC() {
		try {
			hierarchySelector.hierarchyJourney(sic07ABS, searchKey1);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}

	@Test(groups = {"ukbiz"}, dependsOnGroups = {"sic"})
	public void customiseUKBizValue() {
		try {
			optionSelector.optionJourney(uk_Business_value);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}

	@Test(groups = {"getOptions"}, dependsOnGroups = {"ukbiz"})
	public void getSelectedOptions() {
		sicCodes = summarySelector.selectedOptions(sic07ABS, true);
		ukBizVal = summarySelector.selectedOptions(uk_Business_value, false);
	}

	@Test(groups = {"downloadCSV"}, dependsOnGroups = {"getOptions"})
	public void downloadCompleteDS_WithCSV() {
		basePage.downloadOption(true, abs);
		try {
			selectedChkBox = basePage.selectChkBox(1);
			basePage.assertLastPage(basePage.getCheckBoxValues(selectedChkBox));
			FileChecker fileChecker = new FileChecker();
			String url = basePage.getElement(basePage.csv_file_download).getAttribute("href");
			String[] urlSplit = url.split("/");
			String fileName = urlSplit[urlSplit.length - 1];
			fileChecker.getFile(url, fileName);
			fileChecker.checkForFilter(sicCodes, sic07ABS, fileName);
			//		fileChecker.checkForFilter(ukBizVal, uk_Business_value, fileName);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail();
		}
	}


	@AfterClass
	public void closeTest() {
		TestContext.getDriver().close();
	}

}
