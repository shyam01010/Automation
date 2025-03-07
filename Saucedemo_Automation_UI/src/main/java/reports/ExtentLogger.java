package reports;


import com.aventstack.extentreports.MediaEntityBuilder;
import utils.ConfigLoader;
import utils.ScreenshotUtils;

public final class ExtentLogger {
    private ExtentLogger() {

    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }




    public static void pass(String message, boolean isScreenShotRequired) {
      /*  if (ConfigLoader.getInstance().getPassTestsScreenshots().equalsIgnoreCase("yes") && isScreenShotRequired)
        {
         */   ExtentManager.getExtentTest().pass(message);
       /* } else {
            pass(message);
        }*/
    }


    public static void fail(String message, boolean isScreenShotRequired) {
        if (ConfigLoader.getInstance().getFailTestsScreenshots().equalsIgnoreCase("yes") && isScreenShotRequired) {
            ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());

        } else {
            fail(message);
        }
    }

    public static void skip(String message, boolean isScreenShotRequired) {
        if (ConfigLoader.getInstance().getSkipTestsScreenshots().equalsIgnoreCase("yes") && isScreenShotRequired) {
            ExtentManager.getExtentTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        } else {
            skip(message);
        }
    }


}
