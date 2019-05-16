package test.core.common;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Helpers {
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH-mm-ss";
	
	protected static Logger log = LogManager.getLogger("Helpers");
	
	public static void waitForVisibleElement(WebDriver driver, WebElement element, int seconds){
        (new WebDriverWait(driver, seconds)).until(ExpectedConditions.visibilityOf(element));
    }
	
	public static void waitForPageLoad(WebDriver driver, int seconds) {
		new WebDriverWait(driver, seconds).until(
		          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}
	
	public static void waitForFrameAndSwitch(WebDriver driver, int seconds) {
		new WebDriverWait(driver, seconds).until(
				ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
	}
	
	public static <K,V> boolean containsAll(Map<K,V> map, Map<K,V> subMap) {
		return map.entrySet().containsAll(subMap.entrySet());
	}

    public static void sleep(int ms){
        try {
            log.info("Sleep for " + ms + " ms");
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void check2StringIfEquals(String value1, String value2){
        log.info("Check 2 string if equals:\n1st: '" + value1 + "'\n2nd: '" + value2 + "'");
        Assert.assertEquals(value1, value2);
    }
    
    
    public static String getDateTimeText(LocalDateTime ldt) {
    	return ldt.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }
    
    public static String getDrupalDateTimeShort(ZonedDateTime zdt) {
    	return zdt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm"));
    }
    
    public static String getDrupalDateTime(ZonedDateTime zdt) {
    	return zdt.format(DateTimeFormatter.ofPattern("EEE").localizedBy(Locale.ENGLISH)) +
    			", " + getDrupalDateTimeShort(zdt);
    }
}
