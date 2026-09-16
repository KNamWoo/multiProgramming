package com.example.demo;

public class Student { // 학생 1인의 정보 저장 데이터 구조
        private int id; // 학생 번호
        private String name; // 학생 이름
        private String department; // 학생 학과 정보

    public Student(int id, String name, String department){
        // Constructor(생성자), 새 Student 객체를 만들 때 처음 값을 전달받는 역할

        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDepartment(){
        return department;
    }
}
