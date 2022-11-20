package Page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends LoadableComponent<HomePage> {
    private ChromeDriver driver;

    private static final String TITLE = "Одноклассники";
    private static final By IMAGE = By.xpath("//*[@class='tico ellip']");
    private static final By GROUPS = By.xpath("//*[@data-l='t,userAltGroup']");
    private static final By NOTIFICATIONS = By.xpath("//*[@data-l='t,notifications']");
    private static final By ROOT = By.xpath("//*[@id='hook_Block_BodySwitcher']");

    public HomePage(ChromeDriver driver)
    {
        this.driver = driver;
        get();
    }

    public GroupsPage goToGroups() throws InterruptedException {
        driver.findElement(GROUPS).click();
        Thread.sleep(100);
        return new GroupsPage(driver);
    }

    public NotificationPage openNotifications() throws InterruptedException {
        driver.findElement(NOTIFICATIONS).click();
        Thread.sleep(1000);
        NotificationPage notifications = new NotificationPage(driver);
        return notifications;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        Assertions
                .assertDoesNotThrow(() -> wait.until(ExpectedConditions.visibilityOfElementLocated(ROOT)),
                        "Page did't loadeded");    }
}
