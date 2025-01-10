package practice.expandtesting.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTC1 {
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
    public  void Run() {
        // 2. Navigate to the register URL (/register)
        sleep(1000);
        chromeDriver.get("https://practice.expandtesting.com/register");

        // 3. Enter a valid Username
        WebElement userName = chromeDriver.findElement(By.name("username"));
        userName.sendKeys("abctmtt");
        sleep(1000);

        // 4. Enter a valid Password
        WebElement passWord = chromeDriver.findElement(By.name("password"));
        passWord.sendKeys("123abc");
        sleep(1000);

        // 5. Confirm the Password by re-entering it correctly
        WebElement confirmPassWord = chromeDriver.findElement(By.name("confirmPassword"));
        confirmPassWord.sendKeys("123abc");
        Assert.assertEquals(confirmPassWord.getText(), passWord.getText(), "Mật khẩu xác nhận không giống mật khẩu đã điền!");
        sleep(1000);

        // 6. Click the register button
        WebElement registerButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        registerButton.click();
        sleep(2000);

        // 7. Verify that the user is redirected to Login page (/login)
        String loginURL = "https://practice.expandtesting.com/login";
        Assert.assertEquals(chromeDriver.getCurrentUrl(), loginURL, "Chuyển hướng sai đường dẫn Login!");
        sleep(1000);

        // 8. Confirm that the success message "Successfully registered, you can log in now." is displayed
        WebElement messageFlash = chromeDriver.findElement(By.id("flash"));
        Assert.assertTrue(messageFlash.isDisplayed(), "Không hiển thị thông báo tạo tai khoản thành công!");
        String message = "Successfully registered, you can log in now.";
        Assert.assertEquals(messageFlash.getText(), message, "Không hiển thị thông báo đúng!");
    }

    @AfterMethod
    public void Cleanup() {
        sleep(2000);
        chromeDriver.quit();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
