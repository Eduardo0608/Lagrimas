import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LagrimasCrudTest {

    private WebDriver driver;

    private final Faker faker = new Faker();

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
        nameInput.sendKeys(faker.name().firstName());

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.sendKeys(faker.book().genre());

        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.sendKeys(faker.book().author());

        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();
    }

    @Test
    @DisplayName("Should not allow registration when name field is not filled")
    void shouldNotAllowRegistrationWhenNameFieldIsNotFilled() {
        driver.get("http://localhost:3000/cadastrar");

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        String saveCategory = faker.book().genre();
        categoryInput.sendKeys(saveCategory);

        WebElement authorInput = driver.findElement(By.id("autor"));
        String saveBookAuthor = faker.book().author();
        authorInput.sendKeys(saveBookAuthor);

        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();

        String categoryValue = categoryInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");

        WebElement nameInput = driver.findElement(By.id("nome"));
        String nameValue = nameInput.getAttribute("value");
        assertEquals("", nameValue);

        assertEquals(saveCategory, categoryValue);
        assertEquals(saveBookAuthor, authorValue);
    }

    @Test
    @DisplayName("Should not allow registration when category field is not filled")
    void shouldNotAllowRegistrationWhenCategoryFieldIsNotFilled() {
        driver.get("http://localhost:3000/cadastrar");

        WebElement nameInput = driver.findElement(By.id("nome"));
        String saveName = faker.name().firstName();
        nameInput.sendKeys(saveName);

        WebElement authorInput = driver.findElement(By.id("autor"));
        String saveAuthorName = faker.book().author();
        authorInput.sendKeys(saveAuthorName);

        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();

        String nameValue = nameInput.getAttribute("value");
        String authorValue = authorInput.getAttribute("value");
        assertEquals(saveName, nameValue);
        assertEquals(saveAuthorName, authorValue);
    }

    @Test
    @DisplayName("Should not allow registration when author field is not filled")
    void shouldNotAllowRegistrationWhenAuthorFieldIsNotFilled() {
        driver.get("http://localhost:3000/cadastrar");

        WebElement nameInput = driver.findElement(By.id("nome"));
        String saveName = faker.name().firstName();
        nameInput.sendKeys(saveName);

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        String saveCategory = faker.book().genre();
        categoryInput.sendKeys(saveCategory);

        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();

        String nameValue = nameInput.getAttribute("value");
        String categoryValue = categoryInput.getAttribute("value");
        assertEquals(saveName, nameValue);
        assertEquals(saveCategory, categoryValue);
    }

    @Test
    @DisplayName("Should not allow registration when name and category fields are not filled")
    void shouldNotAllowRegistrationWhenNameAndCategoryFieldsAreNotFilled() {
        driver.get("http://localhost:3000/cadastrar");

        WebElement authorInput = driver.findElement(By.id("autor"));
        String saveAuthorName = faker.book().author();
        authorInput.sendKeys(saveAuthorName);

        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();

        String authorValue = authorInput.getAttribute("value");
        assertEquals(saveAuthorName, authorValue);
    }

    @Test
    @DisplayName("Should not allow registration when name and author fields are not filled")
    void shouldNotAllowRegistrationWhenNameAndAuthorFieldsAreNotFilled() {
        driver.get("http://localhost:3000/cadastrar");

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        String saveCategory = faker.book().genre();
        categoryInput.sendKeys(saveCategory);

        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();

        String categoryValue = categoryInput.getAttribute("value");
        assertEquals(saveCategory, categoryValue);
    }

    @Test
    @DisplayName("Should not allow registration when category and author fields are not filled")
    void shouldNotAllowRegistrationWhenCategoryAndAuthorFieldsAreNotFilled() {
        driver.get("http://localhost:3000/cadastrar");

        WebElement nameInput = driver.findElement(By.id("nome"));
        String saveName = faker.name().firstName();
        nameInput.sendKeys(saveName);

        WebElement registerButton = driver.findElement(By.id("cadastrar-button"));
        registerButton.click();

        String nameValue = nameInput.getAttribute("value");
        assertEquals(saveName, nameValue);
    }

    @Test
    @DisplayName("Should register tear successfully without name when required is removed from the field")
    void shouldRegisterTearSuccessfullyWithoutNameWhenRequiredIsRemovedFromTheField() {
        driver.get("http://localhost:3000/cadastrar");

        WebElement nameInput = driver.findElement(By.id("nome"));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", nameInput);

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        String saveCategory = faker.book().genre();
        categoryInput.sendKeys(saveCategory);

        WebElement authorInput = driver.findElement(By.id("autor"));
        String saveAuthorName = faker.book().author();
        authorInput.sendKeys(saveAuthorName);

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
        String saveName = faker.name().firstName();
        nameInput.sendKeys(saveName);

        WebElement authorInput = driver.findElement(By.id("autor"));
        String saveAuthor = faker.book().author();
        authorInput.sendKeys(saveAuthor);

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
        String saveName = faker.name().firstName();
        nameInput.sendKeys(saveName);

        WebElement authorInput = driver.findElement(By.id("autor"));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('required')", authorInput);

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        String saveCategory = faker.book().genre();
        categoryInput.sendKeys(saveCategory);

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

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        WebElement nameInput = driver.findElement(By.id("nome"));
        WebElement categoryInput = driver.findElement(By.id("categoria"));
        WebElement authorInput = driver.findElement(By.id("autor"));

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
    @DisplayName("Should navigate to edit page and edit the correct fields")
    void shouldNavigateToEditPageAndEditTheCorrectFields() throws InterruptedException {
        driver.get("http://localhost:3000/");

        WebElement editButton = driver.findElement(By.xpath("(//div[@class='lagrima-card']//a)[1]"));
        editButton.click();
        Thread.sleep(1000);

        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.clear();
        String saveName = faker.name().firstName();
        nameInput.sendKeys(saveName);
        Thread.sleep(1000);

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.clear();
        String saveCategory = faker.book().genre();
        categoryInput.sendKeys(saveCategory);
        Thread.sleep(1000);

        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.clear();
        String saveAuthor = faker.book().author();
        authorInput.sendKeys(saveAuthor);

        WebElement editButton2 = driver.findElement(By.id("editar-button"));
        editButton2.click();
        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1000);

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
        String saveCategory = faker.book().genre();
        categoryInput.sendKeys(saveCategory);

        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.clear();
        String saveAuthor = faker.book().author();
        authorInput.sendKeys(saveAuthor);

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

    @Test
    @DisplayName("Should navigate to edit page and do not fill in any fields")
    void shouldNavigateToEditPageAndDoNotFillInAnyFields() throws InterruptedException {
        driver.get("http://localhost:3000/");

        WebElement editButton = driver.findElement(By.xpath("(//div[@class='lagrima-card']//a)[1]"));
        editButton.click();

        WebElement nameInput = driver.findElement(By.id("nome"));
        nameInput.clear();

        WebElement categoryInput = driver.findElement(By.id("categoria"));
        categoryInput.clear();

        WebElement authorInput = driver.findElement(By.id("autor"));
        authorInput.clear();
        Thread.sleep(1000);

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

    @Test
    @DisplayName("Should delete an item when clicking delete button")
    void shouldDeleteAnItemWhenClickingDeleteButton() throws InterruptedException{
        driver.get("http://localhost:3000/");

        WebElement editButton = driver.findElement(By.xpath("(//div[@class='lagrima-card']//a)[1]"));
        editButton.click();

        WebElement nameInput = driver.findElement(By.id("nome"));

        //For some reason, Firefox doesn't let you delete a Tear if all the fields are filled in, so leaving a field empty makes the test pass successfully.
        //nameInput.clear();

        WebElement deleteButton = driver.findElement(By.id("deletar-button"));
        deleteButton.click();
        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1000);

        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/", currentUrl);
    }
}
