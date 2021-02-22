package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
        name = "getAllFollows",
        query = "SELECT f FROM Follow AS f ORDER BY f.id DESC"
    ),
    @NamedQuery(
        name = "getFollowsCount",
        query = "SELECT COUNT(f) FROM Follow AS f"
    ),
    @NamedQuery(
        name = "getMyAllFollows",
        query = "SELECT f FROM Follow AS f WHERE f.employee = :employee ORDER BY f.id DESC"
    ),
    @NamedQuery(
        name = "getMyFollow",
        query = "SELECT f FROM Follow AS f WHERE f.employee = :employee and f.follow_employee = :follow_emp"
    ),
    @NamedQuery(
        name = "getMyFollowsCount",
        query = "SELECT COUNT(f) FROM Follow AS f WHERE f.employee = :employee"
    )
})
@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "follow_id", nullable = false)
    private Employee follow_employee;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getFollow_employee() {
        return follow_employee;
    }

    public void setFollow_employee(Employee follow_employee) {
        this.follow_employee = follow_employee;
    }

}