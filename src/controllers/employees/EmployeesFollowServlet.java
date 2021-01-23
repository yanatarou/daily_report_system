package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;
/**
 * Servlet implementation class EmployeesFollowServlet
 */
@WebServlet("/employees/follow")
public class EmployeesFollowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    public EmployeesFollowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Follow f = new Follow();

        f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));



        List<Follow> follows = f.createNamedQuery("getAllFollows", follow.class).getResultList();
        response.getWriter().append(Integer.valueOf(folllows.size()).toString());


  }

}
