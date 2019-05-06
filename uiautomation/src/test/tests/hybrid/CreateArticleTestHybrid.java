package test.tests.hybrid;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.core.hybrid.PropertyManager;
import test.core.hybrid.UIOperation;

public class CreateArticleTestHybrid extends BaseTest {
	@Test(dataProvider = "hybridData")
	public void testLogin( 
			String keyword, String objectType, String objectName, String objectLocatorType,
			String value) 
					throws Exception {

		Properties properties = 
				PropertyManager.loadpropertiesFromFile("/properties/createarticle.property");
		UIOperation operation = new UIOperation(driver);
    	operation.perform(properties, keyword, objectType, objectName, objectLocatorType, value);
	}

    
    @DataProvider(name="hybridData")
	public Object[][] getDataFromDataprovider() throws IOException {
    	Object[][] object = null; 
    	final int COLUMNS = 5;
    	final int FIRST_DATA_ROW = 2;
    	
    	try (InputStream is = new FileInputStream("testcases/testcase.xlsx")) {
    		Workbook wb = new XSSFWorkbook(is);
    		XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
    		Sheet testcaseSheet = wb.getSheetAt(0);
    		
    		int rowCount = testcaseSheet.getLastRowNum() - FIRST_DATA_ROW;
         	object = new Object[rowCount][COLUMNS];
         	for (int i = 0; i < rowCount; i++) {
        		Row row = testcaseSheet.getRow(i + FIRST_DATA_ROW);
        		for (int j = 0; j < row.getLastCellNum(); j++) 
        			object[i][j] = row.getCell(j).toString();
        	}
    	}
    	
    	return object;	 
	}
}
