/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.request;

import controller.iam.BaseRequiredAuthorizationController;
import dal.DepartmentDBContext;
import dal.RequestDBContext;
import dal.RoleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Request;
import model.iam.Role;
import model.iam.User;
import model.Department;

/**
 *
 * @author sonnt
 */
@WebServlet(urlPatterns = "/request/create")
public class CreateController extends BaseRequiredAuthorizationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        Request rq = new Request();
        User cu = (User) req.getSession().getAttribute("auth");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        rq.setCreatedby(cu.getId());
        rq.setCreatedtime(timestamp);
        rq.setFrom(Date.valueOf(req.getParameter("fromDate")));
        rq.setTo(Date.valueOf(req.getParameter("toDate")));
        rq.setReason(req.getParameter("reason"));
        rq.setStatus(0);

        RequestDBContext db = new RequestDBContext();
        db.insert(rq);
        req.getRequestDispatcher("/view/request/list.jsp").forward(req, resp);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        RoleDBContext rdb = new RoleDBContext();
        DepartmentDBContext ddb = new DepartmentDBContext();
        User cu = (User) req.getSession().getAttribute("auth");
        Department dpt = ddb.get(cu.getId());
        
        ArrayList<Role> roles = rdb.getByUserId(cu.getId());
        String dname = dpt.getName();
        
        req.setAttribute("dname", dname);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/view/request/create.jsp").forward(req, resp);
    }

}
