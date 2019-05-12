package test.intro;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {
	private static final String CHROME_DRIVER_PATH = "c:\\Portable Software\\ChromeDriver\\chromedriver.exe";

	private static WebDriver driver;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		init();
		List<String> urls = getGoogleSearchResultsFromFirstPage("weather");
		saveSearchResultsInfo(urls, "googlesearch.xlsx", "weather");
		quit();
	}
	
	private static void init() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	private static void quit() {
		driver.quit();
	}
	
	private static List<String> getGoogleSearchResultsFromFirstPage(String query) {
		driver.get("https://www.google.md/");
        WebElement searchInput = driver.findElement(By.xpath("//input[@name='q']"));
        searchInput.clear();
        searchInput.sendKeys("weather");
        
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='FPdoLc VlcLAe']//input[@name='btnK']"));
        searchButton.submit();
        
        List<String> resultList = new LinkedList<>();
        List<WebElement> urlList = driver.findElements(By.xpath("//div[@class = 'r']/a[1]"));
        for (WebElement url : urlList)
        	resultList.add(url.getAttribute("href"));
        return resultList;
	}
	
	private static void saveSearchResultsInfo(List<String> urls, String filename, String query) 
			throws FileNotFoundException, IOException {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Google search of <" + query + ">");
		
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		headerStyle.setFont(headerFont);
		
		Row headers = sheet.createRow(0);
		headers.setRowStyle(headerStyle);
		headers.createCell(0).setCellValue("Search result");
		headers.createCell(1).setCellValue("Page title");
		headers.createCell(2).setCellValue("Matches of '" + query + "' in page code");
		
		for (int i = 0; i < urls.size(); ++i) {
			String url = urls.get(i);
			Row row = sheet.createRow(i + 1);
			
			driver.get(url);
			row.createCell(0).setCellValue(url);
			row.createCell(1).setCellValue(driver.getTitle());
			row.createCell(2).setCellValue(
					StringUtils.countMatches(driver.getPageSource().toLowerCase(Locale.ROOT), query));
		}
		
		try (OutputStream os = new FileOutputStream(filename)) {
			wb.write(os);
		}
		
		wb.close();
	}
}
