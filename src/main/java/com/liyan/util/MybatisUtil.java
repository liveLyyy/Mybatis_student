package com.liyan.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    //Factory实例化的过程是一个耗费性能的过程，保证有且只有一个Factory
    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> sessionThreadLocal = new ThreadLocal<>();
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSession() {
        SqlSession sqlSession = sessionThreadLocal.get();
        if (sqlSession == null) {
            sessionThreadLocal.set(sqlSessionFactory.openSession());
        }
        return sessionThreadLocal.get();
    }
    public static void closeSession() {
        SqlSession sqlSession = sessionThreadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
        }
        sessionThreadLocal.set(null);
    }
}
