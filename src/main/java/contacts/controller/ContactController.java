package contacts.controller;

import contacts.dao.ContactsDao;
import contacts.dao.ProfessionDao;
import contacts.dao.ZipDao;
import contacts.model.Contact;
import contacts.model.Profession;
import contacts.model.Zip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by Pavel on 27.06.2017.
 */
public class ContactController extends HttpServlet {

    private ContactsDao contactsDao = new ContactsDao();
    private ZipDao zipDao = new ZipDao();
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
        req.setAttribute("contacts", contactsDao.getAll());
        req.setAttribute("zips",zipDao.getAll());
        req.setAttribute("professions",professionDao.getAll());
        if (action.equals("contactsList")) {
            contactsList(req,resp,url);
        } if (action.equals("aboutContact")){
            aboutContact(req,resp,url);
        } if(action.equals("edit")){
            edit(req,resp,url);
        } if(action.equals("add")){
           add(req,resp);
        } if(action.equals("delete")){
            delete(req,resp);
        } if(action.equals("search")){
           search(req,resp,url);
        }
    }

    private void contactsList(HttpServletRequest req, HttpServletResponse resp, String url)throws ServletException, IOException{
        url +="contacts.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    private void aboutContact(HttpServletRequest req, HttpServletResponse resp, String url)throws ServletException, IOException{
        int contactId = Integer.parseInt(req.getParameter("contactId"));
        Contact contact = contactsDao.getById(contactId);
        req.setAttribute("contact",contact);
        url+="aboutContact.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp, String url)throws ServletException, IOException{
        int contactId = Integer.parseInt(req.getParameter("contactId"));
        req.setAttribute("contact",contactsDao.getById(contactId));
        url+="/contacts.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
            String contactId = req.getParameter("contactId");
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String gender = req.getParameter("gender");
            String phone = req.getParameter("phone");
            Date date = Date.valueOf(req.getParameter("date"));
            Profession profession = professionDao.getByTitle(req.getParameter("prof"));
            Zip zip = zipDao.getByTitle(req.getParameter("city"));
            Contact contact = new Contact(firstName, lastName, email, gender, date, phone, zip, profession);
            if(contactId==null) {
                contactsDao.add(contact);
            } else {
                contact.setContactId(Integer.parseInt(contactId));
                contactsDao.edit(contact);
            }
        resp.sendRedirect("/contacts?action=contactsList");
        }

    private void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        int contactId = Integer.parseInt(req.getParameter("contactId"));
        contactsDao.delete(contactsDao.getById(contactId));
        resp.sendRedirect("/contacts?action=contactsList");
    }

    private void search(HttpServletRequest req, HttpServletResponse resp, String url)throws ServletException, IOException{
        String searchZone = req.getParameter("searchOn");
        if (req.getParameter("searching").equalsIgnoreCase("")){
            req.setAttribute("emptySearch","Пожалуйста, введите критерии для поиска");
            getServletContext().getRequestDispatcher("/contacts?action=contactsList").forward(req,resp);
        }
        if (searchZone!=null && searchZone.equals("name")){
            String name = req.getParameter("searching");
            req.setAttribute("contactsSearching",contactsDao.getByTitle(searchZone,name));
        } if (searchZone!=null && searchZone.equals("surname")){
            String surname = req.getParameter("searching");
            req.setAttribute("contactsSearching",contactsDao.getByTitle(searchZone,surname));
        }
        url+="searchingContacts.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }
}
