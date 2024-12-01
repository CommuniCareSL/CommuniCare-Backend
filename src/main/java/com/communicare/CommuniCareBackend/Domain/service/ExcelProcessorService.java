package com.communicare.CommuniCareBackend.Domain.service;


import io.jsonwebtoken.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class ExcelProcessorService {

    private static final String FILE_PATH = "data/admin_data.xlsx";

    public Integer findAdmin3IdByAdmin4Name(String admin4Name) {
        try {
            // Load the file from resources
            InputStream inputStream = new ClassPathResource(FILE_PATH).getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (Row row : sheet) {
                String admin4NameEn = row.getCell(1).getStringCellValue(); // admin4Name_en is in the first column
                if (admin4NameEn.equalsIgnoreCase(admin4Name)) {
                    return (int) row.getCell(3).getNumericCellValue(); // admin3_id is in the second column
                }
            }

            workbook.close(); // Close the workbook
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }

        return null; // Return null if no match is found or an error occurs
    }
}