package uk.gov.ons.dd.frontend.filters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import uk.gov.ons.dd.frontend.pages.BasePage;

import java.util.ArrayList;


public class SummarySelector extends BasePage {

	public By addMore = getElementLocator("add_more_css");
	public By continue_selection = getElementLocator("save_selection_css");

	public void removeAll() {
		ArrayList <WebElement> removeAllLists = getRemoveAll_Lists();
		for (WebElement removeLink : removeAllLists) {
			removeLink.click();
		}

	}

	public ArrayList <String> selectedOptions(String filterText, boolean hierarchy) {
		getCustomiseLink(filterText).click();
		ArrayList <String> selectedValues = new ArrayList <>();
		if (hierarchy) {
			for (WebElement webTemp : getAllRangeOptions()) {
				selectedValues.add(webTemp.getText());
			}
		} else {
			for (WebElement webTemp : getAllSelectedChkBoxes()) {
				selectedValues.add(webTemp.getText());
			}
		}
		click(continue_selection);
		return selectedValues;

	}


}
