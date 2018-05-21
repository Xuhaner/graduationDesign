package com.dao;

import com.entity.Answer;
import com.entity.Replay;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface RepalyDao {
    public Map<Integer,List<Map<Integer,Integer>>> getAllAnswer(int oid);
    public List<Answer> getAnswers(int oid, int qSeq);
    public int getQuesCount(Connection con, int oid);
    public int getSelCount(Connection con, int oid, int qSeq);
    public int getAnswerCount(int oid);
    public int getAnswerCount(Connection con, int oid,int qSeq);
    public int getAnswerCount(Connection con, int oid,int qSeq,int seSeq);
    public boolean save(Replay r, List<Answer> answers);
    public boolean delReplay(int oid) throws Exception;
    public boolean exit(int oid,String replayIp,String replayCode);

}
