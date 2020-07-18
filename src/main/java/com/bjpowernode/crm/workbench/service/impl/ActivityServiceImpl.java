package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import javafx.scene.control.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LNL
 * @date 2020/7/13 11:27
 */
public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);


    public boolean save(Activity activity) {
        boolean flag = true;

        int count = activityDao.save(activity);
        if(count != 1){
            flag = false;
        }
        return flag;
    }

    public PaginationVO<Activity> pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> dataList = activityDao.getActivityByCondition(map);
        //将total和dataList封装到vo中
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;
    }

    public boolean delete(String[] ids) {

        boolean flag = true;

        //查询出需要删除的备注的数量
        int count1 = activityRemarkDao.getCountByAids(ids);

        //删除备注，返回收到影响的条数(实际删除的数量)
        int count2 = activityRemarkDao.deleteByAids(ids);
        if(count1 != count2){
            flag = false;
        }

        //删除市场活动
        int count3 = activityDao.delete(ids);
        if(count3 != ids.length){
            flag = false;
        }
        return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {

        Map<String,Object> map = new HashMap<String, Object>();

        //查询一个uList
        List<User> userList = userDao.getUserList();

        //根据id查询activity
        Activity activity = activityDao.getById(id);

        map.put("userList",userList);
        map.put("activity",activity);


        return map;
    }

    public boolean update(Activity activity) {


        boolean flag = true;

        int count = activityDao.update(activity);
        if(count != 1){
            flag = false;
        }
        return flag;
    }

    public Activity detail(String id) {
        Activity activity = activityDao.detail(id);
        return activity;
    }

}
