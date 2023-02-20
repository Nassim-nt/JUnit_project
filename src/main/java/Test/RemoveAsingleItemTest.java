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

public class RemoveAsingleItemTest {

	WebDriver driver;

	
	By ProductmNameField = By.name("data");
	By AddButton = By.cssSelector("input[type='submit'][value='Add']");
	By ToggleAllBox = By.cssSelector("input[type='checkbox'][value='on']");
	By RemoveButton = By.cssSelector("input[type='submit'][value='Remove']");
	By ItemList = By.xpath("//*[@id=\"todos-content\"]/form/ul");
	By CheckBox = By.xpath("//*[@id=\"todos-content\"]/form/ul/li[1]/input");

	int startSize;
	int sizeAfterAddinsixboxes;
	int sizeAfterRemoveOneBox;
	int sizeBeforeRemoveOneBox;
	
	String startList = "//*[@id=\"todos-content\"]/form/ul/li[";
	String endList = "]";
	String names;
	String nameOnList;
	String user = "Test0007";

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

		driver.findElement(ProductmNameField).sendKeys(user);
		driver.findElement(AddButton).click();

		sizeBeforeRemoveOneBox = driver.findElement(ItemList).findElements(By.tagName("li")).size();
		System.out.println("Size before removing a box = " + sizeBeforeRemoveOneBox);

		List<WebElement> elements = driver.findElement(ItemList).findElements(By.tagName("li"));
		driver.findElement(By.xpath(startList + elements.size() + endList)).click();
		driver.findElement(RemoveButton).click();

		sizeAfterRemoveOneBox = driver.findElement(ItemList).findElements(By.tagName("li")).size();
		System.out.println("size after removing one box :" + sizeAfterRemoveOneBox);

		nameOnList = driver.findElement(By.xpath(startList + elements.size() + endList)).getText();
		System.out.println(nameOnList);

		if (nameOnList.equals(user)) {
			System.out.println("deleted wrong box: ");

		} else {
			System.out.println("Box removed properly :");
		}

		Assert.assertEquals(sizeBeforeRemoveOneBox, sizeAfterRemoveOneBox);

	}

	@After
	public void tearDown() {

		driver.close();
	}

}
