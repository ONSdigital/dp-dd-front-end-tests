package uk.gov.ons.dd.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ArmedForces extends BasePage{

	public By armedForces_link = By.linkText("Members of the Armed Forces");
	public By customise_data_set = By.linkText("Customise this dataset");
	public By back_link = By.linkText("Back");
	public By filter_name_css = By.cssSelector("div.col--lg-8 > strong");
	public By selected_options_css = By.cssSelector("div.col--lg-25");
	public By customise_links = By.cssSelector("div.col--lg-6 > a");
	public By male = By.id("DO000153");
	public By female = By.id("DO000154");
	public By enable_all_disabled = By.xpath("//span[contains(text(),'Enable all')]");
	public By disable_all_disabled = By.xpath("//span[contains(text(),'Disable all')]");
	public By error_message = By.cssSelector("div.error__message");
	public enum Sex{
		DO000153,DO000154
	}
	public enum Residence_Type{
		DO000161,DO000162,DO000163
	}
	public enum Age{
		DO000265, DO000266,DO000267,DO000268, DO000269
	}
	public By getId(String id){
		return By.id(id);
	}
	public By getLink(String textToReplace){
		String text = "//a[contains(text(),'replace')]";
		return By.xpath(text.replace("replace", textToReplace));
	}

	private ArrayList<WebElement> filterNames = new ArrayList <WebElement>();
	private ArrayList<WebElement> selectedOptions = new ArrayList <WebElement>();
	private ArrayList<WebElement> customiseLinks = new ArrayList <WebElement>();

	public int getFilterNameIndex(String filterString){
		int indexToReturn = 0;
		filterNames = (ArrayList<WebElement>) findElementsBy(filter_name_css);
		for(int index =0; index<filterNames.size(); index++){
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


}
