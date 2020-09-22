package vn.asm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import vn.asm.helper.ExcelWriter;

import java.io.IOException;

public class LoadBrowser {

    public static WebDriver webDriver;

    @BeforeSuite(alwaysRun = true)
    public void loadDriver(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterSuite
    private void closeBrowser(){
        webDriver.close();
        try {
            new ExcelWriter().wirteExcel("TestCase");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
