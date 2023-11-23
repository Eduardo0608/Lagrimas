import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Should open and close google")
    public void shouldOpenAndCloseGoogle() {
        driver.get("https://www.google.com/");
    }

    @Test
    @DisplayName("Should open and close Lagrimas project")
    public void shouldOpenAndCloseLagrimasProject() {
        driver.get("http://localhost:3000/");
    }

    @Test
    @DisplayName("Should open tear's register page")
    public void shouldOpenTearsRegisterPage() {
        driver.get("http://localhost:3000/");
        WebElement registerPath = driver.findElement(By.xpath("//a[@href='/cadastrar']"));
        registerPath.click();
        String novaURL = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/cadastrar", novaURL);
    }

    @Test
    @DisplayName("Should let change the size of the main page")
    void shouldLetChangeTheSizeOfTheMainPage() {
        driver.get("http://localhost:3000/");
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().window().minimize();
    }
}