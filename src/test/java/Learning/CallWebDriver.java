package Learning;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CallWebDriver {

    ChromeDriver chromeDriver;

    // Setup data, etc.
    @BeforeMethod
    public void Setup() {
        WebDriverManager.chromedriver().setup();

        // Normal setup
        // chromeDriver = new ChromeDriver();

        // Incognito setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        chromeDriver = new ChromeDriver(options);
    }

    // Test scripts body
    @Test
    public void run() {
        chromeDriver.get("https://google.com");
        sleep(2000);
    }

    // Cleanup data, etc.
    @AfterMethod
    public void CleanUp() {
        chromeDriver.quit();
    }

    // Sleep
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
