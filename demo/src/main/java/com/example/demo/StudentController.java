package com.example.demo;

// 브라우저의 Get 방식 요청을 특정 메서드와 연결하기 위함
import org.springframework.web.bind.annotation.*;
// 브라우저나 클라이언트의 PUT 방식 요청을 특정 메서드와 연결하기 위함
// 기존 데이터를 삭제하는 DELETE 요청을 특정 메서드와 연결하기 위함
// 클래스의 웹 요청 Controller 임을 Spring Boot에 전달
// 객체 여러 개를 List로 반환
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
// URL 주소에 포함된 값을 Java로 가져오는 기능


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
    public List<Student> students(){
        return studentList;
    }

    private List<Student> getStudentList(){
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
        for(Student student : studentList){
            if(student.getID() == id){
                return student;
            }
        }
        return "해당 번호의 학생을 찾을 수 없습니다.";
    }

    @GetMapping("/students/search/{id}/name")
    public Object studentName(@PathVariable int id){
        if(id < 1 || id > getStudentList().size()){
            return "해당 번호의 학생을 찾을 수 없습니다.";
        }
        return getStudentList().get(id - 1).getName();
    }

    @GetMapping("/students/search/name")
    public Object searchByName(@RequestParam String name){
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

    //수정하는 코드
    @PutMapping("/students/{id}")
    public Object updateStudent(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String department
    ){
        for(Student student : studentList){
            if(student.getID() == id){
                student.setName(name);
                student.setDepartment(department);

                return student;
            }
        }
        return "해당 학생이 없습니다.";
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id){
        for(Student student : studentList){
            if(student.getID() == id){
                studentList.remove(student);
                return "학생이 삭제되었습니다.";
            }
        }
        return "해당 학생이 없습니다";
    }

    @PostMapping("/students/new")
    public Object postStudent(
            // ?이후에 값을 쌍으로 id=10의 식으로 받으려면 RequestParam의 형식을 이용해야 함
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String department
    ){
        for(Student student : studentList){
            if(student.getID() == id){
                return "이미 존재하는 학생번호입니다.";
            }
        }
        Student newStudent = new Student(id, name, department);

        studentList.add(newStudent);

        return "학생을 추가했습니다.";
    }

    @GetMapping("/students/count")
    public int getStudentCount(){
        return studentList.size();
    }

    @GetMapping("/students/count/department")
    public int getDepartmentCount(@RequestParam String department){
        int count = 0;
        for(Student student : studentList){
            if(student.getDepartment().equals(department)){
                count++;
            }
        }
        return count;
    }

    @GetMapping("/students/search/grade/{name}")
    public Object getStudentGrade(
            @RequestParam String name,
            @RequestParam String department
    ){
        for(Student student: studentList){
            boolean nameMatch = name.isEmpty() || student.getName().equals(name);
            boolean departmentMatch = department.isEmpty() || student.getDepartment().equals(department);

            if(nameMatch && departmentMatch){
                return student;
            }
        }
        return "해당 학생이 없습니다";
    }
    //'/students/search/grade/' + encodeURIComponent(name) + '?department=' + encodeURIComponent(department)
}
