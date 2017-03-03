package uk.gov.ons.dd.frontend.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;
import uk.gov.ons.dd.frontend.filters.OptionSelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.pages.ArmedForces;

import java.util.ArrayList;

public class ArmedForcesTests extends BaseTest {

	private final String armedForcesFile = "AF001EW_v3_E2E_Tests.csv";
	// CHECK FOR THE DATA RESOURCE ON THE DD PAGE
	// EXISTS = TRUE
	// RUN THE TESTS
	// EXISTS = FALSE
	// CHECK IN THE METADATA EDITOR WHETHER THE FILE EXISTS
	// EXISTS = TRUE
	// CHECK FOR THE DATA RESOURCE
	// EXISTS = FALSE
	// UPLOAD THE FILE
	// EXISTS = TRUE
	// LINK THE DATA RESOURCE TO THE DATASET
	// EXISTS = FALSE
	// CREATE THE DATA RESOURCE
	// LINK THE DATA RESOURCE TO THE DATASET
	ArmedForces armedForces = new ArmedForces();
	HierarchySelector hierarchySelector = new HierarchySelector();
	SummarySelector summary = new SummarySelector();
	OptionSelector optionSelector = new OptionSelector();
	ArrayList <String> age = new ArrayList <>();
	ArrayList <String> residence = new ArrayList <>();
	ArrayList <String> sex = new ArrayList <>();
	String armedForcesDS = null;



	@Test(groups = {"downloadComplete"})
	public void downloadCompleteDS() throws Exception {
		checkForDS(armedForces.armedForces_link);
		basePage.click(basePage.download_complete_dataset);
		basePage.selectDownloadCSV(false);
		System.out.println("downloadComplete");

	}

	@Test(groups = {"openAF"}, dependsOnGroups = {"downloadComplete"})
	public void openArmedForces() throws Exception {
		checkForDS(armedForces.armedForces_link);
		basePage.click(basePage.customise_data_set);
		System.out.println("openAF");
	}

	@Test(groups = "sex", dependsOnGroups = {"openAF"})
	public void customiseSexFilter() {
		try {
			optionSelector.optionJourney(armedForces.sex_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			org.testng.Assert.fail();
		}
		System.out.println("customiseSex");
	}

	@Test(groups = "residence", dependsOnGroups = {"sex"})
	public void customiseResidenceType() throws Exception {
		try {
			optionSelector.optionJourney(armedForces.residence_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			org.testng.Assert.fail();
		}
		System.out.println("customiseResidence");
	}

	@Test(groups = "age", dependsOnGroups = {"residence"})
	public void customiseAge() throws Exception {
		try {
			optionSelector.optionJourney(armedForces.age_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			org.testng.Assert.fail();
		}

		System.out.println("customiseAge");
	}

	@Test(groups = "geography", dependsOnGroups = {"age"})
	public void customiseGeo() throws Exception {
		try {

			hierarchySelector.simpleGeoJourney(armedForces.geo_filter, armedForces.geo_search_text, true);
		} catch (Exception ee) {
			ee.printStackTrace();
			org.testng.Assert.fail();
		}

		System.out.println("customiseGeo");
	}

	@Test(groups = {"getOptions"}, dependsOnGroups = {"geography"})
	public void getSelectedOptions() {
		age = summary.selectedOptions(armedForces.age_filter, false);
		residence = summary.selectedOptions(armedForces.residence_filter, false);
		sex = summary.selectedOptions(armedForces.sex_filter, false);
		System.out.println("getOptions");
	}

	@Test(groups = {"customiseCSV"}, dependsOnGroups = {"getOptions"})
	public void downloadCustomisedDS_CSV() {
		basePage.selectDownloadCSV(true);
		basePage.checkDownloadedFile(age, armedForces.age_filter, false);
		basePage.checkDownloadedFile(residence, armedForces.residence_filter, false);
		basePage.checkDownloadedFile(sex, armedForces.sex_filter, false);
		System.out.println("customiseCSV");
	}


	@AfterClass
	public void closeTest() {
		TestContext.getDriver().close();
		TestContext.getDriver().quit();
	}
}
