import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LagrimasTest {

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

    @Test
    @DisplayName("Should let change the size of the register page")
    void shouldLetChangeTheSizeOfTheRegisterPage() {
        driver.get("http://localhost:3000/cadastrar");
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().window().minimize();
    }

    @Test
    @DisplayName("Should save if all fields are filled in")
    void shouldSaveIfAllFieldsAreFilledIn() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.sendKeys("NameTest");
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.sendKeys("CategoryTest");
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.sendKeys("AuthorTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
    }
}