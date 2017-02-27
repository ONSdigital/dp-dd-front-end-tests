package uk.gov.ons.dd.frontend.tests;

import junit.framework.Assert;
import org.testng.annotations.Test;
import uk.gov.ons.dd.frontend.filters.OptionSelector;
import uk.gov.ons.dd.frontend.filters.SummarySelector;
import uk.gov.ons.dd.frontend.model.DataResource;
import uk.gov.ons.dd.frontend.model.MetaDataEditorModel;
import uk.gov.ons.dd.frontend.pages.ArmedForces;
import uk.gov.ons.dd.frontend.pages.FileUploader;
import uk.gov.ons.dd.frontend.pages.MetaDataEditor;
import uk.gov.ons.dd.frontend.util.Helper;

import java.util.ArrayList;

public class ArmedForcesTests extends BaseTest {

	private final String armedForcesFile = "AF001EW_v3_E2E_Tests.csv";
	// CHECK FOR THE DATA RESOURCE ON THE DD PAGE
	// EXISTS = TRUE
	// RUN THE TESTS
	// EXISTS = FALSE
	// CHECK IN THE METADATA EDITOR WHETHER THE FILE EXISTS
	// EXISTS = TRUE
	// CHECK FOR THE DATA RESOURCE
	// EXISTS = FALSE
	// UPLOAD THE FILE
	// EXISTS = TRUE
	// LINK THE DATA RESOURCE TO THE DATASET
	// EXISTS = FALSE
	// CREATE THE DATA RESOURCE
	// LINK THE DATA RESOURCE TO THE DATASET
	ArmedForces armedForces = new ArmedForces();
	MetaDataEditor metaDataEditor = new MetaDataEditor();
	FileUploader fileUploader = new FileUploader();
	SummarySelector summary = new SummarySelector();
	OptionSelector optionSelector = new OptionSelector();
	ArrayList <String> age = new ArrayList <>();
	ArrayList <String> residence = new ArrayList <>();
	ArrayList <String> sex = new ArrayList <>();
	String armedForcesDS = null;

	@Test(groups = {"checkds"})
	public void checkDataResource() {
		String dataSetId = null;
		MetaDataEditorModel metaDataEditorModel = null;
		armedForcesDS = armedForces.armedForces_dataresource;
		try {
			basePage.navigateToUrl(basePage.getConfig().getBaseURL());
			basePage.click(armedForces.armedForces_link);
//			basePage.getDriver().close();
		} catch (Exception ee) {
			DataResource dataResource = metaDataEditor.findMyDataResource("TEST_Resource_" + armedForcesDS);
			metaDataEditorModel = metaDataEditor.verifyDataSetExists(armedForcesFile);
			dataSetId = metaDataEditorModel.getDatasetId();
			if (dataResource == null && dataSetId == null) {
				fileUploader.uploadFile(armedForcesFile);
				metaDataEditor.createDataResource(armedForcesFile);
				Assert.assertTrue(metaDataEditor.mapMetaData(dataSetId, armedForcesFile));
			} else if (dataResource != null && dataSetId == null) {
				fileUploader.uploadFile(armedForcesFile);
				Assert.assertTrue(metaDataEditor.mapMetaData(dataSetId, armedForcesFile));
			} else if (dataResource != null && metaDataEditorModel.getDataResource().equals("")) {
				Assert.assertTrue(metaDataEditor.mapMetaData(dataSetId, armedForcesDS));

			}

		}
	}

	public void checkForDS() throws Exception {
		basePage.navigateToUrl(basePage.getConfig().getBaseURL());
		basePage.click(armedForces.armedForces_link);
		basePage.switchToLatestWindow();
		Helper.pause(10);
		basePage.click(basePage.customise_data_set);

	}

	@Test(groups = {"openAF"}, dependsOnGroups = {"checkds"})
	public void openArmedForces() throws Exception {
		checkForDS();

	}

	@Test(groups = "sex", dependsOnGroups = {"openAF"})
	public void customiseSexFilter() {
		try {
			optionSelector.optionJourney(armedForces.sex_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			org.testng.Assert.fail();
		}
	}

	@Test(groups = "residence", dependsOnGroups = {"sex"})
	public void customiseResidenceType() throws Exception {
		try {
			optionSelector.optionJourney(armedForces.residence_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			org.testng.Assert.fail();
		}
	}

	@Test(groups = "age", dependsOnGroups = {"residence"})
	public void customiseAge() throws Exception {
		try {
			optionSelector.optionJourney(armedForces.age_filter);
		} catch (Exception ee) {
			ee.printStackTrace();
			org.testng.Assert.fail();
		}

	}

	@Test(groups = {"getOptions"}, dependsOnGroups = {"age"})
	public void getSelectedOptions() {
		age = summary.selectedOptions(armedForces.age_filter, false);
		residence = summary.selectedOptions(armedForces.residence_filter, false);
		sex = summary.selectedOptions(armedForces.sex_filter, false);
	}

	@Test(groups = {"customiseCSV"}, dependsOnGroups = {"getOptions"})
	public void downloadCustomisedDS_CSV() {
		basePage.selectDownloadCSV();
		basePage.checkDownloadedFile(age, armedForces.age_filter, false);
		basePage.checkDownloadedFile(residence, armedForces.residence_filter, false);
		basePage.checkDownloadedFile(sex, armedForces.sex_filter, false);
	}


}
