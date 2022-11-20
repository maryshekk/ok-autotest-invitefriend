package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class NotificationPage {
    private static final By LEFT_MENU = By.xpath("//*[@class='nav-side __navigation']");
    private static final By LEFT_MENU_TOOLS = By.xpath("//*[@class='nav-side_i-w']");
    private static final By NOTIFICATIONS_LIST = By.xpath("//*[@id='ntf_layer_content_inner']");
    private static final By NOTIFICATION = By.xpath("//*[@class='hookBlock']");
    private static final By SUMBIT = By.xpath("//*[@data-l='t,btn_accept']");
    private static final By CLOSE = By.xpath("//*[@class='toolbar-layer_close']");

    private List<WebElement> leftMenu;
    private ChromeDriver driver;

    public NotificationPage(ChromeDriver driver)
    {
        this.driver = driver;
        leftMenu = driver.findElement(LEFT_MENU).findElements(LEFT_MENU_TOOLS);
    }

    public NotificationPage showGroupInvitations() throws InterruptedException {
        leftMenu.get(3).findElement(By.xpath("//*[@class='nav-side_i  __with-ic __ac']")).click();
        Thread.sleep(100);
        return this;
    }

    public NotificationPage sumbitInvitation() throws InterruptedException {
        Thread.sleep(100);

        driver
                .findElement(NOTIFICATIONS_LIST)
                .findElements(NOTIFICATION)
                .get(0)
                .findElement(SUMBIT)
                .click();
        Thread.sleep(100);
        return this;
    }

    public HomePage close()
    {
        driver.findElement(CLOSE).click();
        return new HomePage(driver);
    }
}
