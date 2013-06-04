/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excel2csv;

import java.util.ArrayList;

/**
 *
 * @author Thinkpad
 */
public class CsvColumn {
	String attributeName;
	ArrayList<String> valueList;

	public CsvColumn(String attributeName) {
		this.attributeName = attributeName;
		this.valueList = new ArrayList<>();
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public ArrayList<String> getValueList() {
		return this.valueList;
	}

	public void setAttributeName(String name) {
		this.attributeName = name;	
	}

	public void addValue(String value) {
		this.valueList.add(value);
	}

	public void printCsvColumn() {
		System.out.println("attributeName=" + this.attributeName);
		for (String value : this.valueList) {
			System.out.println("value=" + value);
		}
	}
}
