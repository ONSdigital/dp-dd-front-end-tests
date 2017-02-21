package uk.gov.ons.dd.frontend.tests;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.testng.Assert;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class FileChecker {


	public void getFile(String url, String fileName) throws Exception {
		String dirname = "download/";
		File dir = new File(dirname);
		dir.mkdir();
		for (File file : dir.listFiles()) {
			if (file.getName().contains(".csv")) {
				file.delete();
			}
		}
		File csvFile = new File(dirname + fileName);
		csvFile.createNewFile();
		BufferedInputStream in = null;
		OutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(url).openStream());
			try {
				fout = new FileOutputStream(csvFile, true);
			} catch (FileNotFoundException ee) {

			}
			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null)
				in.close();
			if (fout != null)
				fout.close();
		}
	}

	public void checkForFilter(ArrayList <String> dimFiler, String key, String fileName, boolean hierarchy) throws Exception {
		String file = "download/" + fileName;
		CSVReader csvReader = new CSVReader(new FileReader(file));
		ArrayList <String[]> allLines = (ArrayList <String[]>) csvReader.readAll();
		ArrayList <String[]> linesToRemove = new ArrayList <>();
		for (String filter : dimFiler) {
			String searchTerm = hierarchy ? "(.*)" + key + "," + "(.*)," + filter + "(.*)" : "(.*)" + key + "," + filter + "(.*)";
			for (String[] strArr : allLines) {
				String temp = null;
				for (int index = 0; index < strArr.length; index++) {
					temp += strArr[index] + ",";
				}
				if (temp.matches(searchTerm)) {
					linesToRemove.add(strArr);
				}

			}
		}
		allLines.removeAll(linesToRemove);
		StringWriter sw = new StringWriter();
		CSVWriter writer = new CSVWriter(sw);
		writer.writeAll(allLines);
		System.out.println(sw.toString());
		Assert.assertTrue(allLines.size() == 1, "Mismatch between the filter and the downloaded CSV" + sw.toString());

	}

}
