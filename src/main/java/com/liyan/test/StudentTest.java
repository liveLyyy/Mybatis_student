package com.liyan.test;


import com.liyan.pojo.Student;
import com.liyan.service.Impl.StudentServiceImpl;


import java.util.List;


public class StudentTest {
    public static void main(String[] args) {
        StudentServiceImpl studentService = new StudentServiceImpl();
        List<Student> list=studentService.findAll();
        System.out.println(list);
    }
}
