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

public class LoginTC1 {
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

        // 4. Enter Username: practice
        WebElement userName = chromeDriver.findElement(By.id("username"));
        userName.sendKeys("practice");
        sleep(1000);

        // 5. Enter Password: SuperSecretPassword!
        WebElement passWord = chromeDriver.findElement(By.id("password"));
        passWord.sendKeys("SuperSecretPassword!");
        sleep(1000);

        // 6. Click the login button
        WebElement loginButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        sleep(1000);

        // 7. Verify that the user is redirected to the /secure page
        String secureUrl = "https://practice.expandtesting.com/secure";
        Assert.assertEquals(chromeDriver.getCurrentUrl(), secureUrl, "Không đúng đường dẫn trang secure!");
        String secureTitle = "Secure Page page for Automation Testing Practice";
        Assert.assertTrue(Objects.requireNonNull(chromeDriver.getTitle()).contains(secureTitle), "Không đúng tiêu đề trang secure!");
        sleep(1000);

        // 8. Confirm the success message "You logged into secure area!" is visible
        WebElement messageFlash = chromeDriver.findElement(By.id("flash"));
        String message = "You logged into a secure area!";
        Assert.assertTrue(messageFlash.getText().contains(message), "Không hiển thị thông báo đã đăng nhập!");
        sleep(1000);

        // 9. Verify that logout button is displayed
        WebElement logoutButton = chromeDriver.findElement(By.className("btn-danger"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Nút logout không được hiển thị!");
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
