package driver;



import factory.DriverFactory;

import java.net.MalformedURLException;
import java.util.Objects;

public final class Driver {
    private Driver() {
    }

    public static void initializeDriver(String browser,String runMode)  {

        if (Objects.isNull(DriverManager.getDriver())) {
            try {
                DriverManager.setDriver(DriverFactory.prepareDriver(browser,runMode));
            } catch (MalformedURLException e) {
                try {
                    throw new Exception("Problem with Driver initialization");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

}
