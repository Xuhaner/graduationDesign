package com.dao;

import com.entity.ObjectBean;

import java.util.List;

public interface ObjectBeanDao {
    public int intsertObjectBean(ObjectBean bean) throws Exception;
    public int updateObjectBean(ObjectBean bean);
    public boolean delObjectBean(int oid) throws Exception;
    public ObjectBean getObjectBean(int oid);
    public List ListObjectBean();
    public List ListTeamObjectBean(String teamname);
    public ObjectBean findObjectBeanByID(int ID);
    public ObjectBean findPublishedObjectBeanByID(int ID);
    public int getCount(int oid);
}
