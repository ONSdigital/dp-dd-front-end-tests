package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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
	ArrayList <String> selectedSicCodes = new ArrayList <>();
	ArrayList <String> selectedBizValues = new ArrayList <>();
	@BeforeTest
	public void init() {
		openPage(abs);
	}

	@Test(groups = {"downloadComplete"})
	public void downloadCompleteDS() throws Exception {
		checkForDS(abs);
		basePage.click(basePage.download_complete_dataset);
		basePage.selectDownloadCSV(false);
	}

	@Test(groups = {"openCPI"}, dependsOnGroups = {"downloadComplete"})
	public void openCPI() throws Exception {
		checkForDS(abs);
		basePage.click(basePage.customise_data_set);
	}

	@Test(groups = {"sic"}, dependsOnGroups = {"openCPI"})
	public void customiseSIC() {
		try {
			selectedSicCodes = hierarchySelector.hierarchyJourney(sic07ABS, searchKey1);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}

	@Test(groups = {"ukbiz"}, dependsOnGroups = {"sic"})
	public void customiseUKBizValue() {
//		try {
//			optionSelector.optionJourney(uk_Business_value);
//		} catch (Exception ee) {
//			ee.printStackTrace();
//			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
//		}
	}

	@Test(groups = {"getOptions"}, dependsOnGroups = {"ukbiz"})
	public void getSelectedOptions() {
//		sicCodes = summarySelector.selectedOptions(sic07ABS, true);
//		ukBizVal = summarySelector.selectedOptions(uk_Business_value, false);
	}

	@Test(groups = {"downloadCSV"}, dependsOnGroups = {"getOptions"})
	public void downloadCompleteDS_WithCSV() {
//		basePage.selectDownloadCSV(false);
//		basePage.checkFile(selectedSicCodes, sic07ABS, true);
//		basePage.checkFile(selectedBizValues, uk_Business_value, false);

	}


	@AfterClass
	public void closeTest() {
		basePage.getDriver().close();
		basePage.getDriver().quit();
	}

}
