package shopProduct;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


public class AddItem {

	public static void main(String[] args) {
				

				WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("https://www.webstaurantstore.com/");
				System.out.println(driver.getTitle());
				WebElement searchBox = driver.findElement(By.id("searchval"));
				searchBox.click();
				searchBox.sendKeys("stainless work table");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				driver.findElement(By.xpath("//button[@type='submit'][1]")).click();

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)"); // scroll down

//				List<WebElement> pagination = driver.findElements(By.xpath("//nav[@aria-label='pagination']"));
				int pgSize = 9;

				for (int j = 1; j <= pgSize; j++) {
					String var = "//a[normalize-space()='" + j + "']";
					System.out.println("Page : " + j);

					WebElement pagei = driver.findElement(By.xpath(var));
					pagei.click();

					List<WebElement> products = driver.findElements(By.id("ProductBoxContainer"));

					int lastItem = products.size() - 1;

					for (int i = 0; i < products.size(); i++) {
						String name = products.get(i).getText();

						if (name.contains("Table")) {

						} else {

							System.out.println("Error: Table is absent:" + name);
						}

						if (i == lastItem && j == 9) {

							driver.findElement(By.name("addToCartButton")).click();

							js.executeScript("window.scrollBy(document.body.scrollHeight,0)"); // scroll up

							WebElement viewCartButton = driver.findElement(By.xpath("//a[normalize-space()='View Cart']"));
							js.executeScript("arguments[0].click()", viewCartButton);
							WebElement element = driver.findElement((By.xpath("//button[contains(text(),'Empty Cart')]")));
							Actions actions = new Actions(driver);
							actions.moveToElement(element).click().build().perform();
							driver.close();

						}

					}
				}

			}
			
	}


