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
 * Servlet implementation class EmployeeFollowDestroy
 */
@WebServlet("/employees/follow/destroy")
public class EmployeeFollowDestroy extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeFollowDestroy() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();


        String follow_id = request.getParameter("follow_id");

        Employee e = em.find(Employee.class, Integer.parseInt(follow_id));

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        List<Follow> f = em.createNamedQuery("getMyFollow", Follow.class)
                         .setParameter("employee", login_employee)
                         .setParameter("follow_emp", e)
                         .getResultList();




        em.getTransaction().begin();
        for(Follow data : f ){
            em.remove(data);
        }
        em.getTransaction().commit();
        em.close();


        response.sendRedirect(request.getContextPath() + "/employees/show?id=" + follow_id);

    }

}
