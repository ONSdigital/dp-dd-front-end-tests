package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.pages.CPI;

import java.util.ArrayList;


public class CPITests extends BaseTest {
	public String nace = basePage.getTextFromProperty("nace_filter_text");
	public String prodcom = basePage.getTextFromProperty("prodcom_filter_text");
	public String searchKey1 = basePage.getTextFromProperty("nace_searchkey_text");
	public String prodcom_searchKey = basePage.getTextFromProperty("prodcom_searchkey_text");
	public By cpi_link = basePage.getElementLocator("cpi_linkText");

	CPI cpi = new CPI();
	String selectedOption = null;
	String selected_year = null, selected_month = null;
	ArrayList <String> selectedNames = new ArrayList <>();
	String toRet = null;
	HierarchySelector hierarchySelector = new HierarchySelector();
	SummarySelector summarySelector = new SummarySelector();
	ArrayList <String> selected_nace = null;
	ArrayList <String> selectedProdcom = null;

	@BeforeTest
	public void init() {
		openPage(cpi_link);
	}

	@Test(groups = {"nace"})
	public void customiseNace() {
//		try {
//			selected_nace = hierarchySelector.hierarchyJourney(nace, searchKey1);
//		} catch (Exception ee) {
//			ee.printStackTrace();
//			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
//		}
	}

	@Test(groups = {"prodcom"}, dependsOnGroups = {"nace"})
	public void customiseProdCom() {
//		try {
//			selectedProdcom = hierarchySelector.hierarchyJourney(prodcom, prodcom_searchKey);
//		} catch (Exception ee) {
//			ee.printStackTrace();
//			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
//		}
	}


	@Test(groups = {"downloadCSV"}, dependsOnGroups = {"prodcom"})
	public void downloadCompleteDS_WithCSV() {
//		basePage.selectDownloadCSV(false);
//
//		basePage.checkFile(selected_nace, nace, true);
//		basePage.checkFile(selectedProdcom, prodcom, false);

	}

	@AfterClass
	public void closeTest() {
		basePage.getDriver().close();
		basePage.getDriver().quit();
	}


}
