package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.pages.ArmedForces;
import uk.gov.ons.dd.frontend.pages.BasePage;

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

	@BeforeTest
	public void openPage() {
		navigateToUrl(getConfig().getBaseURL());
		click(armedForces.armedForces_link);
		switchToLatestWindow();
		click(customise_data_set);

	}


	@Test(groups = "sex")
	public void customiseSexFilter() throws Exception {
		String defaultSelection = getoptionsText(armedForces.sex_filter);
		getCustomiseLink(armedForces.sex_filter).click();
		click(disable_all);
		click(save_selection);
		Assert.assertEquals(getElementText(error_message), error_message_text,
				"Actual error message : " + getElementText(error_message)
						+ "\n Expected error message : " + error_message_text);
		click(cancel_button);
		Assert.assertEquals(getoptionsText(armedForces.sex_filter), defaultSelection);
		getCustomiseLink(armedForces.sex_filter).click();
		// ids need to be removed
		selectCheckBox(0);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.sex_filter), selectedOption,
				"Actual sex filters : "
						+ getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + selectedOption);
		getCustomiseLink(armedForces.sex_filter).click();
		click(enable_all);
		click(cancel_button);
		Assert.assertEquals(getoptionsText(armedForces.sex_filter), selectedOption,
				"Actual sex filters : "
						+ getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + selectedOption);
		getCustomiseLink(armedForces.sex_filter).click();
		click(enable_all);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.sex_filter), defaultSelection,
				"Actual sex filters : "
						+ getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + defaultSelection);
		getCustomiseLink(armedForces.sex_filter).click();
		click(disable_all);
		selectCheckBox(1);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.sex_filter), selectedOption,
				"Actual sex filters : "
						+ getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + selectedOption);

	}

	@Test(groups = "residence", dependsOnGroups = {"sex"})
	public void customiseResidenceType() throws Exception {
		String defaultSelection = getoptionsText(armedForces.residence_filter);
		getCustomiseLink(armedForces.residence_filter).click();
		click(disable_all);
		selectCheckBox(0);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.residence_filter), selectedOption,
				"Actual residence filters : "
						+ getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + selectedOption);
		getCustomiseLink(armedForces.residence_filter).click();
		selectCheckBox(1);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.residence_filter), selectedOption,
				"Actual residence filters : "
						+ getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + selectedOption);
		getCustomiseLink(armedForces.residence_filter).click();
		selectCheckBox(2);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.residence_filter), selectedOption,
				"Actual residence filters : "
						+ getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + selectedOption);
		getCustomiseLink(armedForces.residence_filter).click();
		click(disable_all);
		click(save_selection);
		Assert.assertEquals(getElementText(error_message), error_message_text,
				"Actual error message : " + getElementText(error_message)
						+ "\n Expected error message : " + error_message_text);
		click(enable_all);
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.residence_filter), defaultSelection,
				"Actual residence filters : "
						+ getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + defaultSelection);
		getCustomiseLink(armedForces.residence_filter).click();
		click(disable_all);
		selectCheckBox(2);
		click(cancel_button);
		Assert.assertEquals(getoptionsText(armedForces.residence_filter), defaultSelection,
				"Actual residence filters : "
						+ getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + defaultSelection);
	}

	@Test(groups = "age", dependsOnGroups = {"residence"})
	public void customiseAge() throws Exception {
		String defaultSelection = getoptionsText(armedForces.age_filter);
		getCustomiseLink(armedForces.age_filter).click();
		click(disable_all);
		selectCheckBox(0);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		getCustomiseLink(armedForces.age_filter).click();
		selectCheckBox(1);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		getCustomiseLink(armedForces.age_filter).click();
		selectCheckBox(2);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		getCustomiseLink(armedForces.age_filter).click();
		selectCheckBox(3);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		getCustomiseLink(armedForces.age_filter).click();
		selectCheckBox(4);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		getCustomiseLink(armedForces.age_filter).click();

		click(disable_all);
		click(save_selection);
		Assert.assertEquals(getElementText(error_message), error_message_text,
				"Actual error message : " + getElementText(error_message)
						+ "\n Expected error message : " + error_message_text);
		click(enable_all);
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), defaultSelection);
		getCustomiseLink(armedForces.age_filter).click();
		click(disable_all);
		selectCheckBox(3);
		click(cancel_button);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), defaultSelection);
		getCustomiseLink(armedForces.age_filter).click();
		click(disable_all);
		selectCheckBox(4);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), selectedOption);
		getCustomiseLink(armedForces.age_filter).click();
		selectCheckBox(0);
		selectCheckBox(1);
		selectCheckBox(2);
		selectCheckBox(3);
		selectedOption = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		downloadOption(false);

	}

	@Test(groups = {"back"}, dependsOnGroups = {"age"})
	public void cancelCustomiseDownload() {
		openPage();
		click(back_link);
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
		String ageSelection = returnSelectedOptionText();
		click(save_selection);
		getCustomiseLink(armedForces.residence_filter).click();
		click(disable_all);
		selectCheckBox(0);
		String residenceSelection = returnSelectedOptionText();
		click(save_selection);
		getCustomiseLink(armedForces.sex_filter).click();
		click(disable_all);
		selectCheckBox(0);
		String sexSelection = returnSelectedOptionText();
		click(save_selection);
		click(choose_download_format);
		click(cancel_button);
		Assert.assertEquals(getoptionsText(armedForces.age_filter), ageSelection);
		Assert.assertEquals(getoptionsText(armedForces.residence_filter), residenceSelection);
		Assert.assertEquals(getoptionsText(armedForces.sex_filter), sexSelection);

	}

	@Test(groups = {"downloadCSV"}, dependsOnGroups = {"canceldownload"})
	public void downloadCompleteDS_WithCSV() throws Exception {
		downloadOption(true);
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
		downloadOption(true);
		selectChkBox(2);
		click(cancel_button);
		Assert.assertTrue(isElementPresent(download_complete_dataset),
				"Cancel button did not take to the Download Complete Dataset page");
	}


	public void downloadOption(boolean completeDataSet) {
		if (completeDataSet) {
			deleteCookies();
			navigateToUrl(getConfig().getBaseURL());
			click(armedForces.armedForces_link);
			switchToLatestWindow();
			click(download_complete_dataset);
		} else {
			click(choose_download_format);
		}
		click(help_with_file_formats);
		Assert.assertTrue(isElementPresent(file_options_help_text), "File formats help text did not open and is still hidden");
		click(help_with_file_formats);
		Assert.assertFalse(isElementPresentWithOutWait(file_options_help_text),
				"File formats help text is still open and did not close after clicking on it");
		click(generate_file);
		Assert.assertEquals(getElementText(error_message), error_message_text,
				"Actual error message : " + getElementText(error_message)
						+ "\n Expected error message : " + error_message_text);
	}


	public String returnSelectedOptionText() throws Exception {
		String valuetoReturn = null;
		int totalNumChkBox = getAllCheckBoxes().size();
		int selectedChkBox = findElementsBy(selected_checkboxes_css).size();
		int diff = totalNumChkBox - selectedChkBox;
		switch (diff) {
			case 0:
				valuetoReturn = "Everything selected (" + totalNumChkBox + ")";
				break;
			default:
				valuetoReturn = "Selected options (" + selectedChkBox + ")";
				break;
		}
		return valuetoReturn;

	}

	public ArrayList <String> getCheckBoxValues(ArrayList <WebElement> selectedCheckBoxes) {
		ArrayList <String> chkBoxValues = new ArrayList <>();
		for (WebElement webElement : selectedCheckBoxes) {
			chkBoxValues.add(webElement.getAttribute("value"));
		}
		return chkBoxValues;
	}


	public void assertLastPage(ArrayList <String> selectedCheckBoxes) throws Exception {

		click(generate_file);
		int counter = 30;
		try {
			getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(file_download_button_options));
		} catch (Exception ee) {
			try {
				while (counter < 1) {
					Thread.sleep(2000);
					assertLastPage(selectedCheckBoxes);
					counter--;
				}

			} catch (InterruptedException ee1) {
			}

		}

		ArrayList <String> actualButtonsForDownload = new ArrayList <>();
		for (WebElement webElement : findElementsBy(file_download_button_options)) {
			actualButtonsForDownload.add(webElement.getText().toUpperCase());
		}


		Assert.assertEquals(actualButtonsForDownload, selectedCheckBoxes,
				"Mismatch between the file formats selected to the file formats available for download");

	}

	@AfterClass
	public void closeTest() {
		TestContext.getDriver().close();
	}
}
