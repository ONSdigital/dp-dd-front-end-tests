package uk.gov.ons.dd.frontend.pages;

import org.openqa.selenium.By;

public class ArmedForces {
	BasePage basePage = new BasePage();
	// ***** Armed Forces dataset
	public By armedForces_link = basePage.getElementLocator("armed_forces_linkText");



	// ********** Filter options
	public String sex_filter = basePage.getTextFromProperty("sex_filter_text");

	public String residence_filter = basePage.getTextFromProperty("residence_filter_text");
	public String age_filter = basePage.getTextFromProperty("age_filter_text");


}