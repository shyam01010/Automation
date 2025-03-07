package pages.oes.user.pages;

import base.BasePage;
import driver.Driver;
import listeners.MyLogger;
import org.openqa.selenium.By;
import reports.ExtentLogger;
import utils.ConfigLoader;


public class OESLoginPage extends BasePage {
    private final By loginBtn = By.id("login-button");
    private final By txtUserName = By.id("user-name");
    private final By txtPassword = By.id("password");


    //X-Path Functions
    public OESLoginPage() {
    }

    public OESLoginPage Load() {
        load("/auth/login");
        return this;
    }

    private OESLoginPage enterUserName(String userName) {
        enterText(txtUserName, userName, "username");
        return this;
    }

    private OESLoginPage enterPassword(String passWord) {
        enterText(txtPassword, passWord, "password");
        return this;
    }

    private OESLoginPage clickLogin() {
        click(loginBtn, "Login Button");
        return this;
    }


    //Functional Methods
    public OESDashboardPage login(String userName, String passWord) {
        enterUserName(userName).enterPassword(passWord).clickLogin();
        //ExtentLogger.pass("Successfully log in to the OES application.");
        MyLogger.info("Successfully log in to the OES application.");
        return new OESDashboardPage();

    }

    public OESDashboardPage loginOES() {
        enterUserName(ConfigLoader.getInstance().getUsername()).
                enterPassword(ConfigLoader.getInstance().getPassword()).
                clickLogin();
        String browserName = System.getProperty("browserName");
        //ExtentLogger.pass("Successfully log in to the OES application.");
        MyLogger.info("Successfully log in to the OES application.");
        return new OESDashboardPage();
    }

    public OESDashboardPage NewRegister() {
        return new OESDashboardPage();
    }

}
