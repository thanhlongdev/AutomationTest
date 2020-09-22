package vn.asm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import vn.asm.helper.ExcelWriter;
import vn.asm.model.Depart;
import vn.asm.model.Response;
import vn.asm.service.DepartService;

import java.util.List;

public class TestDepart {

    private WebDriver webDriver;
    private List<Depart> list;

    @BeforeTest()
    public void startBrowser() {
        webDriver = LoadBrowser.webDriver;
        list = new DepartService().getList();
    }

    @Test(priority = 1)
    public void testInsert() {
        Response response = new Response()
                .setName("Test Function Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Insert_Test_1")
                .setAction("Truy cập vào trang phòng ban \n" +
                        "Nhấn nút thêm phòng ban")
                .setExpectedResult("Thêm thành công")
                .setActualResult("Thêm thành công")
                .setStatus(true)
                .setTester("ThangVTM")
                .setDate("20/06/2020");

        webDriver.findElement(By.linkText("Quản Lý Phòng Ban")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.forEach(depart -> {
            try {
                webDriver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
                Thread.sleep(1500);
                webDriver.findElement(By.id("departId")).sendKeys(depart.getId());
                webDriver.findElement(By.name("name")).sendKeys(depart.getName());
                Thread.sleep(500);
                webDriver.findElement(By.id("btnSave")).click();
                Thread.sleep(1000);
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
    public void testInsertWithExitDepart() {
        Response response = new Response()
                .setName("Test Function Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Insert_Test_1")
                .setAction("Truy cập vào trang phòng ban \n" +
                        "Nhấn nút thêm phòng ban \n" +
                        "Thêm phòng ban đã có trong database")
                .setExpectedResult("Thêm không thành công")
                .setActualResult("Thêm không thành công")
                .setStatus(true)
                .setTester("ThangVTM")
                .setDate("20/06/2020");

        webDriver.findElement(By.linkText("Quản Lý Phòng Ban")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Depart depart = list.get(0);
            try {
                webDriver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
                Thread.sleep(1500);
                webDriver.findElement(By.id("departId")).sendKeys(depart.getId());
                webDriver.findElement(By.name("name")).sendKeys(depart.getName());
                Thread.sleep(2000);
                webDriver.findElement(By.id("btnSave")).click();
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
    public void testUpdate() {
        Response response = new Response()
                .setName("Test Function Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Update_Test")
                .setAction("Truy cập vào trang quản lý phòng ban \n" +
                        "Nhấn nút cập nhật phòng ban \n" +
                        "Thêm 'updated' vào sau tên phòng ban")
                .setExpectedResult("Cập nhật thành công")
                .setActualResult("Cập nhật thành công")
                .setStatus(true)
                .setTester("ThangVTM")
                .setDate("20/06/2020");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Depart depart : list) {
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
                if (target.equals("crunchifyAjax('" + depart.getId() + "')")) {
                    try {
                        ele.click();
                        Thread.sleep(1000);
                        webDriver.findElement(By.name("name")).sendKeys(" update");
                        webDriver.findElement(By.name("update")).click();
                        if(!webDriver.getCurrentUrl().endsWith("mess=Update+success%21")){
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

    @Test(priority = 4)
    public void testDelete() {

        Response response = new Response()
                .setName("Test Function Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Delete_Test")
                .setAction("Truy cập vào trang quản lý phòng ban, nhấn nút xoá phòng ban")
                .setExpectedResult("Xoá thành công")
                .setActualResult("Xoá thành công")
                .setStatus(true)
                .setTester("ThangVTM")
                .setDate("20/06/2020");

        for (Depart depart : list) {
            List<WebElement> elements = webDriver.findElements(By.cssSelector("button[class='btn btn-danger']"));

            for (WebElement ele : elements) {
                if (ele == null) {
                    continue;
                }
                String target = ele.getAttribute("onclick");
                if (target == null) {
                    continue;
                }
                if (target.equals("getUserToDelete('" + depart.getId() + "')")) {
                    try {
                        ele.click();
                        Thread.sleep(1000);
                        webDriver.findElement(By.name("delete")).click();
                        Thread.sleep(1000);
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
