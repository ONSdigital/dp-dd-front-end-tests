package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.pages.BasePage;
import uk.gov.ons.dd.frontend.pages.CPI;
import uk.gov.ons.dd.frontend.util.RandomStringGen;

import java.util.ArrayList;


public class CPITests extends BasePage {
	CPI cpi = new CPI();
	String selectedOption = null;
	String selected_year = null, selected_month = null;
	int selectsize = 0;
	String toRet = null;

	@BeforeTest
	public void openPage() {
		navigateToUrl(getConfig().getBaseURL());
		click(cpi.cpi_link);
		switchToLatestWindow();
		click(customise_data_set);
	}

	public void setSelections() {
		selected_month = null;
		selected_year = null;
	}

	@Test(groups = "cancelSelection")
	public void cancelSelection() {
		setSelections();
		String defaultSelection = getoptionsText(cpi.time_filter);
		getCustomiseLink(cpi.time_filter).click();
		singleOrRange(cpi.single_month);
		selected_year = selectYearMonth(cpi.select_year);
		Assert.assertTrue(!selected_year.equals(""));
		selected_month = selectYearMonth(cpi.select_month);
		Assert.assertTrue(!selected_month.equals(""));
		click(cancel_button);
		//		Assert.assertEquals(getoptionsText(cpi.time_filter), defaultSelection,
//				"Actual Time filters : "
//						+ getoptionsText(cpi.time_filter) + "\n" +
//						"Expected Time filters : " + defaultSelection);

	}

