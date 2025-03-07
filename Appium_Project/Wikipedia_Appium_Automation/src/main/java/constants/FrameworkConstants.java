package constants;

import utils.ConfigLoader;

public final class FrameworkConstants {
    private static final int SHORT_WAIT = 60;
    private static final int NORMAL_WAIT = 90;
    private static final int LONG_WAIT = 120;
    private static final String EXTENT_REPORT_FOLDER_PATH = System.getProperty("user.dir") + "\\extent-test-output\\";


    private static final String excelPath = System.getProperty("user.dir") + "/src/main/resources/runTests.xlsx";
    private static String extentReportFilePath = "";

    private FrameworkConstants() {

    }

    public static String getExtentReportPath() {

        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }


    private static String createReportPath() {
        return (ConfigLoader.getInstance().overrideReports().equalsIgnoreCase("no"))
                ? EXTENT_REPORT_FOLDER_PATH + System.currentTimeMillis() + "\\AutomationReport.html"
                : EXTENT_REPORT_FOLDER_PATH + "AutomationReport.html";
    }

    public static int getWaitMode() {
        if (ConfigLoader.getInstance().getWaitMode().equalsIgnoreCase("normal")) {
            return NORMAL_WAIT;
        } else if (ConfigLoader.getInstance().getWaitMode().equalsIgnoreCase("short")) {
            return SHORT_WAIT;
        } else if (ConfigLoader.getInstance().getWaitMode().equalsIgnoreCase("long")) {
            return LONG_WAIT;
        } else {
            return NORMAL_WAIT;
        }
    }


}
