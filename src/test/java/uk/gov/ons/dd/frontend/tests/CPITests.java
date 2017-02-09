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
	String selected_year, selected_month;
	int selectsize = 0;

	@BeforeTest
	public void openPage() {
		navigateToUrl(getConfig().getBaseURL());
		click(cpi.cpi_link);
		switchToLatestWindow();
		click(customise_data_set);

	}

	@Test(groups = "singleMonth")
	public void customiseSingleMonth() {
		String defaultSelection = getoptionsText(cpi.time_filter);
		getCustomiseLink(cpi.time_filter).click();
		selectSingleMonth(cpi.single_month);
		click(cancel_button);
		Assert.assertEquals(getoptionsText(cpi.time_filter), defaultSelection,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + defaultSelection);
		getCustomiseLink(cpi.time_filter).click();
		selectSingleMonth(cpi.single_month);
		click(cpi.add_selection);
		String selectedOptions = returnSelectedOptionText();
		Assert.assertTrue(getAllRangeHeaders().contains(selected_year),
				"Selected year:  " + selected_year + " is not showing in the selection summary ");
		Assert.assertTrue(getAllRangeOptions().contains(selected_month),
				"Selected month:  " + selected_month + " is not showing in the selection summary ");
		click(save_selection);
		Assert.assertEquals(getoptionsText(cpi.time_filter), selectedOptions,
				"Actual Time filters : "
						+ getoptionsText(cpi.time_filter) + "\n" +
						"Expected Time filters : " + selectedOptions);
		getCustomiseLink(cpi.time_filter).click();
		selectSingleMonth(cpi.single_month);
		click(cpi.add_selection);
		removeAllSelection();


		click(save_selection);


		selectSingleMonth(cpi.range);
		//		click(cpi.add_selection);
		// validate selection
//		getCustomiseLink(cpi.time_filter).click();
		selectAValue(cpi.single_month);
		click(cancel_button);
		getCustomiseLink(cpi.time_filter).click();
		//validate cancel
		selectAValue(cpi.range);
		click(cancel_button);
		// validate cancel


	}

	public void removeAllSelection() {
		for (WebElement remove : getRemoveAll_Lists()) {
			remove.click();
		}
	}

	public String returnSelectedOptionText() {
		String valuetoReturn = null;
		int totalMonths = getRemoveLinks().size();
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

	public String selectAValue(By cpiElement) {
		Select dropdownSelect = new Select(getElement(cpiElement));
		ArrayList <WebElement> dropDownOption = (ArrayList <WebElement>) dropdownSelect.getOptions();
		int index = RandomStringGen.getRandomInt(dropDownOption.size());
		dropdownSelect.selectByIndex(index);
		return dropdownSelect.getFirstSelectedOption().getText();
	}

	public void selectSingleMonth(By selection) {
		if (!getElement(cpi.single_month).isSelected()) {
			click(cpi.single_month);
		}
		selected_year = selectAValue(cpi.select_year);
		selected_month = selectAValue(cpi.select_month);
	}

	public void selectsAndDropDowns() {
		ArrayList <WebElement> selects = (ArrayList <WebElement>) findElementsBy(By.cssSelector("select"));
		if (selectsize < selects.size()) {
			for (WebElement select : selects) {
				Select dropdownSelect = new Select(select);
				//		System.out.println("Before Selection : "+dropdownSelect.getFirstSelectedOption().getText());
				ArrayList <WebElement> dropDownMonths = (ArrayList <WebElement>) dropdownSelect.getOptions();
				int index = RandomStringGen.getRandomInt(dropDownMonths.size());
				dropdownSelect.selectByIndex(index);
				//		System.out.println("After Selection : "+dropdownSelect.getFirstSelectedOption().getText());

			}
		}
	}

	@AfterClass
	public void closeTest() {
		TestContext.getDriver().close();
	}


}
