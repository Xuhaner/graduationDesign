package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAOFactory;
import com.dao.OptionDao;
import com.dao.QuestionDao;
import com.entity.Option;

public class OptionManage extends HttpServlet {
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
        if("AddOption".equals(op)){
            int oid = Integer.valueOf(request.getParameter("oid"));
            int qid = Integer.valueOf(request.getParameter("qid"));
            int otype = Integer.valueOf(request.getParameter("otype"));
            String ohead = request.getParameter("ohead");
            String obody = request.getParameter("obody");
            String oresult = request.getParameter("oresult");
            String oimg = request.getParameter("oimg");
            String oorder = request.getParameter("oorder");

            Option option=new Option();

            option.setOId(oid);
            option.setQId(qid);
            option.setOType(otype);
            option.setOHead(ohead);
            option.setOBody(obody);
            option.setOResult(oresult);
            option.setOImg(oimg);
            option.setOOrder(oorder);
            String [] obodys=obody.split("&\\$\\$&");
            String spliter="";
            for(int i=1;i<obodys.length;i++)
                if(i==obodys.length-1)
                    spliter=spliter+"null&null";
                else
                    spliter=spliter+"null&";

            OptionDao dao=DAOFactory.getOptionDAO();

            try{
                boolean ret=dao.addOption(option);
                if(ret==true)
                    response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=true&qid="+qid);
                else{
                    response.sendRedirect("../admin/OpResult.jsp?op=Question&ret=false");
                }
            }catch(Exception e){

            }
        }else if("EditQuestion".equals(op)){

        }
    }
}
