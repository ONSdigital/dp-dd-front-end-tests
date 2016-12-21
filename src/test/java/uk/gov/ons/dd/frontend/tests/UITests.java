package uk.gov.ons.dd.frontend.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.ArmedForces;
import uk.gov.ons.dd.frontend.BasePage;
import uk.gov.ons.dd.frontend.core.TestContext;

public class UITests extends BasePage{
	ArmedForces armedForces = new ArmedForces();
	String[] filterOptionsText = {"Selected options (1)","Selected options (2)", "Selected options (3)",
			"Selected options (4)", "Everything selected (2)", "Everything selected (3)", "Everything selected (5)"};
	String[] residenceFilters = {   ArmedForces.Residence_Type.DO000161.toString(),
			ArmedForces.Residence_Type.DO000162.toString(), ArmedForces.Residence_Type.DO000163.toString()};
	String[] sexFilters = { ArmedForces.Sex.DO000153.toString(), ArmedForces.Sex.DO000154.toString()};
	String[] ageFilters = {ArmedForces.Age.DO000265.toString(), ArmedForces.Age.DO000266.toString(),
			ArmedForces.Age.DO000267.toString(), ArmedForces.Age.DO000268.toString(), ArmedForces.Age.DO000269.toString() };
	@BeforeTest
	public void openPage(){
		armedForces.navigateToUrl(getConfig().getBaseURL());
		click(armedForces.armedForces_link);
		click(armedForces.customise_data_set);

	}

	@Test(groups = "sex")
	public void customiseSexFilter(){
		String defaultSelection = armedForces.getoptionsText("Sex");
		armedForces.getCustomiseLink("Sex").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(getElementText(armedForces.error_message),"Select at least one option");
		click(armedForces.getlinkText("Cancel"));
		Assert.assertEquals(armedForces.getoptionsText("Sex"),defaultSelection);
		armedForces.getCustomiseLink("Sex").click();
		click(armedForces.getId(ArmedForces.Sex.DO000153.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Sex"),"Selected options (1)");
		armedForces.getCustomiseLink("Sex").click();
		click(armedForces.getlinkText("Enable all"));
		click(armedForces.getlinkText("Cancel"));
		Assert.assertEquals(armedForces.getoptionsText("Sex"),"Selected options (1)");
		armedForces.getCustomiseLink("Sex").click();
		click(armedForces.getlinkText("Enable all"));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Sex"),defaultSelection);
	}

	@Test(groups = "residence", dependsOnGroups = {"sex"})
	public void customiseResidenceType(){
		String defaultSelection = armedForces.getoptionsText("Residence Type");
		armedForces.getCustomiseLink("Residence Type").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getId(ArmedForces.Residence_Type.DO000161.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Residence Type"),"Selected options (1)");
		armedForces.getCustomiseLink("Residence Type").click();
		click(armedForces.getId(ArmedForces.Residence_Type.DO000162.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Residence Type"),"Selected options (2)");
		armedForces.getCustomiseLink("Residence Type").click();
		click(armedForces.getId(ArmedForces.Residence_Type.DO000163.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Residence Type"),defaultSelection);
		armedForces.getCustomiseLink("Residence Type").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(getElementText(armedForces.error_message),"Select at least one option");
		click(armedForces.getlinkText("Enable all"));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Residence Type"),defaultSelection);
		armedForces.getCustomiseLink("Residence Type").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getId(ArmedForces.Residence_Type.DO000163.toString()));
		click(armedForces.getlinkText("Cancel"));
		Assert.assertEquals(armedForces.getoptionsText("Residence Type"),defaultSelection);
	}

	@Test(groups = "age", dependsOnGroups = {"residence"})
	public void customiseAge(){
		String defaultSelection = armedForces.getoptionsText("Age");
		armedForces.getCustomiseLink("Age").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getId(ArmedForces.Age.DO000265.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Age"),"Selected options (1)");
		armedForces.getCustomiseLink("Age").click();
		click(armedForces.getId(ArmedForces.Age.DO000266.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Age"),"Selected options (2)");
		armedForces.getCustomiseLink("Age").click();
		click(armedForces.getId(ArmedForces.Age.DO000267.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Age"),"Selected options (3)");
		armedForces.getCustomiseLink("Age").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(getElementText(armedForces.error_message),"Select at least one option");
		click(armedForces.getlinkText("Enable all"));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Age"),defaultSelection);
		armedForces.getCustomiseLink("Age").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getId(ArmedForces.Age.DO000268.toString()));
		click(armedForces.getlinkText("Cancel"));
		Assert.assertEquals(armedForces.getoptionsText("Age"),defaultSelection);
		armedForces.getCustomiseLink("Age").click();
		click(armedForces.getlinkText("Disable all"));
		click(armedForces.getId(ArmedForces.Age.DO000269.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Age"),"Selected options (1)");
		armedForces.getCustomiseLink("Age").click();
		click(armedForces.getId(ArmedForces.Age.DO000265.toString()));
		click(armedForces.getId(ArmedForces.Age.DO000266.toString()));
		click(armedForces.getId(ArmedForces.Age.DO000267.toString()));
		click(armedForces.getId(ArmedForces.Age.DO000268.toString()));
		click(armedForces.getlinkText("Save selection >"));
		Assert.assertEquals(armedForces.getoptionsText("Age"),defaultSelection);

	}
	@Test(groups = {"back"}, dependsOnGroups = {"age"})
	public void backButton(){
		click(armedForces.back_link);
		Assert.assertTrue(armedForces.getPageSource().contains("Members of the Armed Forces"));
	}

	@AfterClass
	public void closeTest(){
		TestContext.getDriver().close();
	}
}
