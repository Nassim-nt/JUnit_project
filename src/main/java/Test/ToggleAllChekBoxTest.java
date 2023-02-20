package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class ToggleAllChekBoxTest {

	WebDriver driver;

	By ProductmNameField = By.name("data");
	By AddButton = By.cssSelector("input[type='submit'][value='Add']");
	By ToggleAllBox = By.cssSelector("input[type='checkbox'][value='on']");
	By RemoveButton = By.cssSelector("input[type='submit'][value='Remove']");
	By ItemList = By.id("todos-content");

	int startSize;
	int sizeAfterAddinsixboxes;
	int sizeAfterRemoveOneBox;
	int sizeBeforeRemoveOneBox;
	int itemCount1;
	int itemCount2;
	int itemCount3;
	int itemCount4;
	String startList = "//*[@id=\"todos-content\"]/form/ul/li[";
	String endList = "]";
	String names;
	String nameOnList;

	@Before
	public void init() {

		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://techfios.com/test/104/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void validateToggleAllBoxesChecked() {

		List<WebElement> elements = driver.findElements(By.xpath(startList + "i" + endList));

		for (int i = 1; i < elements.size(); i++) {
			WebElement element = driver.findElement(By.xpath(startList + i + endList));

			nameOnList = element.getText();
			if (nameOnList == null) {
				continue;
			}

		}

		System.out.println(nameOnList);

		for (int i = 1; i <= 6; i++) {
			driver.findElement(ProductmNameField).sendKeys("Testsp " + i);
			driver.findElement(AddButton).click();

		}
		int ele = elements.size();

		driver.findElement(ToggleAllBox).click();

		for (int i = 1; i <= ele; i++) {

			WebElement listItemCheckbox = driver.findElement(By.xpath(startList + i + endList));
			boolean isChecked = listItemCheckbox.isSelected();
			Assert.assertTrue(isChecked);
		}

		driver.findElement(ToggleAllBox).click();

	}

	public void validateAllItemsCouldBeRemovedUsingRemoveButton() {

		List<WebElement> elements = driver.findElement(ItemList).findElements(By.tagName("li"));

		for (int i = 1; i < elements.size(); i++) {
			WebElement element = driver.findElement(By.xpath(startList + i + endList));

			nameOnList = element.getText();
			if (nameOnList == null) {
				continue;
			}

		}

		System.out.println(nameOnList);

		for (int i = 1; i <= 6; i++) {
			driver.findElement(ProductmNameField).sendKeys("Test$g " + i);
			driver.findElement(AddButton).click();

		}
		int ele = elements.size();

		driver.findElement(ToggleAllBox).click();
		driver.findElement(RemoveButton).click();

	}

	@After
	public void tearDown() {

		driver.close();
	}

}
