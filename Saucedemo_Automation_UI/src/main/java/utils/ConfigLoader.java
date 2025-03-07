package utils;

import constants.EnvironmentType;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.util.Properties;


public class ConfigLoader {
    private static ConfigLoader configLoader;
    private Properties properties;


    private ConfigLoader(@Optional String envType) {
        String env = System.getProperty("env", String.valueOf(EnvironmentType.QA));
        //String env = String.valueOf(EnvironmentType.SG);
        System.setProperty("env", env);

        switch (EnvironmentType.valueOf(env)) {
            case QA:
                properties = PropertyUtils.propertyLoader("src/test/resources/QA_config.properties");
                break;
            case PRODUCTION:
                properties = PropertyUtils.propertyLoader("src/test/resources/Production_config.properties");
                break;
            case PT:
                properties = PropertyUtils.propertyLoader("src/test/resources/PT_config.properties");
                break;
            case SG:
                properties = PropertyUtils.propertyLoader("src/test/resources/SG_config.properties");
                break;
            case ST:
                properties = PropertyUtils.propertyLoader("src/test/resources/ST_config.properties");
                break;
            case DEMO:
                properties = PropertyUtils.propertyLoader("src/test/resources/DEMO_config.properties");
                break;
            default:
                throw new RuntimeException("Invalid Environment Type: " + envType);
        }

    }

    @Parameters({"envType"})
    public static ConfigLoader getInstance() {
        if (configLoader == null)
        {
            configLoader = new ConfigLoader("envType");
        }
        return configLoader;
    }


    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property baseUrl is not specified in the config.properties file");
    }


    public String overrideReports() {
        String prop = properties.getProperty("overrideReports");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property overrideReports is not specified in the config.properties file");
    }

    public String getRunMode() {
        String prop = properties.getProperty("runMode");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property runMode is not specified in the config.properties file");
    }

    public String getWaitMode() {
        String prop = properties.getProperty("waitMode");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property waitMode is not specified in the config.properties file");
    }

    public String getPassTestsScreenshots() {
        String prop = properties.getProperty("passTestsScreenshots");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property passTestsScreenshots is not specified in the config.properties file");
    }

    public String getFailTestsScreenshots() {
        String prop = properties.getProperty("failTestsScreenshots");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property failTestsScreenshots is not specified in the config.properties file");
    }

    public String getSkipTestsScreenshots() {
        String prop = properties.getProperty("skipTestsScreenshots");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property skipTestsScreenshots is not specified in the config.properties file");
    }

    public String getUsername() {
        String prop = properties.getProperty("username");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property username1 is not specified in the config.properties file");
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property password1 is not specified in the config.properties file");
    }


}
