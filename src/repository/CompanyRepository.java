package repository;

import model.Company;
import model.Name;
import utils.DbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public final class CompanyRepository {
    private static CompanyRepository instance = null;

    public void addCompany(Company company) {
        String sql = "INSERT INTO company VALUES (?, ?)";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getMarketCap().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public HashSet<Company> findCompanies() {
        String sql = "SELECT * FROM company";
        HashSet<Company> companies = new HashSet<>();

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Company company = new Company(
                        set.getInt("company_id"),
                        set.getString("company_name"),
                        set.getLong("market_cap")
                );
                companies.add(company);
            }

            return companies;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Company findCompanyById(Integer id) {
        String sql = "SELECT * FROM company WHERE company_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                String name = set.getString("company_name");
                Long marketCap = set.getLong("market_cap");

                return new Company(id, name, marketCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteCompaniesByName(String name) {
        String sql = "DELETE FROM company WHERE company_name = ?";

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

    public void updateCompany(Company company) {
        String sql = "UPDATE company SET company_name = ?, market_cap = ? WHERE company_id = ?";

        try (
                Connection connection = DbHandler.getInstance().getDbConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getMarketCap().toString());
            statement.setString(3, company.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static CompanyRepository getInstance() {
        if (instance == null)
            instance = new CompanyRepository();
        return instance;
    }
}
