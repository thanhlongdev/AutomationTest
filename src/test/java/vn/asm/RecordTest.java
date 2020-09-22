package vn.asm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import vn.asm.helper.ExcelWriter;
import vn.asm.model.Response;

public class RecordTest {

    private WebDriver webDriver;

    @BeforeClass
    public void loadBrowser(){
        webDriver = LoadBrowser.webDriver;
    }

    @Test
    public void testKhenThuong(){

        Response response = new Response()
                .setName("Test Record")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Record_Show_1")
                .setAction("Truy cập vào trang quản lý thành tích")
                .setExpectedResult("Show thành tích thành công")
                .setActualResult("Show thành tích thành công")
                .setStatus(true)
                .setTester("KhangDH")
                .setDate("20/06/2020");

        try {
            webDriver.findElement(By.linkText("Quản Lý Thành Tích")).click();
            Thread.sleep(500);
            webDriver.findElement(By.linkText("Thành Tích")).click();
            Thread.sleep(500);
            if(!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/recordManage.htm?type=true")){
                response.setActualResult("Không truy cập được trang quản lý");
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e){
            response.setActualResult("Thao tác không thành công");
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test
    public void testKyLuat(){

        Response response = new Response()
                .setName("Test Record")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Record_Show_2")
                .setAction("Truy cập vào trang quản lý kỷ luật")
                .setExpectedResult("Show kỷ luật thành công")
                .setActualResult("Show kỷ luật thành công")
                .setStatus(true)
                .setTester("KhangDH")
                .setDate("20/06/2020");

        try {
            webDriver.findElement(By.linkText("Quản Lý Thành Tích")).click();
            Thread.sleep(500);
            webDriver.findElement(By.linkText("Kỷ Luật")).click();
            Thread.sleep(500);
            if(!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/recordManage.htm?type=false")){
                response.setActualResult("Không truy cập được trang quản lý");
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e){
            response.setActualResult("Thao tác không thành công");
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }


}
