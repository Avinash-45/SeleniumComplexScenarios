package com.selenium.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ComplexCarousalScenario_With_Custom_Wait {
	static WebDriver driver;
	static Actions actions;

	public static WebElement retryingElement(String carousalText, int timeOut, WebDriver driver)
			throws InterruptedException {

		WebElement element = null;
		int attempts = 0;
		actions = new Actions(driver);
		while (attempts < timeOut) {
			try {
				element = driver.findElement(By.xpath("//h2[text()='" + carousalText + "']"));
				break;
			} catch (Exception e) {
				actions.scrollByAmount(1000, 1000).build().perform();
				Thread.sleep(3000);

			}
			attempts++;
		}

		if (element == null) {
			System.out.println("element is not found...tried for " + timeOut + " secs "
					+ " with the interval of 1000 millisecons");
		}
		return element;

	}

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.noon.com/uae-en/");
		driver.manage().window().maximize();
		String carousalHeaderText = "Clearance deals";
		retryingElement(carousalHeaderText, 10, driver);
		getCarousalElements(carousalHeaderText, driver);

	}

	public static void getCarousalElements(String text, WebDriver driver) {
		List<WebElement> products = driver.findElements(By.xpath("//h2[text()='" + text
				+ "']/parent::div/parent::div/following-sibling::div//div[@data-qa='product-name']"));
		WebElement arrowElement = driver.findElement(By.xpath("//h2[text()='" + text
				+ "']/parent::div//parent::div/parent::div//div[contains(@class,'swiper-button-next')]"));

		List<String> productNames = new ArrayList<String>();
		while (!arrowElement.getAttribute("class").contains("swiper-button-disabled")) {
			try {
				arrowElement.click();
			} catch (Exception e) {
				actions = new Actions(driver);
				actions.scrollByAmount(250, 250).build().perform();
			}
		}

		for (WebElement e : products) {
			productNames.add(e.getAttribute("title"));
			Collections.sort(productNames);
		}
		for (String e : productNames) {
			System.out.println(e);
		}
		driver.quit();

	}
}