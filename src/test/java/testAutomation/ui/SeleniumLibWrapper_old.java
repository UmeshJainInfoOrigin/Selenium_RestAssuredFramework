package testAutomation.ui;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class SeleniumLibWrapper_old {

    private static WebDriver driver = null;
    public static WebElement element = null;
    static WebDriverWait wait = null;
    LogEntries entries;
    Properties PropertyFile = new Properties();
    BufferedWriter writer = null;


    /*@Before
    public void browserLaunch() throws IOException {

        System.setProperty("webdriver.gecko.driver",
                System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        loadConfigFile();
        driver = new FirefoxDriver();
        driver.get(PropertyFile.getProperty("URL"));

    }
*/
 /*   @Before
    public void browserLaunch() throws IOException {

        FirefoxBinary fb = new FirefoxBinary();
        fb.addCommandLineOptions("--headless");
        System.setProperty("webdriver.gecko.driver",
                System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxOptions fo = new FirefoxOptions();
        fo.setBinary(fb);
        loadConfigFile();
        driver = new FirefoxDriver(fo);
        driver.get(PropertyFile.getProperty("URL"));

    } */

// @Before
    public void browserLaunch() throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\Automation_Training\\chromedriver_exe\\chromedriver.exe");
       driver= new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://www.google.com");
    }

	/*@Before
	public void browserLaunch() throws IOException
	{
		 WebDriverManager.firefoxdriver().setup();
		 FirefoxOptions fo = new FirefoxOptions();
		 DesiredCapabilities dc =  new DesiredCapabilities();
		 dc.setBrowserName("firefox");
		 dc.setPlatform(Platform.WINDOWS);
		 fo.merge(dc);
		 driver = new RemoteWebDriver(new URL("http://172.17.2.25:4444/wd/hub"), dc);
		 loadConfigFile();
		 driver = new FirefoxDriver();
		 driver.get(PropertyFile.getProperty("URL"));

	}*/


    public void loadConfigFile() throws IOException {
        FileReader ConfigReader = new FileReader(System.getProperty("user.dir") + "\\Configuration.properties");
        PropertyFile.load(ConfigReader);
    }

    public void mouseHoverElement(String webElement) {
        WebElement Melement = getElementByProperty(webElement, driver);
        Actions builder = new Actions(driver);
        builder.moveToElement(Melement).build().perform();
    }

    /*
     * public void ExtractJSLogs() throws IOException { LogEntries logEntries =
     * driver.manage().logs().get(LogType.BROWSER); File logFile = new
     * File(System.getProperty("user.dir")+"errors.log"); writer = new
     * BufferedWriter(new FileWriter(logFile)); for (LogEntry entry : logEntries) {
     * writer.write(entry.getTimestamp() + " " + entry.getLevel() + " " +
     * entry.getMessage()); } }
     */

