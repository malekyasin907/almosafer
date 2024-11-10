package almosafer_test;

import org.testng.Assert;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class almosafer extends Parameters{

	
	@BeforeTest
	public void Setup() {
		
		MySetup();

	}

	@Test(priority = 1 , enabled = false)
	public void CheckLanguage() throws IOException, InterruptedException {

		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		

		Assert.assertEquals(actualLang, expectLang);
		
		ScreenShot();

	}

	@Test(priority = 2, enabled = false)
	public void CheckTheDefaultCurrencyIsSAR() throws IOException, InterruptedException {
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
		ScreenShot();


	}

	@Test(priority = 3, enabled = false)
	public void CheckContactNumber() throws IOException, InterruptedException {
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();

		

		Assert.assertEquals(ActualNumber, ExpectedNumber);
		
		ScreenShot();


	}

	@Test(priority = 4, enabled = false)
	public void CheckQitafLogoIsThereInTheFooter() throws IOException, InterruptedException {

		WebElement TheFooter = driver.findElement(By.tagName("footer"));
		boolean ActualResult = TheFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();
		

		Assert.assertEquals(ActualResult, ExpectedLogoResult);
		
		ScreenShot();

	}

	@Test(priority = 5, enabled = false)

	public void CheckHotelTabIsNotSelected() throws IOException, InterruptedException {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		

		String ActualValue = HotelTab.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, ExpectedValue);
		
		ScreenShot();

	}

	@Test(priority = 6, enabled = false)
	public void CheckDepatureDate() {


		


		String ActualDepature = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		

		Assert.assertEquals(ActualDepature, ExpectedDepature);

		;



	}

	@Test(priority = 7, enabled = false)
	public void CheckReturnDate() {
		

		

		String ActualReturn = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		

		Assert.assertEquals(ActualReturn, ExpectedReturn);

	}

	@Test(priority = 8 , enabled = true)
	public void RandomlyChangeTheLanguage() throws InterruptedException {
		
		
		randomlyGetWebsite();
		

		checkLangAndFillInfo();
		
		
		
	}
	
	
	@Test(priority = 9 , enabled = false)
	public void findResults() throws InterruptedException {
		
		Thread.sleep(30000);
		WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		boolean ActualResult = SearchResult.getText().contains("found") || SearchResult.getText().contains("مكان");

		

		Assert.assertEquals(ActualResult, ExpectedSearchResult);
		
	}
	
	@Test(priority = 10, enabled = true)
	public void CheckTheSortOption() throws InterruptedException, IOException {

		Thread.sleep(15000);

		WebElement SortOption = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		SortOption.click();

		Thread.sleep(2000);


		SortOptionChecker();
	}

}
