package com.example.demo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;

@Service
public class StudentExcelService {
    private final String filePath = "data/students.xlsx";

    public List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();
        
        try(
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)
        ){
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);

                if(row == null){
                    continue;
                }

                int id = (int) row.getCell(0).getNumericCellValue();
                String studentNumber = formatter.formatCellValue(row.getCell(1));
                String name = formatter.formatCellValue(row.getCell(2));
                String department = formatter.formatCellValue(row.getCell(3));
                Student student = new Student(id, studentNumber, name, department);
                
                studentList.add(student);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return studentList;
    }

    public void addStudent(Student student){
        try(
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)
        ){
            Sheet sheet = workbook.getSheetAt(0);
            int newRowNum = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(newRowNum);
            
            row.createCell(0).setCellValue(student.getID());
            row.createCell(1).setCellValue(student.getStudentNumber());
            row.createCell(2).setCellValue(student.getName());
            row.createCell(3).setCellValue(student.getDepartment());
            
            try(FileOutputStream fos = new FileOutputStream(filePath)){
                workbook.write(fos);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public Student updateStudent(int id, String studentNumber, String name, String department){
        try(
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)
        ){
            Sheet sheet = workbook.getSheetAt(0);

            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);

                if(row == null){
                    continue;
                }

                int excelID = (int) row.getCell(0).getNumericCellValue();
                if(excelID == id){
                    row.getCell(1).setCellValue(studentNumber);
                    row.getCell(2).setCellValue(name);
                    row.getCell(3).setCellValue(department);
                    
                    try(FileOutputStream fos = new FileOutputStream(filePath)){
                        workbook.write(fos);
                    }
                    return new Student(id, studentNumber, name, department);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteStudent(int id){
        try(
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)
        ){
            Sheet sheet = workbook.getSheetAt(0);

            for(int i=1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);

                if(row == null){
                    continue;
                }

                int excelID = (int) row.getCell(0).getNumericCellValue();

                if(excelID == id){
                    int lastRow = sheet.getLastRowNum();

                    if(i < lastRow){
                        sheet.shiftRows(i+1, lastRow, -1);
                    }

                    Row lastRowData = sheet.getRow(lastRow);

                    if(lastRowData != null){
                        sheet.removeRow(lastRowData);
                    }

                    try(FileOutputStream fos = new FileOutputStream(filePath)){
                        workbook.write(fos);
                    }

                    return true;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
