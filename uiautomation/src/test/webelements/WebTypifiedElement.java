package test.webelements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class WebTypifiedElement extends TypifiedElement {
	public WebTypifiedElement(WebElement wrappedElement) {
		super(wrappedElement);
	}
	
	protected Logger log = LogManager.getLogger(this.getClass().getSimpleName());
	
	public void clearAndFill(String data) {
		log.info("Filling '" + this.getName() + "' with '" + data);
		this.clear();
		this.sendKeys(data);
	}
	
	@Override
    public void click() {
        log.info("Click on element '" + getName() + "'");
        super.click();
    }
}
