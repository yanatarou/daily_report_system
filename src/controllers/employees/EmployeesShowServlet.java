package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesShowServlet
 */
@WebServlet("/employees/show")
public class EmployeesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        /**
         * パラメーター（id）をゲットしてString型だからInt形に変換してfindで一つずつ抽出していく
         */

        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");


        List<Follow> follows = em.createNamedQuery("getMyFollow", Follow.class)
                                  .setParameter("employee", login_employee)
                                  .setParameter("follow_emp", e)
                                  .getResultList();

           boolean followed = true;
           if(follows.size() > 0) {
               followed = false;
           }


        em.close();

        request.setAttribute("employee", e);
        request.setAttribute("followed", followed);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
        rd.forward(request, response);
    }

}