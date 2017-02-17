package uk.gov.ons.dd.frontend.tests;


import com.opencsv.CSVReader;
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

	public int checkForFilter(ArrayList <String> dimFiler, String key, String fileName) throws Exception {
		int numberOfLines = 0;
		CSVReader csvReader = null;
		for (String filter : dimFiler) {
			String searchTerm = key + "," + filter;
			String file = "download/" + fileName;
			csvReader = new CSVReader(new FileReader(file));
			String[] nextLine;
			while ((nextLine = csvReader.readNext()) != null) {
				if (!nextLine[0].contains("Observation")) {
					if (nextLine[0].contains("****")) {
						System.out.println("******Last line of the CSV***");
					} else {
						numberOfLines++;
						String line = "repl";
						for (String lineVal : nextLine) {
							line = line + "," + lineVal;
						}
						line = line.replace("repl,", "");
						Assert.assertTrue(numberOfLines > 1, "Number of lines in the file is " + numberOfLines);
						if (numberOfLines > 1) {
							Assert.assertTrue(line.contains(searchTerm), "****The filter is not present in the file.****\n" +
									"Expected search term " + searchTerm +
									"\n Actual Line in the csv " + line);
						}
					}
				}
			}
		}
		csvReader.close();
		return numberOfLines;
	}


}
