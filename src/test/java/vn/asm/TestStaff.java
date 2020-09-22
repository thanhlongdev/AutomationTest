package vn.asm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import vn.asm.helper.ExcelWriter;
import vn.asm.model.Depart;
import vn.asm.model.Response;
import vn.asm.model.Staff;
import vn.asm.service.DepartService;
import vn.asm.service.StaffService;

import java.util.List;

public class TestStaff {

    private WebDriver webDriver;
    private List<Staff> list;

    @BeforeTest()
    public void startBrowser() {
        webDriver = LoadBrowser.webDriver;
        list = new StaffService().getList();
    }

    @Test(priority = 1)
    public void testInsert() {
        Response response = new Response()
                .setName("Test Function Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Insert_Staff_1")
                .setAction("Truy cập vào trang quản lý nhân viên \n Nhấn nút thêm nhân viên")
                .setExpectedResult("Thêm thành công")
                .setActualResult("Thêm thành công")
                .setStatus(true)
                .setTester("LongNP")
                .setDate("20/06/2020");

        webDriver.findElement(By.linkText("Quản Lý Nhân Viên")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.forEach(staff -> {
            try {
                webDriver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
                Thread.sleep(1500);
                webDriver.findElement(By.id("staffId")).sendKeys(staff.getId());
                webDriver.findElement(By.name("name")).sendKeys(staff.getName());
                if(!staff.isGender()){
                    webDriver.findElement(By.id("female")).click();
                }
                webDriver.findElement(By.name("birthday")).clear();
                webDriver.findElement(By.name("birthday")).sendKeys(staff.getBirthday());
                webDriver.findElement(By.name("phone")).sendKeys(staff.getPhone());
                webDriver.findElement(By.name("email")).sendKeys(staff.getEmail());
                webDriver.findElement(By.name("salary")).sendKeys(String.valueOf(staff.getSalary()));

                Thread.sleep(2000);
                webDriver.findElement(By.id("btnSave")).click();
                Thread.sleep(1000);
                if (!webDriver.getCurrentUrl().endsWith("mess=Insert+success%21")) {
                    response.setActualResult("Thêm không thành công").setStatus(false);
                    Assert.assertTrue(false);
                }
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            } catch (Exception e){
                response.setActualResult("Thao tác không thành công").setStatus(false);
                Assert.assertTrue(false);
            }
        });
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    public void testInsertWithExsitStaff() {
        Response response = new Response()
                .setName("Test Function Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Insert_Staff_2")
                .setAction("Truy cập vào trang quản lý nhân viên \n" +
                        "Nhấn nút thêm nhân viên \n" +
                        "Thêm nhân viên đã có trong cơ sỡ dữ liệu")
                .setExpectedResult("Thêm không thành công")
                .setActualResult("Thêm không thành công")
                .setStatus(true)
                .setTester("LongNP")
                .setDate("20/06/2020");

        webDriver.findElement(By.linkText("Quản Lý Nhân Viên")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Staff staff = list.get(0);

            try {
                webDriver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
                Thread.sleep(1500);
                webDriver.findElement(By.id("staffId")).sendKeys(staff.getId());
                webDriver.findElement(By.name("name")).sendKeys(staff.getName());
                if(!staff.isGender()){
                    webDriver.findElement(By.id("female")).click();
                }
                webDriver.findElement(By.name("birthday")).clear();
                webDriver.findElement(By.name("birthday")).sendKeys(staff.getBirthday());
                webDriver.findElement(By.name("phone")).sendKeys(staff.getPhone());
                webDriver.findElement(By.name("email")).sendKeys(staff.getEmail());
                webDriver.findElement(By.name("salary")).sendKeys(String.valueOf(staff.getSalary()));

                Thread.sleep(500);
                webDriver.findElement(By.id("btnSave")).click();
                Thread.sleep(1000);
                if (webDriver.getCurrentUrl().endsWith("mess=Insert+success%21")) {
                    response.setActualResult("Thêm thành công").setStatus(false);
                    ExcelWriter.responseList.add(response);
                    Assert.assertTrue(false);
                }
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            } catch (Exception e){
                response.setActualResult("Thao tác không thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }

        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 3)
    public void testUpdate() {
        Response response = new Response()
                .setName("Test Function Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Update_Test")
                .setAction("Truy cập vào trang quản lý nhân viên \n" +
                        "Nhấn nút cập nhật nhân viên \n" +
                        "Thêm 'updated'  vào sau tên nhân viên")
                .setExpectedResult("Cập nhật thành công")
                .setActualResult("Cập nhật thành công")
                .setStatus(true)
                .setTester("LongNP")
                .setDate("20/06/2020");

        webDriver.findElement(By.linkText("Quản Lý Nhân Viên")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Staff staff : list) {
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
                if (target.equals("crunchifyAjax('" + staff.getId() + "')")) {
                    try {
                        ele.click();
                        Thread.sleep(1000);
                        webDriver.findElement(By.name("name")).sendKeys(" updated");
                        webDriver.findElement(By.name("update")).click();
                        Thread.sleep(1000);
                        if(!webDriver.getCurrentUrl().endsWith("mess=Update+success%21")){
                            response.setActualResult("Cập nhật không thành công").setStatus(false);
                            ExcelWriter.responseList.add(response);
                            Assert.assertTrue(false);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e){
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

    @Test(priority = 4)
    public void testDelete() {

        Response response = new Response()
                .setName("Test Function Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Delete_Test")
                .setAction("Truy cập vào trang quản lý nhân viên \n Nhấn nút xoá xoá nhân viên")
                .setExpectedResult("Xoá thành công")
                .setActualResult("Xoá thành công")
                .setStatus(true)
                .setTester("KhangDH")
                .setDate("20/06/2020");

        webDriver.findElement(By.linkText("Quản Lý Nhân Viên")).click();

        for (Staff staff : list) {
            List<WebElement> elements = webDriver.findElements(By.cssSelector("button[class='btn btn-danger']"));

            for (WebElement ele : elements) {
                if (ele == null) {
                    continue;
                }
                String target = ele.getAttribute("onclick");
                if (target == null) {
                    continue;
                }
                if (target.equals("getUserToDelete('" + staff.getId() + "')")) {
                    try {
                        ele.click();
                        Thread.sleep(1000);

                        webDriver.findElement(By.name("delete")).click();
                        if(!webDriver.getCurrentUrl().endsWith("Delete+success%21")){
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
