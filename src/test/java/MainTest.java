import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainTest {

    private WebDriver driver;

    @BeforeEach
    void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    @DisplayName("Should open and close google")
    public void shouldOpenAndCloseGoogle() throws InterruptedException{
        setUp();
        driver.get("https://www.google.com/");
        Thread.sleep(1000);
        tearDown();
    }
}
