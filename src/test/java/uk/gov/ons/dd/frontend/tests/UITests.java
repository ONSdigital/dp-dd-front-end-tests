package uk.gov.ons.dd.frontend.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.core.TestContext;
import uk.gov.ons.dd.frontend.pages.ArmedForces;
import uk.gov.ons.dd.frontend.pages.BasePage;


public class UITests extends BasePage{
	ArmedForces armedForces = new ArmedForces();

	@BeforeTest
	public void openPage(){
		armedForces.navigateToUrl(getConfig().getBaseURL());
		click(armedForces.armedForces_link);
		click(armedForces.customise_data_set);

	}

	@Test(groups = "sex")
	public void customiseSexFilter(){
		String defaultSelection = armedForces.getoptionsText(armedForces.sex_filter);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.disable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(getElementText(armedForces.error_message), armedForces.error_message_text);
		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		// ids need to be removed
		armedForces.selectCheckBox(0);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), armedForces.selectedOptions1_text);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.enable_all);
		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), armedForces.selectedOptions1_text);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.enable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.sex_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(1);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.sex_filter), armedForces.selectedOptions1_text);



	}


	@Test(groups = "residence", dependsOnGroups = {"sex"})
	public void customiseResidenceType(){
		String defaultSelection = armedForces.getoptionsText(armedForces.residence_filter);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(0);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), armedForces.selectedOptions1_text);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		armedForces.selectCheckBox(1);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), armedForces.selectedOptions2_text);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		armedForces.selectCheckBox(2);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		click(armedForces.disable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(getElementText(armedForces.error_message), armedForces.error_message_text);
		click(armedForces.enable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.residence_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(2);
		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.residence_filter), defaultSelection);
	}


	@Test(groups = "age", dependsOnGroups = {"residence"})
	public void customiseAge(){
		String defaultSelection = armedForces.getoptionsText(armedForces.age_filter);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(0);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), armedForces.selectedOptions1_text);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(1);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), armedForces.selectedOptions2_text);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(2);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), armedForces.selectedOptions3_text);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(3);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), armedForces.selectedOptions4_text);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(4);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.age_filter).click();

		click(armedForces.disable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(getElementText(armedForces.error_message),
				armedForces.error_message_text);
		click(armedForces.enable_all);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(3);

		click(armedForces.cancel_button);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), defaultSelection);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		click(armedForces.disable_all);
		armedForces.selectCheckBox(4);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), armedForces.selectedOptions1_text);
		armedForces.getCustomiseLink(armedForces.age_filter).click();
		armedForces.selectCheckBox(0);
		armedForces.selectCheckBox(1);
		armedForces.selectCheckBox(2);
		armedForces.selectCheckBox(3);
		click(armedForces.save_selection);
		Assert.assertEquals(armedForces.getoptionsText(armedForces.age_filter), defaultSelection);

	}
	@Test(groups = {"back"}, dependsOnGroups = {"age"})
	public void backButton(){
		click(armedForces.back_link);
		Assert.assertTrue(armedForces.getPageSource().contains(getTextFromProperty("armed_forces_linkText")));
	}

	@AfterClass
	public void closeTest(){
		TestContext.getDriver().close();
	}
}
