package base;

import constants.FrameworkConstants;
import constants.StrategyType;
import driver.DriverManager;
import factory.ExplicitWaitFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.MyLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import reports.ExtentLogger;
import utils.ConfigLoader;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static constants.StrategyType.ELEMENT_PRESENT;
import static constants.StrategyType.ELEMENT_VISIBLE;


public class BasePage {
    private final By calendar_Icon = By.xpath("//input[contains(@class,'datepicker')]");
    private final By calendar_YearBtn = By.xpath("//mat-calendar[contains(@id,'mat-datepicker')]//span[contains(@id,'mat-calendar-button')]");
    private final By calendar_CalendarContentBody = By.xpath("//mat-calendar[contains(@id,'mat-datepicker')]//table/tbody/tr/td/button");
    private final By calendar_PreviousBtn = By.xpath("//mat-calendar[contains(@id,'mat-datepicker')]//button[contains(@class,'previous')]");
    public List<WebElement> listElements;
    public WebElement element;
    public Date dateObj;
    public SimpleDateFormat sdf;


    String browserName = System.getProperty("browserName");
    String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();


    //Wait For OverLays
    public static void waitForOverlaysToDisappear(By overLay) {
        try {
            WebDriver driver = DriverManager.getDriver();
            List<WebElement> overlays = driver.findElements(overLay);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getWaitMode()));
            if (!overlays.isEmpty()) {
                wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            }
        } catch (NoSuchElementException e) {
            MyLogger.info(e.getMessage());
        }
    }

    public static void fluentWaitForOverlaysToDisappear(By overLay) {
        try {
            WebDriver driver = DriverManager.getDriver();
            List<WebElement> overlays = driver.findElements(overLay);

            if (!overlays.isEmpty()) {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofMinutes(FrameworkConstants.getWaitMode()))
                        .pollingEvery(Duration.ofSeconds(5))
                        .ignoring(NoSuchElementException.class);
                wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            }
        } catch (NoSuchElementException e) {
            MyLogger.info(e.getMessage());
        }
    }


    // Copy to Excel
    public static void copyToExcelSheet(String filepath, String sheetName, String val, String expectedStatusLine, String response) {
        try {
            int columnCount = 3;
            FileInputStream inputStream = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            XSSFRow row = sheet.createRow(++rowCount);
            XSSFCell cell;
            int columnRow = row.getRowNum();
            for (int i = columnRow; i <= columnRow; i++) {
                for (int j = 0; j <= columnCount; j++) {
                    cell = row.createCell(j);
                    if (j == 0) {
                        cell.setCellValue(val);
                    } else if (j == 1) {
                        cell.setCellValue(expectedStatusLine);
                    } else if (j == 2) {
                        cell.setCellValue(response);
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream(filepath);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Browser Info
    public String getInfo() {
        return "BrowserName : " + browserName + " - MethodName : " + methodName + " ------> ";
    }

    // Explicit Element Wait Strategy
    protected WebElement waitStrategy(By locator, StrategyType strategyType) {
        try {
            element = ExplicitWaitFactory.performExplicitWait(locator, strategyType);
            if (element.isDisplayed()) {
                //ExtentLogger.pass("Successfully element is displayed", true);
                MyLogger.info(getInfo() + "Successfully element is displayed");
            } else {
                //ExtentLogger.fail("Element is not displayed", true);
                MyLogger.info(getInfo() + "Element is not displayed");
            }
        } catch (Exception error) {
        }
        return element;
    }




    //Boolean wait Strategy For Text
    protected boolean waitStrategy(StrategyType strategyType, String text) {
        return ExplicitWaitFactory.performExplicitWait(strategyType, text);
    }

    //Boolean  wait Strategy
    protected boolean waitStrategyFor(By locator) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return DriverManager.getDriver().findElements(locator).size() > 0;
    }

    // Add Web Elements in list
    public void getWebElementsInList(By locator) {
        if ((listElements != null) && (listElements.size() > 0)) {
            listElements.clear();
        }
        listElements = DriverManager.getDriver().findElements(locator);
    }

    //Select Dropdown value By text
    public void selectDropdownValueByText(By dropDownLocator, By selectTextContainer, String text, String elementName) {
        Boolean element = waitStrategyFor(dropDownLocator);

        try {
            if (element == true) {
                WebElement drpDownElement = waitStrategy(dropDownLocator, ELEMENT_VISIBLE);
                if (drpDownElement.isEnabled()) {
                    drpDownElement.click();
                    getWebElementsInList(selectTextContainer);
                    for (WebElement dpList : listElements) {
                        String dropDownList = dpList.getText();
                        if (dropDownList.equals(text)) {
                            dpList.click();
                            //ExtentLogger.pass("Successfully selected the dropdown " + elementName, true);
                            MyLogger.info(getInfo() + "Successfully selected the dropdown " + elementName);
                            break;
                        }
                    }
                } else {
                    //ExtentLogger.fail("Drop down element is not enable" + elementName, true);
                    MyLogger.info(getInfo() + "Drop down element is not enable " + elementName);
                }
            } else {
                MyLogger.info(getInfo() + "Element could not be found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Scroll To Element
    public void scrollToElement(By locator, String elementName) {
        Boolean element = waitStrategyFor(locator);
        try {
            if (element == true) {
                WebElement scrollElement = waitStrategy(locator, ELEMENT_PRESENT);
                ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView()", scrollElement);
                //ExtentLogger.pass("Successfully scrolled on " + elementName, true);
                MyLogger.info(getInfo() + "Successfully scrolled on " + elementName);
            } else {
                //ExtentLogger.fail("Scroll element is not found" + elementName, true);
                MyLogger.info(getInfo() + "Scroll element is not found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Element To Click
    public void click(By locator, String elementName) {
        Boolean element = waitStrategyFor(locator);
        try {
            if (element == true) {
                WebElement clickElement = waitStrategy(locator, StrategyType.ELEMENT_CLICKABLE);
                if (clickElement.isEnabled()) {
                    clickElement.click();
                    //ExtentLogger.pass("Successfully Clicked on " + elementName, true);
                    MyLogger.info(getInfo() + "Successfully Clicked on " + elementName);
                } else {
                    //ExtentLogger.fail("Element is not enable for click" + elementName, true);
                    MyLogger.info(getInfo() + "Element is not enable for click " + elementName);
                }
            } else {
                //ExtentLogger.fail("Element could not be found " + elementName, true);
                MyLogger.info(getInfo() + "Element could not be found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Url End Point
    public void load(String endPoint) {
        DriverManager.getDriver().get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    //Enter Element Text
    public void enterText(By locator, String value, String elementName) {
        Boolean element = waitStrategyFor(locator);
        try {
            if (element == true) {
                WebElement enterElement = waitStrategy(locator, StrategyType.ELEMENT_PRESENT);
                if (enterElement.isEnabled()) {
                    enterElement.clear();
                    enterElement.sendKeys(value);
                    //ExtentLogger.pass("Successfully enter text on " + elementName, true);
                    MyLogger.info(getInfo() + "Successfully enter text on " + elementName);
                } else {
                    //ExtentLogger.fail("Element is not enable for enter text" + elementName, true);
                    MyLogger.info(getInfo() + "Element is not enable for enter text " + elementName);
                }
            } else {
                //ExtentLogger.fail("Element could not be found " + elementName, true);
                MyLogger.info(getInfo() + "Element could not be found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Mouse Over Element
    protected void mouseOver(By locator, String elementName) {
        try {
            WebElement element = waitStrategy(locator, ELEMENT_PRESENT);
            if (element.isEnabled()) {
                WebDriver driver = DriverManager.getDriver();
                Actions actions = new Actions(driver);
                WebElement ele = getElement(locator);
                actions.moveToElement(ele).build().perform();
                //ExtentLogger.pass("Successfully mouseOver to " + elementName, true);
                MyLogger.info(getInfo() + "Successfully mouseOver to " + elementName);
            } else {
                //ExtentLogger.fail("Element is not enable for mouse over" + elementName, true);
                MyLogger.info(getInfo() + "Element is not enable for mouse over on " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage());
                //ExtentLogger.fail(e.getMessage(), true);
                e.printStackTrace();
            }
        }
    }


    //Mouse Over Click
    public void mouseOverClick(By locator, String elementName) {
        Boolean element = waitStrategyFor(locator);
        try {
            if (element == true) {
                WebElement mouseOverElement = waitStrategy(locator, StrategyType.ELEMENT_CLICKABLE);
                if (mouseOverElement.isEnabled()) {
                    Actions actions = new Actions(DriverManager.getDriver());
                    actions.moveToElement(mouseOverElement);
                    actions.click().build().perform();
                    //ExtentLogger.pass("Successfully mouseOver clicked on " + elementName, true);
                    MyLogger.info(getInfo() + "Successfully mouseOver clicked on " + elementName);
                } else {
                    //ExtentLogger.fail("Element is not enable for mouseOver click" + elementName, true);
                    MyLogger.info(getInfo() + "Element is not enable for mouseOver click " + elementName);
                }
            } else {
                //ExtentLogger.fail("Element could not be found " + elementName, true);
                MyLogger.info(getInfo() + "Element could not be found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Double Click
    protected void doubleClick(By locator, String elementName) {
        try {
            WebElement doubleClickElement = waitStrategy(locator, StrategyType.ELEMENT_CLICKABLE);
            if (doubleClickElement.isEnabled()) {
                WebDriver driver = DriverManager.getDriver();
                Actions actions = new Actions(driver);
                WebElement ele = getElement(locator);
                actions.moveToElement(ele);
                actions.doubleClick().build().perform();
                //ExtentLogger.pass("Successfully doubleClick on " + elementName, true);
                MyLogger.info(getInfo() + "Successfully doubleClick on " + elementName);

            } else {
                //ExtentLogger.fail("Element is not enable for doubleClick on " + elementName, true);
                MyLogger.info(getInfo() + "Element is not enable for doubleClick on " + elementName);

            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    // Switch to Child Window Using Page Title
    public   void switchToTabWithPageTitle(String pageTitle, String elementName) {
        try {
            WebDriver driver = DriverManager.getDriver();
            Set<String> allOpenTabs = driver.getWindowHandles();
            for (String tab : allOpenTabs) {
                String currentTitle = driver.switchTo().window(tab).getTitle();
                if (currentTitle.equalsIgnoreCase(pageTitle)) {
                    //ExtentLogger.pass("Successfully switched to " + elementName, true);
                    MyLogger.info(getInfo() + "Successfully switched to " + elementName);
                }
            }
        } catch (NoSuchWindowException e) {
            //ExtentLogger.fail("Window could not be found " + elementName, true);
            MyLogger.info(getInfo() + "Window could not be found " + elementName);
        }

          DriverManager.getDriver();
    }


    //Switch to tab or window
    public    WebDriver switchToTab(int tabIndex, String elementName,String passExtentLogger,String failExtentsLogger) {
        try {
            WebDriver driver = DriverManager.getDriver();
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            if (tabIndex >= 0 && tabIndex < tabs.size()) {
                driver.switchTo().window(tabs.get(tabIndex));
                ExtentLogger.pass(passExtentLogger);
                MyLogger.info(getInfo() + "Switched to tab: " + tabIndex);
            } else {
                MyLogger.info(getInfo() + "Invalid tab index: " + tabIndex);
                ExtentLogger.skip(failExtentsLogger);
            }
        } catch (NoSuchWindowException e) {
            MyLogger.info(getInfo() + " Window could not be found: " + elementName);
        }
        return DriverManager.getDriver();
    }


    // Switch to Child Window Using URL
    public void switchToTabWithURL(String partOfURL, String elementName,String passExtentLogger,String failExtentsLogger) {
        try {
            WebDriver driver = DriverManager.getDriver();
            Set<String> allOpenTabs = driver.getWindowHandles();
            for (String tab : allOpenTabs) {
                String currentURL = driver.switchTo().window(tab).getCurrentUrl();
                if (currentURL.contains(partOfURL)) {
                    ExtentLogger.pass(passExtentLogger);
                    MyLogger.info(getInfo() + "Successfully switched to " + elementName);
                }
            }
        } catch (NoSuchWindowException e) {
            ExtentLogger.skip(failExtentsLogger);
            MyLogger.info(getInfo() + "Window could not be found" + elementName);
        }
        DriverManager.getDriver();
    }

    // Switch to Close Child Window Using Page Title
    public void closeChildToTabWithPageTittle(String pageTitle,String passExtentLogger,String failExtentsLogger) {
        try {
            WebDriver driver = DriverManager.getDriver();
            Set<String> allOpenTabs = driver.getWindowHandles();
            for (String tab : allOpenTabs) {
                String currentTitle = driver.switchTo().window(tab).getTitle();
                if (currentTitle.contains(pageTitle)) {
                    ExtentLogger.pass(passExtentLogger);
                    MyLogger.info(getInfo() + "Current Page Tittle is----->" + pageTitle);
                    String tittle = driver.getTitle();
                    //ExtentLogger.pass("IncognitoWindow Page Tittle----->" + tittle, true);
                    MyLogger.info(getInfo() + "IncognitoWindow Page Tittle------>" + tittle);
                    driver.close();
                }
            }
        } catch (NoSuchElementException e) {
            ExtentLogger.skip(failExtentsLogger);
            MyLogger.info(getInfo() + "Element is not found" + pageTitle);
            MyLogger.info(e.getMessage());

        }
    }


    // Switch to Close Child Window Using URL
    public void closeChildToTabWithURL(String partOfURL) {
        try {
            WebDriver driver = DriverManager.getDriver();
            Set<String> allOpenTabs = driver.getWindowHandles();
            for (String tab : allOpenTabs) {
                String currentURL = driver.switchTo().window(tab).getCurrentUrl();
                if (currentURL.contains(partOfURL)) {
                    //ExtentLogger.pass("Current URL is--->" + currentURL, true);
                    MyLogger.info(getInfo() + "Current URL is---->" + currentURL);
                    String tittle = driver.getTitle();
                    //ExtentLogger.pass("IncognitoWindow Page Tittle----->" + tittle, true);
                    MyLogger.info(getInfo() + "IncognitoWindow Page Tittle----->" + tittle);
                    driver.close();
                }
            }
        } catch (NoSuchElementException e) {
            //ExtentLogger.fail(partOfURL + "Element is not found ", true);
            MyLogger.info(getInfo() + (partOfURL + "Element is not found "));
            //ExtentLogger.fail(e.getMessage());
            MyLogger.info(e.getMessage());

        }
    }


    //VerifyGetElementText
    public boolean verifyGetElementText(By locator, String value, String elementName,String passExtentLogger,String failExtentsLogger) {
        WebElement element = waitStrategy(locator, StrategyType.ELEMENT_VISIBLE);
        String actualValue = element.getText();
        if (actualValue.equalsIgnoreCase(value)) {
            ExtentLogger.pass(passExtentLogger);
            MyLogger.info(getInfo() + "Element Text is " + elementName);
            return true;
        } else {
            ExtentLogger.skip(failExtentsLogger);
            MyLogger.info(getInfo() + "Element Text is not found " + elementName);
        }
        return false;
    }

    public boolean verifyGetCurrentPageTittle(String value, String elementName,String passExtentLogger,String failExtentsLogger) {
        WebDriver driver = DriverManager.getDriver();
        String actualValue = driver.getTitle();
        if (actualValue.equalsIgnoreCase(value)) {
            ExtentLogger.pass(passExtentLogger);
            MyLogger.info(getInfo() + "Current Page tittle is" + elementName);
            return true;
        } else {
            ExtentLogger.skip(failExtentsLogger);
            MyLogger.info(getInfo() + "Current Page tittle is " + elementName);
        }
        return false;
    }





    // Get Element Text
    public String getElementText(By locator, String elementName) {
        String displayText = null;
        WebElement element = waitStrategy(locator, StrategyType.ELEMENT_VISIBLE);
        displayText = element.getText();
        //ExtentLogger.pass("Successfully get the text for " + elementName, true);
        MyLogger.info(getInfo() + "Successfully get the text for " + elementName);
        return displayText;
    }


    protected void javScriptClick(By locator, String elementName) {
        Boolean element = waitStrategyFor(locator);
        try {
            if (element == true) {
                WebElement javaScriptElement = waitStrategy(locator, StrategyType.ELEMENT_CLICKABLE);
                if (javaScriptElement.isEnabled()) {
                    JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
                    executor.executeScript("arguments[0].click();", javaScriptElement);
                    //ExtentLogger.pass("Successfully Clicked on " + elementName, true);
                    MyLogger.info(getInfo() + "Successfully Clicked on " + elementName);
                } else {
                    ////ExtentLogger.fail("Element is not enable for click" + elementName, true);
                    MyLogger.info(getInfo() + "Element is not enable for click " + elementName);
                }
            } else {
                //ExtentLogger.fail("Element could not be found " + elementName, true);
                MyLogger.info(getInfo() + "Element could not be found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage());
                //ExtentLogger.fail(e.getMessage(), true);
                e.printStackTrace();
            }
        }
    }


    //Past URL
    public String pastURL(String element) {
        String pastURL = null;
        try {
            pastURL = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //ExtentLogger.pass("Successfully found the URL " + "Past the URL ", true);
        MyLogger.info(getInfo() + "Successfully found the URL " + element);
        return pastURL;
    }

    //Get element
    public WebElement getElement(By locator) {
        try {
            waitStrategy(locator, ELEMENT_PRESENT);
            //ExtentLogger.pass("Successfully get the Web element value ", true);
            MyLogger.info(getInfo() + "Successfully get the Web element value ");
        } catch (NoSuchElementException e) {
            //ExtentLogger.fail("Element is not found ", true);
            MyLogger.info(getInfo() + "Element is not found ");
            //ExtentLogger.fail(e.getMessage(), true);
            MyLogger.info(e.getMessage());
        }
        return DriverManager.getDriver().findElement(locator);
    }


    //VerifyGetCurrentURl
    public boolean verifyGetCurrentURL(String ExpectedURL, String passExtentLogger, String failExtentsLogger) {
        try {
            String CurrentURL = DriverManager.getDriver().getCurrentUrl();
            boolean textMessage = (CurrentURL.equalsIgnoreCase(ExpectedURL));
            if (textMessage == true) {
                ExtentLogger.pass(passExtentLogger);
                MyLogger.info(getInfo() + "Current URL and Expected URL is Same");
                return true;
            } else {
                ExtentLogger.skip(failExtentsLogger);
                MyLogger.info(getInfo() + "Current URL and Expected URL is Different");
            }
        } catch (NoSuchElementException e) {
            //ExtentLogger.fail("Current URL and Expected URL is Different", true);
            MyLogger.info(getInfo() + "Current URL and Expected URL is Different");
            //ExtentLogger.fail(e.getMessage());
        }
        return false;
    }


    //Wait For Page Load
    public void waitForPageLoad() {
        JavascriptExecutor j = (JavascriptExecutor) DriverManager.getDriver();
        if (j.executeScript("return document.readyState").toString().equals("complete")) {
            //ExtentLogger.pass("Page has loaded ", true);
            MyLogger.info(getInfo() + "Page has loaded ");
        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //ExtentLogger.fail("Page has not loaded yet ", true);
                MyLogger.info(getInfo() + "Page has loaded ");
            }
            //again check page state
            if (j.executeScript("return document.readyState ").toString().equals("complete")) {
                break;
            }
        }
    }


    //Upload File Using Robot
    public void uploadFile(String filename) {
        String filepath = System.getProperty("user.dir") + File.separator + "FilesToUpload" + File.separator + filename;
        StringSelection s = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Robot robt = null;
        try {
            robt = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robt.keyPress(KeyEvent.VK_CONTROL);
        robt.keyPress(KeyEvent.VK_V);
        robt.keyRelease(KeyEvent.VK_CONTROL);
        robt.keyRelease(KeyEvent.VK_V);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        robt.keyPress(KeyEvent.VK_ENTER);
        robt.keyRelease(KeyEvent.VK_ENTER);

    }


    //File Upload By Id
    public void fileUploadByID(By locatorId, String fileName, String elementName) {
        String driverName = String.valueOf(DriverManager.getDriver());
        if (driverName.contains("RemoteWebDriver")) {
            WebDriverWait wait;
            wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMinutes(2));
            String filePath = System.getProperty("user.dir") + File.separator + "FilesToUpload" + File.separator + fileName;
            ((RemoteWebDriver) DriverManager.getDriver()).setFileDetector(new LocalFileDetector());
            if (DriverManager.getDriver() instanceof RemoteWebDriver) {
                ((RemoteWebDriver) DriverManager.getDriver()).setFileDetector(new LocalFileDetector());
            }
            WebElement uploadElement = DriverManager.getDriver().findElement(locatorId);
            uploadElement.sendKeys(filePath);
           // wait.until(ExpectedConditions.visibilityOfElementLocated(locatorId));
        } else {
            String filePath = System.getProperty("user.dir") + File.separator + "FilesToUpload" + File.separator + fileName;
            Boolean element = waitStrategyFor(locatorId);
            try {
                if (element == true) {
                    WebElement uploadElement = DriverManager.getDriver().findElement(locatorId);
                    uploadElement.sendKeys(filePath);
                    //ExtentLogger.pass("Successfully file uploaded " + elementName, true);
                    MyLogger.info(getInfo() + "Successfully file uploaded " + elementName);
                } else {
                    //ExtentLogger.fail("Element could not be found " + elementName, true);
                    MyLogger.info(getInfo() + "Element could not be found " + elementName);
                }
            } catch (Exception error) {
                try {
                } catch (NoSuchElementException e) {
                    //ExtentLogger.fail(e.getMessage(), true);
                    MyLogger.info(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }


    //File Upload Using Java Script
    public void fileUploadByJavaScriptExecutor(By locator, String path, String elementName) {
        String filepath = System.getProperty("user.dir") + File.separator + "FilesToUpload" + File.separator + path;
        Boolean element = waitStrategyFor(locator);
        try {
            if (element == true) {
                StringSelection s = new StringSelection(filepath);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
                WebElement elem = DriverManager.getDriver().findElement(locator);
                String jse = "arguments[0].type='file'";
                ((JavascriptExecutor) DriverManager.getDriver()).executeScript(jse, elem);
                elem.sendKeys(filepath);
                //ExtentLogger.pass("Successfully file uploaded " + elementName, true);
                MyLogger.info(getInfo() + "Successfully file uploaded " + elementName);
            } else {
                //ExtentLogger.fail("Element could not be found " + elementName, true);
                MyLogger.info(getInfo() + "Element could not be found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    // Toaster Message
    public boolean ToasterMessage(By locator, String value, String elementName ,String passExtentLogger, String failExtentsLogger) {

            WebElement element = waitStrategy(locator, StrategyType.ELEMENT_VISIBLE);
            String actualValue = element.getText();
            if (actualValue.equalsIgnoreCase(value)) {
                ExtentLogger.pass(passExtentLogger);
                MyLogger.info(getInfo() + "Toaster Message is " + elementName);
                return true;
                } else {

               ExtentLogger.skip(failExtentsLogger);
                MyLogger.info(getInfo() + "Toaster Message is not found " + elementName);
        }
            return false;
        }


            //Select Drop Down First Value
    public void selectDropdownByFirstValue(By dropDownLocator, By selectTextContainer, String elementName) {
        Boolean element = waitStrategyFor(dropDownLocator);

        try {
            if (element == true) {
                WebElement drpDownElement = waitStrategy(dropDownLocator, ELEMENT_VISIBLE);
                if (drpDownElement.isEnabled()) {
                    drpDownElement.click();
                    getWebElementsInList(selectTextContainer);
                    waitForPageLoad();
                    if ((listElements != null) && (listElements.size() > 0)) {
                        listElements.get(1).click();
                        //ExtentLogger.pass("Successfully selected the first dropdown " + elementName, true);
                        MyLogger.info(getInfo() + "Successfully selected the first dropdown " + elementName);
                    }
                } else {
                    //ExtentLogger.fail("Drop down element is not enable" + elementName, true);
                    MyLogger.info(getInfo() + "Drop down element is not enable " + elementName);
                }
            } else {
                MyLogger.info(getInfo() + "Element could not be found " + elementName);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    // DropDown Using Select Class
    public void selectDropDownBySelectClass(By locator, String text) {
        Boolean element = waitStrategyFor(locator);
        try {
            if (element == true) {
                WebElement dropDownElement = waitStrategy(locator, ELEMENT_VISIBLE);
                if (dropDownElement.isDisplayed()) {
                    Select se = new Select(dropDownElement);
                    se.selectByVisibleText(text);
                    //ExtentLogger.pass("Successfully select the dropdown value " + text, true);
                    MyLogger.info(getInfo() + "Successfully select the dropdown value " + text);
                } else {
                    //ExtentLogger.fail("Element is not enable for select dropdown", true);
                    MyLogger.info(getInfo() + "Element is not enable for select dropdown " + text);
                }
            } else {
                //ExtentLogger.fail("Element could not be found ", true);
                MyLogger.info(getInfo() + "Element could not be found " + text);
            }
        } catch (Exception error) {
            try {
            } catch (NoSuchElementException e) {
                //ExtentLogger.fail(e.getMessage(), true);
                MyLogger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Get Current Time
    public String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }


    public String todayDate() {
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        dateObj = new Date();
        System.out.println("Current Date " + sdf.format(dateObj));
        return String.valueOf(sdf.format(dateObj));
    }


    public String getCurrentYear() {
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String yr = formatter.format(d);
        System.out.println("Year is : " + yr);
        return yr;
    }


    public String getCurrentMonth() {
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM");
        String month = formatter.format(d);
        System.out.println("month is : " + month);
        return month;
    }


    public String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        String temp = formatter.format(d);
        System.out.println("Date is : " + temp);
        String dt = temp.trim();
        if (dt.startsWith("0")) {
            System.out.println("formatted date is : " + dt.substring(1));
            return dt.substring(1);
        } else return dt;
    }

    public String getNextYear() {
        String cYear = getCurrentYear();
        int temp = Integer.parseInt(cYear);
        int temp1 = temp + 1;
        System.out.println("Int year : " + temp1);
        String nYear = String.valueOf(temp1);
        return nYear;
    }

    public void calendarIcon() {
        waitStrategy(calendar_Icon, StrategyType.ELEMENT_PRESENT);
        mouseOverClick(calendar_Icon, "Date Picker Button");
    }

    public void calendarYearButton() {
        waitStrategy(calendar_YearBtn, StrategyType.ELEMENT_PRESENT);
        click(calendar_YearBtn, "Year Button");
    }

    public void selectYear(String year) {
        try {
            for (int i = 1; i < year.length(); i++) {
                getWebElementsInList(calendar_CalendarContentBody);
                for (WebElement element : listElements) {
                    String yearsList = element.getText();
                    if (yearsList.equals(year)) {
                        element.click();
                        //ExtentLogger.pass("Successfully year calender selected ", true);
                        MyLogger.info(getInfo() + "Successfully year calender selected " + year);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
                click(calendar_PreviousBtn, "Previous Button");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (NoSuchElementException e) {
            //ExtentLogger.fail(e.getMessage());
            //ExtentLogger.fail("Element could not be found ", true);
            MyLogger.debug("Element could not be found" + year);
            MyLogger.debug(e.getMessage());
        }

    }

    public void selectMonth(String month) {
        try {
            getWebElementsInList(calendar_CalendarContentBody);
            for (WebElement element : listElements) {
                String monthList = element.getText();
                month.toUpperCase();
                if (month.equals(monthList)) {
                    element.click();
                    //ExtentLogger.pass("Successfully month calender selected " + month, true);
                    MyLogger.info("Successfully month calender selected " + month);
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            //ExtentLogger.fail("Element could not be found " + month, true);
            //ExtentLogger.fail(e.getMessage());
            MyLogger.debug("Element could not be found " + month);
            MyLogger.debug(e.getMessage());
        }
    }

    public void selectDate(String date) {
        try {
            getWebElementsInList(calendar_CalendarContentBody);
            for (WebElement element : listElements) {
                String datesList = element.getText();
                if (date.equals(datesList)) {
                    element.click();
                    //ExtentLogger.pass("Successfully date calender selected " + date, true);
                    MyLogger.info("Successfully date calender selected " + date);
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            //ExtentLogger.fail("Element could not be found " + date, true);
            //ExtentLogger.fail(e.getMessage());
            MyLogger.debug("Element could not be found " + date);
            MyLogger.debug(e.getMessage());
        }
    }

    public void selectYearMonthDateCalendar(String year, String month, String date) {
        calendarIcon();
        calendarYearButton();
        selectYear(year);
        selectMonth(month);
        selectDate(date);
    }
}