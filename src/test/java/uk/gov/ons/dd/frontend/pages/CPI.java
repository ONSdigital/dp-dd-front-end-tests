package uk.gov.ons.dd.frontend.pages;

import org.openqa.selenium.By;

public class CPI {
	BasePage basePage = new BasePage();

	// ***** CPI dataset
	public By cpi_link = basePage.getElementLocator("cpi_linkText");
	// ********** Filter options
	public String time_filter = basePage.getTextFromProperty("time_filter_text");

	public String spl_aggregate = basePage.getTextFromProperty("spl_aggregate_filter_text");

	public By single_month = basePage.getElementLocator("single_month_css");
	public By range = basePage.getElementLocator("range_css");
	public By select_month = basePage.getElementLocator("month_css");
	public By select_year = basePage.getElementLocator("year_id");

	public By range_start_month = basePage.getElementLocator("range_start_month_xpath");
	public By range_end_month = basePage.getElementLocator("range_end_month_xpath");
	public By range_start_year = basePage.getElementLocator("range_start_year_xpath");
	public By range_end_year = basePage.getElementLocator("range_end_year_xpath");
	public By add_selection = basePage.getElementLocator("add_selection_linkText");


}
