package com.example.demo;

// 브라우저의 Get 방식 요청을 특정 메서드와 연결하기 위함
import org.springframework.web.bind.annotation.GetMapping;
// 클래스의 웹 요청 Controller 임을 Spring Boot에 전달
import org.springframework.web.bind.annotation.RestController;
// 객체 여러 개를 List로 반환
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;
// URL 주소에 포함된 값을 Java로 가져오는 기능
import org.springframework.web.bind.annotation.PathVariable;

@RestController // 웹 요청을 처리하고 결과를 브라우저에 직접 반환하는 Controller
public class StudentController { // 학생 관련 웹 요청 처리 클래스
    // 브라우저에서 '/student' 주소로 GET 요청이 들어오면 바로 student() 메서드 실행
    @GetMapping("/student")
    public Student student(){
        // 새로운 Student 객체를 생성해서 반환
        // Spring Boot는 이 Student 객체를 자동으로 JSON 형태로 변환하여 브라우저에 전달
        return new Student(
                1,"홍길동","멀티미디어학과"
        );
    }

    private final List<Student> studentList = new ArrayList<>();

    public StudentController(){
        studentList.add(new Student(1,"홍길동", "멀티미디어학과"));
        studentList.add(new Student(2,"김철수", "인공지능학과"));
        studentList.add(new Student(3,"이영희", "소프트웨어학과"));
    }

    @GetMapping("/students")
    public List<Student> getStudentList(){
        return studentList;
    }

    @GetMapping("/students/new")
    // "http://localhost:8080/students/new?id=10&name=김청운&department=디자인학과"의 형식
    // 기존 학생 목록에서 같은 id가 있는지 확인
    public Object newStudent(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String department
    ){
        for(Student student : getStudentList()){
            if(student.getID() == id){
                return "이미 존재하는 학생번호입니다.";
            }
        }
        Student newStudent = new Student(id, name, department);

        studentList.add(newStudent);

        return newStudent;
    }

    @GetMapping("/students/search/{id}")
    // "http://localhost:8080/students/search/1"의 형식
    // List 반환이 아니기 때문에 Object로 자료형을 지정
    public Object studentByID(@PathVariable int id){
        if(id < 1 || id > getStudentList().size()){
            return "해당 번호의 학생을 찾을 수 없습니다.";
        }
        return getStudentList().get(id - 1);
    }

    @GetMapping("/students/search/{id}/name")
    public Object studentName(@PathVariable int id){
        if(id < 1 || id > getStudentList().size()){
            return "해당 번호의 학생을 찾을 수 없습니다.";
        }
        return getStudentList().get(id - 1).getName();
    }

    @GetMapping("/students/search")
    // "http://localhost:8080/students/search?department=멀티미디어학과"의 형식
    public List<Student> searchByDepartment(@RequestParam String department){
        return getStudentList().stream().filter(s->s.getDepartment().equals(department)).toList();
    }
}
