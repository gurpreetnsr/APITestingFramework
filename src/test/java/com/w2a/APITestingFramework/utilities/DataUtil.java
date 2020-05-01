package com.w2a.APITestingFramework.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.w2a.APITestingFramework.setup.BaseTest;



public class DataUtil extends BaseTest {

//	@DataProvider(name = "data")
//	public Object[][] getData(Method m) {
//		
//		System.out.println("Sheet name is "+ m.getName());
//		String sheetName = m.getName();
//
//		int rows = excel.getRowCount(sheetName);
//		int columns = excel.getColumnCount(sheetName);
//		System.out.println(rows);
//		System.out.println(columns);
//		Object[][] data = new Object[rows - 1][columns];
//
////		data[0][0] = excel.getCellData(sheetName, 0, 2);
////		data[0][1] = excel.getCellData(sheetName, 1, 2);
////		data[0][2] = excel.getCellData(sheetName, 2, 2);
//
//		for (int rowNum = 2; rowNum <= rows; rowNum++) {
//			for (int colNum = 0; colNum < columns; colNum++) {
//				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//			}
//		}
//
//		return data;
//	}
	
	@DataProvider
	public Object[][] getData(Method m) {

		ExcelReader excel = new ExcelReader("C:\\Users\\rajdeep\\eclipse-workspace\\APITestingFramework\\src\\test\\resources\\excel\\testdata.xlsx");
		String sheet = "testdata";

		int rows = excel.getRowCount(Constants.DATA_SHEET);
		System.out.println("Total rows are " + rows);

		String testName = m.getName();

		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {
			String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
			if (testCaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}

		System.out.println("The test starts from :" + testCaseRowNum);

		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;
		while (!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum + testRows).equals("")) {
			testRows++;
		}
		System.out.println("Total test rows are :" + testRows);

		int colStartColNum = testCaseRowNum + 1;
		int testColumns = 0;
		while (!excel.getCellData(Constants.DATA_SHEET, testColumns, colStartColNum).equals("")) {
			testColumns++;
		}
		System.out.println("Total columns are " + testColumns);

		Object[] [] data = new Object [testRows][1];
			
		
		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + testRows; rNum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < testColumns; cNum++) {
				System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
				//data[rNum -dataStartRowNum][cNum]= excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String testData = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String colName = excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);
				table.put(colName, testData);
			}
			
			data[rNum -dataStartRowNum][0] =table;
		}

		return data;

	}


}
