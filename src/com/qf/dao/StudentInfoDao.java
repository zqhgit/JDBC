package com.qf.dao;

import com.qf.domain.Studentinfo;

import java.util.List;

public interface StudentInfoDao {

    /**
     * 获取记录列表
     * @return
     */
    List<Studentinfo> getList();

    /**
     * 新增一条记录
     * @param studentinfo
     * @return
     */
    int add(Studentinfo studentinfo);

    /**
     * 根据id查询一条记录
     * @param id
     * @return
     */
    Studentinfo getStuById(int id);

    /**
     * 修改一条记录
     * @param studentinfo
     * @return
     */
    int update(Studentinfo studentinfo);

    /**
     * 删除记录
     * @param ids
     * @return
     */
    int delete(String[] ids);
}
