package test.core.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import test.webelements.WebTypifiedElement;

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
    
    public static List<WebTypifiedElement> getWebTypifiedElementList(List<WebElement> list) {
		return list
				.stream()
				.map(e -> new WebTypifiedElement(e))
				.collect(Collectors.toList());		
	}
    
    public static String getDateTimeText(LocalDateTime ldt) {
    	return ldt.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }
}
