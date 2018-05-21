package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.entity.Selecter;
import com.util.DBConnection;

public interface SelectDao {

    public List listSelecterBySeq(int seq, int oid);
    public int addSelecter(int oid, int seq, String content, int seq_selecter);
    public int updateSelecterSeq(int oid, int qseq);
    public int deleteSelecter(int seq, int oid);
    public int updateSseq(int seq, int oid);

}
