package uk.gov.ons.dd.frontend.filters;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
			hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
			while (hierarchy.size() > 0) {
				toClick = hierarchy.get(RandomStringGen.getRandomInt(hierarchy.size() - 1));
				System.out.println("Browse Parent Hierarchy :    " + toClick.getText());
					toClick.click();
				if (!isElementPresent(customise_hierarchies)) {
					break;
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
		Assert.assertTrue("Options selected by default: " + defaultSelection + ". \n" +
						"Number of options selected by Add All : Everything selected(" + selectedOptions + ")",
				defaultSelection.contains(String.valueOf(selectedOptions)));
		getCustomiseLink(filterText).click();
		Assert.assertTrue("Summary selector Page - Remove All Selections not present ", isElementPresent(summarySelector.remove_all_selected));
		if (removeAll) {
			click(summarySelector.remove_all_selected);
		} else {
			summarySelector.removeGroups();
		}
		Assert.assertTrue("Does not have the add more button", getElement(summarySelector.save_selection).getText().contains("Add more"));
		click(summarySelector.save_selection);
		click(cancel_button);
		Assert.assertTrue("Options selected by default: " + defaultSelection + ". \n" +
						"Number of options selected by Add All : Everything selected(" + selectedOptions + ")",
				defaultSelection.contains(String.valueOf(selectedOptions)));
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

	public void compareGeoSorting(String filterText, boolean hier) throws Exception {
		ArrayList <String> geoValues = new ArrayList <>();
		ArrayList <String> afterAdding = new ArrayList <>();
		getCustomiseLink(filterText).click();
		click(browse_aggregates);
		hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
		for (WebElement webElement : hierarchy) {
			geoValues.add(webElement.getText());
		}
		for (WebElement webElement : hierarchy) {
			if (webElement.getText().contains("United Kingdom")) {
				webElement.click();
				break;
			}
		}
		selectRandomChkBox(RandomStringGen.getRandomInt(
				getAllCheckBoxes().size() - 1), true);
		click(summarySelector.addMore);
		click(browse_aggregates);
		hierarchy = (ArrayList <WebElement>) findElementsBy(customise_hierarchies);
		for (int index = 0; index < hierarchy.size(); index++) {
			Assert.assertTrue("The list is not ordered for geography browse page", hierarchy.get(index).getText().equalsIgnoreCase(geoValues.get(index)));
		}
	}


}
