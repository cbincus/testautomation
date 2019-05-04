package test.core.hybrid;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import test.core.common.BaseTest;
import test.poms.SelectByVisibleText;
import test.webelements.WebLink;
import test.webelements.WebRadio;
import test.webelements.WebTextInput;
import test.webelements.WebTypifiedElement;

public class UIOperation {
	WebDriver driver;
	int waitMilliseconds = BaseTest.EXPLICIT_WAIT;
	
	public UIOperation(WebDriver driver){
		this.driver = driver;
	}
	
	public void perform(Properties p, String operation, 
			String objectType, String objectName, String objectLocatorType, String value) throws Exception{

		WebTypifiedElement webTypifiedElement = null;
		if (!objectName.isBlank())
			webTypifiedElement = this.getWebTypifiedElement(
					driver.findElement(this.getObjectLocator(p, objectName, objectLocatorType)), objectType);
		
		switch (operation.toUpperCase()) {
		case "CLICK":
			// Perform click
			webTypifiedElement.click();
			break;
			
		case "SETTEXT":
			// Set text on control
			webTypifiedElement.clear();
			webTypifiedElement.sendKeys(value);
			break;
			
		case "GOTOURL":
			// Get URL of application
			driver.get(p.getProperty(value));
			break;
			
		case "GETTEXT":
			// Get text of an element
			webTypifiedElement.getText();
			break;
			
		case "SELECTBYTEXT":
			// Select a form element by visible text
			if (webTypifiedElement instanceof SelectByVisibleText)
				((SelectByVisibleText) webTypifiedElement).selectByVisibleText(value);
			else
				throw new UnsupportedOperationException("This element doesn't support selecting a value by text!");
			break;
				
			
		case "CHECKTEXT":
			// Check that text of an element equals the given value
			Assert.assertTrue(value.equalsIgnoreCase(
					webTypifiedElement.getText()));
			break;
			
		case "CONTEXTSWITCH":
			driver.switchTo().frame(webTypifiedElement);
			break;
		
		case "DEFAULTCONTEXT":
			driver.switchTo().defaultContent();
			break;
			
		case "SETEXPICITWAIT":
			waitMilliseconds = Integer.parseInt(value);
			break;
			
		case "WAITTOAPPEAR":
			new WebDriverWait(driver, waitMilliseconds)
					.until(ExpectedConditions.visibilityOf(webTypifiedElement));
			break;
			
		case "WAITTOLOAD":
			new WebDriverWait(driver, waitMilliseconds).until(
			          driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			break;
			
		default:
			break;
		}
	}
	
	private WebTypifiedElement getWebTypifiedElement(WebElement element, String elementType) {
		switch (elementType.toUpperCase()) {
		case "LINK":
			return new WebLink(element);
		case "TEXTINPUT":
			return new WebTextInput(element);
		case "RADIO":
			return new WebRadio(element);
		default:
			return new WebTypifiedElement(element);
		}
	}
	
	/**
	 * Find element BY using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObjectLocator(Properties p, 
			String objectName, String objectLocatorType) 
					throws Exception {
		String objectLocatorString = p.getProperty(objectName);
		
		switch (objectLocatorType.toUpperCase()) {
		case "XPATH":
			return By.xpath(objectLocatorString);
		case "ID":
			return By.id(objectLocatorString);
		case "CLASSNAME":
			return By.className(objectLocatorString);
		case "NAME":
			return By.name(objectLocatorString);
		case "TAGNAME":
			return By.tagName(objectLocatorString);
		case "CSS":
			return By.cssSelector(objectLocatorString);
		case "LINK":
			return By.linkText(objectLocatorString);
		case "PARTIALLINK":
			return By.partialLinkText(objectLocatorString);
		default:
			throw new IllegalArgumentException("Wrong object type!");	
		}
	}
}
