package com.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dao.*;
import com.entity.*;
import com.util.*;

public class QuestionManage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mutex = "";
        String mutex1 = "";
        String mutex2 = "";
        String op=request.getParameter("op");
        if("newQuestion".equals(op)){
            String id=request.getParameter("oid");
            int oid = Integer.parseInt(id);
            String seq=request.getParameter("seq");
            int qseq = Integer.parseInt(seq);
            String content=request.getParameter("content");
            String selCount = request.getParameter("listCnt");
            int listCnt = Integer.parseInt(selCount);
            String type=request.getParameter("qtype");
            int qtype = Integer.parseInt(type);
            QuestionDao qs = DAOFactory.getQuestionDAO();
            SelectDao ss = DAOFactory.getSelectDAO();
            RepalyDao repalyDao = DAOFactory.getRepalyDAO();
            ObjectBeanDao obDao = DAOFactory.getObjectBeanDAO();
            int count = obDao.getCount(oid);
            //如果题目的最大顺序号小于所传进来的顺序号 则先修改顺序号再进行插入 否则直接插入
            if (count > qseq) {
                //修改问题表中题目的顺序
                int updateQuesOrder = qs.updateQuesOrder(oid, qseq);
                if (updateQuesOrder > 0) {
                    //修改选项表中题目的顺序
                    int updateSseq = ss.updateSelecterSeq(oid, qseq);
                    if (updateSseq > 0) {
                        //往题目表中插入题目
                        int insertQues = qs.addQues(oid,content, qtype, (qseq+1));
                        if (insertQues > 0) {
                            //插入选项数据
                            for (int i = 1; i <= listCnt; i++) {
                                String name = String.valueOf("txt" + i);
                                String value = request.getParameter(name);
                                ss.addSelecter(oid, (qseq+1), value, i);
                            }
                        }
                    }
                }
            } else {

                //往题目表中插入题目
                int addQues = qs.addQues(oid,content, qtype, (qseq+1));
                if (addQues > 0) {
                    //插入选项数据
                    for (int i = 1; i <= listCnt; i++) {
                        String name = String.valueOf("txt" + i);
                        String value = request.getParameter(name);
                        ss.addSelecter(oid, (qseq+1), value, i);
                    }
                }
            }
            response.sendRedirect("quesList.jsp?oid="+ oid);
        }else if("AddQuestion".equals(op)){
            String id = request.getParameter("oid");
            System.out.println("id="+id);
            int oid = Integer.parseInt(id);
            QuestionDao qs = DAOFactory.getQuestionDAO();
            SelectDao ss = DAOFactory.getSelectDAO();
            RepalyDao repalyDao = DAOFactory.getRepalyDAO();
            ObjectBeanDao obDao = DAOFactory.getObjectBeanDAO();
            int count = obDao.getCount(oid);
            int seq = 1;
            String content = request.getParameter("content");
            if(content!=null&&content.trim().length()>0){
                //content=new String(content.getBytes("iso8859-1"),"UTF-8");
            }
            String selCount = request.getParameter("listCnt");
            int listCnt = Integer.parseInt(selCount);
            String type=request.getParameter("qtype");
            int qtype = Integer.parseInt(type);
            if (count == 0) {
                //往题目表中插入题目
                int insertQues = qs.addQues(oid, content, qtype, seq);
                if (insertQues > 0) {
                    //插入选项数据
                    for (int i = 1; i <= listCnt; i++) {
                        String name = String.valueOf("txt" + i);
                        String value = request.getParameter(name);
                        //value=new String(value.getBytes("iso8859-1"),"UTF-8");
                        ss.addSelecter(oid, seq, value, i);
                    }
                }
            } else {
                //往题目表中插入题目
                int addQues = qs.addQues(oid, content, qtype, (count + 1));
                if (addQues > 0) {
                    //插入选项数据
                    for (int i = 1; i <= listCnt; i++) {
                        String name = String.valueOf("txt" + i);
                        String value = request.getParameter(name);
                        //value=new String(value.getBytes("iso8859-1"),"UTF-8");
                        ss.addSelecter(oid, (count + 1), value, i);
                    }
                }
            }
            request.getRequestDispatcher("/quesList.jsp?oid=" + oid).forward(request, response);
            //response.sendRedirect("quesList.jsp?oid=" + oid);
        }else if("updateQuestion".equals(op)){
            String id = request.getParameter("oid");
            System.out.println("id="+id);
            int oid = Integer.parseInt(id);
            String qseq = request.getParameter("seq");
            int seq = Integer.parseInt(qseq);
            QuestionDao qs = DAOFactory.getQuestionDAO();
            SelectDao ss = DAOFactory.getSelectDAO();
            RepalyDao repalyDao = DAOFactory.getRepalyDAO();
            ObjectBeanDao obDao = DAOFactory.getObjectBeanDAO();
            int delSel = ss.deleteSelecter(seq, oid);
            if (delSel > 0) {
                int deleteQues = qs.deleteQues(seq, oid);
                if (deleteQues > 0) {
                    String content = request.getParameter("content");
                    //content=new String(content.getBytes("iso8859-1"),"UTF-8");
                    int qtype = Integer.parseInt(request.getParameter("qtype"));
                    int addQues = qs.addQues(oid, content, qtype, seq);
                    System.out.println(addQues);
                    if (addQues > 0) {
                        //插入选项数据
                        int listCnt = Integer.parseInt(request
                                .getParameter("listCnt"));
                        System.out.println("listCnt="
                                + request.getParameter("listCnt"));
                        for (int i = 0; i < listCnt; i++) {
                            String name = String.valueOf("txt_" + i);
                            String value = request.getParameter(name);
                            //value=new String(value.getBytes("iso8859-1"),"UTF-8");
                            ss.addSelecter(oid, seq, value, i);
                        }
                        System.out.println("listCnt=" + listCnt);
                    }
                }
            }
            request.getRequestDispatcher("/quesList.jsp?oid=" + oid).forward(request, response);
            //response.sendRedirect("/quesList.jsp?oid=" + oid);
        }else if("deleteQuestion".equals(op)){
            String id = request.getParameter("oid");
            String qseq = request.getParameter("seq");
            int oid = Integer.parseInt(id);
            int seq = Integer.parseInt(qseq);
            MyTool tool = new MyTool();
            QuestionDao qs = DAOFactory.getQuestionDAO();
            SelectDao ss = DAOFactory.getSelectDAO();
            RepalyDao repalyDao = DAOFactory.getRepalyDAO();
            ObjectBeanDao obDao = DAOFactory.getObjectBeanDAO();

            ObjectBean ob = obDao.findObjectBeanByID(oid);
            int count = qs.getQuesCount(oid);
            int state = ob.getState();
            try{
                if (state == 1 || state == 2) {
                    //清空回复表中的数据
                    repalyDao.delReplay(oid);
                    //修改问卷状态为草稿
                    tool.UpdateCol("wj_object", "state", "0", "oid", id);
                }
            }catch (Exception e){

            }

            //如果题目顺序等于１的情况下执行以下代码
            if (seq == 1) {
                if(count==1){
                    int flags = ss.deleteSelecter(seq, oid);//删除选项
                    if (flags > 0) {
                        int flagq = qs.deleteQues(seq, oid);//删除问题
                        if (flagq > 0)	request.getRequestDispatcher("/quesList.jsp?oid=" + oid).forward(request, response);//response.sendRedirect("quesList.jsp?oid=" + oid);
                    }
                }else{
                    int flags = ss.deleteSelecter(seq, oid);//删除选项
                    if (flags > 0) {
                        int flagq = qs.deleteQues(seq, oid);//删除问题
                        if (flagq > 0) {
                            int updateQseq = qs.updateQseq(seq, oid);//修改问题顺序
                            if (updateQseq > 0) {
                                int updateSseq = ss.updateSseq(seq, oid);//修改选项顺序
                                if (updateSseq > 0)	request.getRequestDispatcher("/quesList.jsp?oid=" + oid).forward(request, response);//response.sendRedirect("quesList.jsp?oid="+oid);
                            }
                        }
                    }
                }
            }
            //如果题目顺序小于题目总数并且不等于１的情况下执行以下代码
            if (seq < count && seq != 1) {
                int flags = ss.deleteSelecter(seq, oid);
                if (flags > 0) {
                    int flagq = qs.deleteQues(seq, oid);
                    if (flagq > 0) {
                        int updateQseq = qs.updateQseq(seq, oid);
                        if (updateQseq > 0) {
                            int updateSseq = ss.updateSseq(seq, oid);
                            if (updateSseq > 0 && updateQseq > 0)
                                request.getRequestDispatcher("/quesList.jsp?oid=" + oid).forward(request, response);
                                //response.sendRedirect("quesList.jsp?oid=" + oid);

                        }
                    }
                }
            }
            //如果题目的顺序等于题目的总数的情况下执行以下代码
            if (seq == count) {
                int flags = ss.deleteSelecter(seq, oid);
                if (flags > 0) {
                    int flagq = qs.deleteQues(seq, oid);
                    if (flagq > 0)
                        request.getRequestDispatcher("/quesList.jsp?oid=" + oid).forward(request, response);
                        //response.sendRedirect("quesList.jsp?oid=" + oid);

                }
            }
        }
    }
}
