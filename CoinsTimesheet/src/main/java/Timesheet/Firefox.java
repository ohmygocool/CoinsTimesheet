package Timesheet;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Firefox {

	@Test
	public static void StartFirefox() throws InterruptedException {
		WebDriverManager.firefoxdriver().setup();
		// It create firefox profile
		FirefoxProfile profile = new FirefoxProfile();
		// This will set the true value
		profile.setAcceptUntrustedCertificates(true);
		// This will open firefox browser using above created profile
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Thread.sleep(500);
		driver.get("https://hourglass.thinksoftglobal.com:8001/accounts/login/?next=/timesheet/create/");
		Thread.sleep(500);
		// Firefox Successfully lauched your coins PAGE
		driver.findElement(By.id("id_username")).sendKeys("TG3025");
		driver.findElement(By.id("id_password")).sendKeys("Start123");
		WebElement link = driver.findElement(By.xpath("//a[starts-with(@href, '#')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link);

		Thread.sleep(10700);
	
		  Alert a = driver.switchTo().alert(); a.accept();
	
		// DATE to Pick todays date from the Xpath
		Date d = new Date();
		String today = new SimpleDateFormat("dd-MMM-YYYY").format(d);
		System.out.println(new SimpleDateFormat("dd-MMM-YYYY").format(d));
		Thread.sleep(700);
		driver.findElement(By.xpath("//dt[@id=\"span_id" + today + "\"]")).click();
		// Below Codes are for clicking the add Button and selecting the task
		WebElement AddButton;
		AddButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Add']")));
		AddButton.click();
		Thread.sleep(500);
		driver.findElement(By.id("dproject_task")).click();
	
		driver.findElement(By.id("project_checkbox")).click();
	
		// driver.findElement(By.xpath("//input[@value='general1']")).click();
		WebElement element = driver.findElement(By.xpath("//input[@value='Ok']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@value='Ok']")).click();
		// Efforts
		driver.findElement(By.xpath("//input[@class='unsave_mode hasTimepicker']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"ui-timepicker-div\"]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[3]/a"))
				.click();
		Thread.sleep(500);
		// Complete
		Select complete = new Select(driver.findElement(By.xpath("//select")));
		complete.selectByValue("100");
		// driver.findElement(By.xpath("//input[@value='Send for approval']")).click();
	}
}