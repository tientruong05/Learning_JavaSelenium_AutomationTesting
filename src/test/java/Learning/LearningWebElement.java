package Learning;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LearningWebElement {

    ChromeDriver chromeDriver;

    // Set up
    @BeforeMethod
    public void Setup(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        chromeDriver = new ChromeDriver(options);
    }

    // Test scripts body
    @Test
    public void run() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(1000);

        // Learning Web Element
        // find 1 id
        org.openqa.selenium.WebElement button1 = chromeDriver.findElement(By.id("btnExample1"));
        button1.click();

        // find list class name
        List<org.openqa.selenium.WebElement> buttons = chromeDriver.findElements(By.className("btn-success"));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
            sleep(1000);
        }
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
