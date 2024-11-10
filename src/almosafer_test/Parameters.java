package almosafer_test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Parameters {
	
	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://global.almosafer.com/en";
	Random rand = new Random();
	
	String expectLang = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedNumber = "+966554400000";
	boolean ExpectedLogoResult = true;
	String ExpectedValue = "false";
	int today = LocalDate.now().getDayOfMonth();
	int Tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
	String ExpectedDepature = String.format("%02d", Tomorrow);
	int DayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
	String ExpectedReturn = String.format("%02d", DayAfterTomorrow);
	
	String[] myWebsites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
	String[]  enCities = { "jeddah", "dubai" , "riyadh" };
	String[]  arCities = { "جدة", "دبي" };
	
	int randomEnCity = rand.nextInt(enCities.length);
	int randomArCity = rand.nextInt(arCities.length);

	int randomIndex = rand.nextInt(myWebsites.length);
	
	boolean ExpectedSearchResult = true;


	
	public void MySetup() {
		
		driver.manage().window().maximize();
		driver.get(websiteURL);

		WebElement ButtonFortheCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));

		ButtonFortheCurrency.click();
		
	}
	
	public void ScreenShot() throws IOException, InterruptedException {
		Thread.sleep(3000);

		Date myDate = new Date();

		String fileName = myDate.toString().replace(":", "-");

		System.out.println(fileName);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File("./ScreenShot/" + fileName + ".png");
		FileUtils.copyFile(SrcFile, destFile);
	}
	
	public void randomlyGetWebsite() {
		
driver.get(myWebsites[randomIndex]);
		
		WebElement stayButton=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		stayButton.click();
		
	}
	public void checkLangAndFillInfo() throws InterruptedException {
		
		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";

			Assert.assertEquals(ActualLaguage, ExpectedLanguage);
			
			Thread.sleep(2000);
			WebElement placeInputFields = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));
			placeInputFields.sendKeys(arCities[randomArCity]);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List")).findElements(By.cssSelector(".sc-phbroq-5.AutoComplete__ListItem.AutoComplete__ListItem--highlighted.AutoComplete__ListItem")).get(0).click();

		} else {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";

			Assert.assertEquals(ActualLaguage, ExpectedLanguage);
			
			Thread.sleep(2000);
			WebElement placeInputFields = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));
			placeInputFields.sendKeys(enCities[randomEnCity]);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List")).findElements(By.cssSelector(".sc-phbroq-5.AutoComplete__ListItem.AutoComplete__ListItem--highlighted.AutoComplete__ListItem")).get(0).click();
		}
		
		WebElement roomSelector = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select selector = new Select(roomSelector);
		int randRoomSelectIndex = rand.nextInt(2);
		selector.selectByIndex(randRoomSelectIndex);
		
		Thread.sleep(3000);
		WebElement searchStaysButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchStaysButton.click();

		
		
	}

	
	public void SortOptionChecker() {
		WebElement Container = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[5]/div"));

		if (driver.getCurrentUrl().contains("en")) {
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("SAR ", ""));
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("SAR ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);
		} else {
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-1l5b3qq"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("ر.س. ", ""));
			System.out.println();
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("ر.س. ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);

		}
	}
}
