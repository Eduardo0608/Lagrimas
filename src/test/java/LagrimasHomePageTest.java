import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LagrimasHomePageTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
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

    @Test
    @DisplayName("Should navigate to Edit page when clicking edit button")
    void shouldNavigateToEditPageWhenClickingEditButton() {
        driver.get("http://localhost:3000/");
        WebElement editButton = driver.findElement(By.xpath("(//div[@class='lagrima-card']//a)[1]"));
        editButton.click();
        String currentUrl = driver.getCurrentUrl();

        System.out.println(currentUrl);
        assertTrue(currentUrl.startsWith("http://localhost:3000/lagrima?id="));
    }
}