package repository;

import model.Name;
import utils.DbHandler;
import utils.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class NameRepository {
    private static NameRepository instance = null;


    public void addName(Name name){
        String sql = "INSERT INTO names VALUES (?, ?)";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, name.getFirstName());
            statement.setString(2, name.getLastName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Name findNameById(Integer id){
        String sql = "SELECT * FROM names WHERE name_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                String u = set.getString("first_name");
                String p = set.getString("last_name");

                return new Name(id, u, p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static NameRepository getInstance(){
        if (instance == null)
            instance = new NameRepository();
        return instance;
    }

}
