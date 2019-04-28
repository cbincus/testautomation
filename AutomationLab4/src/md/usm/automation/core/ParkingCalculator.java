package md.usm.automation.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.webelements.WebTextInput;
import md.usm.automation.webelements.WebTypifiedElement;

public class ParkingCalculator extends AbstractPOM {
	@FindBy(xpath = "//select[@id='Lot']")
	public WebTypifiedElement parkingLotElement;
	
	@FindBy(xpath = "//input[@id='EntryTime']")
	public WebTypifiedElement entryTimeElement;
	
	@FindBy(xpath = "//input[@id='EntryDate']")
	public WebTypifiedElement entryDateElement;
	
	@FindBy(xpath = "//input[@id='ExitTime']")
	public WebTypifiedElement exitTimeElement;
	
	@FindBy(xpath = "//input[@id='ExitDate']")
	public WebTypifiedElement exitDateElement;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Calculate']")
	public WebTypifiedElement calculateButton;
	
	@FindBy(xpath = "//tr[td[contains(.,'COST')]]//span[@class = 'SubHead']")
	public WebTypifiedElement costText;
	
	@FindBy(xpath = "//tr[td[contains(.,'COST')]]//span[@class = 'BodyCopy']")
	public WebTypifiedElement durationText;
	
	public ParkingCalculator(WebDriver driver) {
		super(driver);
	}
	
	public WebTypifiedElement getEntryTimeAMPMButton(String ampm) {
		return new WebTextInput(driver.findElement(By.xpath(
				"//input[@name='EntryTimeAMPM' and @value = '" + ampm + "']")));
	}
	
	public WebTypifiedElement getExitTimeAMPMButton(String ampm) {
		return new WebTextInput(driver.findElement(By.xpath(
				"//input[@name='ExitTimeAMPM' and @value = '" + ampm + "']")));
	}
}
