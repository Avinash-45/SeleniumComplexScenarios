package com.selenium.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetDiseaseAlerts {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://petdiseasealerts.org/forecast-map#/");
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		actions.scrollByAmount(400, 400).build().perform();
		getStatemap("California");

	}

	public static void getStatemap(String State) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,map-instance)]")));
		By stateLocator = By.xpath(
				"(//*[name()='svg'])[5]//*[local-name()='g' and @class='region']//*[local-name()='g' and @class='rpath']/*[local-name()='path' and @name='"
						+ State + "']");

		WebElement stateElement = wait.until(ExpectedConditions.elementToBeClickable(stateLocator));
		stateElement.click();
	}

}
