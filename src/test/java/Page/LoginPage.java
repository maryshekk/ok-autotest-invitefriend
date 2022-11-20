package Page;
import ValueObject.UserBot;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends LoadableComponent<LoginPage> {

    private static final String TITLE = "Социальная сеть Одноклассники. Общение с друзьями в ОК. Ваше место встречи с одноклассниками";
    private static final String ENTER_LOGIN = "Введите логин";
    private static final String ENTER_PASSWORD = "Введите пароль";
    private static final String WRONG_ENTER = "Неправильно указан логин и/или пароль";
    private static final By LOGIN = By.xpath("//*[@class='it h-mod']");
    private static final By PASSWORD = By.xpath("//*[@class='it ']");
    private static final By SUBMIT = By.xpath("//*[@value='Войти в Одноклассники']");
    private static final By LOGIN_ERROR = By.xpath("//*[@class='input-e login_error']");
    private static final By PASSWORD_ERROR = By.xpath("//*[@class='input-e login_error']");
    private static final By SUMBIT_ERROR = By.xpath("//*[@class='input-e login_error']");
    private static final By ROOT = By.xpath("//*[@class='h-mod anon-main-design21_root-wrapper']");


    private ChromeDriver driver;

    public LoginPage(ChromeDriver driver)
    {
        this.driver = driver;
        get();
    }

    public LoginPage enterLogin(String login)
    {
        driver.findElement(LOGIN).sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password)
    {
        driver.findElement(PASSWORD).sendKeys(password);
        return this;
    }

    public HomePage clickSubmit()
    {
        driver.findElement(SUBMIT).click();
        return new HomePage(driver);
    }

    public HomePage login(String login, String password)
    {
        return this
                .enterLogin(login)
                .enterPassword(password)
                .clickSubmit();
    }

    public HomePage login(UserBot bot)
    {
        return this
                .enterLogin(bot.getLogin())
                .enterPassword(bot.getPassword())
                .clickSubmit();
    }

    public boolean loginIsEmpty()
    {
        return driver.findElement(LOGIN_ERROR).getText().equals(ENTER_LOGIN);
    }

    public boolean passwordIsEmpty()
    {
        return driver.findElement(PASSWORD_ERROR).getText().equals(ENTER_PASSWORD);
    }

    public boolean loginOrPasswordIsIncorrect()
    {
        return driver.findElement(SUMBIT_ERROR).
                getText().equals(WRONG_ENTER);
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        Assertions
                .assertDoesNotThrow(() -> wait.until(ExpectedConditions.visibilityOfElementLocated(ROOT)),
                        "Page did't loadeded");
    }
}
