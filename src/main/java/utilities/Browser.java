package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Exceptions.TitleMismatchException;
import Exceptions.URLMismatchException;
import Exceptions.TabsCountMismatchException;
import Exceptions.TextMismatchException;

public class Browser {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private WebElement ele;
	private List<WebElement> eleList;
	private Select dplist;
	
	private static int datanum=4;
	
	public void openChrome(ArrayList<String> data)
	{
		String exepath = "C:\\Users\\M1046846\\Downloads\\chromedriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exepath);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
	}
	
	public void verifyPresence(ArrayList<String> data)
	{
		this.find(data.get(datanum+1));
	}
	
	public void upload(ArrayList<String> data) throws AWTException, InterruptedException
	{
		Thread.sleep(1000);
		StringSelection stringSelection = new StringSelection(data.get(datanum));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
	}
	
	public void stopTill(ArrayList<String> data) throws NumberFormatException, InterruptedException
	{
		Thread.sleep(Integer.parseInt(data.get(datanum)));
	}
	
	public void verifyText(ArrayList<String> data) throws TextMismatchException
	{
		ele=this.find(data.get(datanum+1));
		String temp=ele.getText();
		if(temp.equals(data.get(datanum+2)))
		{
			return;
		}
		throw new TextMismatchException();
	}
	public void mousehover(ArrayList<String> data){
//        	ele=this.find(data.get(datanum));
		 
	    Actions builder = new Actions(driver);
	    
	    System.out.println(data.get(datanum+1));

	    Action mouseOver=builder.moveToElement(driver.findElement(By.xpath(data.get(datanum+1)))).click().build();
	    mouseOver.perform();	    
	}

	public void verifyTabsCount(ArrayList<String> data) throws TabsCountMismatchException
	{
		int i=driver.getWindowHandles().size();
		if( ( Integer.parseInt(data.get(datanum)) ) == i )
		{
			return;
		}
		throw new TabsCountMismatchException();
	}
	
	public void reload(ArrayList<String> data)
	{
		driver.navigate().refresh();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void verifydownload(ArrayList<String> data) {
		String name=find(data.get(datanum+1)).getText();
		driver.navigate().to(data.get(datanum+2));
		driver.findElement(By.linkText(name+"zip")).click();
	}
	public boolean verifysort(ArrayList<String> data) {
	    int size=data.size();
	    for(int i=0;i<=data.size();i++) {
	    	String verify=find(data.get(datanum+1)).getText();
	    	driver.navigate().to(data.get(datanum+2));
	    	driver.findElement(By.xpath(verify)).click();	
	    }
	    return false;
	}
//	public boolean verifysort(ArrayList<String> data) {
//		Collections.sort(data);
//		String verify=find(data.get(datanum+1)).getText();
//		driver.navigate().to(data.get(datanum+2));
//		driver.findElement(By.xpath(verify)).click();
////		if(CollectionUtils.isEqualCollection(data,verify))
//			
//		
//	}
	public void verifyTitle(ArrayList<String> data) throws TitleMismatchException
	{
		String s=driver.getTitle();
		if(s.equals(data.get(datanum)))
		{
			return;
		}
		throw new TitleMismatchException();
	}
	public void verifyUrl(ArrayList<String> data) throws URLMismatchException
	{
		String s=driver.getCurrentUrl();
		if(s.equals(data.get(datanum)))
		{
			return;
		}
		throw new URLMismatchException();
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	public void gotoURL(ArrayList<String> data)
	{
		driver.get(data.get(datanum));
		driver.manage().window().maximize();
	}
	public void closeBrowser(ArrayList<String> data)
	{
		driver.quit();
	}
	public WebElement find(String path)
	{
		ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		return ele;
	}
	public List<WebElement> findall(String path)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		eleList=driver.findElements(By.xpath(path));
		return eleList;
	}
	public void clickOn(ArrayList<String> data)
	{
		ele=this.find(data.get(datanum+1));
		ele.click();
	}
	public void sendData(ArrayList<String> data)
	{
		ele=this.find(data.get(datanum+1));
		ele.sendKeys(data.get(datanum+2));
	}
	public void selectElement(ArrayList<String> data)
	{
		ele=this.find(data.get(datanum+1));
		dplist= new Select(ele);
		dplist.selectByIndex(Integer.parseInt(data.get(datanum+2)));
	}
}
