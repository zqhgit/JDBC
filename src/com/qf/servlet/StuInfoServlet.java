package com.qf.servlet;

import com.qf.dao.StudentInfoDao;
import com.qf.dao.StudentInfoDaoImpl;
import com.qf.domain.Studentinfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/stuInfoServlet")
public class StuInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if ("list".equals(method)) {
            //查询学生信息列表
            list(req,resp);
        } else if ("toAdd".equals(method)){
            //跳转到新增页面
            toAdd(req,resp);

        } else if ("add".equals(method)){
            //新增一条记录
            try {
                add(req,resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if ("toEdit".equals(method)){
            toEdit(req,resp);
        } else if ("edit".equals(method)) {
            try {
                edit(req,resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if ("remove".equals(method)){
            remove(req,resp);
        }

    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] uids = req.getParameterValues("uid");
        System.out.println("uids:"+uids);
        StudentInfoDao studentInfoDao = new StudentInfoDaoImpl();
        int delete = studentInfoDao.delete(uids);
        if (delete>0){
            list(req,resp);
        } else{
            resp.sendRedirect(req.getContextPath()+"/studentinfo/error.jsp");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        System.out.println("sex:"+sex);
        String age = request.getParameter("age");
        String ys = request.getParameter("ys");
        String className = request.getParameter("className");
        String hireDate = request.getParameter("hireDate");

        String tel = request.getParameter("tel");
        String jg = request.getParameter("jg");
        String flag = request.getParameter("flag");
        //2,封装对象
        Studentinfo stuInfo = new Studentinfo();
        stuInfo.setId(Integer.parseInt(id));
        stuInfo.setName(name);
        stuInfo.setSex(sex);
        stuInfo.setAge(Integer.parseInt(age));//转化一下
        stuInfo.setYs(ys);
        stuInfo.setClassName(className);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiredate = sdf.parse(hireDate);
        stuInfo.setHireDate(hiredate);
        stuInfo.setTel(tel);
        stuInfo.setJg(jg);
        stuInfo.setFlag(flag);


        StudentInfoDao infoDao = new StudentInfoDaoImpl();
        int result = infoDao.update(stuInfo);
        if(result>0){
            list(request,response);
        }else{
            response.sendRedirect(request.getContextPath()+"/studentinfo/error.jsp");
        }
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        StudentInfoDao studentInfoDao = new StudentInfoDaoImpl();
        Studentinfo stuInfo = studentInfoDao.getStuById(Integer.parseInt(id));
        req.setAttribute("stuInfo",stuInfo);
        req.getRequestDispatcher("/studentinfo/stuInfoEdit.jsp").forward(req,resp);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, ServletException {
//        request.setCharacterEncoding("utf-8");
        //获取参数
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String ys = request.getParameter("ys");
        String className = request.getParameter("className");

        String hireDate = request.getParameter("hireDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiredate = sdf.parse(hireDate);

        String tel = request.getParameter("tel");
        String jg = request.getParameter("jg");
        //封装对象
        Studentinfo stuInfo = new Studentinfo();
        stuInfo.setName(name);
        stuInfo.setSex(sex);
        stuInfo.setAge(Integer.parseInt(age));//转化一下
        stuInfo.setYs(ys);
        stuInfo.setClassName(className);
        stuInfo.setHireDate(hiredate);
        stuInfo.setTel(tel);
        stuInfo.setJg(jg);

        StudentInfoDao studentInfoDao = new StudentInfoDaoImpl();
        int result = studentInfoDao.add(stuInfo);

        if (result>0){
                list(request,response);
        } else{
            response.sendRedirect(request.getContextPath()+"/studentinfo/error.jsp");
        }

    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/studentinfo/stuInfoAdd.jsp").forward(req,resp);
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全部学生信息列表
        StudentInfoDao studentInfoDao = new StudentInfoDaoImpl();
        List<Studentinfo> list = studentInfoDao.getList();

        req.setAttribute("stuList",list);

        //转发到学生信息列表页面
        req.getRequestDispatcher("/studentinfo/stuInfoList.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
