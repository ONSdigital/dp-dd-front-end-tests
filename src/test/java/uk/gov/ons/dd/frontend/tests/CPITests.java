package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.pages.CPI;

import java.util.ArrayList;


public class CPITests extends BaseTest {
	public String spl_aggr = basePage.getTextFromProperty("nace_filter_text");
	public String searchKey1 = basePage.getTextFromProperty("spl_agg_searchkey_text");
	public By cpi_link = basePage.getElementLocator("cpi_linkText");

	CPI cpi = new CPI();
	String selectedOption = null;
	String selected_year = null, selected_month = null;
	ArrayList <String> selectedNames = new ArrayList <>();
	String toRet = null;
	HierarchySelector hierarchySelector = new HierarchySelector();
	SummarySelector summarySelector = new SummarySelector();
	ArrayList <String> selected_spl_agg = null;
	ArrayList <String> selectedProdcom = null;


	@Test(groups = {"downloadComplete"})
	public void downloadCompleteDS() throws Exception {
		checkForDS(cpi_link);
		basePage.click(basePage.download_complete_dataset);
		basePage.selectDownloadCSV(false);
	}

	@Test(groups = {"openCPI"}, dependsOnGroups = {"downloadComplete"})
	public void openCPI() throws Exception {
		checkForDS(cpi_link);
		basePage.click(basePage.customise_data_set);
	}


	@Test(groups = {"nace"}, dependsOnGroups = {"openCPI"})
	public void customiseNace() {
		try {
			selected_spl_agg = hierarchySelector.hierarchyJourney(spl_aggr, searchKey1, true);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}

	//CUSTOMISE MONTH

//	@Test(groups = {"prodcom"}, dependsOnGroups = {"nace"})
//	public void customiseProdCom() {
////		try {
////			selectedProdcom = hierarchySelector.hierarchyJourney(prodcom, prodcom_searchKey);
////		} catch (Exception ee) {
////			ee.printStackTrace();
////			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
////		}
//	}


	@Test(groups = {"getOptions"}, dependsOnGroups = {"nace"})
	public void getSelectedOptions() {
		selected_spl_agg = summarySelector.selectedOptions(spl_aggr, true);
	}

	@Test(groups = {"customiseCSV"}, dependsOnGroups = {"getOptions"})
	public void downloadCustomisedDS_CSV() {
		basePage.selectDownloadCSV(true);
		basePage.checkDownloadedFile(selected_spl_agg, spl_aggr, true);
	}

	@AfterClass
	public void closeTest() {
		basePage.getDriver().close();
		basePage.getDriver().quit();
	}


}
