package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;

public class SmallAreaPopulation extends BaseTest {
	public By sape_link = basePage.getElementLocator("small_area_pop_estimates_linkText");
	HierarchySelector hierarchySelector = new HierarchySelector();

	@Test(groups = {"opensape"})
	public void openSape() throws Exception {
		if (config.getBaseURL().contains("develop")) {
			System.out.println("************ Starting SAPE Journey Geography Hierarchy Test **********");
			checkForDS(sape_link);
			basePage.click(basePage.customise_data_set);

		}
	}

	@Test(groups = {"orderedGeo"}, dependsOnGroups = {"opensape"})
	public void validateGeoOrdering() throws Exception {
		if (config.getBaseURL().contains("develop")) {
			hierarchySelector.compareGeoSorting("Geographic_Hierarchy", true);
		}
		System.out.println("*************** Completed SAPE Journey Geography Hierarchy Test ***************");
	}


}
