package test.core.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import test.poms.Login;

public abstract class BaseTest {	
	private static final String CONFIG_PATH = "config/application.conf";
	
	private static final String CHROME_DRIVER_PATH = "c:\\Portable Software\\ChromeDriver\\chromedriver.exe";
	private static final String LOG_PATH_ROOT = "logs/";
	
	private static final int PAGE_LOAD_TIMEOUT = 30;
	public static final int EXPLICIT_WAIT = 10;

	protected WebDriver driver;
	protected Logger log;
	protected Properties config = new Properties();
	
	private String testName;
	private LocalDateTime testStartTimestamp; 
	
	@BeforeClass
	public void initTest() throws IOException {
		testName = this.getClass().getSimpleName();
		testStartTimestamp = LocalDateTime.now();
		String testStartTimeString = Helpers.getDateTimeText(testStartTimestamp);
		
		String logPath = LOG_PATH_ROOT + testName + "_" + testStartTimeString + "/" ;
        String logFileName = logPath + testName + "_" + testStartTimeString + ".log";        
        System.setProperty("logPath", logPath);
        System.setProperty("logFileName", logFileName);
        
        ((LoggerContext) LogManager.getContext(false)).reconfigure();
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		
		try (InputStream is = new FileInputStream(CONFIG_PATH)) {
			config.load(is);
		}
		
		log = LogManager.getLogger(testName);
		log.info("'" + testName + "' test started");
	}

	@BeforeMethod
	public void prepareTest() {
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);	
	}
	
	public void login(String username, String password) {
		driver.get(config.getProperty("adminUrl"));
		Helpers.waitForPageLoad(driver, EXPLICIT_WAIT);
		
		Login loginPage = new Login(driver);
		loginPage.usernameInput.sendKeys(username);
		loginPage.passwordInput.sendKeys(password);
		loginPage.submitBtn.click();
		Helpers.waitForPageLoad(driver, EXPLICIT_WAIT);
	}

	public void loginWithDefaultUser() {
		login(config.getProperty("username"), config.getProperty("password"));
	}
	
	@AfterMethod(alwaysRun=true)
    public void afterMethod(ITestResult result){
        if(!result.isSuccess()) 
        	takeScreenshot();
        
        driver.quit();
	}
	
	private void takeScreenshot() {
		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File scrTmpFile = scrShot.getScreenshotAs(OutputType.FILE);
		
		String testFailTimepoint = Helpers.getDateTimeText(LocalDateTime.now());
        String name = System.getProperty("logPath") + testName + "_" + testFailTimepoint + ".png";
        
        System.out.println(name);
        
        File scrFile = new File(name);
        log.info("Screenshot name is '" + name + "'");
        try {
            FileUtils.copyFile(scrTmpFile, scrFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
