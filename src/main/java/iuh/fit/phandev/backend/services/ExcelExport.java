package iuh.fit.phandev.backend.services;

import iuh.fit.phandev.backend.models.Candidate;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExport {
    public byte[] exportToExcel(List<Candidate> candidates) throws IOException {


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách");

        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Danh sách ứng cử viên");


        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);


        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 4));


        String[] columns = {"Họ và Tên", "Ngày Sinh", "Địa Chỉ", "Số Điện Thoại", "Email"};
        Row headerRow = sheet.createRow(1);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);


            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            cell.setCellStyle(headerStyle);
        }


        int rowIdx = 2;
        for (Candidate person : candidates) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(person.getFullName());
            row.createCell(1).setCellValue(person.getDob().toString());
            row.createCell(2).setCellValue(person.getAddress().getStreet() + " " + person.getAddress().getCity());
            row.createCell(3).setCellValue(person.getPhone());
            row.createCell(4).setCellValue(person.getEmail());
        }

  
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }
}
