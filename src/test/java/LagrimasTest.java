import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
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

    @Test
    @DisplayName("Should not allow registration when name field is not filled")
    void shouldNotAllowRegistrationWhenNameFieldIsNotFilled() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.sendKeys("CategoryTest");
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.sendKeys("AuthorTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        String categoryValue = categoryInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");
        assertEquals("CategoryTest", categoryValue);
        assertEquals("AuthorTest", authorValue);
    }

    @Test
    @DisplayName("Should not allow registration when category field is not filled")
    void shouldNotAllowRegistrationWhenCategoryFieldIsNotFilled() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.sendKeys("NameTest");
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.sendKeys("AuthorTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        String nameValue = nameInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");
        assertEquals("NameTest", nameValue);
        assertEquals("AuthorTest", authorValue);
    }

    @Test
    @DisplayName("Should not allow registration when author field is not filled")
    void shouldNotAllowRegistrationWhenAuthorFieldIsNotFilled() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.sendKeys("NameTest");
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.sendKeys("CategoryTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        String nameValue = nameInput.getAttribute("value");
        String categoryValue = categoryInput.getAttribute("value");
        assertEquals("NameTest", nameValue);
        assertEquals("CategoryTest", categoryValue);
    }

    @Test
    @DisplayName("Should not allow registration when name and category fields are not filled")
    void shouldNotAllowRegistrationWhenNameAndCategoryFieldsAreNotFilled() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.sendKeys("AuthorTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        String authorValue = authorInput.getAttribute("value");
        assertEquals("AuthorTest", authorValue);
    }

    @Test
    @DisplayName("Should not allow registration when name and author fields are not filled")
    void shouldNotAllowRegistrationWhenNameAndAuthorFieldsAreNotFilled() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.sendKeys("CategoryTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        String categoryValue = categoryInput.getAttribute("value");
        assertEquals("CategoryTest", categoryValue);
    }

    @Test
    @DisplayName("Should not allow registration when category and author fields are not filled")
    void shouldNotAllowRegistrationWhenCategoryAndAuthorFieldsAreNotFilled() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.sendKeys("NameTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        String nameValue = nameInput.getAttribute("value");
        assertEquals("NameTest", nameValue);
    }

    @Test
    @DisplayName("Should register tear successfully without name when required is removed from the field")
    void shouldRegisterTearSuccessfullyWithoutNameWhenRequiredIsRemovedFromTheField() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement nameInput = driver.findElement(By.id("nome"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", nameInput);
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.sendKeys("CategoryTest");
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.sendKeys("AuthorTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/cadastrar", currentUrl);
        String nameValue = nameInput.getAttribute("value");
        String categoryValue = categoryInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");
        assertEquals("", nameValue);
        assertEquals("", categoryValue);
        assertEquals("", authorValue);
    }

    @Test
    @DisplayName("Should register tear successfully without category when required is removed from the field")
    void shouldRegisterTearSuccessfullyWithoutCategoryWhenRequiredIsRemovedFromTheField() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", categoryInput);
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.sendKeys("NameTest");
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.sendKeys("AuthorTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/cadastrar", currentUrl);
        String nameValue = nameInput.getAttribute("value");
        String categoryValue = categoryInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");
        assertEquals("", nameValue);
        assertEquals("", categoryValue);
        assertEquals("", authorValue);
    }

    @Test
    @DisplayName("Should register tear successfully without author when required is removed from the field")
    void shouldRegisterTearSuccessfullyWithoutAuthorWhenRequiredIsRemovedFromTheField() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.sendKeys("NameTest");
        WebElement authorInput = driver.findElement(By.id("autor"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", authorInput);
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.sendKeys("CategoryTest");
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/cadastrar", currentUrl);
        String nameValue = nameInput.getAttribute("value");
        String categoryValue = categoryInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");
        assertEquals("", nameValue);
        assertEquals("", categoryValue);
        assertEquals("", authorValue);
    }

    @Test
    @DisplayName("Should register tear successfully without any insertion when required is removed from all fields")
    void shouldRegisterTearSuccessfullyWithoutAnyInsertionWhenRequiredIsRemovedFromAllFields() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement nameInput = driver.findElement(By.id("nome"));
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        WebElement authorInput = driver.findElement(By.id("autor"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", nameInput);
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", categoryInput);
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", authorInput);
        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/cadastrar", currentUrl);
        String nameValue = nameInput.getAttribute("value");
        String categoryValue = categoryInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");
        assertEquals("", nameValue);
        assertEquals("", categoryValue);
        assertEquals("", authorValue);
    }

    @Test
    @DisplayName("Should navigate back to home page from Cadastrar")
    void shouldNavigateBackToHomePageFromCadastrar() {
        driver.get("http://localhost:3000/cadastrar");
        WebElement returnButton = driver.findElement(By.xpath("//a[@href='/']"));
        returnButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/", currentUrl);
    }

    @Test
    @DisplayName("Should navigate to Edit page when clicking edit button")
    void shouldNavigateToEditPageWhenClickingEditButton() {
        driver.get("http://localhost:3000/");
        WebElement editButton = driver.findElement(By.xpath("(//div[@class='lagrima-card']//a)[1]"));
        editButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/lagrima?id=2", currentUrl);
    }

    @Test
    @DisplayName("Should navigate to edit page and edit the correct fields")
    void shouldNavigateToEditPageAndEditTheCorrectFields() throws InterruptedException {
        driver.get("http://localhost:3000/");
        WebElement editButton = driver.findElement(By.xpath("(//div[@class='lagrima-card']//a)[1]"));
        editButton.click();
        Thread.sleep(1000);
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.clear();
        nameInput.sendKeys("NewName");
        Thread.sleep(1000);
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.clear();
        categoryInput.sendKeys("NewCategory");
        Thread.sleep(1000);
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.clear();
        authorInput.sendKeys("NewAuthor");
        WebElement editButton2 = driver.findElement(By.id("editar-button"));
        editButton2.click();
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        WebElement nameInputReload = driver.findElement(By.id("nome"));
        WebElement categoryInputReload = driver.findElement(By.id("categoria"));
        WebElement authorInputReload = driver.findElement(By.id("autor"));
        String nameValue = nameInputReload.getAttribute("value");
        String categoryValue = categoryInputReload.getAttribute("value");
        String authorValue = authorInputReload.getAttribute("value");
        assertEquals("", nameValue);
        assertEquals("", categoryValue);
        assertEquals("", authorValue);
    }

    @Test
    @DisplayName("Should navigate to edit page and pass a blank field")
    void shouldNavigateToEditPageAndPassABlankField() throws InterruptedException {
        driver.get("http://localhost:3000/");
        WebElement editButton = driver.findElement(By.xpath("(//div[@class='lagrima-card']//a)[1]"));
        editButton.click();
        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.clear();
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.clear();
        categoryInput.sendKeys("NewCategory");
        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.clear();
        authorInput.sendKeys("NewAuthor");
        WebElement editButton2 = driver.findElement(By.id("editar-button"));
        editButton2.click();
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1000);
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();
        WebElement nameInputReload = driver.findElement(By.id("nome"));
        WebElement categoryInputReload = driver.findElement(By.id("categoria"));
        WebElement authorInputReload = driver.findElement(By.id("autor"));
        String nameValue = nameInputReload.getAttribute("value");
        String categoryValue = categoryInputReload.getAttribute("value");
        String authorValue = authorInputReload.getAttribute("value");
        assertEquals("", nameValue);
        assertEquals("", categoryValue);
        assertEquals("", authorValue);
    }
}