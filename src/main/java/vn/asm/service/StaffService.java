package vn.asm.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import vn.asm.helper.ExcelLoader;
import vn.asm.model.Depart;
import vn.asm.model.Staff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StaffService {

    public List<Staff> getList() {
        List<Staff> list = new ArrayList<Staff>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "\\excel\\testdata.xlsx"));
            Sheet sheet = new ExcelLoader().getSheet(fileInputStream, 3);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Iterator<Cell> cellIterator = rowIterator.next().iterator();
                cellIterator.next();
                list.add(new Staff()
                        .setId(cellIterator.next().getStringCellValue())
                        .setName(cellIterator.next().getStringCellValue())
                        .setGender(cellIterator.next().getBooleanCellValue())
                        .setBirthday(cellIterator.next().getStringCellValue())
                        .setEmail(cellIterator.next().getStringCellValue())
                        .setPhone(cellIterator.next().getStringCellValue())
                        .setSalary(cellIterator.next().getNumericCellValue())
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