	@Test(groups = {"singleMonth"})//, dependsOnGroups = {"cancelSelection"})
	public void saveSelection() {
		getCustomiseLink(cpi.time_filter).click();
		singleOrRange(cpi.single_month);
		setSelections();
		selected_year = selectYearMonth(cpi.select_year);
		Assert.assertTrue(!selected_year.equals(""));
		selected_month = selectYearMonth(cpi.select_month);
		Assert.assertTrue(!selected_month.equals(""));
		click(cpi.add_selection);
		String selectedOptions = returnSelectedOptionText();
		assertSelection(selected_year, getAllRangeHeaders());
		assertSelection(selected_month, getAllRangeOptions());
		click(save_selection);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);
		getCustomiseLink(cpi.time_filter).click();
		selectedOptions = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);

	}

	@Test(groups = {"removeSelection"}, dependsOnGroups = {"singleMonth"})
	public void removeSelections() {
		getCustomiseLink(cpi.time_filter).click();
		removeAllSelection();
		singleOrRange(cpi.single_month);
		setSelections();
		selected_year = selectYearMonth(cpi.select_year);
		Assert.assertTrue(!selected_year.equals(""));
		selected_month = selectYearMonth(cpi.select_month);
		Assert.assertTrue(!selected_month.equals(""));
		click(cpi.add_selection);
		removeAllSelection();
		String selectedOptions = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);

	}

	@Test(groups = {"rangeSelection"}, dependsOnGroups = {"removeSelection"})
	public void selectARange() {
		getCustomiseLink(cpi.time_filter).click();
		singleOrRange(cpi.range);
		selectFromDropDown(cpi.select_year);
		selectFromDropDown(cpi.select_month);
		click(cpi.add_selection);
		String selectedOptions = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);
	}

	@Test(groups = {"removerange"}, dependsOnGroups = {"rangeSelection"})
	public void removeSomeRange() {
		getCustomiseLink(cpi.time_filter).click();
		click(addMore);
		singleOrRange(cpi.range);
		selectFromDropDown(cpi.select_year);
		selectFromDropDown(cpi.select_month);
		click(cpi.add_selection);
		String selectedOptions = returnSelectedOptionText();
		click(save_selection);
		System.out.println(selectedOptions);
		System.out.println(getoptionsText(cpi.time_filter));
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);
		getCustomiseLink(cpi.time_filter).click();
		int removeLinks = getRemoveAll_Lists().size();
		if (removeLinks > 1) {
			removeLinks = removeLinks - 1;
		}
		getRemoveAll_Lists().get(RandomStringGen.getRandomInt(removeLinks)).click();
		selectedOptions = returnSelectedOptionText();
		click(save_selection);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);
		System.out.println(selectedOptions);
		System.out.println(getoptionsText(cpi.time_filter));

	}

	@Test(groups = {"removeAll"}, dependsOnGroups = {"removerange"})
	public void removeAllRange() {
		getCustomiseLink(cpi.time_filter).click();
		removeAllSelection();
		String selectedOptions = returnSelectedOptionText();
		click(cancel_button);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);

	}


	@Test//(groups = {"customiseAggregates"}, dependsOnGroups = {"removeAll"})
	public void searchAggregates() throws Exception {
		String defaultSelection = getoptionsText(cpi.spl_aggregate_filter);
		getCustomiseLink(cpi.spl_aggregate_filter).click();
		click(cpi.search_aggregates);
		sendKeys(cpi.search_textBox, cpi.searchKeys1);
		click(cpi.search_button);
		ArrayList <WebElement> checkBoxes = getAllCheckBoxes();
		ArrayList <WebElement> selectedChkBoxes = selectChkBox(RandomStringGen.getRandomInt(checkBoxes.size() - 1));
		click(save_selection);
		click(addMore);
		click(cpi.search_aggregates);
		sendKeys(cpi.search_textBox, cpi.searchKeys2);
		click(cpi.search_button);
		checkBoxes = getAllCheckBoxes();
		selectedChkBoxes = selectChkBox(RandomStringGen.getRandomInt(checkBoxes.size() - 1));
		click(save_selection);
		String selectedOptions = returnSelectedOptionText();
		for (WebElement webTemp : getAllRangeOptions()) {

		}
		click(save_selection);
		Assert.assertEquals(getoptionsText(cpi.spl_aggregate_filter), selectedOptions,
				"Actual selected filters : "
						+ getoptionsText(cpi.spl_aggregate_filter) + "\n" +
						"Expected selected filters : " + selectedOptions);
	}









	public void assertSelection(String selected, ArrayList <WebElement> elementArrayList) {
		int numberOfItems = 0;
		for (WebElement tempElement : elementArrayList) {
			if (tempElement.getText().contains(selected)) {
				numberOfItems++;
			}
		}
		Assert.assertTrue(numberOfItems > 0,
				"Selected year/month :  " + selected + " is not displayed in the selection summary ");

	}

	public void removeAllSelection() {
		ArrayList <WebElement> removeAllList = getRemoveAll_Lists();
		for (WebElement remove : removeAllList) {
			remove.click();
		}
	}

	public String returnSelectedOptionText() {
		String valuetoReturn = null;
		int totalMonths = 0;
		try {
			totalMonths = getRemoveLinks().size();
		} catch (Exception ee) {
		}
		switch (totalMonths) {
			case 0:
				valuetoReturn = "Nothing selected";
				break;
			default:
				valuetoReturn = "Selected options (" + (totalMonths) + ")";
				break;
		}

		return valuetoReturn;

	}

	public String selectYearMonth(By cpiElement) {
		Select dropdownSelect = null;
		int index = 0;
		try {
			dropdownSelect = new Select(getElement(cpiElement));
			ArrayList <WebElement> dropDownOption = (ArrayList <WebElement>) dropdownSelect.getOptions();
			index = RandomStringGen.getRandomInt(dropDownOption.size() - 1) + 1;
			dropdownSelect.selectByIndex(index);
			toRet = dropdownSelect.getOptions().get(index).getAttribute("label");

		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return toRet;
	}

	public void singleOrRange(By selectType) {
		if (!getElement(selectType).isSelected()) {
			click(selectType);
		}
	}


	public void selectFromDropDown(By cpiElement) {
		ArrayList <WebElement> selects = (ArrayList <WebElement>) findElementsBy(cpiElement);
			for (WebElement select : selects) {
				Select dropdownSelect = new Select(select);
				ArrayList <WebElement> dropDownMonths = (ArrayList <WebElement>) dropdownSelect.getOptions();
				int index = RandomStringGen.getRandomInt(dropDownMonths.size() - 1) + 1;
				dropdownSelect.selectByIndex(index);
			}
	}

	@AfterClass
	public void closeTest() {
		TestContext.getDriver().close();
	}


}