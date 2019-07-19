package com.liyan.filter;

import com.liyan.util.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.InputStream;

/*
* 最开始是由spring提出的，整合hibernate框架中使用opensessionInView*/

@WebFilter("/*")
public class OpsessionInView implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SqlSession sqlSession= MybatisUtil.getSession();
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSession();
        }
    }
    @Override
    public void destroy() {
    }
}
