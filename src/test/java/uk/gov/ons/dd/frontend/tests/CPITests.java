package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
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
	public void downloadCompDS() throws Exception {
		System.out.println("************    CPI Tests  ***********************");
		System.out.println("Starting... downloadCompletecpi");
		checkForDS(cpi_link);
		basePage.click(basePage.download_complete_dataset);
		basePage.selectDownloadCSV(false);
		System.out.println("downloadCompletecpi");
	}

	@Test(groups = {"openCPI"}, dependsOnGroups = {"downloadCompletecpi"})
	public void openCPI() throws Exception {
		System.out.println("Starting... openCPI");
		checkForDS(cpi_link);
		basePage.click(basePage.customise_data_set);
		System.out.println("openCPI");
	}


	@Test(groups = {"nace"}, dependsOnGroups = {"openCPI"})
	public void customiseNace() {
		System.out.println("Starting... nace");
		try {
			hierarchySelector.hierarchyJourney(spl_aggr, searchKey1, true);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
		System.out.println("customised Nace");
	}


	@Test(groups = {"prodcom"}, dependsOnGroups = {"nace"})
	public void customiseProdCom() {
		System.out.println("Starting... prodcom");
		try {
			optionSelector.optionJourney(prodcom);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
		System.out.println("prodcom");
	}


	@Test(groups = {"getOptionscpi"}, dependsOnGroups = {"prodcom"})
	public void getSelectedOptions() {
		System.out.println("Starting... getOptionscpi");
		selected_spl_agg = summarySelector.selectedOptions(spl_aggr, true);
		selectedProdcom = summarySelector.selectedOptions(prodcom, false);
		System.out.println("getOptionscpi");
	}

	@Test(groups = {"customiseCSVcpi"}, dependsOnGroups = {"getOptionscpi"})
	public void downloadCustomisedDS_CSV() {
		System.out.println("Starting...   downloadCustomisedDS_WithCSV");
		basePage.selectDownloadCSV(true);
		basePage.checkDownloadedFile(selected_spl_agg, spl_aggr, true);
		System.out.println("downloadCustomisedDS_WithCSV");
	}


}
