package com.liyan.servlet;

import com.liyan.pojo.PageInfo;
import com.liyan.service.Impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private StudentServiceImpl studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String sname = req.getParameter("sname");
        if (sname != null && sname.equals("")) {
            sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
        }
        String tname = req.getParameter("tname");
        if (tname != null && tname.equals("")) {
            tname = new String(tname.getBytes("iso-8859-1"), "utf-8");
        }
        String pageSize = req.getParameter("pageSize");
        String pageNumber = req.getParameter("pageNumber");
        PageInfo pageInfo = studentService.showPage(sname, tname, pageSize, pageNumber);
        req.setAttribute("pageinfo", pageInfo);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
