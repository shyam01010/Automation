package factory;

import constants.BrowserType;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import utils.ConfigLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class DriverFactory {


    private DriverFactory() {

    }


    public static WebDriver prepareDriver(String browser,String runMode) throws MalformedURLException {
        Capabilities cap;
        WebDriver driver = null;
        if (browser.equalsIgnoreCase(BrowserType.CHROME.name())) {
           // if (ConfigLoader.getInstance().getRunMode().equalsIgnoreCase("local")) {
                if (runMode.equalsIgnoreCase("local")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions Options = new ChromeOptions();
                Options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                Options.addArguments("--remote-allow-origins=*");
                Options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(Options);
            //} else if (ConfigLoader.getInstance().getRunMode().equalsIgnoreCase("remote")) {
                } else if (runMode.equalsIgnoreCase("remote")) {

                 cap = new ChromeOptions();
                //driver = new RemoteWebDriver(new URL("http://65.0.167.206:4444"), cap);
                    driver = new RemoteWebDriver(new URL("http://192.168.9.141:4444"), cap);
            }
        } else if (browser.equalsIgnoreCase(BrowserType.FIREFOX.name())) {
            if (runMode.equalsIgnoreCase("local")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (runMode.equalsIgnoreCase("remote")) {
                cap = new FirefoxOptions();
                //driver = new RemoteWebDriver(new URL("http://65.0.167.206:4444"), cap);
                driver = new RemoteWebDriver(new URL("http://192.168.9.141:4444"), cap);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } else if (browser.equalsIgnoreCase(BrowserType.EDGE.name())) {
            if (runMode.equalsIgnoreCase("local")) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions Options = new EdgeOptions();
                Options.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(Options);
            } else if (runMode.equalsIgnoreCase("remote")) {
                cap = new EdgeOptions();
                //driver = new RemoteWebDriver(new URL("http://65.0.167.206:4444"), cap);
                driver = new RemoteWebDriver(new URL("http://192.168.9.141:4444"), cap);
            }
        } else if (browser.equalsIgnoreCase(BrowserType.SAFARI.name())) {
            if (runMode.equalsIgnoreCase("local")) {
                //logic to execute on Safari Driver .. Test on MAC and finalize
            } else if (runMode.equalsIgnoreCase("remote")) {
                cap = new SafariOptions();
                //driver = new RemoteWebDriver(new URL("http://65.0.167.206:4444"), cap);
                driver = new RemoteWebDriver(new URL("http://192.168.9.141:4444"), cap);
            }
        }
        driver.manage().window().maximize();
        return driver;
    }
}
