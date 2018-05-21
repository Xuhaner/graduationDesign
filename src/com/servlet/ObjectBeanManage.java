package com.servlet;

import com.dao.DAOFactory;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.util.*;
import com.entity.*;
import com.dao.*;

public class ObjectBeanManage {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if("addOb".equals(op)){
            ObjectBeanDao obs = DAOFactory.getObjectBeanDAO();
            FileUtil fu=new FileUtil();
            ObjectBean ob = new ObjectBean();
            String title = request.getParameter("title");
            String discribe = request.getParameter("discribe");
            String anonymousFlag = request.getParameter("anonymousFlag");
            String remark = request.getParameter("remark");
            String team = request.getParameter("team");
            String author = request.getParameter("createUser");

            ob.setTitle(title);
            ob.setDiscribe(discribe);
            ob.setAnonymousFlag(anonymousFlag);
            ob.setRemark(remark);
            ob.setTeam(team);
            ob.setAuthor(author);

            try{
                int id = obs.intsertObjectBean(ob);
                String pathinfo=request.getRealPath("");
                String from=pathinfo+"/diaocha.txt";
                String to=pathinfo+"/diaocha_"+id+".jsp";
                String wj="<% String id=\""+id+"\";"+"%"+">";
                fu.copyFile(from,null,to,"UTF-8",wj);
                System.out.println(pathinfo);
                if(id >0){
                    response.sendRedirect("wjList.jsp");
                    //request.getRequestDispatcher("/wjList.jsp").forward(request, response);
                }else{
                    String errmsg="创建失败";
                    response.sendRedirect("/login.jsp?errmsg="+errmsg);
                    //request.getRequestDispatcher("/wjNew.jsp?errmsg="+errmsg).forward(request, response);
                }
            }catch (Exception e){

            }
        }else if("updateOb".equals(op)){
            ObjectBeanDao obs = DAOFactory.getObjectBeanDAO();
            ObjectBean bean = new ObjectBean();
            String getid = request.getParameter("oid");
            int oid = Integer.parseInt(getid.trim());
            String title = request.getParameter("title");
            String discribe = request.getParameter("discribe");
            String anonymousFlag = request.getParameter("anonymousFlag");
            String remark = request.getParameter("remark");
            String team = request.getParameter("team");

            bean.setOid(oid);
            bean.setTitle(title);
            bean.setDiscribe(discribe);
            bean.setAnonymousFlag(anonymousFlag);
            bean.setRemark(remark);
            bean.setTeam(team);

            int i = obs.updateObjectBean(bean);

            if(i > 0){
                //response.sendRedirect("/wjList.jsp");
                request.getRequestDispatcher("/wjList.jsp").forward(request, response);
            }else{
                //response.sendRedirect("wjUpdate.jsp");
                request.getRequestDispatcher("/wjUpdate.jsp").forward(request, response);
            }
        }else if("delOb".equals(op)){
            String getid = request.getParameter("oid");
            int oid = Integer.parseInt(getid.trim());
            ObjectBeanDao obDao = DAOFactory.getObjectBeanDAO();
            try{
                boolean flag = obDao.delObjectBean(oid);
                request.getRequestDispatcher("/wjList.jsp").forward(request, response);
                //response.sendRedirect("wjList.jsp");
            }catch (Exception e){

            }

        }
    }
}
