package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactsHelper contactsHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.CHROME)){
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromDriver\\chromedriver.exe");
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromDriver\\geckodriver.exe");
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            System.setProperty("webdriver.ie.driver","C:\\Program Files\\ChromDriver\\IEDriverServer.exe");
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactsHelper = new ContactsHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactsHelper getContactsHelper() {
        return contactsHelper;
    }
}
