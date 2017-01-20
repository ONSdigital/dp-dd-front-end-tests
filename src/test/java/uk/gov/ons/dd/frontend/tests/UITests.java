package uk.gov.ons.dd.frontend.tests;

import org.openqa.selenium.By;
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
import java.util.Set;


public class UITests extends BasePage {
	ArmedForces armedForces = new ArmedForces();
	String selectedOption = null;

	public static void main(String[] args) {
		new UITests().downloadCompleteDS_WithXLS();
	}

	@BeforeTest
	public void openPage() {
		armedForces.navigateToUrl(getConfig().getBaseURL());
		click(armedForces.armedForces_link);
		switchToLatestWindow();
		click(armedForces.customise_data_set);

	}

	public void switchToLatestWindow() {
		Set <String> windowHandles = getDriver().getWindowHandles();
		if (windowHandles.size() > 1) {
			for (String wh : windowHandles) {
				String windowHandle = wh.toString();
				getDriver().switchTo().window(windowHandle);
			}
		}
	}

	@Test(groups = "sex")
	public void customiseSexFilter() {
		String defaultSelection = armedForces.getoptionsText(armedForces.sex_filter);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.disable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(getElementText(armedForces.error_message), armedForces.error_message_text,
				"Actual error message : " + getElementText(armedForces.error_message)
						+ "\n Expected error message : " + armedForces.error_message_text);
		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		// ids need to be removed
		armedForces.selectCheckBox(0);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), selectedOption,
				"Actual sex filters : "
						+ armedForces.getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.enable_all);
		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), selectedOption,
				"Actual sex filters : "
						+ armedForces.getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.enable_all);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), defaultSelection,
				"Actual sex filters : "
						+ armedForces.getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + defaultSelection);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(1);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), selectedOption,
				"Actual sex filters : "
						+ armedForces.getoptionsText(armedForces.sex_filter) + "\n" +
						"Expected sex filters : " + selectedOption);

	}

	@Test(groups = "residence", dependsOnGroups = {"sex"})
	public void customiseResidenceType() {
		String defaultSelection = armedForces.getoptionsText(armedForces.residence_filter);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(0);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), selectedOption,
				"Actual residence filters : "
						+ armedForces.getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		armedForces.selectCheckBox(1);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), selectedOption,
				"Actual residence filters : "
						+ armedForces.getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		armedForces.selectCheckBox(2);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), selectedOption,
				"Actual residence filters : "
						+ armedForces.getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		click(armedForces.disable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(getElementText(armedForces.error_message), armedForces.error_message_text,
				"Actual error message : " + getElementText(armedForces.error_message)
						+ "\n Expected error message : " + armedForces.error_message_text);
		click(armedForces.enable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), defaultSelection,
				"Actual residence filters : "
						+ armedForces.getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + defaultSelection);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(2);
		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), defaultSelection,
				"Actual residence filters : "
						+ armedForces.getoptionsText(armedForces.residence_filter) + "\n" +
						"Expected residence filters : " + defaultSelection);
	}

	@Test(groups = "age", dependsOnGroups = {"residence"})
	public void customiseAge() {
		String defaultSelection = armedForces.getoptionsText(armedForces.age_filter);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(0);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ armedForces.getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(1);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ armedForces.getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(2);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ armedForces.getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(3);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ armedForces.getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(4);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ armedForces.getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		armedForces.getCustomiseLink(armedForces.age_filter).click();

		click(armedForces.disable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(getElementText(armedForces.error_message), armedForces.error_message_text,
				"Actual error message : " + getElementText(armedForces.error_message)
						+ "\n Expected error message : " + armedForces.error_message_text);
		click(armedForces.enable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(3);
		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(4);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), selectedOption);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(0);
		armedForces.selectCheckBox(1);
		armedForces.selectCheckBox(2);
		armedForces.selectCheckBox(3);
		selectedOption = returnSelectedOptionText();
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), selectedOption,
				"Actual age filters : "
						+ armedForces.getoptionsText(armedForces.age_filter) + "\n" +
						"Expected age filters : " + selectedOption);
		downloadOption(false);

	}

	@Test(groups = {"back"}, dependsOnGroups = {"age"})
	public void cancelCustomiseDownload() {
		openPage();
		click(armedForces.back_link);
		Assert.assertTrue(armedForces.getPageSource().contains(getTextFromProperty("armed_forces_linkText")),
				"Back button did not take it to the dataset details page");
		click(armedForces.customise_data_set);
		click(armedForces.choose_download_format);
		click(armedForces.cancel_button);
		Assert.assertTrue(isElementPresent(armedForces.choose_download_format),
				"Cancel button did not take it to the customise options page");

	}

	@Test(groups = {"option1"}, dependsOnGroups = {"back"})
	public void downloadCompleteDS_WithXLS() {
		downloadOption(true);
		ArrayList <String> selectedChkBox = selectChkBox(0);
		assertLastPage(selectedChkBox);
	}

	@Test(groups = {"alloptions"}, dependsOnGroups = {"option1"})
	public void downloadCompleteDS_all_options() {
		downloadOption(true);
		ArrayList <String> selectedCheckBoxes = selectChkBox(0, 1, 2);
		assertLastPage(selectedCheckBoxes);

	}

	@Test(groups = {"cancelDownload"}, dependsOnGroups = {"alloptions"})
	public void cancelCompleteDownload() {
		downloadOption(true);
		selectChkBox(2);
		click(armedForces.cancel_button);
		Assert.assertTrue(isElementPresent(armedForces.download_complete_dataset),
				"Cancel button did not take to the Download Complete Dataset page");
	}


	public void downloadOption(boolean completeDataSet) {
		if (completeDataSet) {
			armedForces.deleteCookies();
			armedForces.navigateToUrl(getConfig().getBaseURL());
			click(armedForces.armedForces_link);
			switchToLatestWindow();
			click(armedForces.download_complete_dataset);
		} else {
			click(armedForces.choose_download_format);
		}
		click(armedForces.help_with_file_formats);
		Assert.assertTrue(isElementPresent(armedForces.file_options_help_text), "File formats help text did not open and is still hidden");
		click(armedForces.help_with_file_formats);
		Assert.assertFalse(isElementPresentWithOutWait(armedForces.file_options_help_text),
				"File formats help text is still open and did not close after clicking on it");
		click(armedForces.generate_file);
		Assert.assertEquals(getElementText(armedForces.error_message), armedForces.error_message_text,
				"Actual error message : " + getElementText(armedForces.error_message)
						+ "\n Expected error message : " + armedForces.error_message_text);


	}

	public String returnSelectedOptionText() {
		String valuetoReturn = null;
		int totalNumChkBox = armedForces.getAllCheckBoxes().size();
		int selectedChkBox = findElementsBy(armedForces.selected_checkboxes_css).size();
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

	public ArrayList <String> selectChkBox(int... checkBox) {
		ArrayList <String> checkBoxesSelected = new ArrayList <>();
		for (int checkOption : checkBox) {
			armedForces.selectCheckBox(checkOption);
		}
		for (WebElement webElement : findElementsBy(armedForces.selected_checkboxes_css)) {
			checkBoxesSelected.add(webElement.getAttribute("value").toUpperCase());
		}
		return checkBoxesSelected;
	}

	public void assertLastPage(ArrayList <String> selectedCheckBoxes) {
		click(armedForces.generate_file);
		getWebDriverWait().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("p.margin-top--0"),
				armedForces.files_available_for_download));
		Assert.assertTrue(armedForces.getPageSource().contains(armedForces.files_available_for_download),
				armedForces.files_available_for_download + " is not displayed on the page.");
		ArrayList <String> actualButtonsForDownload = new ArrayList <>();
		for (WebElement webElement : findElementsBy(armedForces.file_download_button_options)) {
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
