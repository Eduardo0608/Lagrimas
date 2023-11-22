import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainTest {
    @BeforeEach
    void setUp(){
        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    @DisplayName("Should open and close google")
    public void shouldOpenAndCloseGoogle() throws InterruptedException{
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        Thread.sleep(1000);
        driver.quit();
    }
}
