
package io.github.ppolushkin;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Pavel Polushkin
 * 01.04.2017.
 */
@Service
public class ExcelReadService {

    public void read() throws IOException, InvalidFormatException {
        Workbook wb = openExcel97("/Book1.xls");
        System.out.println("readDouble(wb, \"ЛИИИСТ1\", \"A2\") = " + readDouble(wb, "ЛИИИСТ1", "A2"));
        System.out.println("readString(wb, \"ЛИИИСТ2\", \"B5\") = " + readString(wb, "ЛИИИСТ2", "B5"));

        wb = open("/Book1.xlsx");
        System.out.println("readDouble(wb, \"ЛИИИСТ1\", \"A2\") = " + readDouble(wb, "ЛИИИСТ1", "A2"));
        System.out.println("readString(wb, \"ЛИИИСТ2\", \"B5\") = " + readString(wb, "ЛИИИСТ2", "B5"));
    }

    private Workbook open(String resourcePath) throws InvalidFormatException, IOException {
        InputStream is = this.getClass().getResourceAsStream(resourcePath);
        return new XSSFWorkbook(is);
    }

    private Workbook openExcel97(String resourcePath) throws InvalidFormatException, IOException {
        InputStream is = this.getClass().getResourceAsStream(resourcePath);
        return new HSSFWorkbook(is);
    }

    public double readDouble(Workbook wb, String workSheet, String cellLocation) {
        Cell cell = getCell(wb, workSheet, cellLocation);
        return cell.getNumericCellValue();
    }

    public String readString(Workbook wb, String workSheet, String cellLocation) {
        Cell cell = getCell(wb, workSheet, cellLocation);
        return cell.getStringCellValue();
    }

    private Cell getCell(Workbook wb, String workSheet, String cellLocation) {
        Sheet sheet = wb.getSheet(workSheet);
        CellReference cellReference = new CellReference(cellLocation);
        Row row = sheet.getRow(cellReference.getRow());
        return row.getCell(cellReference.getCol());
    }

}
