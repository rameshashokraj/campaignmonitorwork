package login.capmaignmonitor;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Login {
	WebDriver driver;
	
	public WebElement get_username() throws Exception {
		return (driver.findElement(By.xpath("/html/body/section[2]/div/div[1]/div/form/label[1]/input")));
	}
	
	public WebElement get_companyname() throws Exception {
		return (driver.findElement(By.xpath("/html/body/section[2]/div/div[1]/div/form/label[2]/input")));
	}
	
	public WebElement get_email() throws Exception {
		return (driver.findElement(By.xpath("/html/body/section[2]/div/div[1]/div/form/label[3]/input")));
	}
	
	public WebElement get_password() throws Exception {
		return (driver.findElement(By.xpath("/html/body/section[2]/div/div[1]/div/form/label[4]/input")));
	}
	public WebElement get_username_submit() throws Exception {
		return (driver.findElement(By.xpath("/html/body/section[2]/div/div[1]/div/form/button")));
	}
	
	
	public WebElement get_continue_page() throws Exception {
		return (driver.findElement(By.cssSelector(".cmds-button")));
	}
	
	public WebElement get_organization_size() throws Exception {
		return (driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div/form/div[2]/fieldset/div/div[1]/div/label/h2")));
	}
	
	public WebElement get_organization_type() throws Exception {
		return (driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div/form/div[3]/fieldset/div/div[1]/div/label/h2")));
	}
	
	public WebElement get_subscribers() throws Exception {
		return (driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div/form/div[1]/fieldset/div/div[1]/div/label/h2")));
	}
	
	public WebElement get_skip_button() throws Exception {
		return (driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div/form/div[2]/fieldset/div/button")));
	}
	
	public WebElement get_cm_user() throws Exception {
		return (driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[1]/div/div/form/div[1]/fieldset/div/div[1]/div/label/h2")));
	}
	
	public WebElement get_let_get_Start() throws Exception {
		return (driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[1]/div/div/form/div[2]/div[2]/button")));
	}
	
	public WebElement get_verify_home_page() throws Exception {
		return (driver.findElement(By.xpath("//*[@id='root']/div/div/div/div[2]/div/div[1]/p")));
	}
	
	//Get chrome Driver and implicit wait
	public Login() {
		System.setProperty("webdriver.chrome.driver",
				"src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	//Login to Campaign Monitor url using test.properties file.
	public WebDriver LoginToCM(String environment,String variable) throws Exception {
		Properties props = new Properties();
		String fileName="environment//"+environment+"//"+environment+".properties";
		InputStream inStream = getClass().getClassLoader().getResourceAsStream(fileName);
		if(inStream != null) {
		props.load(inStream);
		}
		else {
			throw new FileNotFoundException(fileName+ "not found in properties");
		}
		driver.get(props.getProperty("campaignmonitor.baseurl"));
		SoftAssert Assert = new SoftAssert();
		//Signup form page. Getting data from property file
		String userdetails = props.getProperty(variable);
		String[] user = userdetails.split(":");
		String userName=user[0];
		String company=user[1];
		String password=user[2];
		Thread.sleep(2000);
		get_username().sendKeys(userName);
		Thread.sleep(2000);
		get_companyname().sendKeys(company);
		Thread.sleep(2000);
		get_email().sendKeys("cm@gmail.com");
		Thread.sleep(2000);
		get_password().sendKeys(password);
		Thread.sleep(2000);
		get_username_submit().click();
		Thread.sleep(3000);
		
		//Welcome Page form Filling
		Select emailmarketingType = new Select(driver.findElement(By.name("email-marketer-type")));
		emailmarketingType.selectByValue("starting-out");
		Thread.sleep(2000);
		Select emailmarkerrole = new Select(driver.findElement(By.name("email-marketer-role")));
		emailmarkerrole.selectByValue("developer");
		Thread.sleep(2000);
		get_continue_page().click();
		Thread.sleep(4000);
		//Organization page
		Select organization = new Select(driver.findElement(By.className("cmds-select__control cmds-spacing--outer-3x-large qa-organization-industry")));
		organization.selectByValue("automotive");
		Thread.sleep(2000);
		get_organization_size().click();
		Thread.sleep(2000);
		get_organization_type().click();
		Thread.sleep(2000);
		get_continue_page().click();
		Thread.sleep(2000);
		//Filling Subscriber page
		get_subscribers().click();
		Thread.sleep(2000);
		get_skip_button().click();
		Thread.sleep(2000);
		get_continue_page().click();
		Assert.assertTrue(get_cm_user().getText().contains("Your account is ready!"));
		Thread.sleep(2000);
		get_cm_user().click();
		Thread.sleep(2000);
		get_let_get_Start().click();
		//Assert Login successful
		Assert.assertTrue(get_verify_home_page().getText().contains("Let’s get you started"));
		Assert.assertAll();
		return driver;
	}

}
