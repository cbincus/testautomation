package test.webelements;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class WebTextInput extends WebTypifiedElement {
	public WebTextInput(WebElement wrappedElement) {
		super(wrappedElement);
	}
	
	public void sendKeys(String keysToSend) {
		log.info("Fill '" + this.getName() + "' with '" + keysToSend + "'");
		this.clear();
		this.getWrappedElement().sendKeys(keysToSend);
		Assert.assertTrue(keysToSend.equalsIgnoreCase(
				this.getAttribute("value")));
	}
}
