package uk.gov.ons.dd.frontend.pages;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.ons.dd.frontend.core.Configuration;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.util.Do;
import uk.gov.ons.dd.frontend.util.Helper;
import uk.gov.ons.dd.frontend.util.PropertyReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BasePage {
	public PropertyReader propertyReader = new PropertyReader(getConfig());
	/* ***
	COMMON PAGE ELEMENTS


	*/
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
	// ************ Save Selection -- Selection Summary
	public By removeButton = getElementLocator("remove_css");
	public By selectionHeader = getElementLocator("header_summary_css");
	public By selectionOptions = getElementLocator("selected_summary_css");
	public By addMore = getElementLocator("add_more_linkText");
	// ***********  Download Options  ******************
	public By download_complete_dataset = getElementLocator("download_dataset_linkText");
	public By choose_download_format = getElementLocator("choose_download_format_linkText");
	public By help_with_file_formats = getElementLocator("help_with_files_css");
	public By generate_file = getElementLocator("generate_files_linkText");
	public By file_options_help_text = getElementLocator("file_options_help_css");
	public By selected_checkboxes_css = getElementLocator("checkbox_selected_css");
	public By file_download_button_options = getElementLocator("file_format_download_css");
	// ****** Selected Text Options
	public String files_available_for_download = getTextFromProperty("file_available_for_download_text");
	// ****** Error Message
	public String error_message_text = getTextFromProperty("error_message_text");
	private ArrayList <WebElement> filterNames = new ArrayList <>();
	private ArrayList <WebElement> selectedOptions = new ArrayList <>();
	private ArrayList <WebElement> customiseLinks = new ArrayList <>();

	public WebDriver getDriver() {
		return TestContext.getDriver();
	}

	public Configuration getConfig() {
		return TestContext.getConfiguration();
	}

	public WebDriverWait getWebDriverWait() {
		return TestContext.getWebDriverWait();
	}

	public By getlinkText(String link) {
		return By.linkText(link);
	}

	public WebElement getElement(final By by) {
		Do.until(getDriver(), presenceOfElementLocated(by));
		return getDriver().findElement(by);
	}

	public WebElement getElement(final By by, long timeout) {
		return Do.until(getDriver(), ExpectedConditions.presenceOfElementLocated(by), timeout);
	}

	public void refresh() {
		getDriver().navigate().refresh();
	}

	public void refreshAfterSecs(int seconds) {
		sleepInSecs(seconds);
		getDriver().navigate().refresh();
	}

	public void sleepInSecs(int secs) {
		try {
			Thread.sleep(1000 * secs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifyUrlContains(String contains) {
		getWebDriverWait().until(urlContains(contains));
	}

	public void verifyElementNotVisible(By... byItems) {
		for (By by : byItems) {
			Do.until(getDriver(), invisibilityOfElementLocated(by));
		}
	}

	public void verifyElementsNotPresent(By... byItems) {
		try {
			for (By by : byItems) {
				List <WebElement> elements = getDriver().findElements(by);
				if (!elements.isEmpty()) {
					System.out.println("No Element Present");

				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public void verifyElementsPresent(By... byItems) {
		for (By by : byItems) {
			Do.until(getDriver(), presenceOfElementLocated(by));
		}
	}

	public boolean isElementNotPresent(By by) {
		return getDriver().findElements(by).isEmpty();
	}

	public boolean isElementPresent(By by) {
		try {
			final WebElement element = getElement(by);
			return element != null && element.isDisplayed();
		} catch (Exception exception) {
			return false;
		}
	}

	public boolean isElementPresentWithOutWait(By by) {
		try {
			return getDriver().findElement(by).isDisplayed();
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	public boolean isAttributePresent(By elementCss, String attributeName) {
		final WebElement element = getElement(elementCss);
		return element != null && element.isDisplayed() && StringUtils.isNotBlank(element.getAttribute(attributeName));
	}

	public boolean isAttributePresent(By elementCss, String attributeName, String attributeValue) {
		final WebElement element = getElement(elementCss);
		return element != null && element.isDisplayed() && StringUtils.isNotBlank(element.getAttribute(attributeName)) &&
				((element.getAttribute(attributeName)).contains(attributeValue));
	}

	public boolean isTextPresent(By by, Long timeout) {
		WebElement element = getElement(by, timeout);
		return element != null && element.isDisplayed() && StringUtils.isNotBlank(element.getText());
	}

	public boolean isTextPresent(By by) {

		final WebElement element = getElement(by);
		return element != null && element.isDisplayed() && StringUtils.isNotBlank(element.getText());
	}

	public String getAttributeText(By by, String attribute) {
		return getElement(by).getAttribute(attribute);
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}

	public boolean pageSourceContains(String text) {
		return getDriver().getPageSource().contains(text);
	}

	public void clear(By by) {
		Do.until(getDriver(), presenceOfElementLocated(by));
		getElement(by).clear();
	}

	public void click(By by) {
		Do.until(getDriver(), presenceOfElementLocated(by));
		getElement(by).click();
	}

	public void sendKeys(By by, String text) {
		Do.until(getDriver(), presenceOfElementLocated(by));
		getElement(by).sendKeys(text);
	}

	public List <WebElement> findElementsBy(By by) {
		Do.until(getDriver(), presenceOfElementLocated(by));
		return getDriver().findElements(by);
	}

	public List <WebElement> findElementsByWithoutWait(By by) {
		return getDriver().findElements(by);
	}

	public String getElementText(By by) {
		Do.until(getDriver(), presenceOfElementLocated(by));
		return getElement(by).getText();
	}

	public boolean waitUntilTextPresent(By by, String text) {
		return getWebDriverWait().until(textToBePresentInElementLocated(by, text));
	}

	public boolean isEnabled(By by) {
		try {
			return getElement(by).isEnabled();
		} catch (final NoSuchElementException e) {
			return false;
		}
	}

	public void deleteCookies() {
		getDriver().manage().deleteAllCookies();
	}

	public void fillWithValue(By by, String value) {
		try {
			clear(by);
		} catch (Exception ex) {
			// some elements can't be cleared - eg. dropdowns
		}
		WebElement element = getElement(by);

		if (value != null) {
			element.sendKeys(value);
		}
	}

	public void select(By by, String value) {
		Helper.pause(1000);
		try {
			WebElement element = getElement(by);
			Select select = new Select(element);
			select.selectByVisibleText(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deselect(By by) {
		WebElement webElement = getElement(by);
		if (getElement(by).isSelected()) {
			webElement.click();
		}
	}

	public void select(By by) {
		if (!getElement(by).isSelected()) {
			click(by);
		}
	}

	public By getContentId(String cssString, String text) {
		String content = cssString.replace("text_to_replace", text);
		return By.cssSelector(content);
	}

	public void ClickOnLink(final String linkText) throws InterruptedException {
		getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
		getElement(By.linkText(linkText)).click();
	}

	public void getTextFromPageSource(String text) {

		getDriver().getPageSource().contains(text);

	}

	public String getPageSource() {
		return getDriver().getPageSource();
	}

	public void openANewTab() {
		getElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	}

	public void switchToNewTab() {
		ArrayList <String> tabs = new ArrayList <>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));

	}

	public By getElementLocator(String locator) {
		By elementToReturn = null;
		String propValue = propertyReader.getValue(locator);
		if (locator.contains("_xpath")) {
			elementToReturn = By.xpath(propValue);
		} else if (locator.contains("_id")) {
			elementToReturn = By.id(propValue);
		} else if (locator.contains("_linkText")) {
			elementToReturn = By.linkText(propValue);
		} else if (locator.contains("_css")) {
			elementToReturn = By.cssSelector(propValue);
		} else if (locator.contains("_className")) {
			elementToReturn = By.className(propValue);
		}
		return elementToReturn;
	}

	public String getTextFromProperty(String locator) {
		return propertyReader.getValue(locator);
	}

	private int getFilterNameIndex(String filterString) {
		int indexToReturn = 0;
		try {
			filterNames = (ArrayList <WebElement>) findElementsBy(filter_name_css);
			for (int index = 0; index < filterNames.size(); index++) {
				if (filterNames.get(index).getText().equalsIgnoreCase(filterString)) {
					indexToReturn = index;
					break;
				}
			}
		} catch (Exception ee) {
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

	public ArrayList <WebElement> getAllCheckBoxes() throws Exception {
		return (ArrayList <WebElement>) findElementsBy(checkboxes);
	}

	public void selectCheckBox(int num) throws Exception {
		getAllCheckBoxes().get(num).click();
	}

	public ArrayList <WebElement> getRemoveLinks() throws Exception {
		ArrayList <WebElement> removeButtons = (ArrayList <WebElement>) findElementsBy(removeButton);
		Iterator <WebElement> iter = removeButtons.iterator();
		while (iter.hasNext()) {
			WebElement webTemp = iter.next();

			if (webTemp.getText().contains("Remove all"))
				iter.remove();
		}
		return removeButtons;
	}

	public ArrayList <WebElement> getRemoveAll_Lists() {
		ArrayList <WebElement> removeButtons = (ArrayList <WebElement>) findElementsBy(removeButton);
		ArrayList <WebElement> removeAllLinks = new ArrayList <>();
		for (WebElement webTemp : removeButtons) {
			if (webTemp.getText().contains("Remove all")) {
				removeAllLinks.add(webTemp);
			}
		}
		return removeAllLinks;
	}


	public ArrayList <WebElement> getAllRangeHeaders() {

		return (ArrayList <WebElement>) findElementsBy(selectionHeader);
	}

	public ArrayList <WebElement> getAllRangeOptions() {
		return (ArrayList <WebElement>) findElementsBy(selectionOptions);
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


	public void navigateToUrl(String url) {
		getDriver().get(url);
		Cookie splashCookie = new Cookie("splash", "y");
		getDriver().manage().addCookie(splashCookie);
		refresh();
	}


	public ArrayList <WebElement> selectChkBox(int... checkBox) throws Exception {
		ArrayList <WebElement> checkBoxesSelected = new ArrayList <>();
		for (int checkOption : checkBox) {
			selectCheckBox(checkOption);
		}
		for (WebElement webElement : findElementsBy(selected_checkboxes_css)) {
			checkBoxesSelected.add(webElement);
		}
		return checkBoxesSelected;
	}

}


