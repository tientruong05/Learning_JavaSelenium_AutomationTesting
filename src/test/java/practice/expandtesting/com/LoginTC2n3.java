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

import java.util.Objects;

public class LoginTC2n3 {
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
        // 2. Navigate to the login page URL
        chromeDriver.get("https://practice.expandtesting.com/login");
        sleep(1000);

        // 3. Verify that the login page is displayed successfully
        String loginURL = "https://practice.expandtesting.com/login";
        Assert.assertEquals(chromeDriver.getCurrentUrl(), loginURL, "Không đúng URL trang login!");
        String loginTitle = "Test Login Page for Automation Testing Practice";
        Assert.assertTrue(Objects.requireNonNull(chromeDriver.getTitle()).contains(loginTitle), "Không đúng tiêu đề trang login!");
        sleep(1000);

        // 4. Enter an incorrect Username
        WebElement userName = chromeDriver.findElement(By.id("username"));
        userName.sendKeys("notusername");
        sleep(1000);

        // 5. Enter Password
        WebElement passWord = chromeDriver.findElement(By.id("password"));
        passWord.sendKeys("SuperSecretPassword!");
        sleep(1000);

        // 6. Click the login button
        WebElement loginButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        sleep(1000);

        // 7. Verify that an error message "Invalid username." is displayed
        WebElement messageFlash = chromeDriver.findElement(By.id("flash"));
        String message = "Your username is invalid!";
        Assert.assertEquals(messageFlash.getText(), message, "Không hiển thị thông báo sai username!");
        sleep(1000);

        // 8. Ensure the user remains on the login page.
        Assert.assertEquals(chromeDriver.getCurrentUrl(), loginURL, "Không đúng URL trang login!");
        Assert.assertTrue(Objects.requireNonNull(chromeDriver.getTitle()).contains(loginTitle), "Không đúng tiêu đề trang login!");
        sleep(1000);

        // TC3. Wrong password
    }

    @AfterMethod
    public void Cleanup() {
        sleep(1000);
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
