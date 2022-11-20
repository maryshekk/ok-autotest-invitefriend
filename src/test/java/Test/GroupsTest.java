package Test;

import Page.GroupPage;
import Page.GroupsPage;
import Page.HomePage;
import Page.LoginPage;
import ValueObject.UserBot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class GroupsTest extends BaseTest {
    private static final String LOGIN1 = "technoPol8";
    private static final String PASSWORD1 = "technoPolis2022";

    private static final String LOGIN2 = "technoPol22";
    private static final String PASSWORD2 = "technoPolis2022";

    private static UserBot user1 = new UserBot(LOGIN1, PASSWORD1);
    private static UserBot user2 = new UserBot(LOGIN2, PASSWORD2);

    private static String GROUP_NAME = "Санкт-Петербургский политехнический университет";

    @Override
    @AfterEach
    public void close()
    {
        try {
            GroupPage.quitGroup(driver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @DisplayName("go to groups")
    @Test
    public void openGroupsPage()
    {
        HomePage homePage = new LoginPage(driver).login(user1);
        try {
            GroupsPage groupsPage = homePage.goToGroups();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("go to groups and join group")
    @ParameterizedTest
    @ValueSource(strings = {"Санкт-Петербургский политехнический университет"})
    public void joinGroup(String title) {
        HomePage homePage = new LoginPage(driver).login(user1);
        try {
            GroupsPage groupsPage = homePage.goToGroups();

        GroupPage groupPage = groupsPage
                .searchForGroup(title)
                .openGroup(title)
                .joinGroup();
    assert groupPage.groupJoined();
        } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

    @DisplayName("invite frined into group and check invitation")
    @ParameterizedTest
    @ValueSource(strings = {"Санкт-Петербургский политехнический университет"})
    public void inviteFriend(String name) {
        HomePage homePage = new LoginPage(driver).login(user1);
        GroupsPage groupsPage;
        try {
            groupsPage = homePage.goToGroups();
            GroupPage groupPage = groupsPage
                    .searchForGroup(name)
                    .openGroup(name)
                    .joinGroup();
            Assertions.assertTrue(groupPage.groupJoined(), "group didn't join");
            groupPage.inviteFriend(user2.getLogin());

            Assertions.assertEquals(groupPage
                    .invitationSent(), "Приглашение отправлено", "invitation didn't send");

            ChromeDriver driver2 = new ChromeDriver();
            driver2.get(this.getURL());
            HomePage homePage2 = new LoginPage(driver2).login(user2);
            homePage2
                    .openNotifications()
                    .showGroupInvitations()
                    .sumbitInvitation()
                    .close();
            Assertions.assertTrue(homePage2.goToGroups().groupIsRecentlyJoined(name), "group didn't joined");
            driver2.quit();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
