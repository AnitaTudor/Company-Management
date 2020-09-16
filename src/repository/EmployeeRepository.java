package repository;

import model.Employee;
import model.Office;
import utils.DbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public final class EmployeeRepository {
    private static EmployeeRepository instance = null;

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, employee.getName().getId().toString());
            statement.setString(2, employee.getSalary().getId().toString());
            statement.setString(3, employee.getBoss().getId().toString());
            statement.setString(4, employee.getDepartment().getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public HashSet<Employee> findEmployees() {
        String sql = "SELECT * FROM employee";
        HashSet<Employee> employees = new HashSet<>();

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Employee employee = new Employee(
                        set.getInt("employee_id"),
                        NameRepository.getInstance().findNameById(set.getInt("name_id")),
                        SalaryRepository.getInstance().findSalaryById(set.getInt("salary_id")),
                        NameRepository.getInstance().findNameById(set.getInt("boss_name_id")),
                        DepartmentRepository.getInstance().findDepartmentById(set.getInt("department_id"))
                );
                employees.add(employee);
            }

            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employee findEmployeeById(Integer id) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return new Employee(
                        set.getInt("employee_id"),
                        NameRepository.getInstance().findNameById(set.getInt("name_id")),
                        SalaryRepository.getInstance().findSalaryById(set.getInt("salary_id")),
                        NameRepository.getInstance().findNameById(set.getInt("boss_name_id")),
                        DepartmentRepository.getInstance().findDepartmentById(set.getInt("department_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteEmployeeById(Integer id) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name_id = ?, salary_id = ?, boss_name_id = ?, department_id = ? WHERE employee_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, employee.getName().getId().toString());
            statement.setString(2, employee.getSalary().getId().toString());
            statement.setString(3, employee.getBoss().getId().toString());
            statement.setString(4,employee.getDepartment().getId().toString());
            statement.setString(5, employee.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static EmployeeRepository getInstance() {
        if (instance == null)
            instance = new EmployeeRepository();
        return instance;
    }

}
