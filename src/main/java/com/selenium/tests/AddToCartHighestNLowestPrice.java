package com.selenium.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddToCartHighestNLowestPrice {
	static WebDriver driver;

	public static void main(String[] args) {
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		addToCartClickForMaxNMinPricedItems();

	}

	public static void addToCartClickForMaxNMinPricedItems() {
		List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		List<String> prices = new ArrayList<String>();
		List<Double> doublePrices = new ArrayList<Double>();
		for (WebElement e : priceList) {
			prices.add(e.getText().replace("$", ""));
		}
		for (String e : prices) {
			doublePrices.add(Double.parseDouble(e));
		}
		Collections.sort(doublePrices);
		driver.findElement(By.xpath("//div[normalize-space()='$" + doublePrices.get(doublePrices.size() - 1)
				+ "']/following-sibling::button")).click();
		driver.findElement(
				By.xpath("//div[normalize-space()='$" + doublePrices.get(0) + "']/following-sibling::button")).click();

	}

}
