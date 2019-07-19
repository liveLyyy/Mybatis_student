package com.liyan.mapper;

import com.liyan.pojo.PageInfo;
import com.liyan.pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> findPage(PageInfo pageInfo);
    Long findCount(PageInfo pageInfo);
    List<Student> findAll();
}
