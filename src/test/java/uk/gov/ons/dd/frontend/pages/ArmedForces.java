package uk.gov.ons.dd.frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ArmedForces extends BasePage {

	// ***** Armed Forces dataset
	public By armedForces_link = getElementLocator("armed_forces_linkText");

	// *******Customise Options
	public By customise_data_set = getElementLocator("customise_dataset_linkText");
	public By back_link = getElementLocator("back_button_linkText");
	public By filter_name_css = getElementLocator("filter_name_css");
	public By selected_options_css = getElementLocator("filter_selectedOptions_css");
	public By customise_links = getElementLocator("customise_filter_css");
	public By enable_all = getElementLocator("enable_all_xpath");
	public By disable_all = getElementLocator("disable_all_xpath");
	public By enable_all_disabled = getElementLocator("enable_all_disb_xpath");
	public By disable_all_disabled = getElementLocator("disable_all_disb_xpath");
	public By error_message = getElementLocator("error_msg_css");
	public By save_selection = getElementLocator("save_selection_linkText");
	public By cancel_button = getElementLocator("cancel_button_linkText");
	public By checkboxes = getElementLocator("checkboxes_css");

	// ***********  Download Options  ******************
	public By download_complete_dataset = getElementLocator("download_dataset_linkText");
	public By choose_download_format = getElementLocator("choose_download_format_linkText");
	public By help_with_file_formats = getElementLocator("help_with_files_css");
	public By generate_file = getElementLocator("generate_files_linkText");
	public By file_options_help_text = getElementLocator("file_options_help_css");
	public By selected_checkboxes_css = getElementLocator("checkbox_selected_css");
	public By file_download_button_options = getElementLocator("file_format_download_css");


	// ****** Selected Text Options
//	public String selectedOptions1_text = getTextFromProperty("selected_options_1_text");
//	public String selectedOptions2_text = getTextFromProperty("selected_options_2_text");
//	public String selectedOptions3_text = getTextFromProperty("selected_options_3_text");
//	public String selectedOptions4_text = getTextFromProperty("selected_options_4_text");
//	public String everything_selected2_text = getTextFromProperty("everything_selected_2_text");
//	public String everything_selected3_text = getTextFromProperty("everything_selected_3_text");
//	public String everything_selected5_text = getTextFromProperty("everything_selected_5_text");
	public String files_available_for_download = getTextFromProperty("file_available_for_download_text");

	// ********** Filter options
	public String sex_filter = getTextFromProperty("sex_filter_text");

	public String residence_filter = getTextFromProperty("residence_filter_text");
	public String age_filter = getTextFromProperty("age_filter_text");

	// ****** Error Message
	public String error_message_text = getTextFromProperty("error_message_text");
	private ArrayList <WebElement> filterNames = new ArrayList <>();
	private ArrayList <WebElement> selectedOptions = new ArrayList <>();
	private ArrayList <WebElement> customiseLinks = new ArrayList <>();

	private int getFilterNameIndex(String filterString) {
		int indexToReturn = 0;
		filterNames = (ArrayList <WebElement>) findElementsBy(filter_name_css);
		for (int index = 0; index < filterNames.size(); index++) {
			if (filterNames.get(index).getText().equalsIgnoreCase(filterString)) {
				indexToReturn = index;
				break;
			}
		}
		return indexToReturn;
	}

	public String getoptionsText(String filter) {

		selectedOptions = (ArrayList <WebElement>) findElementsBy(selected_options_css);
		return selectedOptions.get(getFilterNameIndex(filter)).getText();
	}

	public WebElement getCustomiseLink(String filter) {
		customiseLinks = (ArrayList <WebElement>) findElementsBy(customise_links);
		return customiseLinks.get(getFilterNameIndex(filter));
	}

	public ArrayList <WebElement> getAllCheckBoxes() {
		return (ArrayList <WebElement>) findElementsBy(checkboxes);
	}

	public void selectCheckBox(int num) {
		getAllCheckBoxes().get(num).click();
	}

}