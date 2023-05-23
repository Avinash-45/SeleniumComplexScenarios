package com.selenium.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ComplexGraphScenario {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://emicalculator.net/");
		driver.manage().window().maximize();
		Actions action = new Actions(driver);
		List<WebElement> greenElements = driver.findElements(By.xpath(
				"//*[name()='svg']//*[local-name()='g' and contains(@class,'highcharts-series')]//*[local-name()='rect']"));
		int count = 0;
		Thread.sleep(2000);
		action.scrollByAmount(1350, 1350).build().perform();
		Thread.sleep(2000);

		for (WebElement e : greenElements) {
			action.moveToElement(e).build().perform();
			By textElement = By.xpath(
					"//*[name()='svg']//*[local-name()='g' and contains(@class,'highcharts-tooltip')]//*[local-name()='text']");
			String text = driver.findElement(textElement).getText();
			System.out.println(text);
			count++;
			if (count == 42) {
				break;
			}
		}

		driver.quit();

	}

}
