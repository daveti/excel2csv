/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excel2csv;

import java.io.*;
import java.util.*;
import jxl.*;

/**
 *
 * @author Thinkpad
 */
public class Excel2csv {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		try {
			String workingDir = "D:\\netbeans_workspace\\excel2csv\\";
			CsvParser csvParser = new CsvParser();
			String fileName;
			String sheetName;
			String tmpString;

			//Excel document to be imported
			String filename = workingDir + "NBA.xls";
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(filename), ws);

			// Gets the sheets from workbook
			for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++) {
				Sheet s = w.getSheet(sheet);
				tmpString = s.getName();
				sheetName = tmpString;

				//File to store data in form of CSV
				fileName = "nba" + "_" + sheet + "_" + tmpString + ".csv";
				File f = new File(workingDir + fileName);

				OutputStream os = (OutputStream) new FileOutputStream(f);
				String encoding = "UTF8";
				OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				try (BufferedWriter bw = new BufferedWriter(osw)) {

					Cell[] row;

					// Gets the cells from sheet
					for (int i = 0; i < s.getRows(); i++) {
						row = s.getRow(i);

						if (row.length > 0) {
							// NOTE: the data itself may contain comma
							tmpString = row[0].getContents();
							if (tmpString.contains(",") == true) {
								tmpString = tmpString.replace(',', '_');
							}
							bw.write(tmpString);
							for (int j = 1; j < row.length; j++) {
								bw.write(',');
								tmpString = row[j].getContents();
								if (tmpString.contains(",") == true) {
									tmpString = tmpString.replace(',', '_');
								}
								bw.write(tmpString);
							}
						}
						bw.newLine();
					}

					bw.flush();
					bw.close();
				}
				// Parse this CSV file into Csv object
				csvParser.parse(workingDir + fileName, sheetName);

				// Print this CSV parser
				csvParser.printCsvParser();
				
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
