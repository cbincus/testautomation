package test.intro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.core.common.AbstractPOM;
import test.webelements.WebButton;
import test.webelements.WebRadio;
import test.webelements.WebSelect;
import test.webelements.WebText;
import test.webelements.WebTextInput;

public class ParkingCalculator extends AbstractPOM {
	public ParkingCalculator(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//select[@id='Lot']")
	public WebSelect parkingLotSelect;
	
	@FindBy(xpath = "//input[@id='EntryTime']")
	public WebTextInput entryTimeInput;
	
	@FindBy(xpath = "//input[@id='EntryDate']")
	public WebTextInput entryDateInput;
	
	@FindBy(xpath = "//input[@id='ExitTime']")
	public WebTextInput exitTimeInput;
	
	@FindBy(xpath = "//input[@id='ExitDate']")
	public WebTextInput exitDateInput;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Calculate']")
	public WebButton calculateButton;
	
	@FindBy(xpath = "//tr[td[contains(.,'COST')]]//span[@class = 'SubHead']")
	public WebText costText;
	
	@FindBy(xpath = "//tr[td[contains(.,'COST')]]//span[@class = 'BodyCopy']")
	public WebText durationText;
	
	@FindBy(xpath = "//input[@name='EntryTimeAMPM' and @value = 'AM']")
	public WebRadio getEntryTimeAMPMRadio;
	
	@FindBy(xpath = "//input[@name='ExitTimeAMPM' and @value = 'AM']")
	public WebRadio getExitTimeAMPMRadio;
}
