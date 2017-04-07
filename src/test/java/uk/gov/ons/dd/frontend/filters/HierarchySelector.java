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
	ArrayList <WebElement> hierarchy = new ArrayList <>();
	public void searchHierarchy(String searchStr) {
		click(search_aggregates);
		sendKeys(search_textBox, searchStr);
		click(search_button);
	}

	// send the link
	public void browseHierarchy(boolean heir) {
		click(browse_aggregates);
		if (heir) {
			topLevelHierarchy();
		} else {
			System.out.println("No links to drill down into the hierarchy");
		}
		}


	private WebElement topLevelHierarchy() {

		WebElement toClick = null;
		try {
			if (isElementPresent(customise_hierarchies)) {
				hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
				while (hierarchy.size() > 0) {
					toClick = hierarchy.get(RandomStringGen.getRandomInt(hierarchy.size() - 1));
					System.out.println("Browse Parent Hierarchy :    " + toClick.getText());
					toClick.click();
					if (!isElementPresent(customise_hierarchies)) {
						break;
					} else {
						hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
					}
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println("END OF HIERARCHIES- FLAT LIST CHECKBOXES");
		}

		return toClick;
	}


	public void addAll() {
		click(add_all);
	}

	// Use this to add the number of checkboxes within a hierarchy


	public ArrayList <String> hierarchyJourney(String filterText, String searchStr, boolean heir, boolean removeAll) throws Exception {
		String defaultSelection = getoptionsText(filterText);
		getCustomiseLink(filterText).click();
		addAll();
		int selectedOptions = getAllRangeOptions().size();
		click(summarySelector.continue_selection);
		Assert.assertTrue(
				defaultSelection.contains(String.valueOf(selectedOptions)), "Options selected by default: " + defaultSelection + ". \n" +
						"Number of options selected by Add All : Everything selected(" + selectedOptions + ")");
		getCustomiseLink(filterText).click();
		Assert.assertTrue(isElementPresent(summarySelector.remove_all_selected), "Summary selector Page - Remove All Selections not present ");
		if (removeAll) {
			click(summarySelector.remove_all_selected);
		} else {
			summarySelector.removeGroups();
		}
		Assert.assertTrue(getElement(summarySelector.save_selection).getText().contains("Add more"), "Does not have the add more button");
		click(summarySelector.save_selection);
		click(cancel_button);
		Assert.assertTrue(
				defaultSelection.contains(String.valueOf(selectedOptions)), "Options selected by default: " + defaultSelection + ". \n" +
						"Number of options selected by Add All : Everything selected(" + selectedOptions + ")");
		getCustomiseLink(filterText).click();
		addAll();
		click(summarySelector.remove_all_selected);
		click(summarySelector.save_selection);
		searchHierarchy(searchStr);
		ArrayList <String> values_selected = selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1), true);
		click(summarySelector.addMore);
		browseHierarchy(heir);
		values_selected.addAll(selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1), true));
		assertSelection(values_selected, getAllRangeOptions());
		click(save_selection);
		return values_selected;
	}

	public ArrayList <String> simpleGeoJourney(String filterText, String searchStr, boolean hier) throws Exception {
		String defaultSelection = getoptionsText(filterText);
		getCustomiseLink(filterText).click();
		searchHierarchy(searchStr);
		ArrayList <String> values_selected = selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1), true);
		click(summarySelector.addMore);
		browseHierarchy(hier);
		values_selected.addAll(selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1), true));
		assertSelection(values_selected, getAllRangeOptions());
		click(save_selection);
		return values_selected;
	}

	public void compareGeoSorting(String filterText, boolean hier, String hierarchyToSelect) throws Exception {
		hierarchyToSelect = hierarchyToSelect.length() > 2 ? hierarchyToSelect : "United Kingdom";
		ArrayList <String> geoValues = new ArrayList <>();
		ArrayList <String> afterAdding = new ArrayList <>();
		getCustomiseLink(filterText).click();
		click(browse_aggregates);
		hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
		for (WebElement webElement : hierarchy) {
			geoValues.add(webElement.getText());
		}
		for (WebElement webElement : hierarchy) {
			if (webElement.getText().contains(hierarchyToSelect)) {
				webElement.click();
				if (hierarchyToSelect.equals("Unitary Authority") || hierarchyToSelect.contains("Westminster")) {
					Assert.assertTrue(isElementPresent(customise_hierarchies), hierarchyToSelect + " does not have multiple hierarchies within it");
				}

				break;
			}
		}
		topLevelHierarchy();
		selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1), true);
		click(summarySelector.addMore);
		click(browse_aggregates);
		hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
		for (int index = 0; index < hierarchy.size(); index++) {
			Assert.assertTrue(hierarchy.get(index).getText().equalsIgnoreCase(geoValues.get(index)), "The list is not ordered for geography browse page");
		}
		click(cancel_button);
		getCustomiseLink(filterText).click();
		summarySelector.removeGroups();
		click(save_selection);
		click(cancel_button);
	}

	public void browseAndSelectOne(String filterText, boolean hier) throws Exception {
		getCustomiseLink(filterText).click();
		click(browse_aggregates);
		ArrayList <WebElement> parent_hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
		ArrayList <String> selectedValues = new ArrayList <>();
		ArrayList <String> parentHier_Texts = new ArrayList <>();
		for (WebElement webElement : parent_hierarchy) {
			parentHier_Texts.add(webElement.getText());
		}
		for (String textValues : parentHier_Texts) {
			System.out.println(textValues);
			hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
			for (WebElement webElement : hierarchy) {
				if (webElement.getText().equalsIgnoreCase(textValues)) {
					webElement.click();
					break;
				}
			}
			topLevelHierarchy();
			ArrayList <String> selections = selectRandomChkBox(RandomStringGen.getRandomInt(
					getAllCheckBoxes().size() - 1), true);
			click(summarySelector.addMore);
			click(browse_aggregates);
			for (String select : selections) {
				selectedValues.add(select);
			}
		}
		click(cancel_button);
		getCustomiseLink(filterText).click();
		ArrayList <String> summarySelections = new ArrayList <>();
		for (WebElement webElement : summarySelector.getAllRangeOptions()) {
			summarySelections.add(webElement.getText());
		}
		String selected = null;

		for (String selectedVal : selectedValues) {
			String[] temp = selectedVal.split(",");
			if (temp.length == 3) {

				selected = temp[1].trim() + ", " + temp[2].trim();
			} else {
				selected = temp[1];
			}
			System.out.println("Selected Value " + selected);
			Assert.assertTrue(summarySelections.contains(selected), "Selected Value " + selected + " is not in the selection summary");
		}
	}

}
