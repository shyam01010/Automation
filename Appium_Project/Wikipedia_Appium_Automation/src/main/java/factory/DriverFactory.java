package factory;

import constants.EnvironmentType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;
import utils.ConfigLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public final class DriverFactory {

    public static AppiumDriverLocalService service;
    static UiAutomator2Options options;
    static String ipAddress;
    static String port;
    static String androidDeviceName;
    private DriverFactory() {

    }


    public static AndroidDriver prepareDriver(String browser, String runMode) throws MalformedURLException {
        Capabilities cap;
        AppiumDriver driver = null;
   //     String browserstackUsername = System.getenv("shyamreddy_6tm2kq");
   //     String browserstackAccessKey = System.getenv("JhXJqFD9tMLwCGPkjNuy");
       // DesiredCapabilities caps = new DesiredCapabilities();

        if (browser.equalsIgnoreCase(EnvironmentType.NATIVE.name())) {
            // if (ConfigLoader.getInstance().getRunMode().equalsIgnoreCase("local")) {
            if (runMode.equalsIgnoreCase("local")) {
                //Start server
                //  bs://a4ed1f192dc69ccd293d85be3758bc9869755157
                Properties prop = new Properties();
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\QA_config.properties");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    prop.load(fis);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                 ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
                System.out.println(ipAddress);

                //String ipAddress = prop.getProperty("ipAddress");
                 port = prop.getProperty("port");

                 androidDeviceName = System.getProperty("AndroidDeviceName")!=null ? System.getProperty("AndroidDeviceName") : prop.getProperty("AndroidDeviceName");



                service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\shyam\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                        .withIPAddress(ipAddress).usingPort(Integer.parseInt(port)).build();
                service.start();


                //caps.setCapability("browserstack.user", browserstackUsername);
               // caps.setCapability("browserstack.key", browserstackAccessKey);



                options = new UiAutomator2Options();

                //options.setCapability("browserstack.user", browserstackUsername);
               // options.setCapability("browserstack.key", browserstackAccessKey);
                options.setDeviceName(androidDeviceName);
                String apkPath = ConfigLoader.getInstance().getApp();
                options.setApp(apkPath);

                // options.setApp("E:\\Automation WorkSpace\\Mobile Automation WorkSpace\\Appium_Framework\\src\\main\\resources\\General-Store.apk");


                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            }
        }
         else if (browser.equalsIgnoreCase(EnvironmentType.WEB.name())) {
                if (runMode.equalsIgnoreCase("local")) {
                    //Start server

                    Properties prop = new Properties();
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\QA_config.properties");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        prop.load(fis);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
                    System.out.println(ipAddress);

                    //String ipAddress = prop.getProperty("ipAddress");
                    port = prop.getProperty("port");

                    androidDeviceName = System.getProperty("AndroidDeviceName")!=null ? System.getProperty("AndroidDeviceName") : prop.getProperty("AndroidDeviceName");



                    service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\shyam\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                            .withIPAddress(ipAddress).usingPort(Integer.parseInt(port)).build();
                    service.start();

                     options = new UiAutomator2Options();

                    options.setDeviceName(androidDeviceName);


                    options.setChromedriverExecutable(System.getProperty("user.dir")+"\\Appium_Framework\\chromedriver.exe");
                    options.setCapability("browserName","Chrome");



                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


                }


        } else if (browser.equalsIgnoreCase(EnvironmentType.HYBRID.name())) {
            if (runMode.equalsIgnoreCase("local")) {

            }
        } else if (browser.equalsIgnoreCase(EnvironmentType.IOS.name())) {
            if (runMode.equalsIgnoreCase("local")) {

            }
        }

      //  driver.manage().window().maximize();
        return (AndroidDriver) driver;
    }
}
