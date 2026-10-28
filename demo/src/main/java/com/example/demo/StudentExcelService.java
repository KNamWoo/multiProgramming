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
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Service;

@Service
public class StudentExcelService {
    private final String filePath = "./demo/data/students.xlsx";

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

    public String uploadStudentData(MultipartFile file){
        int addCount = 0;
        int updateCount = 0;
        int errorCount = 0;

        DataFormatter formatter = new DataFormatter();
        try(
                Workbook uploadWorkbook = new XSSFWorkbook(file.getInputStream());
                FileInputStream fis = new FileInputStream(filePath);
                Workbook studentWorkbook = new XSSFWorkbook(fis)
            ){
            Sheet uploadSheet = uploadWorkbook.getSheetAt(0);
            Sheet studentSheet = studentWorkbook.getSheetAt(0);

            int maxID = getMaxID(studentSheet);
            for(int i = 1;i<=uploadSheet.getLastRowNum(); i++){
                Row uploadRow = uploadSheet.getRow(i);
                if(uploadRow == null){
                    continue;
                }
                String action = formatter.formatCellValue(uploadRow.getCell(0)).trim();
                String studentNumber = formatter.formatCellValue(uploadRow.getCell(1)).trim();
                String name = formatter.formatCellValue(uploadRow.getCell(2)).trim();
                String department = formatter.formatCellValue(uploadRow.getCell(3)).trim();

                if(action.isEmpty() && studentNumber.isEmpty() && name.isEmpty() && department.isEmpty()){
                    continue;
                }
                if(action.isEmpty() || studentNumber.isEmpty() || name.isEmpty() || department.isEmpty()){
                    errorCount++;
                    continue;
                }
                action = action.toUpperCase();

                if(action.equals("ADD")){
                    Row existringRow = findStudentRowByStudentNumber(studentSheet, studentNumber);
                    if(existringRow != null){
                        errorCount++;
                        continue;
                    }
                    maxID++;
                    int newRowNum = studentSheet.getLastRowNum() + 1;
                    Row newRow = studentSheet.createRow(newRowNum);
                    newRow.createCell(0).setCellValue(maxID);
                    newRow.createCell(1).setCellValue(studentNumber);
                    newRow.createCell(2).setCellValue(name);
                    newRow.createCell(3).setCellValue(department);
                    addCount++;
                }else if(action.equals("UPDATE")){
                    Row existingRow = findStudentRowByStudentNumber(studentSheet, studentNumber);
                    if(existingRow == null){
                        errorCount++;
                        continue;
                    }
                    existingRow.getCell(1).setCellValue(studentNumber);
                    existingRow.getCell(2).setCellValue(name);
                    existingRow.getCell(3).setCellValue(department);
                    updateCount++;
                }else{
                    errorCount++;
                }
            }
            try(FileOutputStream fos = new FileOutputStream(filePath)){
                studentWorkbook.write(fos);
            }
        } catch(IOException e){
            e.printStackTrace();
            return "Excel 파일 처리 중 오류가 발생했습니다.";
        }
        return "Excel 업로드 완료 / 추가 : " + addCount + "건 / 수정 : "+updateCount+"건 / 오류 : "+ errorCount+"건";
    }

    private Row findStudentRowByStudentNumber(Sheet sheet, String studentNumber){
        DataFormatter formatter = new DataFormatter();
        for(int i = 1; i<=sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if(row == null){
                continue;
            }
            String excelStudentNumber = formatter.formatCellValue(row.getCell(1)).trim();
            if(excelStudentNumber.equals(studentNumber)){
                return row;
            }
        }
        return null;
    }

    private int getMaxID(Sheet sheet){
        int maxID = 0;
        DataFormatter formatter = new DataFormatter();
        for(int i=1; i<=sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if(row == null){
                continue;
            }
            try{
                String idText = formatter.formatCellValue(row.getCell(0)).trim();
                int id = Integer.parseInt(idText);
                if(id>maxID){
                    maxID = id;
                }
            }catch(NumberFormatException e){}
        }
        return maxID;
    }
}
