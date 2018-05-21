package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.Question;

public interface QuestionDao {

    public List<Question> litQuesByOid(int oid);
    public int addQues(int oid, String content, int qtype, int seq);
    public int updateQuesOrder(int oid, int seq);
    public Question getQuesBySeq(int seq, int oid);
    public int deleteQues(int seq, int oid);
    public int updateQseq(int seq, int oid);
    public int getQuesCount(int oid);

}