//    @After
//    public void killBrowser(Scenario scenario) {
////        if (scenario.isFailed()) {
////            scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
////        }
//        driver.quit();
//    }

    public void killBrowser() {
        driver.quit();
    }

    public static WebElement getElementByProperty(String objectProperty, WebDriver webDriver) {
        String propertyType = null;
        wait = new WebDriverWait(driver, 30);
        try {
            propertyType = StringUtils.substringAfter(objectProperty, "~");
            objectProperty = StringUtils.substringBefore(objectProperty, "~");
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
            }
        } catch (Exception e) {
        }
        return element;
    }


    public boolean isElementPresentVerification(String objectProperty) throws Exception {
        boolean isElementPresent = false;
        wait = new WebDriverWait(driver, 20);
        try {
            element = getElementByProperty(objectProperty, driver);
            if (element != null) {
                isElementPresent = true;
            } else {
                throw new Exception("Object Couldn't be retrieved and verified");
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isElementPresent;
    }


    public void click(String elementID) {
        element = getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            element.click();

        }
    }


    public void enterText(String elementID, String Text) {
        getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Text);

    }

    public void clearAndEnterText(String elementID, String Text) {
        getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(Text);

    }

    public void maskedtextbox(String a) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.chord(Keys.LEFT_CONTROL, a)).build().perform();
    }

    public void clearText(String elementID) {
        getElementByProperty(elementID, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
    }

    /*
     * Methods for browser back navigation
     */
    public void browserNavigation_Back() {
        try {
            driver.navigate().back();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void refresh_Page() {
        driver.navigate().refresh();
    }


    public void scrollTo() throws InterruptedException {
        Thread.sleep(3000);
        Actions a = new Actions(driver);
        a.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(3000);

    }

    public void scrollTo1() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
    }

    public void waitForElement(String element) {
        WebElement elementID = getElementByProperty(element, driver);
        wait.until(ExpectedConditions.elementToBeClickable(elementID));

    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e1) {
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

    public void waitforPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean b = js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete");
        int retry=0;
        do {
            try {
                Thread.sleep(500);
                b = js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete");
                retry++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (b == false && retry<240);
        if(b==false)
            System.out.println("ERROR: Page Loading FAILED!!!");
    }

    public void focusElement(String obj) {
        String elementID = StringUtils.substringBefore(obj, "~");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('" + elementID + "').focus();");
    }

    public void selectByText(String element, String a) {
        WebElement elementID = getElementByProperty(element, driver);
        waitForElement(element);
        Select sel = new Select(elementID);
        sel.selectByVisibleText(a);
    }

    public void waitTillNoLoading() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader-gif")));
    }

    public void pressKeyBoardButton(String Button) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if (Button.equalsIgnoreCase("Enter")) {
            robot.keyPress(KeyEvent.VK_ENTER);
        } else if (Button.equalsIgnoreCase("Down")) {
            robot.keyPress(KeyEvent.VK_DOWN);
        } else if (Button.equalsIgnoreCase("up")) {
            robot.keyPress(KeyEvent.VK_UP);
        } else if (Button.equalsIgnoreCase("Tab")) {
            robot.keyPress(KeyEvent.VK_TAB);
        } else if (Button.equalsIgnoreCase("Left")) {
            robot.keyPress(KeyEvent.VK_LEFT);
        } else if (Button.equalsIgnoreCase("right")) {
            robot.keyPress(KeyEvent.VK_RIGHT);
        } else if (Button.equalsIgnoreCase("escape")) {
            robot.keyPress(KeyEvent.VK_ESCAPE);
        }
    }

    public String getText(String element) {
        WebElement elementID = getElementByProperty(element, driver);
        String text = elementID.getText();
        return text;
    }

    public boolean checkElementIsPresent(String element) {
        WebElement elementID = getElementByProperty(element, driver);
        boolean flag = elementID.isDisplayed();
        return flag;
    }

    public boolean checkElementIsSelected(String element) {
        WebElement elementID = getElementByProperty(element, driver);
        boolean flag = elementID.isSelected();
        return flag;
    }

    public void executeDBQuery() {
        try {
            /*
             * //Class.forName("com.mysql.jdbc.Driver");
             * Class.forName("org.mariadb.jdbc.Driver").newInstance(); Connection con =
             * DriverManager.getConnection("jdbc:mariadb://172.17.1.185/aecs_emr", "sa",
             * "emrserver@)!@"); // here sonoo is database name, root is username and
             * password
             */
            // Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            Connection con = DriverManager.getConnection("jdbc:mariadb://172.17.1.185/", "sa", "emrserver@)!@");
            System.out.println("Connected database successfully...");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "delete from emrpeinvestroutine where investigationid in (select investigationid from emrpeinvestigation where peid in (select PEid FROM  emrPEEncounterDetail where uid='9999010160'))");
            /*
             * while (rs.next()) System.out.println(rs.getInt(1) + "  " + rs.getString(2) +
             * "  " + rs.getString(3));
             */
            System.out.println(rs.toString());

            ResultSet rss = stmt.executeQuery(
                    "update emrPEEncounterDetail_CPage_OPModules set NutritionalAssesment=0 where peid=1892849");
            System.out.println(rss.toString());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getTableSize(String elementID) {

        java.util.List<WebElement> table = driver.findElements(By.xpath(elementID));
        int listSize = table.size();
        return listSize;
    }

    public void selectListByValue(String elementID, String value) {
        java.util.List<WebElement> list = driver.findElements(By.xpath(elementID));
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            if (value == list.get(i).getText()) {
                list.get(i).click();
            }
        }

    }

    public void fileUpload(String elementID, String filePath) {
        getElementByProperty(elementID, driver);
        element.sendKeys(filePath);
    }

    public void alertbox() throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        if (text.contains("Are you sure to clear")) {
            alert.accept();
        } else {
            alert.dismiss();
        }

    }

    public void selectElementFromList(String elementID, int elementNo) {
        java.util.List<WebElement> list = driver.findElements(By.xpath(elementID));
        list.get(elementNo).click();
    }

    public void callautoit(String FilePath) throws IOException, InterruptedException {

        Runtime.getRuntime().exec(FilePath);
        Thread.sleep(4000);
    }

    public void clickName(String name) {
        WebElement table = driver.findElement(By.xpath("//*[@id='tblipPatientListGrid']/tbody"));
        java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));
        int row = rows.size();
        for (int i = 1; i <= row; i++) {
            WebElement patient = driver.findElement(
                    By.xpath("//*[@id='tblipPatientListGrid']/tbody/tr[" + i + "]/td[6]/div[2]/div[2]/label"));
            String BName = patient.getText();
            if (BName.equalsIgnoreCase(name)) {
                patient.click();
                break;
            }
        }
    }

    public void clickElementUsingJS(String name) {
        String elementID = StringUtils.substringBefore(name, "~");
        WebElement rateElement = driver.findElement(By.id(elementID));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", rateElement);
    }

    public void clickElement1UsingJS(String name) {
        String element = StringUtils.substringBefore(name, "~");
        WebElement rateElement = driver.findElement(By.xpath(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", rateElement);
    }

    public void fileUpload(WebElement webElement, String filePath) {
        webElement.sendKeys(filePath);
    }

    public java.util.List<WebElement> getListOfElements(String reFileUpload) {
        java.util.List<WebElement> d = driver.findElements(By.xpath(reFileUpload));
        return d;
    }

    public void switchToWindow(int index) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        java.util.List<String> allHandles = new ArrayList<>();
        allHandles.addAll(allWindowHandles);
        driver.switchTo().window(allHandles.get(index));

    }

    public void keyboardENTER(String name) {
        String elementID = StringUtils.substringBefore(name, "~");
        WebElement textbox = driver.findElement(By.xpath(elementID));
        textbox.sendKeys(Keys.ENTER);
    }

    public void RobotClassUploadFile(String filepath) throws AWTException {
        StringSelection strSel = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSel, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void RobotClassDownloadFile() throws AWTException, InterruptedException {
        Robot r = new Robot();
        Thread.sleep(2000);
        r.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(2000);
        r.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(2000);
        r.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(2000);
        r.keyPress(KeyEvent.VK_ENTER);
    }

    public void childwindowclose() {
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    public void openingnewtab() throws AWTException {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        // driver.close();
        // driver.switchTo().window(tabs.get(0));
    }

    public void scrollup() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-250)", "");

    }

    public void scrolldown() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

    }

    public void displayalertbox() throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception exp) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception exp1) {
                System.out.println("ERROR : Unable to click Element. " + exp1.getMessage());
            }
        }
    }

    public void clickElement(String elementID) {
        element = getElementByProperty(elementID, driver);
        clickElement(element);
    }

    public void waitFor(int durationSecs) {
        try {
            Thread.sleep(durationSecs * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int generateRandom(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }


    public void setcalendar(int year, int monthNumber, int day) {

        String calendarDay = "(//div[@class='calendar-body']/table/tbody/tr/td[(text()='" + day + "')])~xpath";
        System.out.println("calendarDay : "+StringUtils.substringBefore(calendarDay, "~"));
        String calendarMonthYear = "(//div[@class='calendar-title'])[4]~xpath";
        String calendarYears = "//input[starts-with(@class,'calendar-menu-year')]";
        String calendarMonth = "//td[starts-with(@class,'calendar-menu-month')]~xpath";

        WebElement calMonthYear = getElementByProperty(calendarMonthYear, driver);
        WebElement calYear = getElementByProperty(calendarYears, driver);

        java.util.List<WebElement> calYears=driver.findElements(By.xpath(calendarYears));

        for (int i=0;i<calYears.size();i++) {

            if (calYears.get(i).isDisplayed()) {
                System.out.println("before i : "+i+"\t visible : "+calYears.get(i).isDisplayed());
                ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('calendar-menu-year')[" + i + "].value='" + year + "';");
                break;
            }
        }
        waitFor(3);
        java.util.List<WebElement> calMonth = driver.findElements(By.xpath(StringUtils.substringBefore(calendarMonth, "~")));
        clickElement(calMonth.get(monthNumber - 1));
        waitFor(10);
        List<WebElement> calDays=driver.findElements(By.xpath(StringUtils.substringBefore(calendarDay, "~")));
        System.out.println("calDays : "+calDays.size());
        waitFor(5);
        for (int i=calDays.size()-1;i>=0;i--) {
            System.out.println("DAY i : "+i+"\tsize : "+calDays.size());
            if (calDays.get(i).isDisplayed()) {
                System.out.println("before DAY i : "+i+"\t visible : "+calDays.get(i).isDisplayed());
                clickElement(calDays.get(i));
                break;
            }
        }
    }

    public void clicknotificationicon(String patientuid)
    {
        WebElement Notificationmenu = driver.findElement(By.xpath("(//table[@id='tblWaitingList']//td[text()[normalize-space()='" + patientuid + "']]/following-sibling::td)[14]/i"));
        try {
            Notificationmenu.click();
        } catch (ElementClickInterceptedException e) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Notificationmenu.click();

        }
    }

    public void mouseHoverNotification(String patientuid) {
        WebElement Notificationmenu = driver.findElement(By.xpath("(//table[@id='tblWaitingList']//td[text()[normalize-space()='" + patientuid + "']]/following-sibling::td)[2]//div[@class='pat-rema-icons']"));
        Actions builder = new Actions(driver);
        builder.moveToElement(Notificationmenu).build().perform();
    }


    public void enterText(WebElement elementID, String Text) {
        wait.until(ExpectedConditions.elementToBeClickable(elementID));
        try {
            elementID.sendKeys(Text);
        } catch (Exception exp) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].value=argument[1]", elementID, Text);
            } catch (Exception exp1) {
                System.out.println("ERROR : Unable to enter Text");
                throw exp1;
            }

        }

    }

    public void clearAndEnterText(WebElement element, String Text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled')", element);
            element.clear();
            element.sendKeys( Text);
        } catch(Exception exp) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+Text+"'", element);

            } catch (Exception exp1) {
                System.out.println("ERROR : Unable to enter Text");
                System.out.println(exp1.getMessage());
                exp1.printStackTrace();
                throw exp1;
            }



        }


    }

}
