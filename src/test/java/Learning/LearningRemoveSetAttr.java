package Learning;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearningRemoveSetAttr {
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
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(1000);

        WebElement txtInput2 = chromeDriver.findElement(By.id("txtInput2"));
        removeAttribute(txtInput2, "disabled");
        sleep(1000);
        txtInput2.clear();
        sleep(1000);
        txtInput2.sendKeys("het cuu cu Tien");
        sleep(1000);
        setAttribute(txtInput2, "disabled");
    }

    // Xóa thuộc tính
    public void removeAttribute(WebElement element, String attr) {
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].removeAttribute('"+ attr +"')", element);
    }

    // Đặt lại thuộc tính
    public void setAttribute(WebElement element, String attr) {
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].setAttribute('"+ attr +"', '')", element);
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
