/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.request;

import controller.iam.BaseRequiredAuthorizationController;
import dal.DepartmentDBContext;
import dal.RequestDBContext;
import dal.RoleDBContext;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Department;
import model.Request;
import model.iam.Role;
import model.iam.User;

/**
 *
 * @author sonnt
 */
@WebServlet(urlPatterns = "/request/review")
public class ReviewController extends BaseRequiredAuthorizationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDBContext rdb = new RequestDBContext();
        String strId = req.getParameter("id");
        int id = Integer.parseInt(strId);
        Request rq = rdb.get(id);
        
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("auth");

        rq.setReviewedby(u.getId());
        if ("1".equals(action)) {
            rq.setStatus(1);
        } else if ("2".equals(action)) {
            rq.setStatus(2);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        rq.setReviewedtime(timestamp);

        rdb = new RequestDBContext();
        rdb.update(rq);
        resp.sendRedirect(req.getContextPath() + "/request/list");
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        RequestDBContext rdb = new RequestDBContext();
        RoleDBContext roleDb = new RoleDBContext();
        DepartmentDBContext ddb = new DepartmentDBContext();
        UserDBContext udb = new UserDBContext();

        String ridString = req.getParameter("id");
        int rid = Integer.parseInt(ridString);
        Request rq = rdb.get(rid);
        User creator = udb.get(rq.getCreatedby());

        ArrayList<Role> creatorRoles = roleDb.getByUserId(creator.getId());
        Department creatorDept = ddb.get(creator.getId());

        roleDb = new RoleDBContext();
        User reviewer = (User) req.getSession().getAttribute("auth");
        ArrayList<Role> reviewerRoles = roleDb.getByUserId(reviewer.getId());

        req.setAttribute("request", rq);
        req.setAttribute("creator", creator);
        req.setAttribute("creatorRoles", creatorRoles);
        req.setAttribute("creatorDept", creatorDept);
        req.setAttribute("reviewerRoles", reviewerRoles);

        req.getRequestDispatcher("/view/request/review.jsp").forward(req, resp);
    }

}
