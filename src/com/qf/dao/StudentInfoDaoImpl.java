package com.qf.dao;

import com.qf.domain.Studentinfo;
import com.qf.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentInfoDaoImpl implements StudentInfoDao {

    /**
     * 获取记录列表
     * @return
     */
    @Override
    public List<Studentinfo> getList() {
        List<Studentinfo> stuInfoList = new ArrayList<>();

        String sql = "select * from studentinfo where flag = '0'";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //获取数据库连接
            conn = DBUtils.getConnection();
            //获取字符集对象
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            //处理结果集
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String ys = rs.getString("ys");
                String aClass = rs.getString("class");

                Date date = rs.getDate("hiredate");
                Date hiredate = new java.util.Date(date.getTime());

                String tel = rs.getString("tel");
                String jg = rs.getString("jg");
                String flag = rs.getString("flag");

                Studentinfo stu = new Studentinfo();
                stu.setId(id);
                stu.setName(name);
                stu.setSex(sex);
                stu.setAge(age);
                stu.setYs(ys);
                stu.setClassName(aClass);
                stu.setHireDate(hiredate);
                stu.setTel(tel);
                stu.setJg(jg);
                stu.setFlag(flag);

                //将对象存入到list集合中
                stuInfoList.add(stu);
            }
            return stuInfoList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(rs,pstmt,conn);
        }
        return null;
    }

    /**
     * 新增一条记录
     * @param studentInfo
     * @return
     */
    @Override
    public int add(Studentinfo studentInfo) {

        String sql = "insert into studentinfo(name,sex,age,ys,class,hiredate,tel,jg,flag) values(?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,studentInfo.getName());
            pstmt.setString(2,studentInfo.getSex());
            pstmt.setInt(3,studentInfo.getAge());
            pstmt.setString(4,studentInfo.getYs());
            pstmt.setString(5,studentInfo.getClassName());
            Date date = studentInfo.getHireDate();
            pstmt.setDate(6,new java.sql.Date(date.getTime()));
            pstmt.setString(7,studentInfo.getTel());
            pstmt.setString(8,studentInfo.getJg());
            pstmt.setString(9,"0");//默认状态

            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
        return -1;
    }

    /**
     * 根据id查询一条数据
     * @param id
     * @return
     */
    @Override
    public Studentinfo getStuById(int id) {

        String sql = "select * from studentinfo where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);

            rs = pstmt.executeQuery();

            while (rs.next()){
                int idColumn = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String ys = rs.getString("ys");
                String aClass = rs.getString("class");

                Date date = rs.getDate("hiredate");
                Date hiredate = new java.util.Date(date.getTime());

                String tel = rs.getString("tel");
                String jg = rs.getString("jg");
                String flag = rs.getString("flag");

                Studentinfo stu = new Studentinfo();

                stu.setId(id);
                stu.setName(name);
                stu.setSex(sex);
                stu.setAge(age);
                stu.setYs(ys);
                stu.setClassName(aClass);
                stu.setHireDate(hiredate);
                stu.setTel(tel);
                stu.setJg(jg);
                stu.setFlag(flag);
                return stu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            DBUtils.closeConnection(rs,pstmt,conn);
        }
        return null;
    }

    /**
     * 修改一条记录
     * @param studentInfo
     * @return
     */
    @Override
    public int update(Studentinfo studentInfo) {
        String sql = "update studentinfo set name=?,sex=?,age=?,ys=?,class=?,hiredate=?,tel=?,jg=?,flag=? where id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);

            //设置参数
            pstmt.setString(1,studentInfo.getName());
            pstmt.setString(2,studentInfo.getSex());
            pstmt.setInt(3,studentInfo.getAge());
            pstmt.setString(4,studentInfo.getYs());
            pstmt.setString(5,studentInfo.getClassName());
            Date date = studentInfo.getHireDate();
            pstmt.setDate(6,new java.sql.Date(date.getTime()));
            pstmt.setString(7,studentInfo.getTel());
            pstmt.setString(8,studentInfo.getJg());
            pstmt.setString(9,studentInfo.getFlag());
            pstmt.setInt(10,studentInfo.getId());

            int i = pstmt.executeUpdate();
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DBUtils.closeConnection(null,pstmt,conn);
        }
        return -1;
    }

    /**
     * 删除记录
     * @param ids
     * @return
     */
    @Override
    public int delete(String[] ids) {

        StringBuffer sb = new StringBuffer();
        String idStr = null;
        if (ids!=null){
            for (String id : ids){
                sb.append(id).append(",");
            }
            idStr = sb.toString().substring(0,sb.length()-1);
        }

        String sql = "update studentinfo set flag =? where id in("+idStr+")";
        Connection conn = null;
        PreparedStatement pstmt =null;
        try {
            //2,获取数据库连接
            conn = DBUtils.getConnection();
            //3,
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setString(1,"1");//设置逻辑删除状态
            //4,执行SQL
            int result = pstmt.executeUpdate();

            System.out.println(result);

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
        return 0;
    }
}
