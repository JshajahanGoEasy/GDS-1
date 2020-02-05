package TestAutomation;

import java.io.IOException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import com.google.common.base.Function;

import resources.BrowserDriver;
import resources.Popup;

public class Test extends BrowserDriver {

	public WebDriver driver;

	@BeforeTest
	public void initialize1() throws IOException {

		driver = browser();

	}

	@org.testng.annotations.Test
	public void m1() throws InterruptedException {
		driver.get(prop.getProperty("sfUrl"));
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys("PareshK@goeasy.com.spl");
		driver.findElement(By.cssSelector(prop.getProperty("password"))).sendKeys("Hello@32");
		driver.findElement(By.xpath(prop.getProperty("clicklogin"))).click();
		Thread.sleep(3000);
		String baseUrl = driver.getCurrentUrl();
		System.out.println(baseUrl);
		if (baseUrl.contains(prop.getProperty("lightningUrl1"))
				|| baseUrl.contains(prop.getProperty("lightningUrl2"))) {
			// SWITCH TO CLASSIC VIEW
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("userIcon"))).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("switchToSFClassic"))).click();
		}

		// FSR-Application

		driver.findElement(By.xpath(prop.getProperty("searchmenu"))).sendKeys("Asha Jyoti");
		driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("clickfsr"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("fsrmenubutton"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("userdetail"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("fsrloginbutton"))).click();
		Thread.sleep(2000);
	//	driver.findElement(By.xpath(prop.getProperty("applicationsbutton"))).click();
		Thread.sleep(3000);
	//	driver.findElement(By.xpath(prop.getProperty("applicationlink"))).click();
		Thread.sleep(2000);

		

		// WAIT for User to Submit
	//	driver.switchTo().frame(driver.findElement(By.xpath(prop.getProperty("switchIframe"))));
		WebDriverWait waitAlertPresent = new WebDriverWait(driver,360,0000);
		waitAlertPresent.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		System.out.println("accepted");
		WebElement loadingTransferIcon = driver.findElement(By.xpath("//*[@id='loadingTransferPanel']"));    	
    	WebDriverWait waitLoadingTransferIcon = new WebDriverWait(driver,360,0000);
    	waitLoadingTransferIcon.until(ExpectedConditions.invisibilityOf(loadingTransferIcon));	
    	Thread.sleep(3000);
    	WebDriverWait waitLoad1 = new WebDriverWait(driver,360,0000);
		waitLoad1.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'submitForDecisionId')]"))));
		System.out.println("Not clickable");
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.xpath(prop.getProperty("switchIframe"))));
    
		
    	
    	
    	String getInterest = driver.findElement(By.xpath(prop.getProperty("get%"))).getText();
		
		System.out.println(getInterest);

		driver.get("C:\\Users\\ibhangu\\Desktop\\confirmationAlert.html");

		WebElement clickalert = driver.findElement(By.xpath("//*[@name='alert']"));

		clickalert.click();

		Thread.sleep(8000);
		String response = null;

		try {
			if (driver.findElement(By.xpath("//*[@id='msg']")).isDisplayed() == true) {
				 response=driver.findElement(By.xpath("//*[@id='msg']")).getText();

				System.out.println(response);

				
			}

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}
		
		if(response.contains("OK"))
		{

		driver.navigate().back();
		}
		else if(response.contains("CANCEL"))
		{

			driver.close();
			}
		
		// Income Table
		Thread.sleep(2000);

		WebElement Incometable = driver.findElement(By.xpath(prop.getProperty("incomeTable")));
		Thread.sleep(2000);
		List<WebElement> rowValsIncome = Incometable.findElements(By.tagName("tr"));
		int rowNumIncome = Incometable.findElements(By.tagName("tr")).size();

		int colNumIncome = driver.findElements(By.xpath(prop.getProperty("colincome"))).size();
		System.out.println("Total number of rows = " + rowNumIncome);
		System.out.println("Total number of columns = " + colNumIncome);

		int IncomeValue = 0;

		for (int i = 0; i < rowNumIncome; i++) {
			double subTotal = 0;
			// Get each row's column values by tag name
			List<WebElement> colValsIncome = rowValsIncome.get(i).findElements(By.tagName("td"));
			String IncomeFreq = colValsIncome.get(2).getText();
			System.out.println(IncomeFreq);
			String IncomeAmount = colValsIncome.get(3).getText();
			System.out.println(IncomeAmount);

			String str = IncomeAmount.replace(",", "");
			double subValue = Double.parseDouble(str.replace("$", ""));
			System.out.println(subValue);

			if (IncomeFreq.equals("Weekly")) {
				subTotal = subValue * 4;

			}

			else if (IncomeFreq.equals("Bi-Weekly")) {
				subTotal = subValue * 2;

			} else if (IncomeFreq.equals("Semi-Monthly")) {
				subTotal = subValue * 2;

			} else if (IncomeFreq.equals("Monthly")) {
				subTotal = subValue;

			}

			IncomeValue += subTotal;
			System.out.println("---------------");
		}

		System.out.println(IncomeValue);

		// Liabilities Table
		WebElement Liabilitiestable = driver.findElement(By.xpath(prop.getProperty("liabilitiesTable")));
		new Actions(driver).moveToElement(Liabilitiestable).perform();
		Thread.sleep(2000);
		List<WebElement> rowValsLiabilities = Liabilitiestable.findElements(By.tagName("tr"));
		int rowNumLiabilities = Liabilitiestable.findElements(By.tagName("tr")).size();

		int colNumLiabilities = driver.findElements(By.xpath(prop.getProperty("colliabilities"))).size();
		System.out.println("Total number of rows = " + rowNumLiabilities);
		System.out.println("Total number of columns = " + colNumLiabilities);

		int LiabilitiesValue = 0;
		for (int i = 0; i < rowNumLiabilities; i++) {
			// Get each row's column values by tag name
			List<WebElement> colValsLiabilities = rowValsLiabilities.get(i).findElements(By.tagName("td"));
			String LiabilitiesAmount = colValsLiabilities.get(3).getText();
			System.out.println(LiabilitiesAmount);
			String str = LiabilitiesAmount.replace(",", "");
			double subTotal = Double.parseDouble(str.replace("$", ""));
			System.out.println(subTotal);

			LiabilitiesValue += subTotal;

			System.out.println("---------------");
		}

		System.out.println(LiabilitiesValue);

		// Rent Expense
		WebElement Renttable = driver.findElement(By.xpath(prop.getProperty("rentTable")));
		new Actions(driver).moveToElement(Renttable).perform();
		Thread.sleep(2000);
		List<WebElement> rowRent = Renttable.findElements(By.xpath(prop.getProperty("rowrent")));

		int rowNumRent = Renttable.findElements(By.xpath(prop.getProperty("rowrent"))).size();
		System.out.println(rowNumRent);
		int colNumRent = driver.findElements(By.xpath(prop.getProperty("colrent"))).size();
		System.out.println("Total number of columns = " + colNumRent);
		int RentValue = 0;
		for (int i = 0; i < rowNumRent; i++) {
			// Get each row's column values by tag name
			List<WebElement> colValsRent = rowRent.get(i).findElements(By.tagName("td"));
			String RentAmount = colValsRent.get(1).getText();
			System.out.println(RentAmount);
			String str = RentAmount.replace(",", "");
			double subTotal = Double.parseDouble(str.replace("$", ""));

			System.out.println(subTotal);

			RentValue += subTotal;

			System.out.println("---------------");
		}

		System.out.println(RentValue);

		// Mortgages Table
		WebElement Mortgagestable = driver.findElement(By.xpath(prop.getProperty("mortgagesTable")));
		new Actions(driver).moveToElement(Mortgagestable).perform();
		Thread.sleep(2000);
		List<WebElement> rowMortgages = Mortgagestable.findElements(By.xpath(prop.getProperty("rowmortgages")));

		int rowNumMortgages = Mortgagestable.findElements(By.xpath(prop.getProperty("rowmortgages"))).size();
		System.out.println(rowNumMortgages);
		int colNumMortgages = driver.findElements(By.xpath(prop.getProperty("colmortgages"))).size();
		System.out.println("Total number of columns = " + colNumMortgages);
		int MortgagesValue = 0;
		for (int i = 0; i < rowNumMortgages; i++) {
			// Get each row's column values by tag name
			List<WebElement> colValsMortgages = rowMortgages.get(i).findElements(By.tagName("td"));
			String RentAmount = colValsMortgages.get(3).getText();
			System.out.println(RentAmount);
			String str = RentAmount.replace(",", "");
			double subTotal = Double.parseDouble(str.replace("$", ""));

			System.out.println(subTotal);

			MortgagesValue += subTotal;

			System.out.println("---------------");
		}

		System.out.println(MortgagesValue);

		// Other Table
		WebElement Othertable = driver.findElement(By.xpath(prop.getProperty("otherTable")));
		new Actions(driver).moveToElement(Othertable).perform();
		Thread.sleep(2000);
		List<WebElement> rowOther = Othertable.findElements(By.xpath(prop.getProperty("rowother")));

		int rowNumOther = Othertable.findElements(By.xpath(prop.getProperty("rowother"))).size();
		System.out.println(rowNumOther);
		int colNumOther = driver.findElements(By.xpath(prop.getProperty("colother"))).size();
		System.out.println("Total number of columns = " + colNumOther);
		int OtherValue = 0;
		for (int i = 0; i < rowNumOther; i++) {
			// Get each row's column values by tag name
			List<WebElement> colValsOther = rowOther.get(i).findElements(By.tagName("td"));
			String RentAmount = colValsOther.get(3).getText();
			System.out.println(RentAmount);
			String str = RentAmount.replace(",", "");
			double subTotal = Double.parseDouble(str.replace("$", ""));

			System.out.println(subTotal);

			OtherValue += subTotal;

			System.out.println("---------------");
		}

		System.out.println(OtherValue);

		double TotalLaibility = LiabilitiesValue + RentValue + MortgagesValue + OtherValue;
		System.out.println(TotalLaibility);

		// Get Total Debt value
		Thread.sleep(2000);

		driver.switchTo().frame(driver.findElement(By.xpath(prop.getProperty("switchIframe"))));
		WebElement Totalpay = driver.findElement(By.xpath(prop.getProperty("totalpayment")));
		
		String TotalDebtAmount = Totalpay.getText();
		String str = TotalDebtAmount.replace(",", "");
		double TotalDebt = Double.parseDouble(str.replace("$", ""));

		System.out.println(TotalDebt);
		if (TotalLaibility == TotalDebt) {
			System.out.println("Laibilities:Passed");
		} else {
			System.out.println("Laibilities:Failed");
		}

		// Income field Comparison
		Thread.sleep(2000);

		WebElement Totalinc = driver.findElement(By.xpath(prop.getProperty("totalincome")));
		
		String TotalIncomeAmount = Totalinc.getText();
		String st = TotalIncomeAmount.replace(",", "");
		double TotalIncome = Double.parseDouble(st.replace("$", ""));

		System.out.println(TotalIncome);
		if (IncomeValue == TotalIncome) {
			System.out.println("Income:Passed");
		} else {
			System.out.println("Income:Failed");
		}

		// Get Strategy
		String bkstrategy = driver
				.findElement(By.xpath("//th[contains(text(),'Bankruptcy Strategy')]/following-sibling::td[1]/span"))
				.getText();
		System.out.println(bkstrategy);

		// SWITCH IFRAME DEFAULT
		driver.switchTo().defaultContent();

		// Go to page 2 (Get time stamp for Decision received)

		driver.findElement(By.xpath(prop.getProperty("gotopg2"))).click();
		Thread.sleep(3000);
		String ts = driver.findElement(By.xpath(prop.getProperty("getTsApp"))).getText();
		System.out.println(ts);
		// Split Time-stamp, 1/31/2020 12:16 PM to 1/31/2020 & 12:16 PM
		String[] parts = ts.split(" ");
		String tsdate = parts[0];
		String tstime = parts[1];

		// Logout as FSR
		driver.findElement(By.xpath(prop.getProperty("logoutmenu"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("logoutbutton"))).click();

		// Download logs as Admin User

		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("clicklogs"))).click();
		driver.findElement(By.xpath(prop.getProperty("clickedit"))).click();
		driver.findElement(By.xpath(prop.getProperty("addtimestampdate"))).clear();
		driver.findElement(By.xpath(prop.getProperty("addtimestampdate"))).sendKeys(tsdate);
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("addfsrname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("addfsrname"))).sendKeys("asha jyoti");
		driver.findElement(By.xpath(prop.getProperty("clicksave"))).click();
		Thread.sleep(3000);

		// Re-arrange List and Match Timestamp(ts) from page2 with Logs Timestamp
		driver.findElement(By.xpath(prop.getProperty("clickname"))).click();
		String Timestamp = driver.findElement(By.xpath(prop.getProperty("getTsLog"))).getText();
		System.out.println(Timestamp);
		System.out.println(Timestamp.contains(ts));

	}

}
