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

public class Practice1 {
    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        chromeDriver = new ChromeDriver(options);
    }

    @Test
    public void run() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");

        // click button 1
        WebElement button1 = chromeDriver.findElement(By.id("btnExample1"));
        button1.click();

        // get value of lbStatusButton and verify
        WebElement lblstatus = chromeDriver.findElement(By.id("lbStatusButton"));
        String lblStatusValue = lblstatus.getText();
        Assert.assertEquals(lblStatusValue, "Click on Button 1");

        // get value of txtInput1 and verify
        WebElement input1 = chromeDriver.findElement(By.id("txtInput1"));
        String input1value =  input1.getAttribute("value");
        Assert.assertEquals(input1value, "Default value of input");

        String newValue = "code no bug";

        // clear text -> write newValue -> verify
        input1.clear();
        input1.sendKeys(newValue);
        String newInputValue = input1.getAttribute("value");
        Assert.assertEquals(newInputValue, newValue);

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
