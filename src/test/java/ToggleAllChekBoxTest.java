import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

public class ToggleAllChekBoxTest {

	
	WebDriver driver;

	public ToggleAllChekBoxTest(WebDriver driver) {
		this.driver = driver;
	}
	
	By ProductmNameField = By.xpath("//input[@name='data']");
	By AddButton = By.xpath("//input[@value='Add']");
	By ToggleAllBox = By.xpath("//input[@name='allbox']");
	By RemoveButton = By.xpath("//input[@value='Remove']");
	By ItemList = By.xpath("//body/div[@id='todos-content']/form/ul");

	int startSize;
	int itemCount1;
	int itemCount2;
	int itemCount3;
	int itemCount4;
	String startList = "//ul/li[";
	String endList = "]/input[@type='checkbox']";
	
	
	
	

	public void testRemoveSingleItem() {
		List<Integer> boxesNumber = new ArrayList<Integer>();
		for (int i =0; i<=boxesNumber.size();i++ ) {
			driver.findElement(By.xpath(startList + 1 + endList)).getSize();
			System.out.println("This is starting size: "+startSize);
		}
		for (int i = 1; i <= 6; i++) {
			driver.findElement(ProductmNameField).sendKeys("Test " + i);
			driver.findElement(AddButton).click();
			
		}
	
		int sizeAfterAddinsixboxes= boxesNumber.size();
		Assert.assertEquals(sizeAfterAddinsixboxes, (startSize + 6));
		
		driver.findElement(ToggleAllBox).click();
		
		for (int i = 1; i <= sizeAfterAddinsixboxes; i++) {

			WebElement listItemCheckbox = driver.findElement(By.xpath(startList + i + endList));
			boolean isChecked = listItemCheckbox.isSelected();
			Assert.assertTrue(isChecked);
		}
		
		driver.findElement(ToggleAllBox).click();
		
		
		

		int itemCount3 = driver.findElement(ItemList).findElements(By.tagName("li")).size();
		System.out.println("size of list after removing one item :" + itemCount3);

		Assert.assertEquals(itemCount3, (itemCount2 - 1));

	}


}
