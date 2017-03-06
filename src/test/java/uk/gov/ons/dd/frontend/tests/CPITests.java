package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;
import uk.gov.ons.dd.frontend.filters.OptionSelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.pages.CPI;

import java.util.ArrayList;


public class CPITests extends BaseTest {
	public String spl_aggr = basePage.getTextFromProperty("nace_filter_text");
	public String prodcom = basePage.getTextFromProperty("prodcom_filter_text");
	public String searchKey1 = basePage.getTextFromProperty("spl_agg_searchkey_text");
	public By cpi_link = basePage.getElementLocator("cpi_linkText");

	CPI cpi = new CPI();
	String selectedOption = null;
	String selected_year = null, selected_month = null;
	ArrayList <String> selectedNames = new ArrayList <>();
	String toRet = null;
	HierarchySelector hierarchySelector = new HierarchySelector();
	SummarySelector summarySelector = new SummarySelector();
	OptionSelector optionSelector = new OptionSelector();
	ArrayList <String> selected_spl_agg = null;
	ArrayList <String> selectedProdcom = null;


	@Test(groups = {"downloadCompletecpi"})
	public void downloadCompleteDS() throws Exception {
		System.out.println("************    CPI Tests  ***********************");
		checkForDS(cpi_link);
		basePage.click(basePage.download_complete_dataset);
		basePage.selectDownloadCSV(false);
	}

	@Test(groups = {"openCPI"}, dependsOnGroups = {"downloadCompletecpi"})
	public void openCPI() throws Exception {
		checkForDS(cpi_link);
		basePage.click(basePage.customise_data_set);
	}


	@Test(groups = {"nace"}, dependsOnGroups = {"openCPI"})
	public void customiseNace() {
		try {
			hierarchySelector.hierarchyJourney(spl_aggr, searchKey1, true);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}


	@Test(groups = {"prodcom"}, dependsOnGroups = {"nace"})
	public void customiseProdCom() {
		try {
			optionSelector.optionJourney(prodcom);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}


	@Test(groups = {"getOptionscpi"}, dependsOnGroups = {"prodcom"})
	public void getSelectedOptions() {
		selected_spl_agg = summarySelector.selectedOptions(spl_aggr, true);
		selectedProdcom = summarySelector.selectedOptions(prodcom, false);
	}

	@Test(groups = {"customiseCSVcpi"}, dependsOnGroups = {"getOptionscpi"})
	public void downloadCustomisedDS_CSV() {
		basePage.selectDownloadCSV(true);
		basePage.checkDownloadedFile(selected_spl_agg, spl_aggr, true);
	}

	@AfterSuite
	public void closeBrowser() {
		basePage.getDriver().close();
		basePage.getDriver().quit();
	}


}
