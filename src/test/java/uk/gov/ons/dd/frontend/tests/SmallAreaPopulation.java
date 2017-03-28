package uk.gov.ons.dd.frontend.tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.filters.HierarchySelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.filters.TimeSelector;

import java.util.ArrayList;

public class SmallAreaPopulation extends BaseTest {
	public By sape_link = basePage.getElementLocator("small_area_pop_estimates_linkText");
	public By cpi_spl_aggregate_link = basePage.getElementLocator("cpi_spl_aggregates_linkText");
	public String time_filter = basePage.getTextFromProperty("time_filter_text");
	public String spl_aggr_filter = basePage.getTextFromProperty("spl_aggregates_filter_text");
	public String search_terms = basePage.getTextFromProperty("searchKeys2_text");
	HierarchySelector hierarchySelector = new HierarchySelector();
	TimeSelector timeSelector = new TimeSelector();
	SummarySelector summarySelector = new SummarySelector();

	@Test(groups = {"sapeTests"})
	public void sapeGeoTests() throws Exception {
		if (config.getBaseURL().contains("develop")) {
			System.out.println("************ Starting SAPE Journey Geography Hierarchy Test **********");
			checkForDS(sape_link);
			basePage.click(basePage.customise_data_set);
			hierarchySelector.compareGeoSorting("Geographic_Hierarchy", true);
			System.out.println("************ Completed SAPE Journey Geography Hierarchy Test **********");
		}
	}

	@Test(groups = {"cpisplaggr"}, dependsOnGroups = {"sapeTests"})
	public void cpiTimeSelector() throws Exception {
		if (config.getBaseURL().contains("develop")) {
			System.out.println("************    Starting CPI TIME SELECTOR Test     **********");
			checkForDS(cpi_spl_aggregate_link);
			basePage.click(basePage.customise_data_set);
			timeSelector.openTimeSelector(time_filter);
			timeSelector.singleMonthTimeSelector(time_filter);
			timeSelector.removeTimeGroups(time_filter);
			timeSelector.singleMonthTimeSelector(time_filter);
			timeSelector.openTimeSelector(time_filter);
			basePage.click(summarySelector.addMore);
			timeSelector.selectAllTime(time_filter);
			timeSelector.openTimeSelector(time_filter);
			basePage.click(summarySelector.addMore);
			timeSelector.selectRange(time_filter);
			timeSelector.removeRandomGroup(time_filter);
			ArrayList <String> timeGroups = summarySelector.selectedOptions(time_filter, true);
			hierarchySelector.hierarchyJourney(spl_aggr_filter, search_terms, true);
			ArrayList <String> selected_spl_agg = summarySelector.selectedOptions(spl_aggr_filter, true);
			basePage.selectDownloadCSV(true);
			basePage.checkDownloadedFile(selected_spl_agg, spl_aggr_filter, true);
			basePage.checkDownloadedFile(timeGroups, time_filter, true);
			System.out.println("************    Finished CPI TIME SELECTOR TESTS **************");
		}
	}

}
