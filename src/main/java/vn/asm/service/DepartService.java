package vn.asm.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import vn.asm.helper.ExcelLoader;
import vn.asm.model.Depart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DepartService {

    public List<Depart> getList() {
        List<Depart> list = new ArrayList<Depart>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "\\excel\\testdata.xlsx"));
            Sheet sheet = new ExcelLoader().getSheet(fileInputStream, 2);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Iterator<Cell> cellIterator = rowIterator.next().iterator();
                cellIterator.next();
                list.add(new Depart()
                        .setId(cellIterator.next().getStringCellValue())
                        .setName(cellIterator.next().getStringCellValue())
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
