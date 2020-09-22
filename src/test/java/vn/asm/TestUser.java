package vn.asm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import vn.asm.helper.ExcelWriter;
import vn.asm.model.Response;
import vn.asm.model.User;
import vn.asm.service.UserService;

import java.util.List;

public class TestUser {

    private WebDriver webDriver;
    private List<User> list;

    @BeforeTest()
    public void startBrowser() {
        webDriver = LoadBrowser.webDriver;
        list = new UserService().getList();
    }

    @Test(priority = 1)
    public void testInsert() {

        if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/userManage.htm")) {
            webDriver.findElement(By.linkText("Quản Lý User")).click();
        } else {
            webDriver.navigate().refresh();
        }

        Response response = new Response()
                .setName("Test Function User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Insert_Test_1")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút thêm người dùng")
                .setExpectedResult("Thêm thành công")
                .setActualResult("Thêm thành công")
                .setStatus(true)
                .setTester("SonHH")
                .setDate("20/06/2020");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.forEach(user -> {
            try {
                webDriver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
                Thread.sleep(500);
                webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys(user.getUsername());
                webDriver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
                webDriver.findElement(By.xpath("//input[@id='fullname']")).sendKeys(user.getFullname());
                Thread.sleep(1000);
                webDriver.findElement(By.name("insert")).click();
                Thread.sleep(500);
                if (!webDriver.getCurrentUrl().endsWith("mess=Insert+success%21")) {
                    response.setActualResult("Thêm không thành công").setStatus(false);
                    ExcelWriter.responseList.add(response);
                    Assert.assertTrue(false);
                }
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            } catch (Exception e) {
                response.setActualResult("Thao tác không thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        });
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    public void testInsertWithExitsUser() {

        if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/userManage.htm")) {
            webDriver.findElement(By.linkText("Quản Lý User")).click();
        } else {
            webDriver.navigate().refresh();
        }

        Response response = new Response()
                .setName("Test Function User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Insert_Test_2")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút thêm người dùng \n" +
                        "Thêm người dùng đã có trong database")
                .setExpectedResult("Thêm không thành công")
                .setActualResult("Thêm không thành công")
                .setStatus(true)
                .setTester("SonHH")
                .setDate("20/06/2020");

        if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/userManage.htm")) {
            webDriver.findElement(By.linkText("Quản Lý User")).click();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user = list.get(0);
        try {
            webDriver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
            Thread.sleep(1000);
            webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys(user.getUsername());
            webDriver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
            webDriver.findElement(By.xpath("//input[@id='fullname']")).sendKeys(user.getFullname());
            Thread.sleep(500);
            webDriver.findElement(By.name("insert")).click();
            Thread.sleep(500);
            if (webDriver.getCurrentUrl().endsWith("mess=Insert+success%21")) {
                response.setActualResult("Thêm thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }

        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 3)
    public void testInsertWithExitsUserName() {
        Response response = new Response()
                .setName("Test Function User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Insert_Test_2")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút thêm người dùng \n" +
                        "Tiến hành thêm các trường nhưng bỏ qua dòng username")
                .setExpectedResult("Thêm không thành công, yêu cầu nhập username")
                .setActualResult("Thêm không thành công")
                .setStatus(true)
                .setTester("SonHH")
                .setDate("20/06/2020");

        if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/userManage.htm")) {
            webDriver.findElement(By.linkText("Quản Lý User")).click();
        } else {
            webDriver.navigate().refresh();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user = list.get(0);
        try {
            webDriver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
            Thread.sleep(1000);
            //webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys(user.getUsername());
            webDriver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
            webDriver.findElement(By.xpath("//input[@id='fullname']")).sendKeys(user.getFullname());
            Thread.sleep(500);
            webDriver.findElement(By.name("insert")).click();
            Thread.sleep(500);
            if (webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/userManage.htm")) {
                if (webDriver.findElements(By.className("invalid-feedback")).size() == 0) {
                    response.setActualResult("Thêm không thành công và không có thông báo yêu cầu nhập");
                    ExcelWriter.responseList.add(response);
                    Assert.assertTrue(false);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công \n " + e.toString()).setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }

        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 4)
    public void testUpdate() {

        if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/userManage.htm")) {
            webDriver.findElement(By.linkText("Quản Lý User")).click();
        } else {
            webDriver.navigate().refresh();
        }

        Response response = new Response()
                .setName("Test Function User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Update_Test")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút cập nhật người dùng")
                .setExpectedResult("Cập nhật thành công")
                .setActualResult("Cập nhật thành công")
                .setStatus(true)
                .setTester("SonHH")
                .setDate("20/06/2020");
        webDriver.findElement(By.linkText("Quản Lý User")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (User user : list) {
            List<WebElement> elements = webDriver.findElements(By.cssSelector("button[class='btn btn-primary']"));
            for (WebElement ele : elements) {
                if (ele == null) {
                    continue;
                }
                String target = ele.getAttribute("onclick");
                System.out.println(target);
                if (target == null) {
                    continue;
                }
                if (target.equals("crunchifyAjax('" + user.getUsername() + "')")) {
                    ele.click();
                    try {
                        Thread.sleep(2000);

                        webDriver.findElement(By.xpath("//input[@id='fullname']")).sendKeys(" updated");
                        webDriver.findElement(By.name("update")).click();

                        Thread.sleep(1000);

                        if (!webDriver.getCurrentUrl().endsWith("mess=Update+success%21")) {
                            response.setActualResult("Cập nhật không thành công").setStatus(false);
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
                    break;
                }
            }
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 5)
    public void testDelete() {

        if (!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/userManage.htm")) {
            webDriver.findElement(By.linkText("Quản Lý User")).click();
        } else {
            webDriver.navigate().refresh();
        }

        Response response = new Response()
                .setName("Test Function User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Delete_Test")
                .setAction("Truy cập vào trang người dùng \n Nhấn nút xoá người dùng")
                .setExpectedResult("Xoá thành công")
                .setActualResult("Xoá thành công")
                .setStatus(true)
                .setTester("SonHH")
                .setDate("20/06/2020");

        webDriver.findElement(By.linkText("Quản Lý User")).click();

        for (User user : list) {
            List<WebElement> elements = webDriver.findElements(By.cssSelector("button[class='btn btn-danger']"));

            for (WebElement ele : elements) {
                if (ele == null) {
                    continue;
                }
                String target = ele.getAttribute("onclick");
                if (target == null) {
                    continue;
                }
                if (target.equals("getUserToDelete('" + user.getUsername() + "')")) {
                    try {
                        ele.click();
                        Thread.sleep(500);
                        webDriver.findElement(By.name("delete")).click();
                        Thread.sleep(1000);
                        if (!webDriver.getCurrentUrl().endsWith("Delete+success%21")) {
                            response.setActualResult("Xoá không thành công").setStatus(false);
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
                    break;
                }
            }
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }


}
