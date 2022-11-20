package Page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GroupsPage extends LoadableComponent<GroupsPage> {
    private ChromeDriver driver;

    private static final By SEARCH = By.xpath("//*[@class='wrap-input__414z3']//input");
    private static final By GROUP_TABLE = By.xpath("//*[@class='caption']");
    private static final By GROUP_NAME = By.xpath("//*[@class='ucard-v_h ellip']");
    private static final By ROOT = By.xpath("//*[@id='hook_Block_BodySwitcher']");
    private static final By MY_GROUPS_BAR = By.xpath("//*[@id='hook_Block_MyGroupsTopBlock']//*[@class='portlet ']");
    private static final By MY_GROUPS_LINK = By.xpath("//*[@class='ml']");

    public GroupsPage(ChromeDriver driver)
    {
        this.driver = driver;
        get();
    }

    public GroupsPage searchForGroup(String groupName) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(SEARCH).sendKeys(groupName + Keys.ENTER + Keys.ENTER);
        Thread.sleep(1000);
        return this;
    }

    public GroupPage openGroup(String groupName) throws InterruptedException {

        GroupList groupList = new GroupList(driver, GROUP_TABLE);
        groupList.getGroupsList().get(0).findElement(GROUP_NAME).click();
        Thread.sleep(1000);
        return new GroupPage(driver);
    }

    public boolean groupIsRecentlyJoined(String name) throws InterruptedException {
        openMyGroups();
        Thread.sleep(100);
        return (new MyGroups(driver).getLastGroupName().equals(name));
    }

    private GroupsPage openMyGroups()
    {
        driver.findElement(MY_GROUPS_BAR)
                .findElement(MY_GROUPS_LINK).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        Assertions
                .assertDoesNotThrow(() -> wait.until(ExpectedConditions.visibilityOfElementLocated(ROOT)),
                        "Page did't loadeded");
    }
}

class GroupList
{
    private List<WebElement> groupsList;

    private static final By SEARCH_RESULTS = By.xpath("//*[@class='portlet_b']");

    public GroupList(ChromeDriver driver, By by)
    {
        groupsList = driver.findElement(SEARCH_RESULTS).findElements(by);
    }

    public List<WebElement> getGroupsList()
    {
        return this.groupsList;
    }
}

class MyGroups
{
    private List<WebElement> groups;

    private static final By MY_GROUPS = By.xpath("//*[@class='portlet']//*[@class='ugrid __xxxl ugrid__user-groups']");
    private static final By GROUP = By.xpath("//*[@class='group-vitrine-card_name']");

    public MyGroups(ChromeDriver driver)
    {
        groups = driver.findElement(MY_GROUPS).findElements(GROUP);
    }

    public String getLastGroupName() throws InterruptedException {
        Thread.sleep(1000);
        return groups.get(0).getText();
    }
}

