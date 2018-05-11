package operation;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PerformOperation {

	WebDriver driver;
	public PerformOperation(WebDriver driver){
		this.driver = driver;
	}
	public void perform(String operation,String objectName,String objectType,String value, String data) throws Exception{
		By by=null;
		switch (objectType.toLowerCase()){
		case "id":
			by= By.id(value);
			break;
			
		case "name":
			by= By.name(value);
			break;
			
		case "class":
			by= By.className(value);
			break;
			
		case "link":
			by= By.linkText(value);
			break;
			
		case "partiallink":
			by= By.partialLinkText(value);
			break;
			
		case "css":
			by= By.cssSelector(value);
			break;
			
		case "xpath":
			by= By.xpath(value);
			break;
		
		case "tag":
			by= By.tagName(value);
			break;
		
		default :
			break;
		}
		System.out.println("");
		switch (operation.toLowerCase()) {
		case "openurl":
			//open url in browser
			driver.get(data);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		case "entertext":
			//Set text on control
			driver.findElement(by).sendKeys(data);
			break;
			
		case "click":
			//click on element
			driver.findElement(by).click();
			break;
			
		case "select":
			//Get text of an element
			new Select(driver.findElement(by)).selectByVisibleText(data);;
			break;
			
		case "wait":
			Thread.sleep(Integer.parseInt(data));
			break;
			
		case "waitfor":
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
			break;
			
		case "gettext":
			//Get text of an element
			driver.findElement(by).getText();
			break;
			
		case "maximizebrowser":
			//Get text of an element
			driver.manage().window().maximize();
			break;
			
		case "closebrowser":
			//Get text of an element
			driver.quit();
			break;

		default:
			break;
		}
	}
	
	/**
	 * Find element BY using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(p.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		}else
		{
			throw new Exception("Wrong object type");
		}
	}
}
