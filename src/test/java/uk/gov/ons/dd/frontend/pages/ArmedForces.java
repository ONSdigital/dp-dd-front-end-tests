package uk.gov.ons.dd.frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ArmedForces extends BasePage{


	public By armedForces_link = getElementLocator("armed_forces_linkText");
	public By customise_data_set = getElementLocator("customise_dataset_linkText");
	public By back_link = getElementLocator("back_button_linkText");
	public By filter_name_css = getElementLocator("filter_name_css");
	public By selected_options_css = getElementLocator("filter_selectedOptions_css");
	public By customise_links = getElementLocator("customise_filter_css");
	public By male = getElementLocator("male_filter_id");
	public By female = getElementLocator("female_filter_id");
	public By enable_all = getElementLocator("enable_all_xpath");
	public By disable_all = getElementLocator("disable_all_xpath");
	public By enable_all_disabled = getElementLocator("enable_all_disb_xpath");
	public By disable_all_disabled = getElementLocator("disable_all_disb_xpath");
	public By error_message = getElementLocator("error_msg_css");
	public By save_selection = getElementLocator("save_selection_linkText");
	public By cancel_button = getElementLocator("cancel_button_linkText");
	public By checkboxes = getElementLocator("checkboxes_css");
	public String selectedOptions1_text = getTextFromProperty("selected_options_1_text");
	public String selectedOptions2_text = getTextFromProperty("selected_options_2_text");
	public String selectedOptions3_text = getTextFromProperty("selected_options_3_text");
	public String selectedOptions4_text = getTextFromProperty("selected_options_4_text");
	public String everything_selected2_text = getTextFromProperty("everything_selected_2_text");
	public String everything_selected3_text = getTextFromProperty("everything_selected_3_text");
	public String everything_selected5_text = getTextFromProperty("everything_selected_5_text");
	public String sex_filter = getTextFromProperty("sex_filter_text");
	public String error_message_text = getTextFromProperty("error_message_text");
	public String residence_filter = getTextFromProperty("residence_filter_text");
	public String age_filter = getTextFromProperty("age_filter_text");
	private ArrayList<WebElement> filterNames = new ArrayList <WebElement>();
	private ArrayList<WebElement> selectedOptions = new ArrayList <WebElement>();
	private ArrayList<WebElement> customiseLinks = new ArrayList <WebElement>();

	public By getId(String id) {
		return By.id(id);
	}

	public int getFilterNameIndex(String filterString){
		int indexToReturn = 0;
		filterNames = (ArrayList<WebElement>) findElementsBy(filter_name_css);
		for(int index = 0; index<filterNames.size(); index++){
			if(filterNames.get(index).getText().equalsIgnoreCase(filterString)){
				indexToReturn = index;
				break;
			}
		}
		return indexToReturn;
	}

	public String getoptionsText(String filter){

		selectedOptions = (ArrayList<WebElement>) findElementsBy(selected_options_css);
		return selectedOptions.get(getFilterNameIndex(filter)).getText();
	}

	public WebElement getCustomiseLink(String filter){
		customiseLinks = (ArrayList<WebElement>) findElementsBy(customise_links);
		return customiseLinks.get(getFilterNameIndex(filter));
	}

	public ArrayList <WebElement> getAllCheckBoxes() {
		return (ArrayList <WebElement>) findElementsBy(checkboxes);
	}

	public void selectCheckBox(int num) {
		getAllCheckBoxes().get(num).click();
	}

	public enum Sex {
		DO000153, DO000154
	}

	public enum Residence_Type {
		DO000161, DO000162, DO000163
	}

	public enum Age {
		DO000265, DO000266, DO000267, DO000268, DO000269
	}
}