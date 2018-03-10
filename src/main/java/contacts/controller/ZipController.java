package contacts.controller;

import contacts.dao.*;
import contacts.model.Zip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pavel on 28.06.2017.
 */
public class ZipController extends HttpServlet {

    private AbstractDao<Zip> zipDao = new ZipDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String url = "/pages/";
        req.setAttribute("zipList",zipDao.getAll());
        if (action.equals("zipList")){
            add(req,resp,url);
        } if(action.equals("edit")){
           edit(req,resp,url);
        } if(action.equals("addZip")){
            addZip(req,resp);
        } if (action.equals("delete")){
            delete(req,resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException{
        url+="zips.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

    private void addZip(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String zipId = req.getParameter("zipId");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        Zip zip = new Zip(city,state);
        if (zipId==null) {
            zipDao.create(new Zip(city, state));
        } else {
            zip.setId(Integer.parseInt(zipId));
            zipDao.edit(zip);
        }
        resp.sendRedirect("/zips?action=zipList");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException{
        int zipId = Integer.parseInt(req.getParameter("zipId"));
        req.setAttribute("zip",zipDao.getById(zipId));
        url+="zips.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int zipId = Integer.parseInt(req.getParameter("zipId"));
        zipDao.delete(zipDao.getById(zipId));
        resp.sendRedirect("/zips?action=zipList");
    }
}
