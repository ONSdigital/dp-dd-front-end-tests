package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.pages.BasePage;
import uk.gov.ons.dd.frontend.pages.CPI;
import uk.gov.ons.dd.frontend.util.RandomStringGen;

import java.util.ArrayList;


public class CPITests extends BasePage {
	public String nace = getTextFromProperty("nace_filter_text");
	public String prodcom = getTextFromProperty("prodcom_filter_text");
	public String searchKey1 = getTextFromProperty("nace_searchkey_text");
	public String prodcom_searchKey = getTextFromProperty("prodcom_searchkey_text");

	CPI cpi = new CPI();
	String selectedOption = null;
	String selected_year = null, selected_month = null;
	ArrayList <String> selectedNames = new ArrayList <>();
	String toRet = null;
	HierarchySelector hierarchySelector = new HierarchySelector();
	SummarySelector summarySelector = new SummarySelector();
	ArrayList <String> nace_options = null;

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

	//	@Test(groups = "cancelSelection")
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

	//	@Test(groups = {"singleMonth"})//, dependsOnGroups = {"cancelSelection"})
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

	//	@Test(groups = {"removeSelection"}, dependsOnGroups = {"singleMonth"})
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

	//	@Test(groups = {"rangeSelection"}, dependsOnGroups = {"removeSelection"})
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

	//	@Test(groups = {"removerange"}, dependsOnGroups = {"rangeSelection"})
	public void removeSomeRange() {
		getCustomiseLink(cpi.time_filter).click();
		click(new SummarySelector().addMore);
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

	//	@Test(groups = {"removeAll"}, dependsOnGroups = {"removerange"})
	public void removeAllRange() {
		getCustomiseLink(cpi.time_filter).click();
		removeAllSelection();
		String selectedOptions = returnSelectedOptionText();
		click(cancel_button);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);
		click(back_link);

	}

	@Test(groups = {"customiseNACE"})
	public void customiseNACE() {
		try {
			hierarchySelector.hierarchyJourney(nace, searchKey1);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
		}
	}

	@Test(groups = {"customiseProdCom"}, dependsOnGroups = {"customiseNACE"})
	public void customiseProdCom() {
//		try {
//			hierarchySelector.hierarchyJourney(prodcom, prodcom_searchKey);
//		} catch (Exception ee) {
//			ee.printStackTrace();
//			Assert.fail("Exception caught in " + getClass().getSimpleName().toUpperCase());
//		}
	}

	@Test(groups = {"getOptions"}, dependsOnGroups = {"customiseProdCom"})
	public void getSelectedOptions() {
		nace_options = summarySelector.selectedOptions(nace, true);

	}

	@Test(groups = {"verifyData"}, dependsOnGroups = {"getOptions"})
	public void downloadAndVerifyData() {
		click(choose_download_format);
		try {
			ArrayList <WebElement> selectedChkBox = selectChkBox(1);
			assertLastPage(getCheckBoxValues(selectedChkBox));
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail();
		}
		FileChecker fileChecker = new FileChecker();
		String url = getElement(csv_file_download).getAttribute("href");
		String[] urlSplit = url.split("/");
		String fileName = urlSplit[urlSplit.length - 1];
		try {
			fileChecker.getFile(url, fileName);
			fileChecker.checkForFilter(nace_options, nace, fileName);
		} catch (Exception ee) {
			ee.printStackTrace();
			Assert.fail();
		}
	}

	//	@Test(groups = {"selectOptions"}, dependsOnGroups = {"addAllAggregates"})
	public void selectAndBack() {
		refresh();
		getCustomiseLink(cpi.spl_aggregate_filter).click();
		click(cpi.add_all_aggregates);
		click(back_link);
		click(cpi.search_aggregates);
		click(back_link);
		click(cpi.browse_aggregates);
		click(back_link);
		click(back_link);
		Assert.assertTrue(getCustomiseLink(cpi.spl_aggregate_filter).isDisplayed());

	}


	public ArrayList <WebElement> hierarchySearch(By hierarchy, String searchString) throws Exception {
		click(hierarchy);
		//	click(back_link);
//		click(hierarchy);
		sendKeys(cpi.search_textBox, searchString);
		click(cpi.search_button);
		return selectBox();
	}

	public ArrayList <WebElement> hierarchyBrowse() throws Exception {
		click(cpi.browse_aggregates);
		int random = RandomStringGen.getRandomInt(browseHierarchies().size() - 1);
		browseHierarchies().get(random).click();
		return selectBox();
	}

	public ArrayList <WebElement> selectBox() throws Exception {
		ArrayList <WebElement> checkBoxes = getAllCheckBoxes();
		ArrayList <WebElement> toReturn = selectChkBox(RandomStringGen.getRandomInt(checkBoxes.size() - 1));
		getNamesOfSelectedChkBox(toReturn);
		click(save_selection);
		return toReturn;
	}

	public ArrayList <WebElement> drillDownHierarchy(ArrayList <WebElement> allHierarchy) throws Exception {
		try {
			allHierarchy = browseHierarchies();
			int random = RandomStringGen.getRandomInt(allHierarchy.size() - 1);
			allHierarchy.get(random).click();
			return allHierarchy;
		} catch (Exception ee) {

		} finally {
			return selectBox();
		}
	}

	public ArrayList <WebElement> browseHierarchies() throws Exception {
		return (ArrayList <WebElement>) findElementsBy(cpi.customise_hierarchies);
	}

	public void getNamesOfSelectedChkBox(ArrayList <WebElement> checkBoxes) {
		for (WebElement webElement : checkBoxes) {
			String labelElement = selected_chkBox_label.replace("id", webElement.getAttribute("id"));
			selectedNames.add(getElement(By.cssSelector(labelElement)).getText());
		}
	}

	public void assertSelection(String selected, ArrayList <WebElement> elementArrayList) {
		int numberOfItems = 0;
		for (WebElement tempElement : elementArrayList) {
			if (tempElement.getText().toUpperCase().contains(selected.toUpperCase())) {
				numberOfItems++;
			}
		}
		Assert.assertTrue(numberOfItems > 0,
				"Selected Option :  " + selected + " is not displayed in the selection summary ");

	}

	public void removeAllSelection() {
		ArrayList <WebElement> removeAllList = getRemoveAll_Lists();
		for (WebElement remove : removeAllList) {
			remove.click();
		}
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
