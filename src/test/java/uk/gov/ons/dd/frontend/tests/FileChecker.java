package uk.gov.ons.dd.frontend.tests;


import org.testng.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class FileChecker {

	ArrayList <String[]> linesToRemove = new ArrayList <>();
	ArrayList <String> searchRegex = new ArrayList <>();
	boolean exists = false;

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

			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			InputStream inputStream = connection.getInputStream();
			if ("gzip".equalsIgnoreCase(connection.getContentEncoding())) {
				inputStream = new GZIPInputStream(inputStream);
			}
			in = new BufferedInputStream(inputStream);
			try {
				fout = new FileOutputStream(csvFile, true);
			} catch (FileNotFoundException ee) {
				ee.printStackTrace();
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

	public ArrayList <String> searchTerms(boolean hierarchy, String code, String key) {
		String searchTerm = hierarchy ?
				"(.*)" + key + "," + "(.*)" + "," + code + "(.*)" : "(.*)" + key + "," + code + "(.*)";
		searchRegex.add(searchTerm);
		return searchRegex;

	}

	public boolean checkForFilter(ArrayList <String> dimFiler, String key, String fileName, boolean hierarchy, ArrayList <String[]> allLines)
			throws Exception {
		if (linesToRemove.size() > 0) {
			linesToRemove.removeAll(allLines);
		}
		linesToRemove.clear();
		boolean exists = false;
		for (String filter : dimFiler) {
			searchTerms(hierarchy, filter, key);
		}
		if (allLines.size() > 0) {
			for (String[] strArr : allLines) {
				String temp = "";
				for (int index = 0; index < strArr.length; index++) {
					temp += strArr[index] + ",";
				}

				for (String search : searchRegex) {
					if (temp.matches(search)) {
						if (!linesToRemove.contains(strArr)) {
							linesToRemove.add(strArr);
							exists = true;
						}
					}

				}
			}
		}
		if (allLines.size() != linesToRemove.size()) {
			for (String[] strArr : allLines) {
				for (int index = 0; index < strArr.length; index++) {
					System.out.print(strArr[index] + ",");
				}
				System.out.println();
			}

		}
		Assert.assertTrue(allLines.size() == linesToRemove.size(), "Mismatch between the filter and the downloaded CSV");

		return exists;

	}

}
