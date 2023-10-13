package testAutomation.ui;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.target.model.SessionID;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testAutomation.common.ReadProperties;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class SeleniumLibWrapper {
    ReadProperties properties = ReadProperties.getInstance();
    public static WebDriver driver = null;
    public static WebDriver web_driver = null;
    public static WebElement element = null;
    static WebDriverWait wait = null;
    LogEntries entries;
    BufferedWriter writer = null;
    private static SeleniumLibWrapper instance = null;
    Set<String> windowHandleSet;

    public SeleniumLibWrapper() {}

    public static synchronized  SeleniumLibWrapper getInstance() {
        if (instance == null) {
            instance = new SeleniumLibWrapper();
        }
        return  instance;
    }
    Set<SessionID> browser_instance = new HashSet<>();
    public void browserLaunch(String browserType, String applicationName) {
        switch (browserType) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getValue("BROWSER_LOCATION_CHROME"));
                if(properties.getValue("incognito_mode").equals("Yes")){
                    ChromeOptions option = new ChromeOptions(); //when you want to open in private mode
                    option.addArguments("--incognito");
                    driver = new ChromeDriver(option);
                }
                else
                driver= new ChromeDriver();                    //when you want to open in normal mode
                login_to_application(applicationName);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", properties.getValue("BROWSER_LOCATION_EDGE"));
                if(properties.getValue("incognito_mode").equals("Yes")){
                    EdgeOptions edgeoptions = new EdgeOptions();  //when you want to open in private mode
                    edgeoptions.addArguments("-inprivate");
                    driver = new EdgeDriver(edgeoptions);
                }
                else
                driver = new EdgeDriver();                   //when you want to open in normal mode
                waitFor(2);
                login_to_application(applicationName);
                break;
            case "firefox":
                FirefoxBinary fb = new FirefoxBinary();
                fb.addCommandLineOptions("--headless");
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                FirefoxOptions fo = new FirefoxOptions();
                fo.setBinary(fb);
                driver = new FirefoxDriver(fo);
                driver.get(properties.getValue(applicationName+".url"));
                break;
        }
    }

    public void user_credentials() {
        waitFor(8);
        getElementByProperty(properties.getValue("elementToLogin_xpath"), driver).sendKeys(properties.getValue("username"));
        click(properties.getValue("Next_xpath"));
        waitFor(2);
        getElementByProperty(properties.getValue("password_xpath"), driver).sendKeys(properties.getValue("psswd"));
        click(properties.getValue("signIn_xpath"));
    }

    public void login_to_application(String applicationName) {
        driver.get(properties.getValue(applicationName+ ".url"));
        driver.manage().window().maximize();
//        if(properties.getValue("applicationName").equals(applicationName))  //Use only when you are using private mode of browser
//            user_credentials();
    }

    public boolean webPageExists(String applicationName) {
        return !isNull(driver) ?driver.getCurrentUrl().contains(properties.getValue(applicationName + ".url")):false;
    }

    public Boolean isPageUp(String urlToCheckForPageUp, String elementToCheckForPageUp) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(urlToCheckForPageUp));
        return ExpectedConditions.visibilityOfElementLocated(By.xpath(elementToCheckForPageUp))!=null? true:false;
    }

    public void switch_to_userLogin(String applicationname) throws InterruptedException {
        killBrowser();
        browserLaunch(properties.getValue("browserType"), applicationname);
        Thread.sleep(2000);
    }

    public void killBrowser() {
        driver.close();
    }

    public static WebElement getElementByProperty(String objectProperty, WebDriver webDriver) {
//        System.out.println("...objectProperty....."+objectProperty);
//        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectProperty)));
//        System.out.println("...element....."+element);

        String propertyType = null;
        wait = new WebDriverWait(driver, 30);
        try {
            propertyType = StringUtils.substringAfter(objectProperty, "~");
            objectProperty = StringUtils.substringBefore(objectProperty, "~");
            System.out.println("...propertyType....."+propertyType);
            System.out.println("...objectProperty....."+objectProperty);
            switch (propertyType) {
                case "CSS":
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(objectProperty)));
                    highlightElement(element, webDriver);
                    break;
                case "XPATH":
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectProperty)));
                    highlightElement(element, webDriver);
                    break;
                case "ID":
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectProperty)));
                    break;
                case "NAME":
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(objectProperty)));
                    highlightElement(element, webDriver);
                    break;
                case "LINKTEXT":
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(objectProperty)));
                    highlightElement(element, webDriver);
                    break;
                default:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectProperty)));
                    System.out.println("...element....."+element);

            }
        } catch (Exception e) {
        }
        return element;
    }

    public void click(String elementID) {
        try{
            element = getElementByProperty(elementID, driver);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
        catch(Throwable e) {
            element = getElementByProperty(elementID, driver);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
    }

    public void Submit(String elementID) {
        element = getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.submit();
    }

    public void enterText(String elementID, String Text) {
        element = getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Text);
    }

    public void clearAndEnterText(String elementID, String Text) throws  InterruptedException {
        waitFor(2);
        Actions actions = new Actions(driver);
        element = getElementByProperty(elementID, driver);
        actions.click(element)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
        element.sendKeys(Text);
    }

    public String verify_color(String elementID, String propName) {
        element = getElementByProperty(elementID, driver);
        String elementColor = element.getCssValue(propName);
        return Color.fromString(elementColor).asHex();
    }

    public String getValue(String element) {
        WebElement elementID = getElementByProperty(element, driver);
        return elementID.getAttribute("value");
    }

    public String verify_text(String element) {
        WebElement result = getElementByProperty(element, driver);
        return result.getText();
    }

    public boolean verify_enable(String elementID) {
        element = getElementByProperty(elementID, driver);
        if (element.isEnabled()) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isClickable(String el)  {
        try{
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(el)));
                return true;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean elementFocus(String elementID){
        element= getElementByProperty(elementID,driver);
        if(element.equals(driver.switchTo().activeElement()))
         return true;
        else
            return false;
    }

    public void clearText(String elementID) {
        element = getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
    }


    /*
    *Methods for browser back navigations
     */

    public void browserNavigation_Back() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(5000);
    }

    public void refresh_Page() {
        driver.navigate().refresh();
    }

    public void scrollToEnd() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public static void highlightElement(WebElement element, WebDriver webDriver) {
        for (int i = 0; i < 1; i++) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: black; border: 3px solid black;");
        }
    }

    public void focusElement(String obj) {
        String elementID = StringUtils.substringBefore(obj, "~");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('"+ elementID +"').focus();");
    }

    public void selectedByText(String element, String a) {
        WebElement elementID = getElementByProperty(element, driver);
        Select sel = new Select(elementID);
        sel.selectByVisibleText(a);
    }

    public String getText(String element) {
        //element = xpath~"(//a[text()='Home'])[1]"
        WebElement result = getElementByProperty(element, driver);
        return result.getText();
    }

    public boolean checkElementIsPresent(String element) {
        WebElement elementID = getElementByProperty(element, driver);
        return elementID.isDisplayed();
    }

