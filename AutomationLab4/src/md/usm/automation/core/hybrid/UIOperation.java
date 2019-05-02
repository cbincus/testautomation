package md.usm.automation.core.hybrid;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import md.usm.automation.poms.SelectByVisibleText;
import md.usm.automation.webelements.WebLink;
import md.usm.automation.webelements.WebRadio;
import md.usm.automation.webelements.WebTextInput;
import md.usm.automation.webelements.WebTypifiedElement;

public class UIOperation {
	WebDriver driver;
	
	public UIOperation(WebDriver driver){
		this.driver = driver;
	}
	
	public void perform(Properties p, String operation, 
			String objectName, String objectType, String objectLocator, String value) throws Exception{

		WebTypifiedElement webTypifiedElement = null;
		if (!objectName.isBlank())
			webTypifiedElement = this.getWebTypifiedElement(
					driver.findElement(this.getObject(p, objectName, objectType)), objectType);
		
		switch (operation.toUpperCase()) {
		case "CLICK":
			// Perform click
			webTypifiedElement.click();
			break;
			
		case "SETTEXT":
			// Set text on control
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
	private By getObject(Properties p, String objectName, String objectType) throws Exception{
		// Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		// Find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		// Find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}
		// Find by CSS
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		// Find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(p.getProperty(objectName));
			
		}
		// Find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		} else
		{
			throw new IllegalArgumentException("Wrong object type!");
		}
	}
}
