package com.liyan.service;

import com.liyan.pojo.PageInfo;
import com.liyan.pojo.Student;

import java.util.List;

public interface StudentService {
    PageInfo showPage(String sname,String tname,String pageSizestr,String pageNumberstr);
    List<Student> findAll();
}
