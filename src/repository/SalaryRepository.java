package repository;

import model.Office;
import model.Salary;
import utils.DbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public final class SalaryRepository {
    private static SalaryRepository instance = null;

    public Salary findSalaryById(Integer id) {
        String sql = "SELECT * FROM salary WHERE salary_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return new Salary (
                        set.getInt("salary_id"),
                        set.getInt("gross_value"),
                        set.getInt("net_value"),
                        set.getDouble("annual_raise")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static SalaryRepository getInstance() {
        if (instance == null)
            instance = new SalaryRepository();
        return instance;
    }

}
