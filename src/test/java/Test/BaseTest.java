package Test;

//import org.junit.After;
//import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    public ChromeDriver driver;

    private static final String URL = "https://ok.ru/";
    private static final String DRIVER = "webdriver.chrome.driver";
    private static final String LOCATION = "C:/CLionProgects/chromedriver_win32/chromedriver.exe";

    @BeforeEach
    public void initEach()
    {
        System.setProperty(DRIVER, LOCATION);
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterEach
    public void close()
    {
//        driver.quit();
    }

    public String getURL()
    {
        return URL;
    }
}