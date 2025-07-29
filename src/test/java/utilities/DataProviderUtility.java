package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {
	
	@DataProvider(name="TestData")

  public String [][] getData() throws IOException{
		
		String path=".\\testData\\TestData.xlsx";
		ExcelUtility xutil=new ExcelUtility(path);
		int totalRows=xutil.getRowCount("sheet1");
		int totalCols=xutil.getCellCount("sheet1", 1);
		String logindata[][]=new String [totalRows][totalCols];
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCols;j++) {
				logindata[i-1][j]=xutil.getCellData("sheet1", i, j);
			}
		}
		
	return logindata;
	
}
}
