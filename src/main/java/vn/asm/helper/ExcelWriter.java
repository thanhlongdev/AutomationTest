package vn.asm.helper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.asm.model.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriter {

    public static List<Response> responseList = new ArrayList<>();
    private String[] header = {"NAME", "GUI/FUNC", "PRIORITY", "ID", "TEST DATA", "EXPECTED RESULT", "ACTUAL RESULT", "STATUS", "TESTER", "DATE"};
    private String path = System.getProperty("user.dir") + "\\excel\\TestCase.xlsx";
    //Write Data
    public void wirteExcel(String sheetName) throws IOException {
        Workbook workbook = createWorkBook(path);
        Sheet sheet = workbook.createSheet(sheetName);
        int rowIndex = 0;
        //Write Header
        writeHeader(sheet, rowIndex++, header);

        //Write Row
        for (Response response : responseList) {
            //Create Row
            Row row = sheet.createRow(rowIndex);
            //Write data on row
            writeBook(response, row);
            rowIndex++;
        }
        createOutputFile(workbook, path);
    }

    //Create Workbook
    private Workbook createWorkBook(String excelFile) {
        Workbook workbook = null;
        if (excelFile.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else if (excelFile.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    //WriteHeader
    private void writeHeader(Sheet sheet, int rowIndex, String[] header) {
        //Apply Style
        //CellStyle style = createStyleForHeader(sheet);
        //Create Row
        Row row = sheet.createRow(rowIndex);
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            //cell.setCellStyle(style);
            cell.setCellValue(header[i]);
        }
    }

    // Write data
    private void writeBook(Response response, Row row) {

        row.createCell(0).setCellValue(response.getName());
        row.createCell(1).setCellValue(response.getTestType());
        row.createCell(2).setCellValue(response.getPriority());
        row.createCell(3).setCellValue(response.getId());
        row.createCell(4).setCellValue(response.getAction());
        row.createCell(5).setCellValue(response.getExpectedResult());
        row.createCell(6).setCellValue(response.getActualResult());
        row.createCell(7).setCellValue(response.isStatus());
        row.createCell(8).setCellValue(response.getTester());
        row.createCell(9).setCellValue(response.getDate());

    }

    // Create output file
    private void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        OutputStream os = new FileOutputStream(excelFilePath);
        workbook.write(os);
    }

}
