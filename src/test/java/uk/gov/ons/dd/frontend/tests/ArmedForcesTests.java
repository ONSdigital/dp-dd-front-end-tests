package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.filters.OptionSelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.pages.ArmedForces;
import uk.gov.ons.dd.frontend.pages.BasePage;
import uk.gov.ons.dd.frontend.util.Helper;

import java.util.ArrayList;


public class ArmedForcesTests extends BasePage {
	ArmedForces armedForces = new ArmedForces();
	String selectedOption = null;
	/*

		@Test(groups = {"downloadXLS"}, dependsOnGroups = {"back"})
		public void downloadCompleteDS_WithXLS() {
			downloadOption(true);
			ArrayList <String> selectedChkBox = selectChkBox(0);
			assertLastPage(selectedChkBox);
		}
	*/
	ArrayList <WebElement> selectedChkBox = new ArrayList <>();
	ArrayList <String> age = new ArrayList <>();
	ArrayList <String> residence = new ArrayList <>();
	ArrayList <String> sex = new ArrayList <>();
	SummarySelector summarySelector = new SummarySelector();
	OptionSelector optionSelector = new OptionSelector();
	String fileName = null;

	@BeforeTest
	public void openPage() {
		navigateToUrl(getConfig().getBaseURL());
		click(armedForces.armedForces_link);
		switchToLatestWindow();
		Helper.pause(10);
		click(customise_data_set);
	}

	@Test(groups = "sex")
	public void customiseSexFilter() {
		try {
			optionSelector.optionJourney(armedForces.sex_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail();
		}
	}

	@Test(groups = "residence", dependsOnGroups = {"sex"})
	public void customiseResidenceType() throws Exception {
		try {
			optionSelector.optionJourney(armedForces.residence_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail();
		}
	}

	@Test(groups = "age", dependsOnGroups = {"residence"})
	public void customiseAge() throws Exception {
		try {
			optionSelector.optionJourney(armedForces.age_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail();
		}

	}

	@Test(groups = {"back"}, dependsOnGroups = {"age"})
	public void cancelCustomiseDownload() {
		openPage();
		browserBack();
		Assert.assertTrue(getPageSource().contains(getTextFromProperty("customise_dataset_linkText")),
				"Back button did not take it to the dataset details page");
		click(customise_data_set);
		click(choose_download_format);
		click(cancel_button);
		Assert.assertTrue(isElementPresent(choose_download_format),
				"Cancel button did not take it to the customise options page");

	}

	@Test(groups = {"canceldownload"}, dependsOnGroups = {"back"})
	public void customiseSelections() throws Exception {
		getCustomiseLink(armedForces.age_filter).click();
		click(disable_all);
		selectCheckBox(0);
		selectCheckBox(1);
		String ageSelection = optionSelector.returnSelectedChkBox();
		click(save_selection);
		getCustomiseLink(armedForces.residence_filter).click();
		click(disable_all);
		selectCheckBox(0);
		String residenceSelection = optionSelector.returnSelectedChkBox();
		click(save_selection);
		getCustomiseLink(armedForces.sex_filter).click();
		click(disable_all);
		selectCheckBox(0);
		String sexSelection = optionSelector.returnSelectedChkBox();
		click(save_selection);
		click(choose_download_format);
		click(cancel_button);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), ageSelection);
		Assert.assertEquals(getoptionsText(armedForces.residence_filter), residenceSelection);
		Assert.assertEquals(getoptionsText(armedForces.sex_filter), sexSelection);

	}

	// CHANGE HERE ADD SELECTED VALUES CALL OPTIONSELECTOR
	@Test(groups = {"getOptions"}, dependsOnGroups = {"canceldownload"})
	public void getSelectedOptions() {
		age = summarySelector.selectedOptions(armedForces.age_filter, false);
		residence = summarySelector.selectedOptions(armedForces.residence_filter, false);
		sex = summarySelector.selectedOptions(armedForces.sex_filter, false);
	}

	@Test(groups = {"customiseCSV"}, dependsOnGroups = {"getOptions"})
	public void downloadCustomisedDS_CSV() {
		click(choose_download_format);
		try {
			selectCheckBox(1);
		} catch (Exception ee) {
		}
		click(generate_file);
		String url = waitForDownload(fileName);
		String[] urlSplit = url.split("/");
		fileName = urlSplit[urlSplit.length - 1];
		checkFile(url, age, armedForces.age_filter, false);
		checkFile(url, residence, armedForces.residence_filter, false);
		checkFile(url, sex, armedForces.sex_filter, false);
	}

	@Test(groups = {"downloadCSV"}, dependsOnGroups = {"customiseCSV"})
	public void downloadCompleteDS_WithCSV() throws Exception {
		downloadOption(true, armedForces.armedForces_link);
		selectedChkBox = selectChkBox(1);
		assertLastPage(getCheckBoxValues(selectedChkBox));
	}




/*
	@Test(groups = {"alloptions"}, dependsOnGroups = {"option1"})
	public void downloadCompleteDS_all_options() {
		downloadOption(true);
		ArrayList <String> selectedCheckBoxes = selectChkBox(0, 1, 2);
		assertLastPage(selectedCheckBoxes);
	}

*/

	@Test(groups = {"cancelDownload"}, dependsOnGroups = {"downloadCSV"})
	public void cancelCompleteDownload() throws Exception {
		downloadOption(true, armedForces.armedForces_link);
		selectChkBox(1);
		click(cancel_button);
		Assert.assertTrue(isElementPresent(download_complete_dataset),
				"Cancel button did not take to the Download Complete Dataset page");
	}

	@AfterClass
	public void closeTest() {
		TestContext.getDriver().close();
		TestContext.getDriver().quit();
	}
}
