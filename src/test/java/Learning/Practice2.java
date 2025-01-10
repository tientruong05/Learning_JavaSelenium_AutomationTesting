package Learning;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Practice2 {
    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        chromeDriver = new ChromeDriver(options);
    }

    @Test
    public void Run() {
        chromeDriver.get("https://practice.expandtesting.com/login");
        sleep(1000);

        WebElement userName = chromeDriver.findElement(By.id("username"));
        userName.sendKeys("practice");
        sleep(500);
        WebElement passWord = chromeDriver.findElement(By.id("password"));
        passWord.sendKeys("SuperSecretPassword!");
        sleep(500);
        WebElement loginButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        sleep(1000);
        WebElement userNameValue = chromeDriver.findElement(By.id("username"));

        String userNameLog = "Hi, practice!";
        Assert.assertEquals(userNameLog, userNameValue.getText());

        sleep(500);
        WebElement logoutButton = chromeDriver.findElement(By.className("btn-danger"));
        logoutButton.click();
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