//    public boolean checkElementIsExists(String elementID) {
//        return driver.findElement(By.xpath(elementID)).getSize() !=0;  // getSize() should be size(), Disha will check this
//    }

    public boolean checkElementIsSelected(String element) {
        WebElement elementID = getElementByProperty(element, driver);
        return elementID.isSelected();
    }

    public int getTableSize(String elementID) {
        java.util.List<WebElement> table = driver.findElements(By.xpath(elementID));
        int listSize = table.size();
        return listSize;
    }

    public void selectListByValue(String elementID, String value) {
        java.util.List<WebElement> list = driver.findElements(By.xpath(elementID));
        int listSize = list.size();
        for (int i=0; i<listSize; i++) {
            if (value == list.get(i).getText()) {
                list.get(i).click();
            }
        }
    }

    public void fileUpload(String elementID, String filePath) throws AWTException, InterruptedException {
        element = getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        Thread.sleep(2000);
        Robot rb = new Robot();
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_V);
    }

    public void select_all(String filePath) throws AWTException {
        Robot rb = new Robot();
//        StringSelection str = new StringSelection(filePath);
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_A);
    }

    public void dropdown (String elementID, String text) {
        element = getElementByProperty(elementID, driver);
        Select sc = new Select(element);
        sc.selectByVisibleText(text);
    }

    public void waitFor(int durationSecs) {
        try {
            Thread.sleep(durationSecs * 1000);
        }catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void enterText(WebElement elementID, String Text) {
        wait.until(ExpectedConditions.elementToBeClickable(elementID));
        try {
            elementID.sendKeys(Text);
        }catch (Exception exp) {
            try {
                ((JavascriptExecutor) driver).executeScript("argument[0].value=argument[1]", elementID, Text);
            }catch (Exception exp1) {
                throw exp1;
            }
        }
    }







}
