package Learning;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearningActions {
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
        sleep(1000);

        WebElement button = chromeDriver.findElement(By.id("btnExample1"));

        // Learning Actions in selenium
        Actions action = new Actions(chromeDriver);

        // move to Element
        action.moveToElement(button);

        // right click
        action.click(button).build().perform();

        // left click
        action.contextClick(button).build().perform();

        // double click
        action.doubleClick(button).build().perform();

        // drag & drop
        action.dragAndDrop(button, button).build().perform();
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
