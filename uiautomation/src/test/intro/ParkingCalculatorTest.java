package test.intro;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParkingCalculatorTest {
	private static final String URL = "http://adam.goucher.ca/parkcalc/index.php";
	private static final String CHROME_DRIVER_PATH = "c:\\Portable Software\\ChromeDriver\\chromedriver.exe";
	private static final int PAGE_LOAD_TIMEOUT = 30; // seconds

	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	@DataProvider(name = "testData", parallel = true)
	public Object[][] testData() {
		return new Object[][] {
				{ "Short-Term Parking", "09:00", "PM", "4/4/2019", "03:00", "PM", "4/10/2019", "$ 156.00",
						"5 Days, 18 Hours, 0 Minutes" },

				{ "Economy Parking", "13:00", "AM", "4/4/2019", "21:00", "AM", "4/4/2019", "$ 9.00",
						"0 Days, 8 Hours, 0 Minutes" },

				{ "Short-Term Parking", "09:00", "PM", "4/4/2019", "03:00", "PM", "4/10/2019", "$ 156.00",
						"5 Days, 18 Hours, 0 Minutes" } };
	}

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		webDriver.set(new ChromeDriver());
		webDriver.get().manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "testData")
	public void test(String parkingLot, String entryTime, String entryTimeAMPM, String entryDate, String exitTime,
			String exitTimeAMPM, String exitDate, String calcCost, String calcTime) {
		WebDriver driver = webDriver.get();
		driver.get(URL);
		ParkingCalculator calc = new ParkingCalculator(driver);

		calc.parkingLotSelect.selectByVisibleText(parkingLot);

		calc.entryTimeInput.sendKeys(entryTime);
		calc.getEntryTimeAMPMRadio.selectByValue(entryTimeAMPM);
		calc.entryDateInput.sendKeys(entryDate);

		calc.exitTimeInput.sendKeys(exitTime);
		calc.getExitTimeAMPMRadio.selectByValue(exitTimeAMPM);
		calc.exitDateInput.sendKeys(exitDate);

		calc.calculateButton.click();

		Assert.assertEquals(StringUtils.contains(calc.costText.getText(), calcCost), true);
		Assert.assertEquals(StringUtils.contains(calc.durationText.getText(), calcTime), true);
	}

	@AfterMethod
	public void quit() {
		webDriver.get().quit();
	}

}
