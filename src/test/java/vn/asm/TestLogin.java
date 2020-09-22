package vn.asm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import vn.asm.helper.ExcelWriter;
import vn.asm.model.Response;
import vn.asm.model.User;
import vn.asm.service.LoginService;

import java.util.List;

public class TestLogin {

    private WebDriver webDriver;
    private List<User> list;

    @BeforeTest()
    public void startBrowser() {
        webDriver = LoadBrowser.webDriver;
        list = new LoginService().getList();
    }
    @Ignore
    @Test(priority = 1)
    public void testLogin() {
        Response response = new Response()
                .setName("Test Login")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Login_Test_1")
                .setAction("Truy cập vào trang login và tiến hành nhập dữ liệu vào các trường")
                .setExpectedResult("Login thành công")
                .setActualResult("Login thành công")
                .setStatus(true)
                .setTester("LongVT")
                .setDate("20/06/2020");
        list.forEach(user -> {
            try {
                webDriver.get("http://localhost:8080/ASMJava5/login.htm");
                webDriver.findElement(By.name("username")).sendKeys(user.getUsername());
                webDriver.findElement(By.name("password")).sendKeys(user.getPassword());
                Thread.sleep(2000);
                webDriver.findElement(By.cssSelector("button[class='login100-form-btn']")).click();
                Thread.sleep(500);
                if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/dashboard.htm")) {
                    response.setActualResult("Không thành công");
                    response.setStatus(false);
                    ExcelWriter.responseList.add(response);
                    Assert.assertTrue(false);
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                response.setActualResult("Thao tác không thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        });
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }
    @Ignore
    @Test(priority = 2)
    public void testLoginWithUnknownUser() {

        Response response = new Response()
                .setName("Test Login")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Login_Test_2")
                .setAction("Truy cập vào trang login và nhập 1 user không có trong database \n" +
                        "Username: admin1 \n" +
                        "Password: 123")
                .setExpectedResult("Login không thành công và ở lại trang login")
                .setActualResult("Login không thành công")
                .setStatus(true)
                .setTester("LongVT")
                .setDate("20/06/2020");

        try {
            webDriver.get("http://localhost:8080/ASMJava5/login.htm");
            webDriver.findElement(By.name("username")).sendKeys("admin1");
            webDriver.findElement(By.name("password")).sendKeys("123");
            Thread.sleep(2000);
            webDriver.findElement(By.cssSelector("button[class='login100-form-btn']")).click();
            Thread.sleep(1000);
            if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/login.htm")) {
                response.setActualResult("Login thành công");
                response.setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }
    @Ignore
    @Test(priority = 3)
    public void loginWithoutPassword() {
        Response response = new Response()
                .setName("Test Login")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Login_Test_3")
                .setAction("Truy cập vào trang login chỉ nhập username")
                .setExpectedResult("Login không thành công và ở lại trang login")
                .setActualResult("Login không thành công")
                .setStatus(true)
                .setTester("LongVT")
                .setDate("20/06/2020");

        try {
            webDriver.get("http://localhost:8080/ASMJava5/login.htm");
            webDriver.findElement(By.name("username")).sendKeys("admin");
            Thread.sleep(2000);
            webDriver.findElement(By.cssSelector("button[class='login100-form-btn']")).click();
            Thread.sleep(1000);
            if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/login.htm")) {
                response.setActualResult("Login thành công");
                response.setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);

    }
    @Ignore
    @Test(priority = 4)
    public void login() {
        webDriver.get("http://localhost:8080/ASMJava5/login.htm");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.cssSelector("button[class='login100-form-btn']")).click();
    }

    @Test(priority = 5)
    public void testTitle() {
        webDriver.get("http://localhost:8080/ASMJava5/login.htm");
        if (webDriver.getTitle().equals("Login V1")) {
            Response response = new Response()
                    .setName("Test Login")
                    .setTestType("FUNC")
                    .setPriority("High")
                    .setId("Login_Test_3")
                    .setAction("Truy cập vào trang login chỉ nhập username")
                    .setExpectedResult("Title ='LoginV1'")
                    .setActualResult("Oke")
                    .setStatus(true)
                    .setTester("LongVT")
                    .setDate("20/06/2020");
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(true);
        } else {

            Response response = new Response()
                    .setName("Test Login")
                    .setTestType("FUNC")
                    .setPriority("High")
                    .setId("Login_Test_3")
                    .setAction("Truy cập vào trang login chỉ nhập username")
                    .setExpectedResult("Tile ='LoginV1'")
                    .setActualResult("Title = '" + webDriver.getTitle() + "'")
                    .setStatus(false)
                    .setTester("LongVT")
                    .setDate("20/06/2020");
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }

    }

}
