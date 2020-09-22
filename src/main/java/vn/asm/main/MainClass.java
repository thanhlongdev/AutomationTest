package vn.asm.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.asm.service.StaffService;

public class MainClass {

    public static void main (String[] args){
        String a = "abc";
        String b = "Abc";

        System.out.println(a.equals(b));
        System.out.println(a.equalsIgnoreCase(b));
    }

}
