package test.core;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParkingCalcTest {
	private WebDriver driver;
	private static final String URL = "http://adam.goucher.ca/parkcalc/index.php";
	private static final String CHROME_DRIVER_PATH = "c:\\Portable Software\\ChromeDriver\\chromedriver.exe";
	private static final int IMPLICITLY_WAIT = 5; // seconds
	private static final int PAGE_LOAD_TIMEOUT = 30;
	
	@BeforeTest
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", 
				CHROME_DRIVER_PATH);
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT,
				TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT,
				TimeUnit.SECONDS);
		
		driver.get(URL);
	}
	
	@DataProvider(name = "testData")
	public Object[][] testData() {
		return new Object[][] {
			{ "Short-Term Parking", "09:00", "PM", "4/4/2019", 
				"03:00", "PM", "4/10/2019",
				"$ 156.00", "5 Days, 18 Hours, 0 Minutes"},
			{ "Economy Parking", "13:00", "AM", "4/4/2019",
				"21:00", "AM", "4/4/2019",
				"$ 9.00", "0 Days, 8 Hours, 0 Minutes"}
		};
	}
	
	@Test(dataProvider = "testData")
	public void test(String parkingLot, 
			String entryTime, String entryTimeAMPM, String entryDate, 
			String exitTime, String exitTimeAMPM, String exitDate,
			String calcCost, String calcTime) {
		ParkingCalculator calc = new ParkingCalculator(driver);
		
		new Select(calc.parkingLotElement)
			.selectByVisibleText(parkingLot); 
		calc.entryTimeElement.clearAndFill(entryTime);
		calc.getEntryTimeAMPMButton(entryTimeAMPM).click();
		calc.entryDateElement.clearAndFill(entryDate);
		
		calc.exitTimeElement.clearAndFill(exitTime);
		calc.getExitTimeAMPMButton(exitTimeAMPM).click();
		calc.exitDateElement.clearAndFill(exitDate);
		
		calc.calculateButton.click();
		
		try {
			Thread.sleep(IMPLICITLY_WAIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(StringUtils.contains(
				calc.costText.getText(), calcCost), true);
		Assert.assertEquals(StringUtils.contains(
				calc.durationText.getText(), calcTime), true);
	}
	
	@AfterTest
	public void close() {
		driver.close();
		driver.quit();
	}
}
