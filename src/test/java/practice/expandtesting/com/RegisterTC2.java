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

public class RegisterTC2 {
    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        // 1. Launch the browser
        chromeDriver = new ChromeDriver(options);
        sleep(1000);
    }

    @Test
    public void Run() {
        // 2. Navigate to the register page URL (/register)
        chromeDriver.get("https://practice.expandtesting.com/register");
        sleep(1000);

        // 3. Leave the username field blank

        // 4. Enter valid password
        String password = "123abc";
        WebElement passWord = chromeDriver.findElement(By.name("password"));
        passWord.sendKeys(password);
        sleep(1000);

        // 5. Confirm the password by re-entering it
        WebElement confirmPassWord = chromeDriver.findElement(By.name("confirmPassword"));
        confirmPassWord.sendKeys(password);
        Assert.assertEquals(confirmPassWord.getText(), passWord.getText(), "Mật khẩu xác nhận chưa chính xác!");
        sleep(1000);

        // 6. Click the register button
        WebElement registerButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        Actions action = new Actions(chromeDriver);
        action.click(registerButton).build().perform();
        sleep(1000);

        // 7. Verify that an error message "All fields are required." is displayed
        WebElement flashMessage = chromeDriver.findElement(By.id("flash"));
        Assert.assertTrue(flashMessage.isDisplayed(), "Không hiển thị thông báo!");
        String message = "All fields are required.";
        Assert.assertEquals(flashMessage.getText(), message, "Không hiển thị thông báo đúng!");
    }

    @AfterMethod
    public void Cleanup() {
        sleep(1000);
        chromeDriver.quit();
    }

    private void sleep (int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
