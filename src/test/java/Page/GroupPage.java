package Page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroupPage {
    private ChromeDriver driver;

    private static final By JOIN = By.xpath("//*[@class='button-pro __wide']");
    private static final By STATUS_BUTTON = By.xpath("//*[@class='dropdown_ac button-pro __sec __with-arrow __arrow-svg __wide']");
    private static final By QUIT = By.xpath("//*[@class='dropdown_n']");
    private static final By CONFIRM = By.xpath("//*[@class='modal_buttons_yes button-pro']");
    private static final By OPTIONS = By.xpath("//*[@class='modal_buttons_yes button-pro']");
    private static final By NOTIFICATION = By.xpath("//*[@class='tip-block_cnt']");

    public GroupPage(ChromeDriver driver)
    {
        this.driver = driver;
    }

    public GroupPage joinGroup() throws InterruptedException {
        driver.findElement(JOIN).click();
        Thread.sleep(1000);
        return this;
    }

    public GroupPage inviteFriend(String name) throws InterruptedException {
        GroupHead head = new GroupHead(driver);
        head.showOptions().clickInvite();
        Thread.sleep(1000);
        FriendsInvitationWindow window = new FriendsInvitationWindow(driver);
        window.selectFriend(name).invite();
        return this;
    }

    public String invitationSent()
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return Assertions.assertDoesNotThrow(
                () -> wait.until(ExpectedConditions.visibilityOfElementLocated(NOTIFICATION)),
                "invitation didn't send").getText();
    }

    public static void quitGroup(ChromeDriver driver) throws InterruptedException {
        driver.findElement(STATUS_BUTTON).click();
        Thread.sleep(100);
        driver.findElement(QUIT).click();
        Thread.sleep(1000);
        driver.findElement(CONFIRM).click();
    }

    public boolean groupJoined()
    {
        return driver.findElement(STATUS_BUTTON).getText().equals("В группе");
    }
}

class GroupHead
{
    private WebElement header;

    private static final By HEADER = By.xpath("//*[@class='main-content-header_data_top']");
    private static final By DROPDOWN_BUTTON = By.xpath("//*[@class='u-menu_a toggle-dropdown']");
    private static final By DROPDOWN_BUTTONS = By.xpath("//*[@class='u-menu_li expand-action-item']");
    private static final By DROPDOWN_MENU = By.xpath("//*[@class='dropdown_cnt u-menu_lvl2']");
    private static final By DROPDOWN_OPTION = By.xpath("//*[@class='u-menu_a ']");

    public GroupHead(ChromeDriver driver)
    {
        header = driver.findElement(HEADER);
    }

    public GroupHead showOptions() throws InterruptedException {
        header.findElements(DROPDOWN_BUTTONS).get(1).click();
        Thread.sleep(100);
        return this;
    }

    public void clickInvite() throws InterruptedException {
        header.findElements(DROPDOWN_OPTION).get(1).click();
    }
}

class FriendsInvitationWindow extends LoadableComponent<FriendsInvitationWindow>
{
    private WebElement window;

    private static final By FRIENDS_WINDOW = By.xpath("//*[@class='modal-new_center']");
    private static final By FRIENDS_LIST = By.xpath("//*[@class='ugrid_cnt']");
    private static final By FRIENDS_CARD = By.xpath("//*[@class='sel-single selectable-card']");
    private static final By FRIENDS_SEARCH = By.xpath("//*[@class='modal-new_center']//*[@class='it_w search-input']//input");
    private static final By FRIENDS_INVITE = By.xpath("//*[@data-l='t,confirm']");

    public FriendsInvitationWindow(ChromeDriver driver)
    {
        window = driver.findElement(FRIENDS_WINDOW);
        get();
    }

    public FriendsInvitationWindow selectFriend(String name) throws InterruptedException {
        window.findElement(FRIENDS_SEARCH).sendKeys(name);
        Thread.sleep(1000);
        window.findElement(FRIENDS_LIST).findElements(FRIENDS_CARD).get(0).click();
        return this;
    }

    public void invite()
    {
        window.findElement(FRIENDS_INVITE).click();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        Assertions.assertTrue(window.isDisplayed());
    }
}


