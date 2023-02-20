package Test;

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

public class validateRemoveAllTest {

	WebDriver driver;

	By ProductmNameField = By.name("data");
	By AddButton = By.cssSelector("input[type='submit'][value='Add']");
	By ToggleAllBox = By.cssSelector("input[type='checkbox'][value='on']");
	By RemoveButton = By.cssSelector("input[type='submit'][value='Remove']");
	By ItemList = By.id("todos-content");
	
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
			driver.findElement(ProductmNameField).sendKeys("T7 " + i);
			driver.findElement(AddButton).click();

		}
		int ele = elements.size();

		driver.findElement(ToggleAllBox).click();
		driver.findElement(RemoveButton).click();
		int finalSize = elements.size();

		System.out.println(finalSize);
		Assert.assertEquals(finalSize, 0);

	}

	@After
	public void tearDown() {

		driver.close();
	}
}
