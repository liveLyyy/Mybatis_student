package com.liyan.service.Impl;

import com.liyan.pojo.PageInfo;
import com.liyan.mapper.StudentMapper;
import com.liyan.mapper.TeacherMapper;
import com.liyan.pojo.Student;
import com.liyan.service.StudentService;
import com.liyan.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public PageInfo showPage(String sname, String tname, String pageSizestr, String pageNumberstr) {
        int pageSize = 2;
        int pageNumber = 1;
        if (pageSizestr != null && !pageSizestr.equals("")) {
            pageSize = Integer.parseInt(pageSizestr);
        }
        if (pageNumberstr != null && !pageNumberstr.equals("")) {
            pageNumber = Integer.parseInt(pageNumberstr);
        }
        SqlSession sqlSession = MybatisUtil.getSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageStart(pageSize * (pageNumber - 1));
        pageInfo.setSname(sname);
        pageInfo.setTname(tname);
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        List<Student> list = studentMapper.findPage(pageInfo);
        for (Student student:list){
            student.setTeacher(teacherMapper.findById(student.getTid()));
        }
        pageInfo.setList(list);
        long count=studentMapper.findCount(pageInfo);
        pageInfo.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
        return pageInfo;
    }

    @Override
    public List<Student> findAll() {
        SqlSession sqlSession = MybatisUtil.getSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list=studentMapper.findAll();
        return list;
    }
}
