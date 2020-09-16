package repository;

import model.Company;
import model.Location;
import utils.DbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public final class LocationRepository {
    private static LocationRepository instance = null;

    public void addLocation(Location location) {
        String sql = "INSERT INTO location VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, location.getAddressLineA());
            statement.setString(2, location.getAddressLineB());
            statement.setString(3,location.getCity());
            statement.setString(4,location.getCountry());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public HashSet<Location> findLocations() {
        String sql = "SELECT * FROM location";
        HashSet<Location> locations = new HashSet<>();

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Location location = new Location(
                        set.getInt("location_id"),
                        set.getString("address_line_A"),
                        set.getString("address_line_B"),
                        set.getString("city"),
                        set.getString("country")
                );
                locations.add(location);
            }

            return locations;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Location findLocationById(Integer id) {
        String sql = "SELECT * FROM location WHERE location_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return new Location (
                        set.getInt("location_id"),
                        set.getString("address_line_A"),
                        set.getString("address_line_B"),
                        set.getString("city"),
                        set.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteLocationsByAddressLine(String addressLine) {
        String sql = "DELETE FROM location WHERE address_line_A = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, addressLine);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateLocation(Location location) {
        String sql = "UPDATE location SET address_line_A = ?, address_line_B = ?, city = ?, country = ? WHERE location_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, location.getAddressLineA());
            statement.setString(2, location.getAddressLineB());
            statement.setString(3, location.getCity());
            statement.setString(4,location.getCountry());
            statement.setString(5,location.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static LocationRepository getInstance() {
        if (instance == null)
            instance = new LocationRepository();
        return instance;
    }

}
