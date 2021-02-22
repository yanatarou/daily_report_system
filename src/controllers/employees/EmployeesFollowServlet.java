package controllers.employees;

import java.io.IOException;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        /**
         * モデルのインスタンス化
         */
        Follow f = new Follow();
        /*
         * 不整合のチェック開始
         */
        em.getTransaction().begin();
        /**
         * follow.jspからの送られたパラメーターの取得
         */
        String follow_id = request.getParameter("follow_id");
        /*
         *JSPから受け取った値をキーとしてデータベースからレコードを取得する処理
         */
        Employee e = em.find(Employee.class, Integer.parseInt(follow_id));
        /**
         * Follow classに値をセット
         */
        f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
        f.setFollow_employee(e);
        /**
         * DBから一覧を取得
         */
        /*
         *  DBに保存
         */
        em.persist(f);
        em.getTransaction().commit();
        em.close();
        /**
         * 取得したLISTの値をリクエストスコープにセット
         */
        request.setAttribute("follows", e);
        response.sendRedirect(request.getContextPath() + "/employees/show?id=" + follow_id);
  }

}
