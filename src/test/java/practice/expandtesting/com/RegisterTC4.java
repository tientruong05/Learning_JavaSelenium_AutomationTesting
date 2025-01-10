package practice.expandtesting.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTC4 {
    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        // 1. Launch the browser
        chromeDriver = new ChromeDriver(options);
    }

    @Test
    public void Run() {
        // 2. Navigate to the Register page URL (/register)
        chromeDriver.get("https://practice.expandtesting.com/register");

        // 3. Enter a valid Username
        WebElement userName = chromeDriver.findElement(By.name("username"));
        userName.sendKeys("test011");

        // 4. Enter a valid Password
        WebElement passWord = chromeDriver.findElement(By.name("password"));
        passWord.sendKeys("123abc");

        // 5. Confirm the Password by entering different value
        WebElement confirmPassWord = chromeDriver.findElement(By.name("confirmPassword"));
        confirmPassWord.sendKeys("zxczxc");

        // 6. Click the register button
        WebElement registerButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        Actions action = new Actions(chromeDriver);
        action.click(registerButton).build().perform();

        // 7. Verify that an error message "Passwords do not match." is displayed
        WebElement flashMessage = chromeDriver.findElement(By.id("flash"));
        Assert.assertTrue(flashMessage.isDisplayed(), "Không hiển thị thông báo!");
        String message = "Passwords do not match.";
        Assert.assertEquals(flashMessage.getText(), message, "Thông báo hiển thị không đúng!");
    }

    @AfterMethod
    public void Cleanup() {
        chromeDriver.quit();
    }
}
