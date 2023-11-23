package com.selenium.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FilterHandlingEcommApp {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://www.t-mobile.com/tablets");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()='Accept' and @id='onetrust-accept-btn-handler']"))
				.click();
		Thread.sleep(3000);
		selectFilter("Deals", "New", "Special offer");
		selectFilter("Brands", "Apple", "TCL", "Samsung");
		// selectFilter("Operating System", "Android");
		// selectFilter("Deals", "all");
		// selectFilter("Brands", "all");
		selectFilter("Operating System", "all");

	}

	public static void selectFilter(String filterName, String... subFilter) throws InterruptedException {
		if (!subFilter[0].equals("all")) {
			driver.findElement(By.xpath("//legend[normalize-space()='" + filterName + "']")).click();
			Thread.sleep(3000);
			for (int i = 0; i < subFilter.length; i++) {
				driver.findElement(By
						.xpath("//span[normalize-space()='" + subFilter[i] + "']/parent::span/preceding-sibling::span"))
						.click();
			}
		} else {
			driver.findElement(By.xpath("//legend[normalize-space()='" + filterName + "']")).click();
			Thread.sleep(3000);
			List<WebElement> checkBoxElements = driver.findElements(By.xpath("//legend[normalize-space()='" + filterName
					+ "']/parent::mat-panel-title/parent::span/parent::mat-expansion-panel-header/following-sibling::div//span[@class='mat-checkbox-inner-container']"));
			for (int i = 0; i < checkBoxElements.size(); i++) {
				checkBoxElements.get(i).click();
			}

		}

	}

}
