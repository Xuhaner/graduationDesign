package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAOFactory;
import com.dao.QuestionDao;
import com.entity.Question;
import com.dao.OptionDao;
import com.entity.Option;

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
        if("AddQuestion".equals(op)){
            int qid = Integer.valueOf(request.getParameter("qid"));
            String QName = request.getParameter("QName");
            String QDesc = request.getParameter("QDesc");
            String QAuthor =request.getParameter("QAuthor");
            boolean QIsRepeat = Boolean.getBoolean("QIsRepeat");
            //String QCreateDate =request.getParameter("QCreateDate");
            String QPassword = request.getParameter("QPassword");
            boolean QIsOpen = Boolean.getBoolean("QIsOpen");
            //String QExpireDate = request.getParameter("QExpireDate");
            String team = request.getParameter("team");

            Question question=new Question();

            question.setQId(qid);
            question.setQName(QName);
            question.setQDesc(QDesc);
            question.setQAuthor(QAuthor);
            question.setQIsRepeat(QIsRepeat);
            question.setQPassword(QPassword);
            question.setQIsOpen(QIsOpen);
            //question.setQExpireDate(QExpireDate);
            question.setTeam(team);


            QuestionDao dao = DAOFactory.getQuestionDAO();
            try{
                boolean ret= dao.addQuestion(question);
                if(ret==true)
                    response.sendRedirect("../OpResult.jsp?op=Question&ret=true&qid="+ qid);
                else{
                    response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=false");
                }
            }catch(Exception e){

            }
        }else if("EditQuestion".equals(op)){
            QuestionDao questiondao=DAOFactory.getQuestionDAO();
            String sid=request.getParameter("Survey_id");
            synchronized(mutex2){
                Question question = questiondao.findQuestion(Integer.valueOf(request.getParameter("qid")));
                question.setQName(request.getParameter("Survey_name"));
                question.setQAuthor(request.getParameter("Survey_author"));
                question.setQDesc(request.getParameter("Survey_description"));

//                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//
//                try {
//                    question.setSExpireDate(sdf.parse(request.getParameter("Survey_ExpireDate")));
//                } catch (ParseException e) {
//                    out.println("wrong DATE format.please check it!");
//                }
                question.setQIsRepeat(Boolean.valueOf(request.getParameter("Survey_ipRepeat")));
                question.setQIsOpen(Boolean.valueOf(request.getParameter("Survey_isOpen")));
                try{
                    boolean ret=questiondao.updateQuestion(question);
                    //System.out.println(ret);
                    if(ret==true)
                        response.sendRedirect("../admin/SurveyEdit.jsp?sid="+sid+"&words="+URLEncoder.encode("操作成功！", "UTF-8"));
                    else
                        response.sendRedirect("../admin/OpResult.jsp?op=SurveyEdit&ret=false");
                }catch (Exception e) {

                }
            }
        }else if("DeleteQuestion".equals(op)){
            int qId= Integer.valueOf(request.getParameter("qid")).intValue();

            QuestionDao questionDao = DAOFactory.getQuestionDAO();
            try {
                boolean ret1= questionDao.deleteQuestion(qId);
                OptionDao optionDao = DAOFactory.getOptionDAO();

                if(ret1==true)
                    ret1 = optionDao.deleteOption(qId);
//                if(ret1==true){
//                    TextDAO tdao=DAOFactory.getTextDAO();
//                    ret1=tdao.delText(surveyId);
//                }
//                if(ret1==true){
//                    AnswersheetDAO adao=DAOFactory.getAnswersheetDAO();
//                    ret1=adao.delAnswersheets(surveyId);
//                }
                if(ret1==true)
                    response.sendRedirect("../admin/SurveyAdmin.jsp");
                else
                    response.sendRedirect("../admin/OpResult.jsp?op=SurveyDel&ret=false");
            }catch (Exception e){

            }


        }
    }
}
