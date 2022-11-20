package Test;
import Page.*;

import ValueObject.UserBot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import org.junit.Test;

public class LoginTest extends BaseTest {
    private static final String URL = "https://ok.ru/";
    private static final String LOGIN1 = "technoPol8";
    private static final String PASSWORD1 = "technoPolis2022";

    private static UserBot user1 = new UserBot(LOGIN1, PASSWORD1);

    @Test
    @DisplayName("successfull login")
    public void succesfullLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user1);
    }

    @DisplayName("empty login")
    @Test
    public void emptyLoginTest()
    {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user1.setLogin(""));
        assert loginPage.loginIsEmpty();
    }

    @DisplayName("empty password")
    @Test
    public void emptyPasswordTest()
    {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user1.setPassword(""));
        assert loginPage.passwordIsEmpty();
    }

    @DisplayName("empty login and password")
    @Test
    public void emptyLoginAndPasswordTest()
    {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user1.setLogin("").setPassword(""));
        assert loginPage.loginIsEmpty();
    }

    @DisplayName("wrong login and password")
    @Test
    public void wrongLoginAndPasswordTest()
    {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user1.setLogin("wrong").setPassword("wrong"));

        assert loginPage.loginOrPasswordIsIncorrect();
    }
}
