package repository;

import model.Department;
import model.Location;
import model.Office;
import utils.DbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public final class DepartmentRepository {
    private static DepartmentRepository instance = null;

    public void addDepartment(Department department) {
        String sql = "INSERT INTO department VALUES (?, ?)";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getOffice().getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public HashSet<Department> findDepartments() {
        String sql = "SELECT * FROM department";
        HashSet<Department> departments = new HashSet<>();

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Department department = new Department(
                        set.getInt("department_id"),
                        set.getString("department_name"),
                        OfficeRepository.getInstance().findLocationById(set.getInt("office_id"))
                );
                departments.add(department);
            }

            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Department findDepartmentById(Integer id) {
        String sql = "SELECT * FROM department WHERE department_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return new Department(
                        set.getInt("department_id"),
                        set.getString("department_name"),
                        OfficeRepository.getInstance().findLocationById(set.getInt("office_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteDepartmentsByName(String name) {
        String sql = "DELETE FROM department WHERE department_name = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateDepartments(Department department) {
        String sql = "UPDATE department SET department_name = ?, office_id=? WHERE department_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getOffice().getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static DepartmentRepository getInstance() {
        if (instance == null)
            instance = new DepartmentRepository();
        return instance;
    }
}
