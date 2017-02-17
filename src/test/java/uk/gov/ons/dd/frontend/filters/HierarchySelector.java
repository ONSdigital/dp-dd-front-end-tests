package uk.gov.ons.dd.frontend.filters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import uk.gov.ons.dd.frontend.pages.BasePage;
import uk.gov.ons.dd.frontend.util.RandomStringGen;

import java.util.ArrayList;


public class HierarchySelector extends BasePage {

	public By search_aggregates = getElementLocator("search_linkText");
	public By search_textBox = getElementLocator("search_textbox_css");
	public By search_button = getElementLocator("search_button_css");
	public By browse_aggregates = getElementLocator("browse_linkText");
	public By customise_hierarchies = getElementLocator("customise_hierarchies_css");
	public By add_all = getElementLocator("add_all_linkText");
	public String selected_chkBox_label = getTextFromProperty("label_selected_chkbox_css");
	SummarySelector summarySelector = new SummarySelector();

	public void searchHierarchy(String searchStr) {
		click(search_aggregates);
		sendKeys(search_textBox, searchStr);
		click(search_button);
	}

	// send the link
	public void browseHierarchy(boolean geography) {
		click(browse_aggregates);
		if (!geography) {
			topLevelHierarchy().click();
		}
	}

	private WebElement topLevelHierarchy() {
		ArrayList <WebElement> hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
		return hierarchy.get(RandomStringGen.getRandomInt(hierarchy.size() - 1));
	}


	public void addAll() {
		click(add_all);
	}

	// Use this to add the number of checkboxes within a hierarchy

	public ArrayList <String> selectRandomChkBox(int num) throws Exception {
		ArrayList <String> selectedValues = new ArrayList <>();
		ArrayList <WebElement> selectedElements = selectChkBox(num);
		for (WebElement webElement : selectedElements) {
			String labelElement = selected_chkBox_label.replace("id", webElement.getAttribute("id"));
			selectedValues.add(getElement(By.cssSelector(labelElement)).getText());
		}
		click(save_selection);
		return selectedValues;
	}


	public void hierarchyJourney(String filterText, String searchStr) throws Exception {
		String defaultSelection = getoptionsText(filterText);
		getCustomiseLink(filterText).click();
		addAll();
		int selectedOptions = getAllRangeOptions().size();
		click(summarySelector.continue_selection);
//		Assert.assertTrue(defaultSelection.contains(String.valueOf(selectedOptions)),
//				"Options selected by default: "+defaultSelection+ ". \n" +
//				"Number of options selected by Add All : Everything selected("+selectedOptions+")");
		getCustomiseLink(filterText).click();
		summarySelector.removeAll();
		browserBack();
		Assert.assertEquals(getoptionsText(filterText), "Nothing selected",
				"Actual selected filters : "
						+ getoptionsText(filterText) + "\n" +
						"Expected selected filters : " + "Nothing selected");
		getCustomiseLink(filterText).click();
		searchHierarchy(searchStr);
		ArrayList <String> values_selected = selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1));
		click(summarySelector.addMore);
		browseHierarchy(false);
		ArrayList <String> browsed_selection = selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1));
		assertSelection(values_selected, getAllRangeOptions());
		assertSelection(browsed_selection, getAllRangeOptions());
		click(save_selection);
	}

	public void assertSelection(ArrayList <String> selected, ArrayList <WebElement> elementArrayList) {
		ArrayList <String> compareArrayList = new ArrayList <>();
		for (WebElement tempElement : elementArrayList) {
			compareArrayList.add(tempElement.getText());
		}
		for (String tempStr : selected) {
			Assert.assertTrue(compareArrayList.contains(tempStr),
					"Selected Option : " + tempStr + "is not displayed in the selection summary ");
		}
	}
}
