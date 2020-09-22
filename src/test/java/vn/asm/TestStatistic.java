package vn.asm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import vn.asm.helper.ExcelWriter;
import vn.asm.model.Response;

public class TestStatistic {

    private WebDriver webDriver;

    @BeforeClass
    public void loadBrowser(){
        webDriver = LoadBrowser.webDriver;
    }

    @Test
    public void testThongKeNhanVien(){

        Response response = new Response()
                .setName("Test Statistic")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Statictis_Show_1")
                .setAction("Truy cập vào trang thống kê thành tích")
                .setExpectedResult("Show Thành Tích Nhân Viên thành công")
                .setActualResult("Show Thành Tích Nhân Viên thành công")
                .setStatus(true)
                .setTester("LongVT")
                .setDate("20/06/2020");

        try {
            webDriver.findElement(By.linkText("Thống Kê Thành Tích")).click();
            Thread.sleep(500);
            webDriver.findElement(By.linkText("Thành Tích Nhân Viên")).click();
            Thread.sleep(500);
            if(!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/statisticStaff.htm")){
                response.setActualResult("Không truy cập được trang thống kê");
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
                .setName("Test Statistic")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Statictis_Show_2")
                .setAction("Truy cập vào trang thống kê thành tích")
                .setExpectedResult("Show Thành Tích Phòng Ban thành công")
                .setActualResult("Show Thành Tích Phòng Ban thành công")
                .setStatus(true)
                .setTester("LongVT")
                .setDate("20/06/2020");

        try {
            webDriver.findElement(By.linkText("Thống Kê Thành Tích")).click();
            Thread.sleep(500);
            webDriver.findElement(By.linkText("Thành Tích Phòng Ban")).click();
            Thread.sleep(500);
            if(!webDriver.getCurrentUrl().equals("http://localhost:8080/ASMJava5/statisticDepart.htm")){
                response.setActualResult("Không truy cập được trang thống kê");
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
