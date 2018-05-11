package testCases;

import java.util.Properties;

import operation.PerformOperation;
import operation.ReadObject;
import operation.UIOperation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import excelExportAndFileIO.ReadExcelFile;

/**
 * THIS IS THE EXAMPLE OF KEYWORD DRIVEN TEST CASE
 *
 */
public class ExecuteTestModified {
    @Test
	public void testLogin() throws Exception {
		// TODO Auto-generated method stub
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		WebDriver webdriver = new ChromeDriver();
       ReadExcelFile file = new ReadExcelFile();
        ReadObject object = new ReadObject();
//        Properties allObjects =  object.getObjectRepository();
        PerformOperation operation = new PerformOperation(webdriver);
        //Read keyword sheet
        Sheet sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestCaseNew.xlsx" , "KeywordFramework");
      //Find number of rows in excel file
    	int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
    	//Create a loop over all the rows of excel file to read it
    	for (int i = 1; i < rowCount+1; i++) {
    		//Loop over all the rows
    		Row row = sheet.getRow(i);
    		//Check if the first cell contain a value, if yes, That means it is the new testcase name
//    		if(row.getCell(0).toString().length()==0){
//    		//Print testcase detail on console
    			System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
    			row.getCell(3).toString()+"----"+ row.getCell(4).toString()+"-----"+row.getCell(5).toString());
    		//Call perform function to perform operation on UI
    			operation.perform(row.getCell(1).toString(), row.getCell(2).toString(),
    				row.getCell(3).toString(), row.getCell(4).toString(),row.getCell(5).toString());
//    	    }
//    		else{
//    			//Print the new  testcase name when it started
//    				System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
//    			}
    		}
	}

}
