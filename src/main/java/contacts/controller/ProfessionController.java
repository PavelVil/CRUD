package contacts.controller;

import contacts.dao.ProfessionDao;
import contacts.model.Profession;
import contacts.model.Zip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pavel on 28.06.2017.
 */
public class ProfessionController extends HttpServlet {

    private ProfessionDao professionDao = new ProfessionDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String url = "/pages/";
        req.setAttribute("professionList",professionDao.getAll());
        if (action.equals("professionList")){
            professionList(req,resp,url);
        } if (action.equals("edit")){
            edit(req,resp,url);
        } if (action.equals("addProf")){
            add(req,resp);
        } if (action.equals("delete")){
            delete(req,resp);
        }
    }

    private void professionList(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException{
        url+="/professions.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException{
        int profId = Integer.parseInt(req.getParameter("profId"));
        req.setAttribute("profession",professionDao.getById(profId));
        url+="professions.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String profId = req.getParameter("profId");
        String title = req.getParameter("profession");
        Profession profession = new Profession(title);
        if (profId==null){
            professionDao.add(profession);
        } else {
            profession.setId(Integer.parseInt(profId));
            professionDao.edit(profession);
        }
        resp.sendRedirect("/professions?action=professionList");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int profId = Integer.parseInt(req.getParameter("profId"));
        professionDao.delete(professionDao.getById(profId));
        resp.sendRedirect("/professions?action=professionList");
    }
}
