package com.example.demo;

// 브라우저의 Get 방식 요청을 특정 메서드와 연결하기 위함
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

// 브라우저나 클라이언트의 PUT 방식 요청을 특정 메서드와 연결하기 위함
// 기존 데이터를 삭제하는 DELETE 요청을 특정 메서드와 연결하기 위함
// 클래스의 웹 요청 Controller 임을 Spring Boot에 전달
// 객체 여러 개를 List로 반환
import java.util.List;
//import java.util.ArrayList;
// URL 주소에 포함된 값을 Java로 가져오는 기능

// Excel 파일 업로드를 위해 사용
import org.springframework.web.multipart.MultipartFile;
// Excel 템플릿 다운로드를 위해 사용
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;


@RestController // 웹 요청을 처리하고 결과를 브라우저에 직접 반환하는 Controller
public class StudentController { // 학생 관련 웹 요청 처리 클래스
    private final StudentExcelService studentExcelService;

    public StudentController(StudentExcelService studentExcelService){
        this.studentExcelService = studentExcelService;
    }

    private List<Student> getStudentList(){
        return studentExcelService.getAllStudents();
    }

    @GetMapping("/students")
    public List<Student> students(){
        return getStudentList();
    }

    @GetMapping("/students/search/{id}")
    // "http://localhost:8080/students/search/1"의 형식
    // List 반환이 아니기 때문에 Object로 자료형을 지정
    public Object studentByID(@PathVariable int id){
        List<Student> studentList = getStudentList();

        for(Student student : studentList){
            if(student.getID() == id){
                return student;
            }
        }
        return "해당 번호의 학생을 찾을 수 없습니다.";
    }

    @GetMapping("/students/search/name")
    public Object searchByName(@RequestParam String name){
        List<Student> studentList = getStudentList();

        for(Student student: studentList){
            if(student.getName().equals(name)){
                return student;
            }
        }
        return "해당 이름의 학생이 없습니다.";
    }

    @GetMapping("/students/search")
    // "http://localhost:8080/students/search?department=멀티미디어학과"의 형식
    public List<Student> searchByDepartment(@RequestParam String department){
        return getStudentList().stream().filter(s->s.getDepartment().equals(department)).toList();
    }

    @PostMapping("/students/new")
    public Object addStudent(@RequestParam int id, @RequestParam String studentNumber, @RequestParam String name, @RequestParam String department){
        List<Student> studentList = getStudentList();

        for(Student student : studentList){
            if(student.getID() == id){
                return "이미 존재하는 학생ID 입니다.";
            }
            if(student.getStudentNumber().equals(studentNumber)){
                return "이미 존재하는 학번입니다.";
            }
        }
        Student newStudent = new Student(id, studentNumber, name, department);
        studentExcelService.addStudent(newStudent);
        
        return newStudent;
    }

    //수정하는 코드
    @PutMapping("/students/{id}")
    public Object updateStudent(
            @PathVariable int id,
            @RequestParam String studentNumber,
            @RequestParam String name,
            @RequestParam String department
    ){
        Student updatedStudent = studentExcelService.updateStudent(id, studentNumber, name, department);

        if(updatedStudent == null){
            return "해당 학생이 없습니다.";
        }

        return updatedStudent;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id){
        boolean result = studentExcelService.deleteStudent(id);

        if(result){
            return "학생이 삭제되었습니다.";
        }

        return "해당 학생이 없습니다.";
    }

    @GetMapping("/students/count")
    public int getStudentCount(){
        return getStudentList().size();
    }

    @GetMapping("/students/count/department")
    public int getDepartmentCount(@RequestParam String department){
        int count = 0;
        List<Student> studentList = getStudentList();

        for(Student student : studentList){
            if(student.getDepartment().equals(department)){
                count++;
            }
        }
        return count;
    }

    //'/students/search/grade/' + encodeURIComponent(name) + '?department=' + encodeURIComponent(department)

    @GetMapping("/students/template")
    public ResponseEntity<Resource>
    downloadTemplate() throws IOException{
        Resource resource = new ClassPathResource("static/files/dataUpload.xlsx");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"dataUpload.xlsx\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

    @PostMapping("/students/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "업로드할 Excel 파일을 선택하세요.";
        }
        String fileName = file.getOriginalFilename();
        if(fileName == null || !fileName.toLowerCase().endsWith(".xlsx")){
            return "xlsx 형식의 Excel 파일만 업로드할 수 있습니다.";
        }
        // StudentExcelService에서 업로드된 Excel 내용을 읽어서 student.xlsx에 반영
        return studentExcelService.uploadStudentData(file);
    }
}
