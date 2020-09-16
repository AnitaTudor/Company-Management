package repository;

import model.Location;
import model.Office;
import utils.DbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public final class OfficeRepository {
    private static OfficeRepository instance = null;

    public void addOffice(Office office) {
        String sql = "INSERT INTO office VALUES (?, ?, ?)";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, office.getName());
            statement.setString(2, office.getLocation().getId().toString());
            statement.setString(3, office.getCompany().getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public HashSet<Office> findOffices() {
        String sql = "SELECT * FROM office";
        HashSet<Office> offices = new HashSet<>();

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Office office = new Office(
                        set.getInt("office_id"),
                        set.getString("office_name"),
                        LocationRepository.getInstance().findLocationById((set.getInt("location_id"))),
                        CompanyRepository.getInstance().findCompanyById(set.getInt("company_id"))
                );
                offices.add(office);
            }

            return offices;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Office findLocationById(Integer id) {
        String sql = "SELECT * FROM office WHERE office_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return new Office (
                        set.getInt("office_id"),
                        set.getString("office_name"),
                        LocationRepository.getInstance().findLocationById((set.getInt("location_id"))),
                        CompanyRepository.getInstance().findCompanyById(set.getInt("company_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteOfficeByName(String name) {
        String sql = "DELETE FROM office WHERE office_name = ?";

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

    public void updateOffice(Office office) {
        String sql = "UPDATE office SET office_name = ?, location_id = ?, company_id = ? WHERE office_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, office.getName());
            statement.setString(2, office.getLocation().getId().toString());
            statement.setString(3, office.getCompany().getId().toString());
            statement.setString(4, office.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static OfficeRepository getInstance() {
        if (instance == null)
            instance = new OfficeRepository();
        return instance;
    }

}
