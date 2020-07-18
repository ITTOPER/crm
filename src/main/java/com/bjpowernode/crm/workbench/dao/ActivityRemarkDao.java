package com.bjpowernode.crm.workbench.dao;

/**
 * @author LNL
 * @date 2020/7/14 18:03
 */
public interface ActivityRemarkDao {
    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);
}
