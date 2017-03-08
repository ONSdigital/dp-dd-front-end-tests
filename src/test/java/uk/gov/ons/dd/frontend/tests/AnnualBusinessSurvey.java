package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

	@Test(groups = {"downloadCompleteabs"})
	public void downloadCompleteDS() throws Exception {
		System.out.println("************    Annual Business Survey  ***********************");
		checkForDS(abs);
		basePage.click(basePage.download_complete_dataset);
		basePage.selectDownloadCSV(false);
		System.out.println("downloadCompleteDS");
	}

	@Test(groups = {"openABS"}, dependsOnGroups = {"downloadCompleteabs"})
	public void openABS() throws Exception {
		checkForDS(abs);
		basePage.click(basePage.customise_data_set);
		System.out.println("openABS");
	}

	@Test(groups = {"sic"}, dependsOnGroups = {"openABS"})
	public void customiseSIC() {
		try {
			selectedSicCodes = hierarchySelector.hierarchyJourney(sic07ABS, searchKey1, true);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
		System.out.println("customiseSICCodes");
	}

	@Test(groups = {"ukbiz"}, dependsOnGroups = {"sic"})
	public void customiseUKBizValue() {
		try {
			optionSelector.optionJourney(uk_Business_value);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
		System.out.println("customiseUKBizValue");
	}

	@Test(groups = {"getOptionsabs"}, dependsOnGroups = {"ukbiz"})
	public void getSelectedOptions() {
		sicCodes = summarySelector.selectedOptions(sic07ABS, true);
		ukBizVal = summarySelector.selectedOptions(uk_Business_value, false);
		System.out.println("getSelectedOptions");
	}

	@Test(groups = {"downloadCSVabs"}, dependsOnGroups = {"getOptionsabs"})
	public void downloadCustomisedDS_WithCSV() {
		basePage.selectDownloadCSV(true);
//		basePage.checkFile(selectedSicCodes, sic07ABS, true);
//		basePage.checkFile(selectedBizValues, uk_Business_value, false);
		System.out.println("downloadCustomisedDS_WithCSV");

	}

}
