package com.selenium.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragNDropScenario {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> draggable = driver
				.findElements(By.xpath("//div[@id='dropContent']//div[@class='dragableBox' and contains(@id,'box')]"));
		List<WebElement> droppable = driver.findElements(By.xpath("//div[@id='countries']/div"));

		Actions action = new Actions(driver);
		// positive Scenario

		for (WebElement e : draggable) {
			String capitalText = e.getText();
			for (WebElement k : droppable) {
				action.dragAndDrop(e, k).build().perform();
				By locator = By.xpath("(//div[text()='" + capitalText + "'])[2]");
				WebElement elementLocator1 = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				if (elementLocator1.getAttribute("style").contains("background-color")) {

					break;
				}
			}
		}

		returnDroppableElementsToStart();
		// Negative Scenario

		dragCountry("Madrid", "Norway");
		dragCountry("Rome", "Spain");
		dragCountry("Washington", "Italy");
		dragCountry("Copenhagen", "South Korea");
		dragCountry("Seoul", "Sweden");
		dragCountry("Stockholm", "United States");
		dragCountry("Oslo", "Denmark");

		returnDroppableElementsToStart();

		// Mixed Scenario

		dragCountry("Washington", "United States");
		dragCountry("Madrid", "Spain");
		dragCountry("Rome", "Denmark");
		dragCountry("Copenhagen", "South Korea");
		dragCountry("Seoul", "Sweden");
		dragCountry("Stockholm", "Italy");
		dragCountry("Oslo", "Norway");

		returnDroppableElementsToStart();

	}

	public static void returnDroppableElementsToStart() {
		List<WebElement> afterDropping = driver.findElements(By.xpath("//div[@id='countries']/div"));
		WebElement draggableSpace = driver.findElement(By.xpath("//div[@id='capitals']//div[@id='dropContent']"));
		Actions action = new Actions(driver);
		for (WebElement p : afterDropping) {

			action.dragAndDrop(p, draggableSpace).build().perform();

		}
	}

	public static void dragCountry(String capital, String country) throws InterruptedException {
		WebElement desam = driver.findElement(By.xpath("//div[text()='" + country + "' and contains(@id,box)]"));
		WebElement ooru = driver.findElement(By.xpath("(//div[text()='" + capital + "' and contains(@id,box)])[2]"));
		Actions action = new Actions(driver);
		action.dragAndDrop(ooru, desam).build().perform();

	}

}
